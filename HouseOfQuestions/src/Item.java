
public class Item {
	String name;
	String descrip;
	boolean isDiscovered;
	int value;
	
	public Item(String name, String descrip){
		this.name = name;
		this.descrip = descrip;
		this.value = 5;
		this.isDiscovered = false;
	};
	
	@Override
	public String toString(){
		return this.name;
	}
	
}
