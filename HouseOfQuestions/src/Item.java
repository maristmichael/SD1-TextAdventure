
public class Item {
	String name;
	String descrip;
	String discovered;
	boolean isDiscovered;
	int value;
	
	public Item(String name, String descrip, String discovered){
		this.name = name;
		this.descrip = descrip;
		this.discovered = discovered;
		this.value = 5;
		this.isDiscovered = false;
	};
	
	@Override
	public String toString(){
		return this.name;
	}
	
}
