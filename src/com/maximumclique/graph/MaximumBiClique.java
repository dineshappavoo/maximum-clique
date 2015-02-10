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
public class MaximumBiClique {

	public static Graph constructGraph(String fileName) throws FileNotFoundException
	{

		int noOfVertices=0;
		int noOfEdges=0;
		
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
	public void findMaximumBiClique(String fileName) throws FileNotFoundException {

		Graph graph = constructGraph(fileName);
		//graph.printGraph();

		//To shuffle the graph
		ArrayList<Integer>[] adjList = graph.getAdjacencyList();
		//GraphDNA.shuffle(adjList);
		
		//Find Maximal Clique
		GraphUtil graphUtil =  new GraphUtil();

		HashSet<Integer> maximalCliqueK1 = graphUtil.findOneMaximalClique(52, graph);
		HashSet<Integer> maximalCliqueK2 = graphUtil.findOneMaximalClique(79, graph);


		//Print maximal Clique
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
		

		//Find Bipartite Complement of the merged maximal cliques
		Graph biGraph = graphUtil.findBipartiteComplement(maximalCliqueK1, maximalCliqueK2, graph);
		biGraph.printGraph();

		//Grow the merged maximal clique further - TEST
		System.out.println("GROWN MAXIMAL CLIQUE");
		HashSet<Integer> grownMaximalClique = graphUtil.growMaximalClique(maximalCliqueK1, graph);
		
		System.out.print("[ ");
		for(int node : grownMaximalClique)
		{
			System.out.print(node+" ");
		}
		System.out.println("]");
		
		
		//Find the minimum vertex cover from the bipartite complement
		MinimumVertexCover minVertexCover  = new MinimumVertexCover();
		
		
	}


	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		String fileName = "/Users/Dany/Documents/FALL-2013-COURSES/Imp_Data_structures/workspace/MaximumClique/src/com/maximumclique/input/C125.9.clq.txt";
		MaximumBiClique maxBiClique = new MaximumBiClique();
		maxBiClique.findMaximumBiClique(fileName);
	}

}
