
public class Locale {
	public String name;
	public String description;
	public String item;
	
	public Locale(String name, String description, String item) {
		this.name = name;
		this.description = description;
		this.item = item;
	}
	
	@Override
	public String toString() {
		return this.description;
	}
}