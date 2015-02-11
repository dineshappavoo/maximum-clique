/**
 * 
 */
package com.maximumclique.graph;

import java.util.ArrayList;

/**
 * @author Dany
 *
 */
public class BiCliqueGraph {
	ArrayList<Integer> leftVertices;
	ArrayList<Integer> rightVertices;

	public BiCliqueGraph(ArrayList<Integer> leftVertices, ArrayList<Integer> rightVertices)
	{
		this.leftVertices = leftVertices;
		this.rightVertices = rightVertices;
	}

}
