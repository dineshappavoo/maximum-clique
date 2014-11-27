package com.maximumclique.maximalclique;

import java.util.*;

public class MC0 extends MC {

    MC0 (int n,int[][]A,int[] degree) {super(n,A,degree);}

    void expand(ArrayList<Integer> C,ArrayList<Integer> P){
	if (timeLimit > 0 && System.currentTimeMillis() - cpuTime >= timeLimit) return;
	nodes++;
	for (int i=P.size()-1;i>=0;i--){
	    if (C.size() + P.size() <= maxSize) return;
	    int v = P.get(i);
	    C.add(v);
	    ArrayList<Integer> newP = new ArrayList<Integer>(i);
	    for (int j=0;j<=i;j++){
		int w = P.get(j);
		if (A[v][w] == 1) newP.add(w);
	    }
	    if (newP.isEmpty() && C.size() > maxSize) saveSolution(C);
	    if (!newP.isEmpty()) expand(C,newP);
	    C.remove(C.size()-1);
	    P.remove(i);
	}
    }
}
