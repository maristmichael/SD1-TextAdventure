import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Player {
	String name;
	ArrayList<Item> inventory;
	boolean ignoreVisitVictory;
	boolean spiritual;
	boolean lucky;
	int location;
	int score;
	int actionCount;
	int questionCounter;
	int clueCounter;


	
	public Player() {
		this.inventory = new ArrayList<Item>();
		this.ignoreVisitVictory = false;
		this.spiritual          = false;
		this.lucky            = false;
		this.location           = 0;
		this.score              = 0;
		this.actionCount        = 20;
		this.questionCounter    = 3;
		this.clueCounter        = 3;
	}
	
	public final static String[] PRAYERS = {
			"You shall answer all questions that may arise to succeed",
			"The genre you seek is very repetitive",
			"You have found the answers",
			"Use technology to seek the answer",
			"Drink to replinish thou's life",
			"The same color of thou's feces",
			"The famous mortal whose names begins with P",
			"The number you seek is below 30",
			"The word you seek has a length of 6",
			"The wonderful city is located in the beautiful country of Italy"
		};
	
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
	
	public static boolean checkUserLoc(Player user, Locale[] LOCALES, int locNum) {
		if (LOCALES[user.location] == LOCALES[locNum]){
			return true;
		}
		return false;
	}
	
	static boolean checkUserTrait(Player user) {
		if (user.lucky) {
			return true;
		}
		return false;
	}
	
	
	public static Locale returnUserLoc(Player user, Locale [] LOCALES) {
		return LOCALES[user.location];
	}
	
	public static void setUserName(Player user, Scanner inputSource){
		String enteredName;

		System.out.print("\n" + "What's your name?: ");
		enteredName = inputSource.nextLine();
		user.name = enteredName;
		System.out.println("\nHello " + user.name + "\n");
	}
	
	public static void setUserTrait(Player user, Scanner inputSource){
		String enteredTrait;
		
		while(true) {
			System.out.print("Are you a person who's (S)piritual, or (L)ucky?: ");
			enteredTrait = inputSource.nextLine().trim().toUpperCase();
			
			if (enteredTrait.equals("S")) {
				user.spiritual = true;
				System.out.println("\nYou are a spiritual person");
				break;
			} else if(enteredTrait.equals("L")) {
				user.actionCount     *= 2;
				user.questionCounter *= 2;
				user.lucky = true;
				System.out.println("\nYou are a lucky person");
				break;
			} else {
				System.out.println("\nPlease answer the question...");
				continue;
			}
		}
	}
	
	
	// This method allows player to grab all items and store them in the inventory
		static void take(Player user, Locale userLocation, String[] item) {
			if (item[1].equals("ALL")) {
				for (int k = 0; k < userLocation.items.size(); k++) {
					if (userLocation.items.get(k).isDiscovered == false) {
						System.out.println("Maybe I should examine the room for an item");
						break;
					} else {
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
		static void examine(Player user, Locale[] currentLoc) {
			SecureLocale secureLoc;
			currentLoc[user.location].hasExamined = true;
			if (currentLoc[user.location] instanceof SecureLocale) {
				secureLoc = (SecureLocale) currentLoc[user.location];
				secureLoc.questionFound = true;
				
				if (secureLoc.questionCount != 0 && secureLoc.question != null) {
					System.out.println("\nThere is a question inscribed in the wall\n" + secureLoc.question);
				} else if (secureLoc.questionCheck == true && secureLoc.question != null) {
					System.out.println("\nThere is a check mark now, next to the question");
				} else if (secureLoc.questionCount == 0) {
					System.out.println("\nThe question on the wall dissapeared after you yelled a few times");
				}	
			}
			
			for (int i = 0; i < currentLoc[user.location].items.size(); i++) {
				if (currentLoc[user.location].items.get(i).isDiscovered == false) {
					currentLoc[user.location].items.get(i).isDiscovered = true;
					System.out.println("\n" + currentLoc[user.location].items.get(i).discovered);
					
				} else if( currentLoc[user.location].items.size() != 0) {
					System.out.println("\nThe " + currentLoc[user.location].items.get(i).name + " is in the room");
				} 
			}
			if (currentLoc[user.location].items.size() == 0) {
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
			int gainMoveAction = 5;
			for (int i = 0; i < user.inventory.size(); i++) {
				if(user.inventory.get(i).name.equals(item[1].toLowerCase()) && limitedItem.usesRemaining != 0) {
					limitedItem.usesRemaining --;
					user.actionCount += gainMoveAction;
					System.out.println("You drank the water inside the bottle\n" + "The number on right hand increased by 5\n");
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
						if (user.inventory.get(m).name.equals("batteries") && checkUserLoc(user,LOCALES,3) == true) {
							limitedItem.usesRemaining --;
							System.out.print("You calculated the equation on the wall 761 − 347 = 414");
							return;
						} else if (user.inventory.get(m).name.equals("batteries")) {
							limitedItem.usesRemaining --;
							System.out.println("You play with calculator");
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
		
		static void yell(Player user, Locale[] currentLoc, String[] text) {
			System.out.println("\nYou yelled out '" + text[1] + "'");
			
			if (!(currentLoc[user.location] instanceof SecureLocale)) {
				System.out.println("Nothing happened...");
			} else {
				SecureLocale secureLoc = (SecureLocale) currentLoc[user.location];
				if (secureLoc.questionFound == false) {
					System.out.println("Nothing happened...");
					return;
				} else if (text[1].equals(secureLoc.answer) && secureLoc.questionCheck == false && secureLoc.questionCount != 0) {
					secureLoc.questionCheck = true;
					System.out.println("A checkmark appeared to next the question written on the wall");
					return;
				} else if (secureLoc.questionCheck == true) {
					System.out.println("You already got the answer to the room");
					return;
				} else if (secureLoc.questionCount == 0) {
					System.out.println("The question on the wall dissapeared");
					System.out.println("Nothing happened...");
					return;
				} else {
					secureLoc.questionCount--;
					user.questionCounter--;
					
					if (secureLoc.questionCount != 0) {
						System.out.println("Nothing happened...");
					} else {
						System.out.println("The question on the suddenly dissapeared");
					}
					System.out.println("\nThe number on ur left palm is now " + user.questionCounter);
				}

			}
		}
		
		static void playerAction(Player user, Locale[] currentLoc, SecureLocale[] loc) {
			if (user.spiritual) {
				pray(user, currentLoc, loc);
			} else {
				System.out.println("\nNot a valid command");
			}
		}
		
		static void pray(Player user, Locale[] currentLoc, SecureLocale[] loc) {
			if (currentLoc[user.location].hasExamined) {
				if (!(currentLoc[user.location] instanceof SecureLocale)) {
					if (user.clueCounter != 0) {
						System.out.println("\n" + PRAYERS[user.location]);
						user.clueCounter--;
						return;
					} else if (user.clueCounter == 0) {
						System.out.println("\nYou asked for divine help but nothing happened");
						return;
					}
				} else {
					for (int i = 0; i < loc.length; i++) {
						if (loc[i].equals(currentLoc[user.location])) {
							if (loc[i].questionFound && user.clueCounter != 0) {
								System.out.println("\n" + PRAYERS[user.location]);
								user.clueCounter--;
								return;
							} else if (user.clueCounter == 0) {
								System.out.println("\nYou asked for divine help but nothing happened");
								return;
							}
						}
					}
				}
			}
			System.out.println("\nMaybe I should examine the room before I ask for help");
		}
		
		static void something(Player user, Locale[] currentLoc) {
			
		}
	
	
	// A more useful toString method
	@Override
	public String toString() {
		return "\nName: " + this.name + "\n" +
				"Trait: " + HouseOfQuestions.returnUserTrait() + "\n" +
				"Current Location: " + HouseOfQuestions.findUserLoc(this.location) + "\n" +
				"Score: " + this.score + "\n" +
				"Inventory: " + this.inventory + "\n";
	}
}
