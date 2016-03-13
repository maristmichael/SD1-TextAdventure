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
	
	@Override
	public String toString() {
		return this.description + "\n(You are in the " + this.name + ")";
	};
	
}