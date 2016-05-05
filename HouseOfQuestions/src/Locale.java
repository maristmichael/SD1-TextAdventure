import java.util.ArrayList;

/**
 * The Class Locale
 */
public class Locale {
	
	/** The name */
	String name;
	
	/** The description. */
	String description;
	
	/** The items contained in */
	ArrayList<Item>items;
	
	/** The has examined. */
	boolean hasExamined;
	
	/** The visit count. */
	int visitCount;
	
	/**
	 * Instantiates a new locale.
	 *
	 * @param name the name
	 * @param description the description
	 */
	public Locale(String name, String description) {
		this.name        = name;
		this.description = description;
		this.items       = new ArrayList<Item>();
		this.hasExamined = false;
		this.visitCount  = 0;
	};
	
	/**
	 * Place items.
	 *
	 * @param name the name
	 */
	// This method creates and places new items into the Locale ArrayList
	public void placeItems(Item name) {
		this.items.add(name);   
	}
	

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	// A more useful toString method
		@Override
		public String toString() {
			return this.description + "\n*You are in the " + this.name + "*";
		}
}