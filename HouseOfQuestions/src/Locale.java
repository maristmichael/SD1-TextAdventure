import java.util.ArrayList;

public class Locale {
	String name;
	String description;
	ArrayList<Item>items;
	
	public Locale(String name, String description) {
		this.name = name;
		this.description = description;
		this.items = new ArrayList<Item>();
	};
	
	public void placeItems(String name, String descrip) {
		Item newItem = new Item(name, descrip);
		this.items.add(newItem);   
	    }
	
	@Override
	public String toString() {
		return this.description + "\n(You are in the " + this.name + ")";
	};
	
}