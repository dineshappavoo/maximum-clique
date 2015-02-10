/**
 * 
 */
package com.maximumclique.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @author Dany
 *
 */
public class GraphConstruction {

	public static int noOfVertices=0;
	public static int noOfEdges=0;
	
	public static Graph constructGraph(String fileName) throws FileNotFoundException
	{
		Graph graph=null;
		File file = new File(fileName);
		int u, v, w;
		Scanner scanner=new Scanner(file);
		while(scanner.hasNext())
		{
			String checker;
			if((checker=scanner.next()).equals("p"))
			{
				scanner.next();
				noOfVertices=scanner.nextInt();
				noOfEdges=scanner.nextInt();

				//System.out.println("edge "+noOfEdges+" vertex "+noOfVertices);

				graph=new Graph(noOfVertices);
				for(int i=0;i<noOfEdges;i++)
				{
					scanner.next();
					u=scanner.nextInt();
					v=scanner.nextInt();
					graph.addEdge(u, v);
					graph.addEdge(v, u);
				}
				break;
			}
		}
		//graph.printGraph();
		return graph;
	}
	

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {

		Graph graph = GraphConstruction.constructGraph("/Users/Dany/Documents/FALL-2013-COURSES/Imp_Data_structures/workspace/MaximumClique/src/com/maximumclique/input/C125.9.clq.txt");
		//graph.printGraph();

		ArrayList<Integer>[] adjList = graph.getAdjacencyList();
		GraphDNA.shuffle(adjList);

		HashSet<Integer> maximalCliqueK1 = GraphUtil.findOneMaximalClique(118, graph);
		HashSet<Integer> maximalCliqueK2 = GraphUtil.findOneMaximalClique(79, graph);


		System.out.print("[ ");
		for(int node : maximalCliqueK1)
		{
			System.out.print(node+" ");
		}
		System.out.println("]");
		
		
		System.out.print("[ ");
		for(int node : maximalCliqueK2)
		{
			System.out.print(node+" ");
		}
		System.out.println("]");
		

		Graph biGraph = GraphUtil.findBipartiteComplement(maximalCliqueK1, maximalCliqueK2, graph);
		biGraph.printGraph();

		System.out.println("GROWN MAXIMAL CLIQUE");
		HashSet<Integer> grownMaximalClique = GraphUtil.growMaximalClique(maximalCliqueK1, graph);
		
		System.out.print("[ ");
		for(int node : grownMaximalClique)
		{
			System.out.print(node+" ");
		}
		System.out.println("]");
		
	}

}
