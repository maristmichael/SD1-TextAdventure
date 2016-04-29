/**
 * @author Michael Gutierrez
 * CMPT 220L-114
 * Professor Johnson
 * 25 February 2016
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HouseOfQuestions {

 	static Item map        = new Item("map", "A map of the house", "You spot a map on the floor");
	static Item guitar     = new Item("guitar", "A nifty acoustic guitar", "You found a cool guitar");
	static Item calculator = new Item("calculator","A calculator used for math classes", "You spot a nice calculator");
	static Item beaker     = new Item("beaker", "A beaker for measurement", "You see a fancy beaker on a lab table");
	static Item painting   = new Item("painting", "Van Gogh's famous famous painting","A familiar painting catches your eyes");
	static Item novel      = new Item("novel", "Great Gatsby, a famous book by F.Scott Fitzgerald", "You find your favorite novel of all time");
	static Item textbook   = new Item("textbook", "A thick book containing U.S. history", "You see a big and textbook on the table");
	static final LimitedUseItem bottle = new LimitedUseItem("bottle", "A water bottle", "You see a bottle", 1, "No water left");
	
	// These are constant variables representing directions for matrix
	public static final int N = 0;
	public static final int S = 1;
	public static final int W = 2;
	public static final int E = 3;

	// This is a String array containing Locale descriptions
	public static final String[] LocDescrip = {
		"This is the starting area, there are several paths to take",
		"In this room, you hear many melodies emanating from the walls",
		"You have entered the secret room, its wall completely white and has " + 
				"a wall inscribed with the answers to the questions you answered",
		"This is a room that has walls written with math equations",
		"Suprisingly you found the kitchen though it doesn't look like it has much to eat",
		"You enter a room littered with many scientific journals and books",
		"This room contains many philosophical rhetoric inscribed on the walls",
		"What a beautiful room! It has many works of art scattered around",
		"You enter a room with many dictionaries and novels all stacked neatly" + 
				"\nThe next path is closed off by a metal door inscribed with numbers with no way to open it by hand",
		"You are now in a room with a giant globe with many history books surrounding it"
	};
	
	// This is a Locale array with instances of locations
	public final static Locale[] LOCALES = {
		new Locale("Starting Room", LocDescrip[0], false),
		new Locale("Music Room", LocDescrip[1], false),
		new SecureLocale("Result Room", LocDescrip[2], true),
		new Locale("Math Room", LocDescrip[3], false),
		new Locale("Kitchen", LocDescrip[4], true),
		new Locale("Science Room", LocDescrip[5], false),
		new Locale("Philosophy Room", LocDescrip[6], false),
		new Locale("Art Room", LocDescrip[7], false),
		new Locale("English Room", LocDescrip[8], false),
		new SecureLocale("History Room", LocDescrip[9], false)
	};
	
	// This is the navigation matrix
	public final static int [][] MAP = {
			  /*{N,S,W,E}*/
		/*0*/	{1,2,3,7},   // From Start   --> Math(W), Music(N), Art(E), Result(S)
	 	/*1*/	{-1,0,-1,-1},// From Music   --> Start(S)
	 	/*2*/	{0,-1,-1,-1},// From Result  --> Start(N)
	 	/*3*/	{4,5,-1,0}, // From Math    --> Start(E), Kitchen(N), Science(S)
	 	/*4*/	{-1,3,-1,-1},// From Kitchen --> Math(S)
	 	/*5*/	{3,6,-1,-1}, // From Science --> Math(N), Philosophy(S)
	 	/*6*/	{5,-1,-1,-1},// From Philosophy --> Science(N)
	 	/*7*/	{8,-1,0,-1}, // From Art     --> English (N), Start(E)
	 	/*8*/	{9,7,-1,-1}, // From English --> History(N), Art(S)
	 	/*9*/	{-1,8,-1,-1} // From History --> English(S)
	};
	
	// This is a String variable holding the game map
	public static String gameMap = 
		"                     History \n" +
		"                         |   \n" +
		"                         |   \n" +
		"  Kitchen     Music  English \n" +
		"     |         |         |   \n" +
		"     |         |         |   \n" +
		"   Math------Start------Art  \n" +
		"     |         |             \n" +
		"     |         |             \n" +
		"  Science     Result         \n" +
		"     |                       \n" +
		"     |                       \n" +
		"   Philosophy                \n";
	
	// This is the instance of the Player object 
	static Player currentPlayer = new Player("", 0);
	static BreadcrumbTrail playerTrail = new BreadcrumbTrail();
	
	// These variables handle input by user
	public static Scanner inputSource = new Scanner(System.in);
	public static String userInput;
	
	// This method looks at player's location and displays appropriate description
	static String locToScene() {
		return LOCALES[currentPlayer.location].toString();
	}
	
	// This method converts the directions into integers to navigate the matrix
	static int dirToInt(String direction) {
		if (direction.equals("N")) {
			return 0;
		} else if (direction.equals("S")) {
			return 1;
		} else if (direction.equals("W")) {
			return 2;
		} else {
			return 3;
		}
	}
	
	// This method displays the game map if player has obtained the map
	static void showMap(){
		if (currentPlayer.inventory.contains(map)) {
					System.out.println("\n" + gameMap);
		} else {
			System.out.println("You do not have a map");
		}
	}
	
	// This method looks at player's current location 
	static int from(int dir){
		int locId = currentPlayer.location;
		return MAP[locId][dir];
	}
	
	// This method moves player based on where they are at
	static void move(int direction) {
		int nextLoc = from(direction);
		
		if (!(nextLoc ==-1) && LOCALES[nextLoc] instanceof SecureLocale) {
			if (SecureLocale.canEnter(currentPlayer, calculator)) {
				LOCALES[currentPlayer.location].visitCount++;
				currentPlayer.location = nextLoc;
				System.out.println("The door suddenly opened allowing me to access the next room");
				System.out.println("\n"+HouseOfQuestions.locToScene());
			} else {
				System.out.println("\nThe door leading to the next room wont budge" + "\nMaybe I need to have an item...");
				System.out.println("You are in the " + LOCALES[currentPlayer.location].name);
			}
			
		} else if (!(nextLoc ==-1)) {
			currentPlayer.location = nextLoc;
			LOCALES[currentPlayer.location].visitCount++;
			playerTrail.dropCrumb(currentPlayer.location);
			currentPlayer.actionCount--;
			System.out.println("\n"+HouseOfQuestions.locToScene());
		} else {
			System.out.println("Cannot go this way... Choose another path");
			System.out.println("You are in the " + LOCALES[currentPlayer.location].name);
		}
	}
	
	// This method uses a stack interface in order for player to back track
	static void back(BreadcrumbTrail trail) {
		if (trail.hasMoreCrumbs()) {
			trail.pickupCrumb();
			if (trail.currentCrumb() >= 0) {
				currentPlayer.actionCount--;
				currentPlayer.location = trail.currentCrumb();
				System.out.println("You followed your breadcrumb trail back a room");
				System.out.println("\n" + HouseOfQuestions.locToScene());
			} else {
				currentPlayer.location = 0;
				trail.dropCrumb(currentPlayer.location);
				System.out.println("Your already at your final crumb on the trail you made.\n");
				System.out.println("You are in the " + LOCALES[currentPlayer.location].name);
			}
		}
	}
	
	// This method checks to see if player has visited every location, and if so, player can choose victory.
	static void visitVictoryCheck() {
		int locsVisited = 0;
		
		for (int i = 0; i < LOCALES.length; i++) {
			if (LOCALES[i].visitCount != 0) {
				locsVisited++;
			}		
		}
		
		if (currentPlayer.ignoreVisitVictory == false) {
			if (locsVisited == LOCALES.length) {
				System.out.println("You have visited every location of the House Of Questions");
				System.out.print("Do you want to leave the house and be done with it?" +"\nY or N?: ");
				
				while (true) {
					userInput = inputSource.nextLine().trim().toUpperCase();
					if (userInput.equals("Y")) {
						System.out.println("\n" +"CONGRATULATIONS"  + "\nYou have won the game via visiting every location.");
						System.out.println("\nCopyright Michael Gutierrez");
						System.out.println("===========================");
						System.out.println("Under the supervision of Professor Johnson");
						inputSource.close();
					} else if (userInput.equals("N")) {
						currentPlayer.ignoreVisitVictory = true;
						System.out.println("");
						break;
					} else {
						System.out.print("Not a valid command\n" + "Y or N?");
						continue;
					}
				}
			}
		}
	}
	
	// This method checks to see if player's action count is zero, and if so, the player loses.
	static void outOfActions() {
		if (currentPlayer.actionCount == 0) {
			System.out.println("\n" +"Unfortunately you have ran out of actions to take." + "\nYOU LOSE :(");
			System.out.println("\nCopyright Michael Gutierrez");
			System.out.println("===========================");
			System.out.println("Under the supervision of Professor Johnson");
			userInput = inputSource.nextLine();
			inputSource.close();
		}
	}
	
	// This method creates instances of Item and sets them in their proper location
	static void setItems() {
		LOCALES[0].placeItems(map);
		LOCALES[1].placeItems(guitar);
		LOCALES[3].placeItems(calculator);
		LOCALES[4].items.add(bottle);
		LOCALES[5].placeItems(beaker);
		LOCALES[7].placeItems(painting);
		LOCALES[8].placeItems(novel);
		LOCALES[9].placeItems(textbook);
	}
	
	// This method starts the game loop
	public static void gameStart() {
		String locationScene = "";
		setItems();
		LOCALES[currentPlayer.location].visitCount++;
		playerTrail.dropCrumb(currentPlayer.location);
		
		while (true) {
			
			// User input that is case-insensitive
			visitVictoryCheck();
			outOfActions();
			System.out.println("Move count: " + currentPlayer.actionCount + "\n");
			System.out.println("**************************");
			System.out.print("What should I do?: ");
			userInput = inputSource.nextLine().trim().toUpperCase();
			String[] inputSplit = userInput.split(" ");
			
			// Game loops until user quits
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
				Player.examine(currentPlayer,LOCALES[currentPlayer.location]);
			} else if (userInput.equals("M")) {
				showMap();
			} else if (userInput.equals("H")) {
				locationScene = "\nExplore by entering 'n', 's', 'e', 'w'\n" + 
				"Enter 'q' to quit the game.\n" + 
				"Enter 't' to take all items that may be in a room.\n" + 
				"Enter 'd' to drop an item by entering the exact name of it.\n" +
				"Enter 'm' to display the game map if you found it.\n" + 
				"Enter 'b' to follow your crumb trail back to a previos room\n";
			} else if (userInput.equals("Q")) {
				break;
				
			} else if (inputSplit[0].equals("T")) {
				if (inputSplit.length == 1) {
					System.out.println("What did you want to take?");
				} else {
					Player.take(currentPlayer, LOCALES[currentPlayer.location], inputSplit);
				}
				
			} else if (inputSplit[0].equals("D")) {
				if (inputSplit.length == 1) {
					System.out.println("What did you want to drop?");
				} else {
					Player.drop(currentPlayer, LOCALES[currentPlayer.location], inputSplit);
				}
				
			} else if (inputSplit[0].equals("U")) {
				if (inputSplit.length == 1) {
					System.out.println("\nWhat did you want to use?");
				} else if (Item.hasLimitedItem(currentPlayer)) {
					Player.use(currentPlayer, Item.returnLimitedItem(currentPlayer), inputSplit);
				} else {
					System.out.println("\nNo item in your inventory that can be used");
				}
				
			} else {
				System.out.println("Not a valid comman\n");
				continue;
			}	
			System.out.println(locationScene);
			locationScene = "";
		}
	} 
	
	// This method displays the welcome message and captures player's name
	static void gameIntro(){
		String enteredName;
		
		System.out.println("\n"+"House of Questions");
		System.out.println("------------------");
		System.out.print("\n" + "What's your name?: ");
		enteredName = inputSource.nextLine();
		currentPlayer.name = enteredName;
		System.out.println("\nYou, " + currentPlayer.name + ", wake up to find yourself inside of the "+
			"'House of Questions'\n"+"Nothing else to do but explore...\n");
		System.out.println("You have a limited amount of moves you can use\n");
		System.out.println(HouseOfQuestions.locToScene() + "\n");
	}
	
	// This method displays the game credits
	static void gameCredits() {
		System.out.println("\n" +"Quitting game... ;(\n" + "Thank you for playing :)");
		System.out.println("\nCopyright Michael Gutierrez");
		System.out.println("===========================");
		System.out.println("Under the supervision of Professor Johnson");
	}
	
	public static void main(String[] args) {
		HouseOfQuestions.gameIntro();
		HouseOfQuestions.gameStart();
		HouseOfQuestions.gameCredits();
		inputSource.close();
	}	
}
