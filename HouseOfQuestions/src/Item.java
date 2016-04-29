
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
	
	public static boolean hasLimitedItem(Player user){
		for(int i = 0; i < user.inventory.size(); i++) {
			if (user.inventory.get(i) instanceof LimitedUseItem) {
				return true;
			}
		}
		return false;
	}
	
	public static LimitedUseItem returnLimitedItem(Player user){
		for(int i = 0; i < user.inventory.size(); i++) {
			if (user.inventory.get(i) instanceof LimitedUseItem) {
				return (LimitedUseItem) user.inventory.get(i);
			}
		}
		return null;
	}
}
