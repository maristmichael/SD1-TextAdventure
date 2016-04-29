
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
	
	public static boolean canEnter(Locale[] locations) {
		for (int i = 0; i < locations.length; i++){
			if (locations[i].questionCheck == false) {
				return false;
			}
		}
		return true;
	}
	
	public SecureLocale(String name, String description, boolean questionCheck, boolean questionFound) {
		super(name, description, questionCheck, questionFound);
	}
}
