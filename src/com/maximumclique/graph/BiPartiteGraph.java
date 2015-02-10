/**
 * 
 */
package com.maximumclique.graph;

import java.util.ArrayList;

/**
 * @author Dany
 *
 */
public class BiPartiteGraph {

	ArrayList<Integer> leftVertices;
	ArrayList<Integer> rightVertices;
	
	public BiPartiteGraph(ArrayList<Integer> leftVertices,	ArrayList<Integer> rightVertices)
	{
		this.leftVertices = leftVertices;
		this.rightVertices = rightVertices;
	}
}
