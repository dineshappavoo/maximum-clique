/**
 * 
 */
package com.maximumclique.graph;

import java.util.ArrayList;

/**
 * @author Dany
 *
 */
public class MinVertexCoverGraph {

	ArrayList<Integer> leftVertices;
	ArrayList<Integer> rightVertices;
	
	public MinVertexCoverGraph(ArrayList<Integer> leftVertices,	ArrayList<Integer> rightVertices)
	{
		this.leftVertices = leftVertices;
		this.rightVertices = rightVertices;
	}
}
