/**
 * 
 */
package com.maximumclique.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;


/**
 * @author Dinesh Appavoo
 * 
 * Minimum vertex cover problem based on Hopcraft - Karp algorithm
 * 
 * By Konig's theorem, the minimum vertex cover in a bipartite graph is equivalent to the maximum matching problem of that graph.
 *
 *
 * ALGORITHM : 
 *  - Find the maximum matching and (left, right) pairs
 *  - Identify the free vertices both sides which are not participating in the matching
 *  - Vertex cover vertices includes,
 *     - Vertices which are participating in the matching and also connected to any of the 
 *       free vertices(both sides)
 *     - Vertices which are not connected to the free vertices (From any one side)  
 */
public class MinimumVertexCover {

	/**
	 * @param args
	 */
	public static int noOfVertices=0;
	public static int noOfEdges=0;
	public static Graph biGraph=null;
	public static int leftVertices=0, rightVertices=0;
	public static int[] Pair;
	public static int[] Dist;
	public final int NIL=0;
	public final int INF=Integer.MAX_VALUE;

	public static Set<Integer> leftVertexCoverVertices = new HashSet<Integer>();
	public static Set<Integer> rightVertexCoverVertices = new HashSet<Integer>();

	public static ArrayList<Integer> leftFreeVertices = new ArrayList<Integer>();
	public static ArrayList<Integer> rightFreeVertices = new ArrayList<Integer>();	

	public static void main(String[] args) throws FileNotFoundException {

		MinimumVertexCover minVertCover = new MinimumVertexCover();
		//int result=minVertCover.findMaxMatching("/Users/Dany/Documents/FALL-2013-COURSES/Imp_Data_structures/workspace/MaximumClique/src/com/maximumclique/input/matching_input_5.txt");
		minVertCover.constructGraph("/Users/Dany/Documents/FALL-2013-COURSES/Imp_Data_structures/workspace/MaximumClique/src/com/maximumclique/input/matching_input_5.txt");
		int result=minVertCover.findMaxMatching(biGraph);

		System.out.println("\nTotal matching : "+result);
		minVertCover.identifyFreeVertices();
		minVertCover.findMinimumVertexCover();

		minVertCover.printDetailedGraphInformation();
		//biGraph.printGraph();
	}

	public void printDetailedGraphInformation()
	{
		System.out.println("===============================");
		System.out.println("     FREE VERTICES    ");
		System.out.println("-------------------------------");

		for(int i : leftFreeVertices)
		{
			System.out.println("Left Free  : "+i);
		}

		for(int i : rightFreeVertices)
		{
			System.out.println("Right Free : "+i);
		}
		System.out.println("-------------------------------");
		System.out.println("   VERTEX COVER   ");
		System.out.println("-------------------------------");
		for(int i : leftVertexCoverVertices)
		{
			System.out.println("Left Vertex Cover  : "+i);
		}

		for(int i : rightVertexCoverVertices)
		{
			System.out.println("Right Vertex Cover : "+i);
		}		
		
		System.out.print("Vertex Cover List : [ ");
		for(int i : leftVertexCoverVertices)
		{
			System.out.print(i+" ");
		}

		for(int i : rightVertexCoverVertices)
		{
			System.out.print(i-leftVertices+" ");  //Final display
		}
		System.out.print("]");
		System.out.println("\n===============================");

		
	}

	/**
	 * Method to find the minimum vertex cover from the maximum matching results
	 * @throws FileNotFoundException
	 */
	public void findMinimumVertexCover() throws FileNotFoundException
	{
		for(int i=1;i<=leftVertices;i++)
		{
			if(Pair[i]!=0)
			{
				ArrayList<Integer> adjacencyList = biGraph.getOutEdges(i);
				for(int n : adjacencyList)
				{
					if(rightFreeVertices.contains(n))
					{
						leftVertexCoverVertices.add(i);
					}
					else // In both cases we are adding because any one side can be added to vertex cover list
					{
						leftVertexCoverVertices.add(i);
					}
				}
				System.out.println("Left  : "+(i)+"  "+(Pair[i]-leftVertices));
			}
		}
		System.out.println();
		for(int j=leftVertices+1;j<Pair.length;j++)
		{
			if(Pair[j]!=0)
			{
				ArrayList<Integer> adjacencyList = biGraph.getOutEdges(j);
				for(int n : adjacencyList)
				{
					if(leftFreeVertices.contains(n))
						rightVertexCoverVertices.add(j);
				}
				System.out.println("Right : "+(j-leftVertices)+"  "+Pair[j]);
			}
		}
	}


