import java.util.ArrayList;

public class Player {
	String name;
	int location = 0;
	ArrayList<Item> inventory;
	public int score;
	
	public Player(String name, int location) {
		this.name = name;
		this.score = 0;
		this.inventory = new ArrayList<Item>();
		this.location = location;
	}
	
	// This method allow's player to grab items and store them in the inventory
		static void take(Player user, Locale userLocation){

			if (userLocation.items.size() == 0) {
				System.out.print("No items to find\n");
			} else {
				for (int i = 0; i< 1; i++){
					user.inventory.addAll(userLocation.items);
					System.out.println("\nYou picked up a(n): " + userLocation.items);
					userLocation.items.clear();
					user.score += 5;
					System.out.println("Score +5");
					System.out.println("Your total score is: " + user.score);
					System.out.println("Your inventory: " + user.inventory.toString());

				}
			}
		}
		
	@Override
	public String toString() {
		return "Your name is " + this.name + "\n" +
				"You're current at " + this.location + "\n" +
				"Your score is: " + this.score + "\n" +
				"You have the following items: " + this.inventory;
	}

}
