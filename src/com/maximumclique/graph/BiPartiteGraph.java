/**
 * 
 */
package com.maximumclique.graph;

import java.util.HashSet;

/**
 * @author Dany
 *
 */
public class BiPartiteGraph {

	HashSet<Integer> leftVertices;
	HashSet<Integer> rightVertices;
	
	public BiPartiteGraph(HashSet<Integer> leftVertices,	HashSet<Integer> rightVertices)
	{
		this.leftVertices = leftVertices;
		this.rightVertices = rightVertices;
	}
}
