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
			System.out.println("\nScore +" + scoreChange);
			System.out.println("Your total score is: " + user.score);
			System.out.println("Total score: " + user.inventory.toString());
			return scoreChange;
			
		} else if (singleItem == false && gainPoints == false) {
			for (int l = 0; l < user.inventory.size(); l++) {
				scoreChange -= user.inventory.get(l).value;
			}
			user.score += scoreChange;
			System.out.println("\nScore " + scoreChange);
			System.out.println("Total score: " + user.score);
			return scoreChange;
				
		} else if (singleItem == true && gainPoints == true) {
			scoreChange += userLocation.items.get(itemNum).value;
			user.score += scoreChange;
			System.out.println("\nScore +" + scoreChange);
			System.out.println("Total score: " + user.score);
			System.out.println("Your inventory: " + user.inventory.toString());
			return scoreChange;
			
		} else if (singleItem == true && gainPoints == false) {
			scoreChange -= user.inventory.get(itemNum).value;
			user.score += scoreChange;
			System.out.println("\nScore " + scoreChange);
			System.out.println("Total score: " + user.score);
			return scoreChange;
		}
		return scoreChange;
	}
	
	public static boolean findUserLoc(Player user, Locale[] LOCALES, int locNum) {
		if (LOCALES[user.location] == LOCALES[locNum]){
			return true;
		}
		return false;
	}
	
	
	// This method allows player to grab all items and store them in the inventory
		static void take(Player user, Locale userLocation, String[] item) {
			
			if (item[1].equals("ALL")) {
				for (int k = 0; k < userLocation.items.size(); k++) {
					if (userLocation.items.get(k).isDiscovered == false) {
						System.out.println("Maybe I should examine the room for an item");
						break;
					} else {
						user.actionCount--;
						user.inventory.addAll(userLocation.items);
						System.out.println("\nYou picked up everything you could find in the room");
						updateScore(user, userLocation, false, true, 0);
						userLocation.items.clear();
					}
				}
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
						System.out.println("\nYou picked up the " + userLocation.items.get(i).name);
						updateScore(user, userLocation, true, true, i);
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
				System.out.println("\nYou dropped everything in your inventory here in the " + userLocation.name);
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
			userLocation.questionFound = true;
			if (userLocation.questionCount != 0 && userLocation.question != null) {
				System.out.println("\nThere is a question inscribed in the wall\n" + userLocation.question);
			} else if (userLocation.questionCheck == true && userLocation.question != null) {
				System.out.println("\nThere is a check mark now, next to the question");
			} else if (userLocation.questionCount == 0) {
				System.out.println("\nThe question on the wall dissapeared after you yelled a few times");
			}
			
			for (int i = 0; i < userLocation.items.size(); i++) {
				user.actionCount--;
				if (userLocation.items.get(i).isDiscovered == false) {
					userLocation.items.get(i).isDiscovered = true;
					System.out.println("\n" + userLocation.items.get(i).discovered);
				} else if( userLocation.items.size() != 0) {
					System.out.println("\nThe " + userLocation.items.get(i).name + " is in the room");
				} 
			}
			if (userLocation.items.size() == 0) {
				System.out.println("Nothing special to take in this room");
			}
		}
		
		// This method allows user to use a limited-use-item
		static void use(Player user, Locale[] LOCALES, LimitedUseItem limitedItem, String[] item) {
			if (item[1].equals("BOTTLE")) {
				useBottle(user, item, limitedItem);
			} else if (item[1].equals("CALCULATOR")) {
				useCalculator(user, LOCALES, item, limitedItem);
			} else { 
				System.out.println("\nNot an item that can be used");
			}
		}
		
		
		static void useBottle(Player user, String[] item, LimitedUseItem limitedItem) {
			for (int i = 0; i < user.inventory.size(); i++) {
				if(user.inventory.get(i).name.equals(item[1].toLowerCase()) && limitedItem.usesRemaining != 0) {
					user.actionCount--;
					System.out.println("You drank the water inside the bottle");
					limitedItem.usesRemaining --;
					break;
				} else if (limitedItem.usesRemaining == 0) {
					System.out.println(limitedItem.afterUse);
					break;
				}
			}
		}
		
		static void useCalculator(Player user, Locale[] LOCALES, String[] item, LimitedUseItem limitedItem) {
			for (int i= 0; i < user.inventory.size(); i++) {
				if (user.inventory.get(i).name.equals(item[1].toLowerCase()) && limitedItem.usesRemaining != 0) {
					for (int m = 0; m < user.inventory.size(); m++) {
						if (user.inventory.get(m).name.equals("batteries") && findUserLoc(user,LOCALES,3) == true) {
							user.actionCount--;
							System.out.print("You calculated the equation on the wall 761 − 347 = 414");
							return;
						} else if (user.inventory.get(m).name.equals("batteries")) {
							user.actionCount--;
							System.out.println("You are playing with the calculator, weirdo...");
							return;
						}
					}
					
				} else if (limitedItem.usesRemaining == 0) {
					System.out.println(limitedItem.afterUse);
					return;
				}
			}
			System.out.println("The calulator doesn't turn on, maybe there are batteries to find...");
		}
		
		static void yell(Player user, Locale userLocation, String[] text) {
			user.actionCount--;
			System.out.println("\nYou yelled out '" + text[1] + "'");
	
			if (userLocation.questionFound == false) {
				System.out.println("Nothing happened");
			} else {
				if (text[1].equals(userLocation.answer) && userLocation.questionCheck == false && userLocation.questionCount != 0) {
					userLocation.questionCheck = true;
					System.out.println("A checkmark appeared to next the question written on the wall");
				} else if (text[1].equals(userLocation.answer) && userLocation.questionCheck == true) {
					System.out.println("You already got the answer to the room");
				} else if (userLocation.questionCount == 0) {
					System.out.println("The question on the wall dissapeared");
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
