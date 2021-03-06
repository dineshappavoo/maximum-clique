/**
 * 
 */
package com.maximumclique.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Dany
 *
 */
public class GraphUtil {

	public static boolean[] visited;

	/**
	 * Method to find the maximal clique from the graph
	 * This method uses BFS to iterate through the nodes and get the maximal vertices set
	 * 
	 * @param startNode
	 * @param graph
	 * @return potentialClique
	 */
	public HashSet<Integer> findOneMaximalClique(int startNode, Graph graph	)
	{

		HashSet<Integer> maximalClique = new HashSet<Integer>();

		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(startNode);
		maximalClique.add(startNode);

		int graphSize = graph.size();
		visited = new boolean[graphSize];
		visited[startNode] = true;

		while(!queue.isEmpty())
		{
			int u = queue.poll();
			ArrayList<Integer> adjList = graph.getOutEdges(u);
			for(int v : adjList)
			{
				if(visited[v]==false)
				{
					if(isAdjacentToCurrentMaximal(maximalClique, v, graph))
					{
						maximalClique.add(v);
						queue.add(v);
						visited[v] = true;
					}
				}
			}
		}

		return maximalClique;
	}

	/**
	 * Method to grow current maximal clique further if possible
	 * @param currentMaximalClique
	 * @param graph
	 * @return
	 */
	public HashSet<Integer> growMaximalClique(HashSet<Integer> currentMaximalClique, Graph graph)
	{

		Queue<Integer> queue = new LinkedList<Integer>();

		int graphSize = graph.size();
		visited = new boolean[graphSize];

		for(int node : currentMaximalClique)
		{
			queue.add(node);
			visited[node] = true;

		}

		while(!queue.isEmpty())
		{
			int u = queue.poll();
			ArrayList<Integer> adjList = graph.getOutEdges(u);
			for(int v : adjList)
			{
				if(visited[v]==false)
				{
					if(isAdjacentToCurrentMaximal(currentMaximalClique, v, graph))
					{
						currentMaximalClique.add(v);
						queue.add(v);
						visited[v] = true;
					}
				}
			}
		}

		return currentMaximalClique;


	}

	/**
	 * Method to verify whether one vertex is neighbor of  vertices in the current maximal set
	 * 
	 * @param currentMaximal
	 * @param node
	 * @param graph
	 * @return
	 */
	public boolean isAdjacentToCurrentMaximal(HashSet<Integer> currentMaximal, int node, Graph graph)
	{
		for(int u : currentMaximal)
		{
			if(!graph.isNeighbor(u, node))
			{
				return false;
			}
		}
		return true;			
	}

