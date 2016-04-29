import java.util.ArrayList;

public class Locale {
	String name;
	String description;
	ArrayList<Item>items;
	int visitCount;
	boolean questionCheck;
	
	public Locale(String name, String description, boolean questionCheck) {
		this.name = name;
		this.description = description;
		this.items = new ArrayList<Item>();
		this.visitCount = 0;
		this.questionCheck = questionCheck;
	};
	
	// This method creates and places new items into the Locale ArrayList
	public void placeItems(Item name) {
		this.items.add(name);   
	}
	
	// A more useful toString method
	@Override
	public String toString() {
		return this.description + "\n(You are in the " + this.name + ")";
	}
	
	public boolean hasLimitedItem(Player user, LimitedUseItem item){
		if (user.inventory.contains(item)) {
			return true;
		}
		return false;
	}
}