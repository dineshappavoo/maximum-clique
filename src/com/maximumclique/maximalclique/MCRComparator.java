package com.maximumclique.maximalclique;

import java.util.*;

public class MCRComparator implements Comparator {

    public int compare(Object o1, Object o2){
	Vertex u = (Vertex) o1;
	Vertex v = (Vertex) o2;
	if (u.degree < v.degree || 
	    u.degree == v.degree && u.nebDeg < v.nebDeg ||
	    u.degree == v.degree && u.nebDeg == v.nebDeg && u.index > v.index) return 1;
	return -1;
    }
    //
    // to sort vertices by decreasing degree, tie breaking on neighbourhood degree (nebDeg)
    //
}
