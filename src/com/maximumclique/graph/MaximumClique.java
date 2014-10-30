/**
 * 
 */
package com.maximumclique.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Dany
 *
 */
public class MaximumClique {

	/**
	 * @param args
	 */

	public static int noOfVertices=0,noOfEdges=0;
	public static Graph<Integer> graph=null;
	public final static int INFINITY=Integer.MAX_VALUE;

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		new MaximumClique().constructGraph("/Users/Dany/Documents/FALL-2013-COURSES/Imp_Data_structures/workspace/MaximumClique/src/input/C1000.9.clq.txt");
	}

	public void constructGraph(String filename) throws FileNotFoundException
	{
		File file = new File(filename);
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

				graph=new Graph<Integer>(noOfVertices);
				for(int i=0;i<noOfEdges;i++)
				{
					scanner.next();
					u=scanner.nextInt();
					v=scanner.nextInt();
					graph.addEdge(u, v);
					//graph.addEdge(v, u);
				}
				break;
			}
		}
		graph.printGraph();


	}

}
