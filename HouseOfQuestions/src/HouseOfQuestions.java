/**
 * @author Michael Gutierrez
 * CMPT 220L-114
 * Professor Johnson
 * 25 February 2016
 *
 */

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HouseOfQuestions {
	
	// These are constant variables representing directions for matrix
	public static final int N = 0;
	public static final int S = 1;
	public static final int W = 2;
	public static final int E = 3;

	// This is a String array containing Locale descriptions
	public static final String[] LocDescrip = {
		"This is the starting area, there are several paths to take",
		"In this room, you hear many melodies emanating from the walls.",
		"This is a room that has walls written with math equations",
		"You enter a room littered with many scientific journals and books",
		"This room contains many philosophical rhetoric inscribed on the walls",
		"What a beautiful room! It has many works of art scattered around",
		"You enter a room with many dictionaries and novels all stacked neatly",
		"You are now in a room with a giant globe with many history books surrounding it"
		};
	
	// This is a Locale array with instances of locations
	public final static Locale[] LOCALES = {
		new Locale("Starting Room", LocDescrip[0]),
		new Locale("Music Room", LocDescrip[1]),
		new Locale("Math Room", LocDescrip[2]),
		new Locale("Science Room", LocDescrip[3]),
		new Locale("Philosophy Room", LocDescrip[4]),
		new Locale("Art Room", LocDescrip[5]),
		new Locale("English Room", LocDescrip[6]),
		new Locale("History Room", LocDescrip[7])
	};
	
	// This is the navigation matrix
	public final static int [][] MAP = {
			  /*{N,S,W,E}*/
		/*0*/	{1,-1,2,5},  // From Start --> Math(W), Music(N), or Art(E)
	 	/*1*/	{-1,0,-1,-1},// From Music --> Start(S)
	 	/*2*/	{-1,3,-1,0}, // From Math --> Start(E), or Science(S)
	 	/*3*/	{2,4,-1,-1}, // From Science --> Math(N), or Philosophy(S)
	 	/*4*/	{3,-1,-1,-1},// From Philosophy --> Science(N)
	 	/*5*/	{6,-1,0,-1}, // From Art --> English (N), or Start(E)
	 	/*6*/	{7,5,-1,-1}, // From English --> History(N), or Art(S)
	 	/*7*/	{-1,6,-1,-1} // From History --> English(S)
	};
	
	// This is a String variable holding the game map
	public static String gameMap = 
		"                    History \n" +
		"                       |    \n" +
		"                       |    \n" +
		"          Music     English \n" +
		"            |          |    \n" +
		"            |          |    \n" +
		"   Math----Start------Art   \n" +
		"     |                      \n" +
		"     |                      \n" +
		"   Science                  \n" +
		"     |                      \n" +
		"     |                      \n" +
		"   Philosophy               \n";
	
	// This is the instance of the Player object 
	static Player currentPlayer = new Player("", 0);
	static BreadcrumbTrail playerTrail = new BreadcrumbTrail();
	
	// These variables handle input by user
	public static Scanner inputSource = new Scanner(System.in);
	public static String userInput;
	
	
	// This method looks at player's location and displays appropriate description
	static String locToScene() {
		int locNum = currentPlayer.location;
		return LOCALES[locNum].toString();
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
	static void map(){
		if (currentPlayer.inventory.size() != 0) {
			String map = "map";
			for (int i = 0; i < currentPlayer.inventory.size(); i++){
				if (currentPlayer.inventory.get(i).name.equals(map)) {
					System.out.println();
					System.out.println(gameMap);
				} else {
					System.out.println("You do not have a map yet");
				}
			}
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
		
		if (!(nextLoc ==-1)) {
			currentPlayer.location = nextLoc;
			LOCALES[currentPlayer.location].visitCount++;
			playerTrail.dropCrumb(currentPlayer.location);
			currentPlayer.actionCount--;
			System.out.println("You dropped a crumb");
		} else {
			System.out.println("Cannot go this way... Choose another path");
		}
		System.out.println("\n"+HouseOfQuestions.locToScene());
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
	static void setItems(){
		LOCALES[0].placeItems("map", "A map of the house", "You spot a map on the floor");
		LOCALES[1].placeItems("guitar", "A nifty acoustic guitar", "You found a cool guitar");
		LOCALES[2].placeItems("calculator","A calculator used for math classes", "You spot a nice calculator");
		LOCALES[3].placeItems("beaker", "A beaker for measurement", "You see a fancy beaker on a lab table");
		LOCALES[5].placeItems("painting", "Van Gogh's famous famous painting","A familiar painting catches your eyes");
		LOCALES[6].placeItems("novel", "Great Gatsby, a famous book by F.Scott Fitzgerald", "You find your favorite novel of all time");
		LOCALES[7].placeItems("textook", "A thick book containing U.S. history", "You see a big and textbook on the table");
	}
	
	// This method starts the game loop
	public static void gameStart() {
		String locationScene = "";
		HouseOfQuestions.setItems();
		LOCALES[currentPlayer.location].visitCount++;
		LimitedUseItem bottle = new LimitedUseItem("bottle", "A water bottle", "You see a bottle", 1, "No water left");
		LOCALES[4].items.add(bottle);
		playerTrail.dropCrumb(currentPlayer.location);
		System.out.println("You dropped a breadcrumb to make a trail if you get lost\n");
		
		
		while (true) {
			// User input that is case-insensitive
			visitVictoryCheck();
			outOfActions();
			System.out.println("Move count: " + currentPlayer.actionCount);
			System.out.print("What should I do?: ");
			userInput = inputSource.nextLine().trim().toUpperCase();
			String[] inputSplit = userInput.split(" ");
			
			// Game loops until user quits
			if (userInput.equals("N")) {
				HouseOfQuestions.move(N);
			} else if (userInput.equals("S")) {
				HouseOfQuestions.move(S);
			} else if (userInput.equals("W")) {
				HouseOfQuestions.move(W);
			} else if (userInput.equals("E")) {
				HouseOfQuestions.move(E);	
			} else if (userInput.equals("B")) {
				HouseOfQuestions.back(playerTrail);
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
					System.out.println("What did you want to use?");
				} else {
					Player.use(currentPlayer, bottle, inputSplit);
				}
			} else if (userInput.equals("X")) {
				Player.examine(currentPlayer,LOCALES[currentPlayer.location]);
			} else if (userInput.equals("M")) {
				HouseOfQuestions.map();
			} else if (userInput.equals("H")) {
				locationScene = "\nExplore by entering 'n', 's', 'e', 'w'\n" + 
				"Enter 'q' to quit the game.\n" + 
				"Enter 't' to take all items that may be in a room.\n" + 
				"Enter 'd' to drop an item by entering the exact name of it.\n" +
				"Enter 'm' to display the game map if you found it.\n" + 
				"Enter 'b' to follow your crumb trail back to a previos room\n";
			} else if (userInput.equals("Q")) {
				break;
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
