
public class SecureLocale extends Locale {
	Item requiredItem;
	int questionCount;
	boolean questionCheck;
	boolean questionFound;
	
	public static boolean canEnter(Player user, Item requiredItem) {
		for (int i = 0; i < user.inventory.size(); i++) {
			if (user.inventory.contains(requiredItem)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean canEnter(SecureLocale[] locations) {
		for (int i = 0; i < locations.length; i++){
			if (locations[i].questionCheck == false) {
				return false;
			}
		}
		return true;
	}
	
	public SecureLocale(String name, String description, Item item) {
		super(name, description);
		this.requiredItem = item;
	}
	
	
	public SecureLocale(String name, String description, boolean questionCheck, boolean questionFound, int questionCount) {
		super(name, description);
		this.questionCheck = questionCheck;
		this.questionCount = questionCount;
		this.questionFound = questionFound;
	}
	
	
}
