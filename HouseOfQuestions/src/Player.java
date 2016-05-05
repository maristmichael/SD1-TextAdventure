import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * The Class Player.
 */
public class Player {
	
	/** String that holds player name */
	String name;
	
	/** ArrayList that serves as player inventory */
	ArrayList<Item> inventory;
	
	/** Player attribute spiritual */
	boolean spiritual;
	
	/** Player attribute lucky */
	boolean lucky;
	
	/** Player location */
	int location;
	
	/** Player score. */
	int score;
	
	/** Count for player moves */
	int actionCount;
	
	/** Count for player answering questions */
	int answerCount;
	
	/** Count for player receiving clues */
	int clueCount;


	
	/**
	 * Instantiates a new player.
	 */
	public Player() {
		this.inventory        = new ArrayList<Item>();
		this.spiritual        = false;
		this.lucky            = false;
		this.location         = 0;
		this.score            = 0;
		this.actionCount      = 20;
		this.answerCount      = 3;
		this.clueCount        = 3;
	}
	
	/** String array that holds text for when player prays  */
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
	
	/**
	 * Method that computes score
	 *
	 * @param user the player
	 * @param userLocation the player location
	 * @param singleItem check to see if player picked up a single item
	 * @param gainPoints check to see if player is gaining points
	 * @param itemNum number passes through to a for loop to determine item
	 */
	static void updateScore(Player user, Locale userLocation, boolean singleItem, boolean gainPoints, int itemNum) {
		int scoreChange = 0;
		
		if (singleItem == false && gainPoints == true) {
			for (int l = 0; l < userLocation.items.size(); l++) {
				scoreChange += userLocation.items.get(l).value;
			}
			user.score += scoreChange;
			System.out.println("\nScore +" + scoreChange);
			System.out.println("Your total score is: " + user.score);
			System.out.println("Total score: " + user.inventory.toString());
			
		} else if (singleItem == false && gainPoints == false) {
			for (int l = 0; l < user.inventory.size(); l++) {
				scoreChange -= user.inventory.get(l).value;
			}
			user.score += scoreChange;
			System.out.println("\nScore " + scoreChange);
			System.out.println("Total score: " + user.score);
				
		} else if (singleItem == true && gainPoints == true) {
			scoreChange += userLocation.items.get(itemNum).value;
			user.score += scoreChange;
			System.out.println("\nScore +" + scoreChange);
			System.out.println("Total score: " + user.score);
			System.out.println("Your inventory: " + user.inventory.toString());
			
		} else if (singleItem == true && gainPoints == false) {
			scoreChange -= user.inventory.get(itemNum).value;
			user.score += scoreChange;
			System.out.println("\nScore " + scoreChange);
			System.out.println("Total score: " + user.score);
		}
	}
	
