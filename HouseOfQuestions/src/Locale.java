
public class Locale {
	String name;
	String description;
	Item item;
	
	public Locale(String name, String description, Item item) {
		this.name = name;
		this.description = description;
	};
	
	@Override
	public String toString() {
		return this.description + "\n(You are in the " + this.name + ")";
	};
	
}