/**
 * 
 */
package com.maximumclique.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author Dany
 *
 */
public class GraphUtil {
	
	public ArrayList<Integer> getMaximalClique(Graph graph, int startNode)
	{
		ArrayList<Integer> candidates = new ArrayList<Integer>();
		ArrayList<Integer> potentialClique = new ArrayList<Integer>();
		potentialClique.add(startNode);
		findOneMaximalClique(startNode, candidates, potentialClique, graph);
		return potentialClique;
	}

	
	public void findOneMaximalClique(int startNode, 
			ArrayList<Integer> candidates, 
			ArrayList<Integer> potentialClique, 
			Graph graph	)
	{
		ArrayList<Integer> adjList = graph.getOutEdges(startNode);
		
		for(int v : adjList)
		{
			
		}
		
		
	}
	
	private void findCliques(
	        List<Integer> potential_clique,
	        List<Integer> candidates,
	        List<Integer> already_found)
	    {
	        List<Integer> candidates_array = new ArrayList<Integer>(candidates);
	        if (!end(candidates, already_found)) {
	            // for each candidate_node in candidates do
	            for (Integer candidate : candidates_array) {
	                List<Integer> new_candidates = new ArrayList<Integer>();
	                List<Integer> new_already_found = new ArrayList<Integer>();

	                // move candidate node to potential_clique
	                potential_clique.add(candidate);
	                candidates.remove(candidate);

	                // create new_candidates by removing nodes in candidates not
	                // connected to candidate node
	                for (Integer new_candidate : candidates) {
	                    if (graph.isNeighbor(candidate, new_candidate))
	                    {
	                        new_candidates.add(new_candidate);
	                    } // of if
	                } // of for

	                // create new_already_found by removing nodes in already_found
	                // not connected to candidate node
	                for (Integer new_found : already_found) {
	                    if (graph.isNeighbor(candidate, new_found)) {
	                        new_already_found.add(new_found);
	                    } // of if
	                } // of for

	                // if new_candidates and new_already_found are empty
	                if (new_candidates.isEmpty() && new_already_found.isEmpty()) {
	                    // potential_clique is maximal_clique
	                    cliques.add(new HashSet<Integer>(potential_clique));
	                } // of if
	                else {
	                    // recursive call
	                    findCliques(
	                        potential_clique,
	                        new_candidates,
	                        new_already_found);
	                } // of else

	                // move candidate_node from potential_clique to already_found;
	                already_found.add(candidate);
	                potential_clique.remove(candidate);
	            } // of for
	        } // of if
	    }
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