	/**
	 * Checks to see if user is a certain location
	 *
	 * @param user the player
	 * @param LOCALES the array containing all instances of Locale
	 * @param locNum the location number
	 * @return true, if user is at specified location
	 */
	public static boolean checkUserLoc(Player user, Locale[] LOCALES, int locNum) {
		if (LOCALES[user.location] == LOCALES[locNum]){
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if user has lucky trait
	 *
	 * @param user the player
	 * @return true, if user has lucky trait
	 */
	static boolean checkUserTrait(Player user) {
		if (user.lucky) {
			return true;
		}
		return false;
	}
	
	/**
	 * Sets the player name
	 *
	 * @param user the player
	 * @param inputSource the input source
	 */
	public static void setUserName(Player user, Scanner inputSource){
		String enteredName;

		System.out.print("\n" + "What's your name?: ");
		enteredName = inputSource.nextLine();
		user.name = enteredName;
		System.out.println("\nHello " + user.name + "\n");
	}
	
	/**
	 * Sets the user trait
	 *
	 * @param user the player
	 * @param inputSource the input source
	 */
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
				user.actionCount *= 2;
				user.answerCount *= 2;
				user.lucky        = true;
				System.out.println("\nYou are a lucky person");
				break;
			} else {
				System.out.println("\nPlease answer the question...");
				continue;
			}
		}
	}
	
	
	/**
	 * Method that allows player to take items and store it in their inventory
	 *
	 * @param user the player
	 * @param userLocation the player location
	 * @param item String array that takes in the split user input
	 */
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
		
	
		
	/**
	 * Method allows player to drop specified items or all items in the player inventory
	 *
	 * @param user the player
	 * @param userLocation the player location
	 * @param item String array that takes in the split user input
	 */
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
		
	
		
	/**
	 * Method allows player to examine room in order to discover items and question
	 *
	 * @param user the player
	 * @param userLocation the player location in the array of Locale
	 */
	static void examine(Player user, Locale[] userLocation) {
		SecureLocale secureLoc;
		userLocation[user.location].hasExamined = true;
			
		if (userLocation[user.location] instanceof SecureLocale) {
			secureLoc = (SecureLocale) userLocation[user.location];
			secureLoc.questionFound = true;
				
			if (secureLoc.questionCount != 0 && secureLoc.question != null) {
				System.out.println("\nThere is a question inscribed in the wall\n" + secureLoc.question);
			} else if (secureLoc.questionCheck == true && secureLoc.question != null) {
				System.out.println("\nThere is a check mark now, next to the question");
			} else if (secureLoc.questionCount == 0) {
				System.out.println("\nThe question on the wall dissapeared after you yelled a few times");
			}	
		}
			
		for (int i = 0; i < userLocation[user.location].items.size(); i++) {
			if (userLocation[user.location].items.get(i).isDiscovered == false) {
				userLocation[user.location].items.get(i).isDiscovered = true;
				System.out.println("\n" + userLocation[user.location].items.get(i).discovered);
				
			} else if (userLocation[user.location].items.size() != 0) {
				System.out.println("\nThe " + userLocation[user.location].items.get(i).name + " is in the room");
			} 
		}
		if (userLocation[user.location].items.size() == 0) {
			System.out.println("Nothing special to take in this room");
		}
	}
		
	/**
	 * Method allows user to use a limited-use-item
	 *
	 * @param user the player
	 * @param LOCALES the array containing all instances of Locale
     * @param limitedItem the limited use item that is being used
	 * @param item String array that takes in the split user input
     */
	static void use(Player user, Locale[] LOCALES, LimitedUseItem limitedItem, String[] item) {
		if (item[1].equals("BOTTLE")) {
			useBottle(user,limitedItem, item);
		} else if (item[1].equals("CALCULATOR")) {
			useCalculator(user, LOCALES, limitedItem, item);
		} else { 
			System.out.println("\nNot an item that can be used");
		}
	}
		
		
	/**
	 * user() helper method that handles player using the bottle item
	 *
	 * @param user the player
     * @param limitedItem the limited use item that is being used
	 * @param item String array that takes in the split user input
	 */
	static void useBottle(Player user, LimitedUseItem limitedItem, String[] item) {
		int gainMoveAction = 3;
			
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
		
	/**
	 * user() helper method that handles player using the calculator item
	 *
	 * @param user the player
	 * @param LOCALES the array containing all instances of Locale
     * @param limitedItem the limited use item that is being used
	 * @param item String array that takes in the split user input
	 */
	static void useCalculator(Player user, Locale[] LOCALES, LimitedUseItem limitedItem, String[] item) {
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
		
	/**
	 * Method that allows user to yell text, also used to answer questions that rooms may have
	 *
	 * @param user the player
	 * @param currentLoc the player location in the array of Locale
	 * @param text String array that takes in the split user input
	 */
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
				user.answerCount--;
					
				if (secureLoc.questionCount != 0) {
					System.out.println("Nothing happened...");
				} else {
					System.out.println("The question on the suddenly dissapeared");
				}
				
				System.out.println("\nThe number on ur left palm is now " + user.answerCount);
			}
		}
	}
		
	/**
	 * Method that checks to see if player can perform certain actions such as pray()
	 *
	 * @param user the player
	 * @param currentLoc the player location in the array of Locale
	 * @param secLocs array containing all instances of SecureLocale
	 */
	static void actionCheck(Player user, Locale[] currentLoc, SecureLocale[] secLocs) {
		if (user.spiritual) {
			pray(user, currentLoc, secLocs);
		} else {
			System.out.println("\nNot a valid command");
		}
	}
		
	/**
	 * Method that allow player to pray, which gives the user hints to answer questions
	 *
	 * @param user the user
	 * @param currentLoc the player location in the array of Locale
	 * @param secLocs array containing all instances of SecureLocale
	 */
	static void pray(Player user, Locale[] currentLoc, SecureLocale[] secLocs) {
		if (currentLoc[user.location].hasExamined) {
			if (!(currentLoc[user.location] instanceof SecureLocale)) {
				if (user.clueCount != 0) {
					System.out.println("\n" + PRAYERS[user.location]);
					user.clueCount--;
					return;
				} else if (user.clueCount == 0) {
					System.out.println("\nYou asked for divine help but nothing happened");
					return;
				}
			} else {
				for (int i = 0; i < secLocs.length; i++) {
					if (secLocs[i].equals(currentLoc[user.location])) {
						if (secLocs[i].questionFound && user.clueCount != 0) {
							System.out.println("\n" + PRAYERS[user.location]);
							user.clueCount--;
							return;
						} else if (user.clueCount == 0) {
							System.out.println("\nYou asked for divine help but nothing happened");
							return;
						}
					}
				}
			}
		}
		System.out.println("\nMaybe I should examine the room before I ask for help");
	}
		

	@Override
	/* 
	 * A more useful toString method
	 * 
	 * @return a string
	 */
	public String toString() {
		return "\nName: " + this.name + "\n" +
				"Trait: " + HouseOfQuestions.returnUserTrait() + "\n" +
				"Current Location: " + HouseOfQuestions.findUserLoc(this.location) + "\n" +
				"Score: " + this.score + "\n" +
				"Inventory: " + this.inventory + "\n";
	}
}
