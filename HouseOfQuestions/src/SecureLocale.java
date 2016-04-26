
public class SecureLocale extends Locale {
	Item requiredItem;
	
	public static boolean canEnter(Player user, Item requiredItem) {
		for (int i = 0; i < user.inventory.size(); i++) {
			if (user.inventory.contains(requiredItem)) {
				return true;
			}
		}
		return false;
	}
	
	public SecureLocale(String name, String description, Item requiredItem) {
		super(name, description);
		this.requiredItem = requiredItem;
	}
}