	/**
	 * Method to find the free vertices after the bipartite matching
	 */
	public void identifyFreeVertices()
	{
		for(int i=1;i<=leftVertices;i++)
		{
			if(Pair[i]==0)
			{
				leftFreeVertices.add(i);
			}
		}
		for(int j=leftVertices+1;j<Pair.length;j++)
		{
			if(Pair[j]==0)
			{
				rightFreeVertices.add(j);
			}
		}
	}

	/**
	 * Method to find the maximum matching in the bipartite graph
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	public int findMaxMatching(Graph graph) throws FileNotFoundException
	{
		int maxMatching=0;
		//constructGraph(fileName);
		long inTime=System.currentTimeMillis();
		maxMatching=doHopCraft_Karp();
		long pTime=System.currentTimeMillis();
		System.out.println("Time taken in Milli Secs "+(pTime-inTime));
		return maxMatching;
	}

	/**
	 * Method the construct the graph from the input file
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public void constructGraph(String fileName) throws FileNotFoundException
	{
		File infile=new File(fileName);
		int u,v;

		Scanner scanner=new Scanner(infile);
		leftVertices=scanner.nextInt();
		rightVertices=scanner.nextInt();
		//leftVertices+=1;
		//rightVertices+=1;
		noOfEdges=scanner.nextInt();
		biGraph=new Graph(leftVertices+rightVertices+1);
		for(int i=0;i<noOfEdges;i++)
		{
			u=scanner.nextInt();
			v=scanner.nextInt();
			//scanner.nextInt();
			biGraph.addEdge(u,leftVertices+v);// To distinct the vertex ID's 'leftVertice+v'
			biGraph.addEdge(leftVertices+v, u);
		}
		
		//Shuffle the adjacency list
		ArrayList<Integer>[] adjList = biGraph.getAdjacencyList();
		
		//Shuffle the vertices
		GraphDNA.shuffle(adjList);
		
		//Shuffle each adjacency list
		for(ArrayList<Integer> list : adjList)
		{
			Collections.shuffle(list);
		}
	}
	
			
	public static ArrayList<Integer>[] RandomizeArray(ArrayList<Integer>[] adjList){
		Random rgen = new Random();  // Random number generator 
		for (int i=0; i<adjList.length; i++) {
		    int randomPosition = rgen.nextInt(adjList.length);
		    ArrayList<Integer> temp = adjList[i];
		    adjList[i] = adjList[randomPosition];
		    adjList[randomPosition] = temp;
		}
 
		return adjList;
	}

	public boolean doBFS()
	{

		Queue<Integer> queue = new LinkedList<Integer>();
		for (int v = 1; v <= leftVertices; ++v) 
			if (Pair[v] == NIL) 
			{ 
				Dist[v] = 0; 
				queue.add(v); 
			}
			else 
				Dist[v] = INF;

		Dist[NIL] = INF;

		while (!queue.isEmpty()) 
		{
			int v = queue.poll();
			if (Dist[v] < Dist[NIL]) 
				for (int e: biGraph.getOutEdges(v)) 
					if (Dist[Pair[e]] == INF) 
					{
						Dist[Pair[e]] = Dist[v] + 1;
						queue.add(Pair[e]);
					}           
		}
		return Dist[NIL] != INF;

	}

	public boolean doDFS(int v)
	{

		if (v != NIL) 
		{
			for (int e : biGraph.getOutEdges(v)) 
				if (Dist[Pair[e]] == Dist[v] + 1)
					if (doDFS(Pair[e])) 
					{
						Pair[e] = v;
						Pair[v] = e;
						return true;
					}               

			Dist[v] = INF;
			return false;
		}
		return true;

	}

	public int doHopCraft_Karp()
	{
		Pair = new int[leftVertices+rightVertices+1];
		Dist = new int[leftVertices+rightVertices+1];
		int matching = 0;
		while (doBFS())
			for (int v = 1; v <= leftVertices; ++v)
				if (Pair[v] == NIL)
					if (doDFS(v))
						matching = matching + 1;
		return matching;
	}

}
