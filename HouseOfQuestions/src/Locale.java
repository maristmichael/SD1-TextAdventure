/**
 * @author Michael Gutierrez <michael.gutierrez2@marist.edu>
 * @version 1.0
 * CMPT 220L-114
 * Professor Johnson
 * 6 May 2016
 *
 */

import java.util.ArrayList;

/**
 * The Class Locale
 */
public class Locale {
	
	/** The name */
	String name;
	
	/** The description */
	String description;
	
	/** The items contained in Locale*/
	ArrayList<Item>items;
	
	/** Check if player has examined Locale */
	boolean hasExamined;
	
	/** Visit count for the Locale */
	int visitCount;
	
	/**
	 * Instantiates a new locale
	 *
	 * @param name the name of the locale
	 * @param description the description of the locale
	 */
	public Locale(String name, String description) {
		this.name        = name;
		this.description = description;
		this.items       = new ArrayList<Item>();
		this.hasExamined = false;
		this.visitCount  = 0;
	};
	
	/**
	 * Method that creates and places new items into the Locale ArrayList
	 *
	 * @param name the name of item being placed
	 */
	public void placeItems(Item name) {
		this.items.add(name);   
	}

	@Override
	/* 
	 * A more useful toString method
	 */
	public String toString() {
		return this.description + "\n*You are in the " + this.name + "*";
	}
}