	/**
	 * 
	 * @param maximalCliqueK1
	 * @param maximalCliqueK2
	 * 
	 * ALGORITHM : 
	 *  1. Take the new mapping of graph elements
	 *  2. Compare the array values to take the bipartite complement between two maximal cliques.
	 * 
	 * 
	 * 
	 */
	public BiCliqueGraph findBipartiteComplementAndVertexCoverComplement(HashSet<Integer> maximalCliqueK1, HashSet<Integer> maximalCliqueK2, Graph OrigGraph)
	{		
		//Take bipartite complement from two maximal cliques and arrange the graph with minimal memory
		int clique1Size = maximalCliqueK1.size();
		int clique2Size = maximalCliqueK2.size();

		System.out.println("Clique 1 : "+clique1Size);
		System.out.println("Clique 2 : "+clique2Size);

		//Remove the common nodes from maximal cliques
		ArrayList<Integer> commonNode = findCommonNodesFromMaximalCliques(new ArrayList<Integer>(maximalCliqueK1), new ArrayList<Integer>(maximalCliqueK2));
		

		int graphSize = clique1Size + clique2Size;
		System.out.println("Original graph size 1 : "+OrigGraph.size());

		Graph bipartiteGraph = new Graph(graphSize);
		System.out.println("New graph size  : "+graphSize);

		CustomizedArrayList leftIndexArray = new CustomizedArrayList(clique1Size+1);
		CustomizedArrayList rightIndexArray = new CustomizedArrayList(clique2Size+1);

		ArrayList<Integer> indexArrayList = new ArrayList<Integer>(); 
		int[] indexArray = new int[graphSize+1];


		int leftVertexIndex = 1;
		int rightVertexIndex = clique1Size + 1;
		for(int u : maximalCliqueK1)
		{
			System.out.println("U Value : "+u);
			for(int v : maximalCliqueK2)
			{
				System.out.println("V Value : "+v);

				if((!OrigGraph.isNeighbor(u, v)) && (u!=v))
				{
					System.out.println("No Edge U :"+u+"  V : "+v+"  Left "+leftVertexIndex+"  Right   "+rightVertexIndex);
					bipartiteGraph.addEdge(leftVertexIndex, rightVertexIndex);
					bipartiteGraph.addEdge(rightVertexIndex, leftVertexIndex);

					//Left index array doesnt have to be verified with contains method, because the u is iterated in the outer for loop. So it will be iterated only once
					leftIndexArray.add(leftVertexIndex, u);
					if(!rightIndexArray.contains(v))
					{
						rightIndexArray.add(rightVertexIndex, v);
						rightVertexIndex++;
					}
					//indexArray[leftVertexIndex] = u;
					//indexArray[rightVertexIndex] = v;
				}

			}
			leftVertexIndex++;
			//rightVertexIndex = clique1Size +1;
		}

		//Find the minimum vertex cover from the bipartite complement
		MinimumVertexCover minVertexCover  = new MinimumVertexCover();
		BiPartiteGraph vertexCover = minVertexCover.getMinimumVertexCover(bipartiteGraph, leftVertexIndex, rightVertexIndex);

		ArrayList<Integer> leftBiCliqueVertices = new ArrayList<Integer>();
		ArrayList<Integer> rightBiCliqueVertices = new ArrayList<Integer>();
		//Find vertex cover complement
		for(int u : vertexCover.leftVertices)
		{
			for(int v : vertexCover.rightVertices)
			{
				if(!bipartiteGraph.isNeighbor(u, v))
				{
					leftBiCliqueVertices.add(leftIndexArray.get(u));
					rightBiCliqueVertices.add(rightIndexArray.get(v));

				}
			}
		}

		System.out.println(vertexCover.leftVertices.size());
		System.out.println(vertexCover.rightVertices.size());
		//Validating biclique
		boolean isValidBiClique = GraphValidationUtil.isValidBiClique(bipartiteGraph, leftBiCliqueVertices, rightBiCliqueVertices);
		System.out.println("The BiClique is  : "+isValidBiClique);

		printBiGraph(bipartiteGraph, leftBiCliqueVertices, rightBiCliqueVertices);

		return new BiCliqueGraph(leftBiCliqueVertices, rightBiCliqueVertices);
	}

	/**
	 * Method to remove the common elements from two maximal cliques and add the same to the common nodes array list
	 * @param maximalClique1
	 * @param maximalClique2
	 * @return
	 */
	public static ArrayList<Integer> findCommonNodesFromMaximalCliques(ArrayList<Integer> maximalClique1, ArrayList<Integer> maximalClique2)
	{
		ArrayList<Integer> commonNodes = new ArrayList<Integer>();
		int length1 = maximalClique1.size();
		int length2 = maximalClique2.size();

		System.out.println("Length : "+length1+"  "+length2);
		Collections.sort(maximalClique1);
		Collections.sort(maximalClique2);
		int i=0, j=0;
		System.out.println(" Max Clique "+maximalClique2.get(14));
		while(i<length1 && j<length2)
		{
			System.out.println("i j "+i+" "+j);
			if(maximalClique1.get(i) < maximalClique2.get(j))
			{
				i++;
			}else if (maximalClique1.get(i)>maximalClique2.get(j))
			{
				j++;
			}else
			{
				commonNodes.add(maximalClique1.get(i));
				maximalClique1.remove(i);
				maximalClique2.remove(i);
				i++;
				j++;
			}
		}

		return commonNodes;
	}


	public static HashSet<Integer> findVertexCoverComplement(Graph biGraph)
	{
		HashSet<Integer> biCliqueSet = new HashSet<Integer>();


		return biCliqueSet;
	}

	public void printBiGraph(Graph graph, ArrayList<Integer> left, ArrayList<Integer> right)
	{
		for(int u : left)
		{
			for(int v : right)
			{
				if(graph.isNeighbor(u, v))
				{
					System.out.println("u : "+u+"    v : "+v);
				}
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
