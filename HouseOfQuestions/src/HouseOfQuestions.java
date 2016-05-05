/**
 * @author Michael Gutierrez <michael.gutierrez2@marist.edu>
 * @version 1.0
 * CMPT 220L-114
 * Professor Johnson
 * 6 May 2016
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * The Class HouseOfQuestions
 */
public class HouseOfQuestions {
	
	/** All items in the game: bottle, and calculator are items that can be used */
 	static Item map                  = new Item("map", "A map of the house", "You spot a map on the floor");
	static Item guitar               = new Item("guitar", "A nifty acoustic guitar", "You found a cool guitar");
	static Item batteries            = new Item("batteries", "a pair of Double-A batteries", "You see a pair of batteries on a lab table");
	static Item painting             = new Item("painting", "Van Gogh's famous famous painting","A familiar painting catches your eyes");
	static Item novel                = new Item("novel", "Great Gatsby, a famous book by F.Scott Fitzgerald", "You find your favorite novel of all time");
	static Item textbook             = new Item("textbook", "A thick book containing U.S. history", "You see a big and textbook on the table");
	static Item beard	             = new Item("beard", "A fake beard to wear for philosophizing", "You see a beard on the floor");
	static LimitedUseItem bottle     = new LimitedUseItem("bottle", "A water bottle", "You see a bottle", 2, "You drank some of the water");
	static LimitedUseItem calculator = new LimitedUseItem("calculator","A nifty TI-84 calculator", "You spot a nice calculator",
														   4,"The calulator turned off, the batteries must be drained");

	/** The Constant N represents North as the number 0*/
	public static final int N = 0;
	
	/** The Constant S represents South as the number 1*/
	public static final int S = 1;
	
	/** The Constant W represents West as the number 2*/
	public static final int W = 2;
	
	/** The Constant E represents East as the number 3 */
	public static final int E = 3;

	/** String array that contains Locale descriptions */
	public static final String[] LocDescrip = {
		"This is the starting area, there are several paths to take",
		"In this room, you hear many melodies emanating from the walls",
		"You have entered the secret room, its wall completely white and has " + 
				"a wall inscribed with the answers to the questions you answered",
		"This is a room that has walls written with math equations",
		"Suprisingly you found the kitchen though it doesn't look like it has much to eat",
		"You enter a room littered with many scientific journals and books",
		"This room contains many philosophical rhetoric inscribed on the walls",
		"You are now in a room with a giant globe with many history books surrounding it",
		"You enter a room with many dictionaries and novels all stacked neatly" + 
				"\nThe next path is closed off by a metal door inscribed with a drawing of a calculator",
		"What a beautiful room! It has many works of art scattered around"
	};
	
	/** Locale array with instances of Locale and SecureLocale */
	public final static Locale[] LOCALES = {
		new Locale      ("Starting Room",   LocDescrip[0]),
		new SecureLocale("Music Room",      LocDescrip[1] , false, false, 3),
		new Locale      ("Result Room",     LocDescrip[2]),
		new SecureLocale("Math Room",       LocDescrip[3] , false, false, 3),
		new Locale      ("Kitchen",         LocDescrip[4]),
		new SecureLocale("Science Room",    LocDescrip[5] , false, false, 3),
		new SecureLocale("Philosophy Room", LocDescrip[6] , false, false, 3),
		new SecureLocale("History Room",    LocDescrip[7] , false, false, 3),
		new SecureLocale("English Room",    LocDescrip[8] , false, false, 3),
		new SecureLocale("Art Room",        LocDescrip[9] , false, false, 3)
	};
	
	
	/** SecureLocale array that references all Locale that are instances of SecureLocale in the array LOCALES */
	public final static SecureLocale[] SECURELOCS = new SecureLocale[] {
		(SecureLocale) LOCALES[1],
		(SecureLocale) LOCALES[3],
		(SecureLocale) LOCALES[5],
		(SecureLocale) LOCALES[6],
		(SecureLocale) LOCALES[7],
		(SecureLocale) LOCALES[8],
		(SecureLocale) LOCALES[9],
	};
	
