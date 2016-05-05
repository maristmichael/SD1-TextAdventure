
// TODO: Auto-generated Javadoc
/**
 * The Class Item.
 */
public class Item {
	
	/** The name. */
	String name;
	
	/** The descrip. */
	String descrip;
	
	/** The discovered. */
	String discovered;
	
	/** The is discovered. */
	boolean isDiscovered;
	
	/** The value. */
	int value;
	
	/**
	 * Instantiates a new item.
	 *
	 * @param name the name
	 * @param descrip the descrip
	 * @param discovered the discovered
	 */
	public Item(String name, String descrip, String discovered){
		this.name = name;
		this.descrip = descrip;
		this.discovered = discovered;
		this.value = 5;
		this.isDiscovered = false;
	};
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return this.name;
	}
	
	/**
	 * Checks for limited item.
	 *
	 * @param user the user
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
	
	/**
	 * Return limited item.
	 *
	 * @param user the user
	 * @return the limited use item
	 */
	public static LimitedUseItem returnLimitedItem(Player user){
		for(int i = 0; i < user.inventory.size(); i++) {
			if (user.inventory.get(i) instanceof LimitedUseItem) {
				return (LimitedUseItem) user.inventory.get(i);
			}
		}
		return null;
	}
}
