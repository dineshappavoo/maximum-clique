/**
 * 
 */
package com.maximumclique.graph;

import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.RandomAccess;

/**
 * @author Dany
 *
 */
public class GraphDNA {


	/**
	 * Randomly permutes the specified list using a default source of
	 * randomness.  All permutations occur with approximately equal
	 * likelihood.<p>
	 *
	 * The hedge "approximately" is used in the foregoing description because
	 * default source of randomness is only approximately an unbiased source
	 * of independently chosen bits. If it were a perfect source of randomly
	 * chosen bits, then the algorithm would choose permutations with perfect
	 * uniformity.<p>
	 *
	 * This implementation traverses the list backwards, from the last element
	 * up to the second, repeatedly swapping a randomly selected element into
	 * the "current position".  Elements are randomly selected from the
	 * portion of the list that runs from the first element to the current
	 * position, inclusive.<p>
	 *
	 * This method runs in linear time.  If the specified list does not
	 * implement the {@link RandomAccess} interface and is large, this
	 * implementation dumps the specified list into an array before shuffling
	 * it, and dumps the shuffled array back into the list.  This avoids the
	 * quadratic behavior that would result from shuffling a "sequential
	 * access" list in place.
	 *
	 * @param  list the list to be shuffled.
	 * @throws UnsupportedOperationException if the specified list or
	 *         its list-iterator does not support the <tt>set</tt> operation.
	 */
	public static void shuffle(Object[] arr) {
		if (r == null) {
			r = new Random();
		}
		shuffle(arr, r);
	}
	private static Random r;


	/**
	 * Randomly permute the specified list using the specified source of
	 * randomness.  All permutations occur with equal likelihood
	 * assuming that the source of randomness is fair.<p>
	 *
	 * This implementation traverses the list backwards, from the last element
	 * up to the second, repeatedly swapping a randomly selected element into
	 * the "current position".  Elements are randomly selected from the
	 * portion of the list that runs from the first element to the current
	 * position, inclusive.<p>
	 *
	 *
	 * @param  arr the array to be shuffled.
	 * @param  rnd the source of randomness to use to shuffle the list.
	 * @throws UnsupportedOperationException if the specified array or its
	 *         iterator does not support the <tt>set</tt> operation.
	 */
	public static void shuffle(Object[] arr, Random rnd) {
		int size = arr.length;

		// Shuffle array
		for (int i=size; i>1; i--)
			swap(arr, i-1, rnd.nextInt(i));

	}


	/**
	 * Swaps the elements at the specified positions in the specified list.
	 * (If the specified positions are equal, invoking this method leaves
	 * the list unchanged.)
	 *
	 * @param list The list in which to swap elements.
	 * @param i the index of one element to be swapped.
	 * @param j the index of the other element to be swapped.
	 * @throws IndexOutOfBoundsException if either <tt>i</tt> or <tt>j</tt>
	 *         is out of range (i &lt; 0 || i &gt;= list.size()
	 *         || j &lt; 0 || j &gt;= list.size()).
	 * @since 1.4
	 */

	/**
	 * Swaps the two specified elements in the specified array.
	 */
	private static void swap(Object[] arr, int i, int j) {
		Object tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}    


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
