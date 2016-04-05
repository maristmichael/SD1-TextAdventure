import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Player {
	String name;
	int location = 0;
	int score;
	ArrayList<Item> inventory;
	
	public Player(String name, int location) {
		this.name = name;
		this.score = 0;
		this.inventory = new ArrayList<Item>();
		this.location = location;
	}
	
	// This method allows player to grab all items and store them in the inventory
		static void take(Player user, Locale userLocation, String[] item) {
			int scoreToAdd = 0;

			if (userLocation.items.size() == 0) {
				System.out.print("Already found an item here\n");	
			} else if(userLocation.items.size() != 0) {
				for (int k = 0; k < userLocation.items.size(); k++) {
					if (userLocation.items.get(k).isDiscovered == false) {
						System.out.println("Maybe I should examine the room for an item");
						break;
					
					} else if(item[1].equals("ALL")) {
						
						for (int l = 0; l < userLocation.items.size(); l++) {
							scoreToAdd += userLocation.items.get(l).value;
						}
						user.inventory.addAll(userLocation.items);
						System.out.println("\nYou picked up a(n): " + userLocation.items);
						System.out.println("Your inventory: " + user.inventory.toString());
						userLocation.items.clear();
						user.score += scoreToAdd;
						System.out.println("Score +" + scoreToAdd);
						System.out.println("Your total score is: " + user.score);
						System.out.println("Your inventory: " + user.inventory.toString());
						
					} else if(item[1].equals(userLocation.items.get(k).name.toUpperCase())) {		
						user.inventory.add(userLocation.items.get(k));
						user.score += userLocation.items.get(k).value;
						System.out.println("\nYou picked up the " + user.inventory.get(k).name + " here in the " + userLocation.name);
						userLocation.items.remove(userLocation.items.get(k));
						System.out.println("Score +5");
						System.out.println("Your total score is: " + user.score);
						System.out.println("Your inventory: " + user.inventory.toString());				
						
					} else {
						System.out.println("Not an item available to pick up");
						System.out.println(userLocation.items);
						break;
					}

				}
			}
		}
	
		
	// This method allows player to drop specified items or all items in the player inventory
		static void drop(Player user, Locale userLocation, String[] item) {			
			if (user.inventory.size() == 0){
				System.out.println("No items to drop");
			} else { 				
				for (int i = 0; i < user.inventory.size(); i++ ) {
					if (item[1].equals(user.inventory.get(i).name.toUpperCase())) {
						userLocation.items.add(user.inventory.get(i));
						System.out.println("\nYou dropped the " + user.inventory.get(i).name + " here in the " + userLocation.name);
						user.score -= userLocation.items.get(i).value;
						user.inventory.remove(user.inventory.get(i));
						System.out.println("Score -5");
						System.out.println("Your total score is: " + user.score);
						System.out.println("Your inventory: " + user.inventory.toString());	
					} else if (item[1].equals("ALL")) {
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
		
		// This method allows user to examine room in order to discover items to pick up
		static void examine(Locale userLocation) {
			for (int i = 0; i < userLocation.items.size(); i++) {
				if (userLocation.items.get(i).isDiscovered == false) {
					userLocation.items.get(i).isDiscovered = true;
					System.out.println(userLocation.items.get(i).discovered);
				} else if(userLocation.items.size() != 0) {
					System.out.println("The " + userLocation.items.get(i).name + " is in the room");
				} 
			}
			if(userLocation.items.size() < 1) {
				System.out.println("Nothing special to take in this room");
			}
		}
		
		// This method allows user to use a limited-use-item
		static void use(Player user, LimitedUseItem limitedItem, String[] item) {
			if (item[1].equals("BOTTLE")) {
				if (user.inventory.size() > 0) {
					for (int i = 0; i < user.inventory.size(); i++) {
						if(user.inventory.get(i).name.equals("bottle") && limitedItem.usesRemaining != 0) {
							System.out.println("You drank the water inside the bottle");
							limitedItem.usesRemaining --;
							break;
						} else if (limitedItem.usesRemaining == 0) {
							System.out.println(limitedItem.afterUse);
							break;
						}
					}
				}
			} else {
				System.out.println("Not an item to that can be used in your inventory");
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
