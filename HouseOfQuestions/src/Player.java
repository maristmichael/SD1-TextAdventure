import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Player {
	String name;
	int location = 0;
	int score;
	ArrayList<Item> inventory;
	boolean ignoreVisitVictory;
	int actionCount;
	
	public Player(String name, int location) {
		this.name = name;
		this.score = 0;
		this.inventory = new ArrayList<Item>();
		this.location = location;
		this.ignoreVisitVictory = false;
		this.actionCount = 50;
	}
	
	
	static int updateScore(Player user, Locale userLocation, boolean singleItem, boolean gainPoints, int itemNum) {
		int scoreChange = 0;
		if (singleItem == false && gainPoints == true) {
			for (int l = 0; l < userLocation.items.size(); l++) {
				scoreChange += userLocation.items.get(l).value;
			}
			user.score += scoreChange;
			System.out.println("Score +" + scoreChange);
			System.out.println("Your total score is: " + user.score);
			System.out.println("Your inventory: " + user.inventory.toString());
			return scoreChange;
			
		} else if (singleItem == false && gainPoints == false) {
			for (int l = 0; l < user.inventory.size(); l++) {
				scoreChange -= user.inventory.get(l).value;
			}
			user.score += scoreChange;
			System.out.println("Score " + scoreChange);
			System.out.println("Your total score is: " + user.score);
			return scoreChange;
				
		} else if (singleItem == true && gainPoints == true) {
			scoreChange += userLocation.items.get(itemNum).value;
			user.score += scoreChange;
			System.out.println("Score +" + scoreChange);
			System.out.println("Your total score is: " + user.score);
			System.out.println("Your inventory: " + user.inventory.toString());
			return scoreChange;
			
		} else if (singleItem == true && gainPoints == false) {
			scoreChange -= user.inventory.get(itemNum).value;
			System.out.println(scoreChange);
			user.score += scoreChange;
			System.out.println("Score " + scoreChange);
			System.out.println("Your total score is: " + user.score);
			return scoreChange;
		}
		return scoreChange;
	}
	
	// This method allows player to grab all items and store them in the inventory
		static void take(Player user, Locale userLocation, String[] item) {
			
			if (item[1].equals("ALL")) {
				user.actionCount--;
				user.inventory.addAll(userLocation.items);
				updateScore(user, userLocation, false, true, 0);
				userLocation.items.clear();
			} else if (userLocation.items.size() == 0) {
				System.out.print("No items in the room\n");
				
			} else if (userLocation.items.size() == 1) {
				for (int k = 0; k < userLocation.items.size(); k++) {
					if (userLocation.items.get(k).isDiscovered == false) {
						System.out.println("Maybe I should examine the room for an item");
						break;
					} else if (item[1].equals(userLocation.items.get(k).name.toUpperCase())) {
						user.actionCount--;
						user.inventory.add(userLocation.items.get(k));
						System.out.println("\nYou picked up the " + userLocation.items.get(k).name);
						updateScore(user, userLocation,true, true, k);
						userLocation.items.remove(userLocation.items.get(k));
						break;
					} else {
						System.out.println("Not an item the room");
						continue;
					}
				}
						
			} else if (userLocation.items.size() > 1) {
				int itemCount = userLocation.items.size();
				for (int i = 0; i < userLocation.items.size(); i++) {
					if (item[1].equals(userLocation.items.get(i).name.toUpperCase())) {
						user.actionCount--;
						user.inventory.add(userLocation.items.get(i));
						updateScore(user, userLocation, true, true, i);
						System.out.println("\nYou picked up the " + userLocation.items.get(i).name);
						userLocation.items.remove(userLocation.items.get(i));
					}
				}
				
				if (itemCount == userLocation.items.size()) {
					System.out.println("Not an item the room");
				}
			}
		}
		
	
		
	// This method allows player to drop specified items or all items in the player inventory
		static void drop(Player user, Locale userLocation, String[] item) {			
			if (item[1].equals("ALL")) {
				user.actionCount--;
				userLocation.items.addAll(user.inventory);
				updateScore(user, userLocation, false, false, 0);
				user.inventory.clear();
				System.out.println("Your inventory: " + user.inventory.toString());
			} else if (user.inventory.size() == 0) {
				System.out.println("No items to drop");	
			} else if (user.inventory.size() == 1) { 				
				for (int m = 0; m < user.inventory.size(); m++) {
					if (item[1].equals(user.inventory.get(m).name.toUpperCase())) {
						user.actionCount--;
						userLocation.items.add(user.inventory.get(m));
						System.out.println("\nYou dropped the " + user.inventory.get(m).name + " here in the " + userLocation.name);
						updateScore(user, userLocation, true, false, m);
						user.inventory.remove(user.inventory.get(m));
						System.out.println("Your inventory: " + user.inventory.toString());
						break;						
					} else {
						System.out.print("\nYou don't have that item\n");
						System.out.println("Your inventory: " + user.inventory.toString());
						continue;
					}
				}
			} else if (user.inventory.size() > 1) {
				int itemCount = user.inventory.size();
				
				for (int i = 0; i < user.inventory.size(); i++) {
					if (item[1].equals(user.inventory.get(i).name.toUpperCase())) {
						user.actionCount--;
						userLocation.items.add(user.inventory.get(i));
						System.out.println("\nYou dropped the " + user.inventory.get(i).name + " here in the " + userLocation.name);
						updateScore(user, userLocation, true, false, i);
						user.inventory.remove(user.inventory.get(i));
						System.out.println("Your inventory: " + user.inventory.toString());
					}
				}
				if (itemCount == userLocation.items.size()) {
					System.out.print("\nYou don't have that item\n");
					System.out.println("Your inventory: " + user.inventory.toString());
				}
			}
		}
		
	
		
		// This method allows user to examine room in order to discover items to pick up
		static void examine(Player user, Locale userLocation) {
			for (int i = 0; i < userLocation.items.size(); i++) {
				user.actionCount--;
				if (userLocation.items.get(i).isDiscovered == false) {
					userLocation.items.get(i).isDiscovered = true;
					System.out.println(userLocation.items.get(i).discovered);
				} else if( userLocation.items.size() != 0) {
					System.out.println("The " + userLocation.items.get(i).name + " is in the room");
				} 
			}
			if (userLocation.items.size() < 1) {
				System.out.println("Nothing special to take in this room");
			}
		}
		
		// This method allows user to use a limited-use-item
		static void use(Player user, LimitedUseItem limitedItem, String[] item) {
			if (item[1].equals("BOTTLE")) {
				user.actionCount--;
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
