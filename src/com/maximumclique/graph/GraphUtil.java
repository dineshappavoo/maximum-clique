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

	public boolean[] visited;

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
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
