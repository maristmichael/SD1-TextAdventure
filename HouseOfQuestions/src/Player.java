import java.util.ArrayList;
import java.util.Scanner;

public class Player {
	String name;
	int location = 0;
	ArrayList<Item> inventory;
	int score;
	
	public Player(String name, int location) {
		this.name = name;
		this.score = 0;
		this.inventory = new ArrayList<Item>();
		this.location = location;
	}
	
	// This method allows player to grab all items and store them in the inventory
		static void take(Player user, Locale userLocation) {
			int scoreToAdd = 0;

			if (userLocation.items.size() == 0) {
				System.out.print("No items to find\n");
			} else {
				for (int i = 0; i < 1; i++) {
					// This small loop adds points to player for every item picked up
					for (int l = 0; l < userLocation.items.size(); l++){
						scoreToAdd =+5;
						user.score += scoreToAdd;
					}
					user.inventory.addAll(userLocation.items);
					System.out.println("\nYou picked up a(n): " + userLocation.items);
					System.out.println("Your inventory: " + user.inventory.toString());
					userLocation.items.clear();
					System.out.println("Score +" + scoreToAdd);
					System.out.println("Your total score is: " + user.score);
					System.out.println("Your inventory: " + user.inventory.toString());
					userLocation.visitCount ++;
				}
			}
		}
		
	// This method allows player to drop specified items or all items in the player inventory
		static void drop(Player user, Locale userLocation) {
			Scanner inputSource = new Scanner(System.in);
			String userInput;
			if (user.inventory.size() == 0){
				System.out.println("No items to drop");
			} else { 
				System.out.print("What to drop?: ");
				userInput = inputSource.nextLine().toLowerCase().trim();
				
				for (int i = 0; i < user.inventory.size(); i++ ) {
					if (userInput.equals(user.inventory.get(i).name.toLowerCase())) {
						userLocation.items.add(user.inventory.get(i));
						System.out.println("\nYou dropped the " + user.inventory.get(i).name + " here in the " + userLocation.name);
						user.score -=5;
						user.inventory.remove(user.inventory.get(i));
						System.out.println("Score -5");
						System.out.println("Your total score is: " + user.score);
						System.out.println("Your inventory: " + user.inventory.toString());	
					} else if (userInput.equals("all")) {
						userLocation.items.addAll(user.inventory);
						user.inventory.clear();
						user.score = 0;
						System.out.println("\nYou droped all your items");
						System.out.println("Your total score is: " + user.score);
					} else {
						System.out.print("\nYou don't have that item\n");
						System.out.println("Your inventory: " + user.inventory.toString());	
					}
				}
			}
		}
	
	// A more useful toString method
	@Override
	public String toString() {
		return "Your name is " + this.name + "\n" +
				"You're current at " + this.location + "\n" +
				"Your score is: " + this.score + "\n" +
				"You have the following items: " + this.inventory;
	}

}
