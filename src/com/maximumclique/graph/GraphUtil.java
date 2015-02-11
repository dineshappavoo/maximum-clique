/**
 * 
 */
package com.maximumclique.graph;

import java.util.ArrayList;
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
	 */
	public BiCliqueGraph findBipartiteComplementAndVertexCoverComplement(HashSet<Integer> maximalCliqueK1, HashSet<Integer> maximalCliqueK2, Graph OrigGraph)
	{		
		int clique1Size = maximalCliqueK1.size();
		int clique2Size = maximalCliqueK2.size();
		
		int graphSize = clique1Size + clique2Size;
		System.out.println("Original graph size 1 : "+OrigGraph.size());

		Graph bipartiteGraph = new Graph(graphSize);
		System.out.println("New graph size  : "+graphSize);

		int[] indexArray = new int[graphSize+1];

		
		int leftVertexIndex = 1;
		int rightVertexIndex = clique1Size + 1;
		for(int u : maximalCliqueK1)
		{
			//System.out.println("U Value : "+u);
			for(int v : maximalCliqueK2)
			{
				//System.out.println("V Value : "+v);

				if((!OrigGraph.isNeighbor(u, v)) && (u!=v))
				{
					System.out.println("No Edge U :"+u+"  V : "+v+"  Left "+leftVertexIndex+"  Right   "+rightVertexIndex);
					bipartiteGraph.addEdge(leftVertexIndex, rightVertexIndex);
					bipartiteGraph.addEdge(rightVertexIndex, leftVertexIndex);
					
					indexArray[leftVertexIndex] = u;
					indexArray[rightVertexIndex] = v;
					rightVertexIndex++;
				}

			}
			leftVertexIndex++;
			rightVertexIndex = clique1Size +1;
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
					leftBiCliqueVertices.add(indexArray[u]);
					rightBiCliqueVertices.add(indexArray[v]);

				}
			}
		}
				
		boolean isValidBiClique = GraphValidationUtil.isValidBiClique(bipartiteGraph, leftBiCliqueVertices, rightBiCliqueVertices);
		System.out.println("The BiClique is  : "+isValidBiClique);
		printBiGraph(bipartiteGraph, leftBiCliqueVertices, rightBiCliqueVertices);

		return new BiCliqueGraph(leftBiCliqueVertices, rightBiCliqueVertices);
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
