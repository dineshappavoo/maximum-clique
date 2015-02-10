/**
 * 
 */
package com.maximumclique.graph;

import java.util.HashSet;

/**
 * @author Dany
 *
 */
public class GraphTestUtil {

	public static boolean isValidMaximalClique(Graph graph, HashSet<Integer> maximalClique)
	{
		for(int u : maximalClique)
		{
			for(int v : maximalClique)
			{
				if(u!=v)
				{
					if (!graph.isNeighbor(u, v))
					{
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public static boolean isValidBiClique(Graph graph, HashSet<Integer> leftVertices, HashSet<Integer> rightVertices)
	{
		for(int u : leftVertices)
		{
			for(int v : rightVertices)
			{
				if(!graph.isNeighbor(u, v))
				{
					return false;
				}
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
