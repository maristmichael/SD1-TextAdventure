/**
 * @author Michael Gutierrez <michael.gutierrez2@marist.edu>
 * @version 1.0
 * CMPT 220L-114
 * Professor Johnson
 * 6 May 2016
 *
 */

/**
 * The Class Item
 */
public class Item {
	
	/** The name name of the item */
	String name;
	
	/** The description of the item */
	String descrip;
	
	/** The text for when the item has been discovered */
	String discovered;
	
	/** Check if item has been discovered */
	boolean isDiscovered;
	
	/** The value of the item */
	int value;
	
	/**
	 * Instantiates a new item
	 *
	 * @param name the name of the item
	 * @param descrip the description of the item
	 * @param discovered the text for when item is discovered
	 */
	public Item(String name, String descrip, String discovered){
		this.name = name;
		this.descrip = descrip;
		this.discovered = discovered;
		this.value = 5;
		this.isDiscovered = false;
	};
	
	
	/**
	 * Checks if player has a LimitedUseItem
	 *
	 * @param user the player
	 * @return true, if successful
	 */
	public static boolean hasLimitedItem(Player user){
		for(int i = 0; i < user.inventory.size(); i++) {
			if (user.inventory.get(i) instanceof LimitedUseItem) {
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	/* 
	 * A more useful toString method
	 */
	public String toString(){
		return this.name;
	}
}