	/** The navigation matrix */
	public final static int [][] MAP = {
			  /*{N,S,W,E}*/
		/*0*/	{1,2,3,7},   // From Start   --> Math(W), Music(N), History(E), Result(S)
	 	/*1*/	{-1,0,-1,-1},// From Music   --> Start(S)
	 	/*2*/	{0,-1,-1,-1},// From Result  --> Start(N)
	 	/*3*/	{4,5,-1,0},  // From Math    --> Start(E), Kitchen(N), Science(S)
	 	/*4*/	{-1,3,-1,-1},// From Kitchen --> Math(S)
	 	/*5*/	{3,6,-1,-1}, // From Science --> Math(N), Philosophy(S)
	 	/*6*/	{5,-1,-1,-1},// From Philosophy --> Science(N)
	 	/*7*/	{8,-1,0,-1}, // From History     --> English (N), Start(E)
	 	/*8*/	{9,7,-1,-1}, // From English --> Art(N), Art(S)
	 	/*9*/	{-1,8,-1,-1} // From Art --> English(S)
	};
	
	
	/** Starting instance of Player */
	static Player currentPlayer = new Player();
	
	/** Starting instance of BreadcrumTrail */
	static BreadcrumbTrail playerTrail = new BreadcrumbTrail();
	
	/** Starting instance of Scanner used for user input */
	public static Scanner inputSource = new Scanner(System.in);
	
	/** String variable that holds user in */
	public static String userInput;
	
	/**
	 * Method looks at player's location and displays appropriate description.
	 *
	 * @return String description of Locale that player is located at
	 */
	static String locToScene() {
		return LOCALES[currentPlayer.location].toString();
	}
	
	/**
	 * Method that displays map in String form if player has a map item
	 */
	public static void displayMap(){
		if (currentPlayer.inventory.contains(map)) {
			System.out.println(
				". . . . . . . . . . . . . . . . .\n"+
				".                               .\n" +
				".                         Art   .\n" +
				".                          |    .\n" +
				".                          |    .\n" +
				".   Kitchen    Music   English  .\n" +
				".     |          |         |    .\n" +
				".     |          |         |    .\n" +
				".    Math------Start---History  .\n" +
				".     |          |              .\n" +
				".     |          |              .\n" +
				".   Science     Result          .\n" +
				".     |                         .\n" +
				".     |                         .\n" +
				".   Philosophy                  .\n" +
				".                               .\n" +
				". . . . . . . . . . . . . . . . ."
			);
		} else {
			System.out.println("You do not have a map");
		}
	}
	
	/**
	 * Method that finds the name of the Locale player is located at
	 *
	 * @param userLoc the player location number
	 * @return the name of the Locale
	 */
	public static String findUserLoc(int userLoc) {
		return LOCALES[userLoc].name;
	}
	
	/**
	 * Method that returns the Player trait in String form
	 *
	 * @return the user trait
	 */
	public static String returnUserTrait(){
		if (currentPlayer.lucky) {
			return "Lucky";
		}
		return "Spiritual";
	}
	
	/**
	 * Method that displays all possible commands in String form
	 */
	static void showHelp() {
		System.out.println( 
			". . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . .\n" +
			".                                                             .\n" +
			".  Enter 'n', 's', 'e', 'w': move a direction                 .\n" + 
			".  Enter 'q' : quits the game                                 .\n" + 
			".  Enter 'm' : displays the game map                          .\n" + 
			".  Enter 'b' : backtrack to last room                         .\n" +
			".  Enter 'x' : examines the room                              .\n" +
			".  Enter 'i' : info on your current status                    .\n" +
			".  Enter 'p' : pray to get divine help *if you're spiritual*  .\n" +
			".                                                             .\n" +
			".  Enter 't' + 'all' or 'item name': takes an item(s)         .\n" + 
			".  Enter 'd' + 'all' or 'item name': drops an item(s)         .\n" +
			".  Enter 'u' + 'item name': uses an item                      .\n" +
			".  Enter 'y' + 'any word': yells something out loud           .\n" +
			".                                                             .\n" +
			". . . . . . . . . . . . . . . . . . . . . . . . . . . . . . . ."
		);
	}
	
