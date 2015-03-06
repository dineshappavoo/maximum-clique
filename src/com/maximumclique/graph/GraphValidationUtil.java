/**
 * 
 */
package com.maximumclique.graph;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Dany
 *
 */
public class GraphValidationUtil {

	/**
	 * Util to verify whether the given clique is a valid maximal clique
	 * @param graph
	 * @param maximalClique
	 * @return
	 */
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
	
	/**
	 * Util to verify whether a given biclique is valid
	 * @param graph
	 * @param leftVertices
	 * @param rightVertices
	 * @return
	 */
	public static boolean isValidBiClique(Graph graph, ArrayList<Integer> leftVertices, ArrayList<Integer> rightVertices)
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
	
	
	public static boolean isValidVertexCover()
	{
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
