/**
 * 
 */
package com.maximumclique.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author Dany
 *
 */
public class GraphUtil {

	public boolean[] visited;

	/**
	 * Method to find the maximal clique from the graph
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
					}
				}
			}
		}
		
		return maximalClique;
	}

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