	/**
	 * Method looks at player's current location 
	 *
	 * @param dir the desired direction
	 * @return the location number player is at
	 */
	static int from(int dir){
		int locId = currentPlayer.location;
		return MAP[locId][dir];
	}
	
	/**
	 * Method that moves player based on where they are located at
	 *
	 * @param direction the desired locaton
	 */
	static void move(int direction) {
		int nextLoc = from(direction);
		
		if (!(nextLoc ==-1) && LOCALES[nextLoc] instanceof SecureLocale && LOCALES[nextLoc] == LOCALES[9]) {
			if (SecureLocale.canEnter(currentPlayer, calculator)) {
				currentPlayer.location = nextLoc;
				LOCALES[currentPlayer.location].visitCount++;
				playerTrail.dropCrumb(currentPlayer.location);
				System.out.println("The door suddenly opened allowing me to access the next room");
				System.out.println(HouseOfQuestions.locToScene());
			} else {
				System.out.println("\nThe door leading to the next room wont budge" + "\nMaybe I need to have an item...");
				System.out.println("You are in the " + LOCALES[currentPlayer.location].name);
			}
		} else if (!(nextLoc ==-1) && LOCALES[nextLoc] == LOCALES[2]) {
			if (SecureLocale.canEnter(SECURELOCS)) {
				currentPlayer.location = nextLoc;
				LOCALES[currentPlayer.location].visitCount++;
				playerTrail.dropCrumb(currentPlayer.location);
				System.out.println("The wall magically dissapeared before your eyes, allowing you to enter the room");
				System.out.println(HouseOfQuestions.locToScene());
			} else {
				System.out.println("\nThere is a wall blocking your path");
				System.out.println("*You are in the " + LOCALES[currentPlayer.location].name + "*");
			}
		} else if (!(nextLoc ==-1)) {
			currentPlayer.location = nextLoc;
			LOCALES[currentPlayer.location].visitCount++;
			playerTrail.dropCrumb(currentPlayer.location);
			currentPlayer.actionCount--;
			System.out.println(HouseOfQuestions.locToScene());
		} else {
			System.out.println("Cannot go this way... Choose another path");
			System.out.println("You are in the " + LOCALES[currentPlayer.location].name);
		}
	}
	
	/**
	 * Method uses a stack interface in order for player to backtrack
	 *
	 * @param trail the BreadcrumbTrail
	 */
	static void back(BreadcrumbTrail trail) {
		if (trail.hasMoreCrumbs() && trail.currentCrumb() > 0) {
			trail.pickupCrumb();
			if (trail.currentCrumb() >= 0) {
				currentPlayer.actionCount--;
				currentPlayer.location = trail.currentCrumb();
				System.out.println("\n" + HouseOfQuestions.locToScene());
			}
		} else {	
			System.out.println("\nAlready reached your first crumb");
			System.out.println("*You are in the " + LOCALES[currentPlayer.location].name + "*");
		}
	}
	
	/**
	 * Method that checks to see if player has visited every location, and if so, player can choose victory
	 *
	 * @return true, if successful
	 */
	static boolean visitVictoryCheck() {
		int locsVisited = 0;
		
		for (int i = 0; i < LOCALES.length; i++) {
			if (LOCALES[i].visitCount != 0) {
				locsVisited++;
			}		
		}
		
		if (locsVisited == LOCALES.length-1) {
			System.out.println("*********************************************************");
			System.out.println("You have visited every location of the House Of Questions");
			System.out.print("Do you want to leave the house and be done with it?" +"\nEnter Yes or No: ");
				
			while (true) {
				userInput = inputSource.nextLine().trim().toUpperCase();
				if (userInput.equals("YES")) {
					System.out.println("\n" +"CONGRATULATIONS"  + "\nYou have won the game via visiting every location.");
					return true;
				} else if (userInput.equals("NO")) {
					System.out.println("*********************************************************");
					break;
				} else {
					System.out.print("Not a valid command\n" + "Yes or No?");
					continue;
				}
			}
		}
	
		return false;
	}
	
