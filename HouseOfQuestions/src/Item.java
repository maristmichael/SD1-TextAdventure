
public class Item {
	String name;
	String descrip;
	
	public Item(String name, String descrip){
		this.name = name;
		this.descrip = descrip;
	};
	
	@Override
	public String toString(){
		return this.name;
	}
	
}
