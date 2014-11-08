/**
 * 
 */
package com.maximumclique.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


/**
 * @author Dinesh Appavoo
 * 
 * Minimum vertex cover problem based on Hopcraft - Karp algorithm
 * 
 * By Konig's theorem, the minimum vertex cover in a bipartite graph is equivalent to the maximum matching problem of that graph.
 *
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//new BiPartiteMaxMatching().constructGraph("/users/dany/downloads/in.txt");
		//biGraph.printGraph();
		//int result=new BiPartiteMaxMatching().findMaxMatxhing("/users/dany/downloads/matching/test.txt");
		int result=new MinimumVertexCover().findMaxMatching("/Users/Dany/Documents/FALL-2013-COURSES/Imp_Data_structures/workspace/MaximumClique/src/input/C1000.9.clq.txt");


		/*for(int i=1;i<=leftVertices;i++)
		{
			System.out.print("("+i+","+(Pair[i]-leftVertices)+")");
		}*/
		System.out.println("Total matching : "+result);

		//biGraph.printGraph();


	}
	public int findMaxMatching(String fileName)
	{
		int maxMatching=0;
		constructGraph(fileName);
		//biGraph.printGraph();
		long inTime=System.currentTimeMillis();
		maxMatching=doHopCraft_Karp();
		long pTime=System.currentTimeMillis();
		System.out.println("Time taken in Milli Secs "+(pTime-inTime));
		return maxMatching;
	}

	public void constructGraph(String fileName)
	{

		//File infile=new File(inFile);
		int u,v;

		Scanner scanner=new Scanner(System.in);
		leftVertices=scanner.nextInt();
		rightVertices=scanner.nextInt();
		//leftVertices+=1;
		//rightVertices+=1;
		noOfEdges=scanner.nextInt();
		biGraph=new Graph(leftVertices+rightVertices);
		for(int i=0;i<noOfEdges;i++)
		{
			u=scanner.nextInt();
			v=scanner.nextInt();
			biGraph.addEdge(u,leftVertices+v);// To distinct the vertex ID's 'leftVertice+v'
			biGraph.addEdge(leftVertices+v, u);
		}

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
