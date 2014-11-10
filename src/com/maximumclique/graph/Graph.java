package com.maximumclique.graph;

import java.util.ArrayList;


/**
 * @author Dinesh Appavoo
 *
 */
public class Graph{

	
	public static ArrayList<Integer>[] adjacencyList=null;
	public int noOfVertices;
	
	
	public Graph(int noOfVertices)
	{
		adjacencyList=(ArrayList<Integer>[])new ArrayList[noOfVertices+1];
		this.noOfVertices=noOfVertices;
		for(int i=0;i<(noOfVertices+1);i++)
			adjacencyList[i]=new ArrayList<Integer>();
	}

	/**
	 * 
	 * @param u
	 * @param v
	 * @param w
	 * To add edges to the adjacency list in graph
	 */
	public void addEdge(int u, int v)
	{
		if(adjacencyList[u]==null)
			adjacencyList[u]=new ArrayList<Integer>();
		adjacencyList[u].add(v);
	}
	
	/**
	 * 
	 * @param u
	 * @param v
	 * To remove the edge from the graph
	 */
	public void removeEdge(int u, int v)
	{
		int indexToBeRemoved=-1;
		ArrayList<Integer> edgeList=adjacencyList[u];
		for(int i=0;i<adjacencyList[u].size();i++)
		{
			int val=edgeList.get(i);
			if(val==v)
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
			for(int v : edgeList)
				System.out.println("u : "+i+" v : "+v);
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

		Graph graph=new Graph(3);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(3, 1);
		graph.addEdge(2, 1);
		graph.addEdge(2, 3);
		graph.addEdge(3, 2);
		graph.printGraph();
		
	}
	
	

}
