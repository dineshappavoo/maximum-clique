package com.maximumclique.maximalclique;

import java.util.*;
import java.io.*;

public class MaxClique {

	static int[] degree;   // degree of vertices
	static int[][] A;      // 0/1 adjacency matrix
	static int n;

	static void readDIMACS(String fname) throws IOException {
		String s = "";
		Scanner sc = new Scanner(new File(fname));
		while (sc.hasNext() && !s.equals("p")) s = sc.next();
		sc.next();
		n      = sc.nextInt();   
		int m  = sc.nextInt();   
		degree = new int[n];
		A      = new int[n][n];
		while (sc.hasNext()){
			s     = sc.next(); // skip "edge"
			int i = sc.nextInt() - 1;
			int j = sc.nextInt() - 1; 
			degree[i]++; degree[j]++;
			A[i][j] = A[j][i] = 1;
		}
		sc.close();
	}

	public static void main(String[] args)  throws IOException {

		//String fileName = "/Users/Dany/Documents/FALL-2013-COURSES/Imp_Data_structures/workspace/MaximumClique/src/com/maximumclique/input/C125.9.clq.txt";
		//readDIMACS(fileName);
		readDIMACS(args[1]);

		MC mc = null;
		if (args[0].equals("MC"))         mc = new MC(n,A,degree);
		else if (args[0].equals("MC0"))   mc = new MC0(n,A,degree);
		else if (args[0].equals("MCQ1"))  mc = new MCQ(n,A,degree,1);
		else if (args[0].equals("MCQ2"))  mc = new MCQ(n,A,degree,2);
		else if (args[0].equals("MCQ3"))  mc = new MCQ(n,A,degree,3);
		/*else if (args[0].equals("MCSa1")) mc = new MCSa(n,A,degree,1);
	else if (args[0].equals("MCSa2")) mc = new MCSa(n,A,degree,2);
	else if (args[0].equals("MCSa3")) mc = new MCSa(n,A,degree,3);
	else if (args[0].equals("MCSb1")) mc = new MCSb(n,A,degree,1);
	else if (args[0].equals("MCSb2")) mc = new MCSb(n,A,degree,2);
	else if (args[0].equals("MCSb3")) mc = new MCSb(n,A,degree,3);
	else if (args[0].equals("BBMC1")) mc = new BBMC(n,A,degree,1);
	else if (args[0].equals("BBMC2")) mc = new BBMC(n,A,degree,2);
	else if (args[0].equals("BBMC3")) mc = new BBMC(n,A,degree,3);*/
		else return;

		System.gc();
		if (args.length > 2) mc.timeLimit = 1000 * (long)Integer.parseInt(args[2]);
		long cpuTime = System.currentTimeMillis();
		mc.search();
		cpuTime = System.currentTimeMillis() - cpuTime;
		System.out.println(mc.maxSize +" "+ mc.nodes +" "+ cpuTime);
		for (int i=0;i<mc.n;i++) if (mc.solution[i] == 1) System.out.print(i+1 +" ");
		System.out.println();
	}
}