	/**
	 * Method that checks if player has answered final question correctly
	 *
	 * @return true, if successful
	 */
	static boolean questionVictoryCheck() {
		if (LOCALES[currentPlayer.location].equals(LOCALES[2])) {
			System.out.print("Final Question: Did you like this game?: ");
			
			while (true) {
				userInput = inputSource.nextLine().trim().toUpperCase();
				if (userInput.equals("YES")) {
					System.out.println("YOU " + currentPlayer.name.toUpperCase() + " WIN!!!!!!!!!\n\n\n");
					return true;
				} else if (userInput.equals("NO")) {
					System.out.println("Keep playing");
					break;
				} else {
					System.out.println("Not a valid answer");
					continue;
				}
			}
		}
		return false;
	}
	
	/**
	 * Method checks to see if player has taken too many steps, and if so, the player loses.
	 *
	 * @return true, if successful
	 */
	static boolean outOfActions() {
		if (currentPlayer.actionCount == 0) {
			System.out.println("\n" +"The number on your right hand turned to 0...." + "\nYOU DIED....");
			return true;
		}
		return false;
	}
	
	/**
	 * Method checks to see if player has guessed too many times when answering questions, if so, the player loses
	 *
	 * @return true, if successful
	 */
	static boolean outOfGuesses() {
		if (currentPlayer.answerCount == 0) {
			System.out.println("\n" +"The number on your left hand turned to 0...." + "\nYOU DIED....");
			return true;
		}
		return false;
	}
	
	/**
	 * Method creates instances of Item and sets them in their proper location
	 */
	static void setItems() {
		LOCALES[0].placeItems(map);
		LOCALES[1].placeItems(guitar);
		LOCALES[3].placeItems(calculator);
		LOCALES[4].items.add(bottle);
		LOCALES[5].placeItems(batteries);
		LOCALES[6].placeItems(beard);
		LOCALES[7].placeItems(painting);
		LOCALES[8].placeItems(novel);
		LOCALES[9].placeItems(textbook);
	}
	
	/**
	 * Method that sets the questions and answers of some SecureLocale
	 */
	static void setQuizAndAnswers() {
		SECURELOCS[0].setQuestion("What genre of music does Michael Jackson perform?: ");
		SECURELOCS[0].setAnswer("POP");
		SECURELOCS[1].setQuestion("What is 761 − 347?: ");
		SECURELOCS[1].setAnswer("414");
		SECURELOCS[2].setQuestion("What eye color is typically dominant in humans?: ");
		SECURELOCS[2].setAnswer("BROWN");
		SECURELOCS[3].setQuestion("Who is Socrates's famous student: ");
		SECURELOCS[3].setAnswer("PLATO");
		SECURELOCS[4].setQuestion("Currently, how many amendments are in the U.S. constitution?: ");
		SECURELOCS[4].setAnswer("27");
		SECURELOCS[5].setQuestion("What is the word that is defined as 'a time of intense difficulty': ");
		SECURELOCS[5].setAnswer("CRISIS");
		SECURELOCS[6].setQuestion("In what city is the Statue of David located at?: ");
		SECURELOCS[6].setAnswer("FLORENCE");
		
	}
	
