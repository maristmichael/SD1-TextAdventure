import java.util.ArrayList;

public class Locale {
	String name;
	String description;
	ArrayList<Item>items;
	int visitCount;
	
	public Locale(String name, String description) {
		this.name = name;
		this.description = description;
		this.items = new ArrayList<Item>();
		this.visitCount = 0;
	};
	
	// This method creates and places new items into the Locale ArrayList
	public void placeItems(String name, String descrip, String discovered) {
		Item newItem = new Item(name, descrip, discovered);
		this.items.add(newItem);   
	}
	
	// A more useful toString method
	@Override
	public String toString() {
		return this.description + "\n(You are in the " + this.name + ")";
	}

	public void remove(Item item) {
		// TODO Auto-generated method stub
		
	};
	
}