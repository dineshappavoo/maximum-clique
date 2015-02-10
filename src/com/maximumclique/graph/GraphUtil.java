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
	public static HashSet<Integer> findOneMaximalClique(int startNode, Graph graph	)
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
	public static HashSet<Integer> growMaximalClique(HashSet<Integer> currentMaximalClique, Graph graph)
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
	public static boolean isAdjacentToCurrentMaximal(HashSet<Integer> currentMaximal, int node, Graph graph)
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
	public static Graph findBipartiteComplement(HashSet<Integer> maximalCliqueK1, HashSet<Integer> maximalCliqueK2, Graph graph)
	{		
		int clique1Size = maximalCliqueK1.size();
		int clique2Size = maximalCliqueK2.size();
		
		int graphSize = clique1Size + clique2Size;
		System.out.println("Original graph size : "+graph.size());

		Graph bipartiteGraph = new Graph(graphSize);
		
		int[] indexArray = new int[graphSize];
		
		
		int leftVertexIndex = 1;
		int rightVertexIndex = leftVertexIndex + 1;
		System.out.println("Original graph size : "+graph.size());
		for(int u : maximalCliqueK1)
		{
			for(int v : maximalCliqueK2)
			{
				if(!graph.isNeighbor(u, v))
				{
					bipartiteGraph.addEdge(leftVertexIndex, rightVertexIndex);
					bipartiteGraph.addEdge(rightVertexIndex, leftVertexIndex);
					
					indexArray[leftVertexIndex] = u;
					indexArray[rightVertexIndex] = v;
					leftVertexIndex++;
					rightVertexIndex++;
				}
			}
		}
		return bipartiteGraph;
	}

	public static HashSet<Integer> findVertexCoverComplement(Graph biGraph)
	{
		HashSet<Integer> biCliqueSet = new HashSet<Integer>();
		
		
		return biCliqueSet;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
