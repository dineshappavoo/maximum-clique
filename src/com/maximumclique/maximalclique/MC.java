package com.maximumclique.maximalclique;

import java.util.*;

public class MC {
    int[] degree;   // degree of vertices
    int[][] A;      // 0/1 adjacency matrix
    int n;          // n vertices
    long nodes;     // number of decisions
    long timeLimit; // milliseconds
    long cpuTime;   // milliseconds
    int maxSize;    // size of max clique
    int style;      // used to flavor algorithm
    int[] solution; // as it says

    MC (int n,int[][]A,int[] degree) {
	this.n = n;
	this.A = A;
	this.degree = degree;
	nodes = maxSize = 0;
	cpuTime = timeLimit = -1;
	style = 1;
	solution = new int[n];
    }

    void search(){
	cpuTime              = System.currentTimeMillis();
	nodes                = 0;
	ArrayList<Integer> C = new ArrayList<Integer>();
	ArrayList<Integer> P = new ArrayList<Integer>(n);
	for (int i=0;i<n;i++) P.add(i);
	expand(C,P);
    }

    void expand(ArrayList<Integer> C,ArrayList<Integer> P){
	if (timeLimit > 0 && System.currentTimeMillis() - cpuTime >= timeLimit) return;
	nodes++;
	for (int i=P.size()-1;i>=0;i--){
	    if (C.size() + P.size() <= maxSize) return;
	    int v = P.get(i);
	    C.add(v);
	    ArrayList<Integer> newP = new ArrayList<Integer>();
	    for (int w : P) if (A[v][w] == 1) newP.add(w);
	    if (newP.isEmpty() && C.size() > maxSize) saveSolution(C);
	    if (!newP.isEmpty()) expand(C,newP);
	    C.remove((Integer)v);
	    P.remove((Integer)v);
	}
    }

    void saveSolution(ArrayList<Integer> C){
	Arrays.fill(solution,0);
	for (int i : C) solution[i] = 1;
	maxSize = C.size();
    }
}
