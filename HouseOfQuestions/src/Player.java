import java.util.ArrayList;
import java.util.Scanner;

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
		static void take(Player user, Locale userLocation) {

			if (userLocation.items.size() == 0) {
				System.out.print("No items to find\n");
			} else {
				for (int i = 0; i < 1; i++){
					user.inventory.addAll(userLocation.items);
					System.out.println("\nYou picked up a(n): " + userLocation.items);
					userLocation.items.clear();
						
					if (userLocation.visitCount == 0) {
						user.score += 5;
						System.out.println("Score +5");
						System.out.println("Your total score is: " + user.score);
						System.out.println("Your inventory: " + user.inventory.toString());
					}
					userLocation.visitCount ++;
				}
			}
		}
		
		static void drop(Player user, Locale userLocation) {
			Scanner inputSource = new Scanner(System.in);
			String userInput;
			if (user.inventory.size() == 0){
				System.out.println("No items to drop\n");
			} else {
				System.out.print("What to drop?: ");
				userInput = inputSource.nextLine().toLowerCase();
				for (int i = 0; i < user.inventory.size(); i++ ) {
					if (userInput.equals(user.inventory.get(i).name.toLowerCase())) {
						userLocation.items.add(user.inventory.get(i));
						System.out.print("You dropped the " + user.inventory.get(i).name + " here in the " + userLocation.name);
						System.out.println();
						user.inventory.remove(user.inventory.get(i));
					} else {
						System.out.println("What item did you want to drop again?");
					}
				break;
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
