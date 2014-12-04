package com.maximumclique.maximalclique;



import java.util.*;

public class Vertex implements Comparable<Vertex> {

    int index, degree, colour, saturation, nebDeg;
    boolean[] domain;

    public Vertex (int index,int degree) {
	this.index  = index;
	this.degree = degree;
	nebDeg      = 0;
    }

    void init(int n){
	domain = new boolean[n]; 
	Arrays.fill(domain,true);
	colour = -1;
	saturation = 0;
    }

    int colour(){
	for (colour=0;!domain[colour];colour++);
	domain[colour] = false;
	return colour;
    }
    //
    // colour vertex with lowest possible colour
    //

    void removeColour(int i){if (domain[i]){saturation++; domain[i] = false;}}

    public int compareTo(Vertex v){
	if (degree < v.degree || degree == v.degree && index > v.index) return 1;
	return -1;
    }
}
