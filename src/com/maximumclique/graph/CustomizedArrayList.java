/**
 * 
 */
package com.maximumclique.graph;

import java.util.Arrays;

/**
 * @author Dany
 *
 */
public class CustomizedArrayList {

	
	private int[] array;
	private int size;
	public CustomizedArrayList(int size)
	{
		this.size = size;
		array = new int[size];
		Arrays.fill(array, 0);
	}
	
	/**
	 * Array function to find whether value already exists
	 * @param value
	 * @return
	 */
	public boolean contains(int value)
	{
		for(int i=0;i<size;i++)
		{
			if(array[i]==value)
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Array method to add element to a specific index
	 * @param index
	 * @param value
	 * @return
	 */
	public boolean add(int index, int value)
	{
		if(array[index]!=0)
		{
			return false;
		}
		array[index] = value;
		return true;
	}
	
	/**
	 * Array method to get the element from a specific index
	 * @param index
	 * @return
	 */
	public int get(int index)
	{
		return array[index];
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
