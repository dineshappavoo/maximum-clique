package com.maximumclique.graph;

import java.util.ArrayList;

/**
 * @author Dinesh Appavoo
 *
 */
public class Graph<T extends Comparable<T>>{

	
	public static ArrayList<Integer>[] adjacencyList=null;
	public int noOfVertices;
	
	
	public Graph(int noOfVertices)
	{
		adjacencyList=(ArrayList<Integer>[])new ArrayList[noOfVertices+1];
		this.noOfVertices=noOfVertices;
	}

	/**
	 * 
	 * @param u
	 * @param v
	 * @param w
	 * To add edges to the adjacency list in graph
	 */
	public void addEdge(T u, T v)
	{
		if(adjacencyList[(Integer) u]==null)
			adjacencyList[(Integer) u]=new ArrayList<Integer>();
		adjacencyList[(Integer) u].add((Integer) v);
	}
	
	/**
	 * 
	 * @param u
	 * @param v
	 * To remove the edge from the graph
	 */
	public void removeEdge(T u, T v)
	{
		int indexToBeRemoved=-1;
		ArrayList<Integer> edgeList=adjacencyList[(Integer) u];
		for(int i=0;i<adjacencyList[(Integer) u].size();i++)
		{
			Integer val=edgeList.get(i);
			if(val.compareTo((Integer) v)==0)
			{
				indexToBeRemoved=i;
			}
		}
		edgeList.remove(indexToBeRemoved);
	}
	
	/**
	 * 
	 * @param u
	 * @return
	 * To return the outgoing edges for the given source
	 */
	public ArrayList<Integer> getOutEdges(int u)
	{
		return adjacencyList[u];
	}

	
	public void printGraph()
	{
		ArrayList<Integer> edgeList;
		for(int i=1;i<=noOfVertices;i++)
		{
			edgeList=adjacencyList[i];
			if(edgeList!=null)
			{
			for(Integer e : edgeList)
				System.out.println("u : "+i+" v : "+e);
			}
		}
	}
	/**
	 * 
	 * @param args
	 * Main function to test the graph
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Graph<Integer> graph=new Graph<Integer>(3);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(3, 1);
		graph.addEdge(2, 1);
		graph.addEdge(2, 3);
		graph.addEdge(3, 2);
		graph.printGraph();
		
	}
	
	

}