	/**
	 * Method that starts the game
	 */
	public static void gameStart() {
		// Continue if player does not quit before games truly begins
		if (!(gameIntro())) {
			return;
		}
		setItems();
		setQuizAndAnswers();
		LOCALES[currentPlayer.location].visitCount++;
		playerTrail.dropCrumb(currentPlayer.location);
		
		// Main game loop, when player loses or quits the loop is broken
		while (true) {
			// Victory conditions and Lose conditions are checked
			if (visitVictoryCheck()) {
				break;
			} else if (outOfActions()) {
				break;
			} else if (questionVictoryCheck()) {
				break;
			} else if (outOfGuesses()) {
				break;
			}
			
			System.out.println("****************************");
			System.out.println("Number on right hand: " + currentPlayer.actionCount + "\n");
			System.out.print("What should I do?: ");
			userInput = inputSource.nextLine().trim().toUpperCase();
			String[] inputSplit = userInput.split(" ");
			
			// Determines what to do if player inputs certain commands
			if (userInput.equals("N")) {
				move(N);
			} else if (userInput.equals("S")) {
				move(S);
			} else if (userInput.equals("W")) {
				move(W);
			} else if (userInput.equals("E")) {
				move(E);	
			} else if (userInput.equals("B")) {
				back(playerTrail);
			} else if (userInput.equals("X")) {
				Player.examine(currentPlayer,LOCALES);
			} else if (userInput.equals("P")) {
				Player.actionCheck(currentPlayer, LOCALES, SECURELOCS);
			} else if (userInput.equals("M")) {
				displayMap();
			} else if (userInput.equals("I")) {
				System.out.println(currentPlayer);;
			} else if (userInput.equals("H")) {
				showHelp();
			} else if (userInput.equals("Q")) {
				break;
				
			} else if (inputSplit[0].equals("T")) {
				if (inputSplit.length == 1) {
					System.out.println("\nWhat did you want to take?");
				} else {
					Player.take(currentPlayer, LOCALES[currentPlayer.location], inputSplit);
				}
				
			} else if (inputSplit[0].equals("D")) {
				if (inputSplit.length == 1) {
					System.out.println("\nWhat did you want to drop?");
				} else {
					Player.drop(currentPlayer, LOCALES[currentPlayer.location], inputSplit);
				}
				
			} else if (inputSplit[0].equals("U")) {
				if (inputSplit.length == 1) {
					System.out.println("\nWhat did you want to use?");
				} else if (Item.hasLimitedItem(currentPlayer)) {
					Player.use(currentPlayer, LOCALES, Item.returnLimitedItem(currentPlayer), inputSplit);
				} else {
					System.out.println("\nNo item in your inventory that can be used");
				}
				
			} else if (inputSplit[0].equals("Y")) {
				if (inputSplit.length == 1) {
					System.out.println("\nWhat did you want to yell?"); 
				} else {
					Player.yell(currentPlayer,LOCALES, inputSplit);
				}
				
			} else {
				System.out.println("Not a valid comman\n");
				continue;
			}	
			System.out.println("");
		}
	} 
	
	/**
	 * Method that handles the game's introduction
	 *
	 * @return true, if player wishes to start the game
	 */
	static boolean gameIntro(){
		// Player can choose to quit game before it starts by entering 'Q', else continue with 'C'
		String gameStart;
		boolean continueGame;
		
		System.out.println("\n"+"House of Questions");
		System.out.println("------------------");
		showHelp();
		
		while(true) {
			System.out.print("Enter C to continue or Q to quit: ");
			gameStart = inputSource.nextLine().trim().toUpperCase();
			
			if (gameStart.equals("C")) {
				continueGame = true;
				break;
			} else if(gameStart.equals("Q")) {
				continueGame = false;
				break;
			} else {
				System.out.println("\nPlease enter C or Q...");
				continue;
			}
		}
		
		if (continueGame == false) {
			return continueGame;
		}
		
		// Captures Player name, and trait
		Player.setUserName(currentPlayer, inputSource);
		Player.setUserTrait(currentPlayer, inputSource);
		System.out.println("\n****************************");
		System.out.println("\nHello " + currentPlayer.name + ",\n\nYou wake up to find yourself inside of the "+
			"'House of Questions'\n"+"Nothing else to do but explore...\n");
		System.out.println("On your right hand you see the number " + currentPlayer.actionCount + " branded on your skin");
		System.out.println("On your left hand you see the number " + currentPlayer.actionCount + " branded on your skin");
		System.out.println("Game Note: Enter 'h' for a list of commands\n");
		System.out.println(locToScene());
		return continueGame;
	}
	
	/**
	 * Method that displays the game credits
	 */
	// This method end the game and displays the game credits
	static void gameCredits() {
		System.out.println("\nThank you for playing :)");
		System.out.println("\nCopyright Michael Gutierrez");
		System.out.println("===========================");
		System.out.println("Under the supervision of Professor Johnson");
		inputSource.close();
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		gameStart();
		gameCredits();
	}	
}
