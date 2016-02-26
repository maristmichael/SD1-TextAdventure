/**
 * @author Michael Gutierrez
 * CMPT 220L-114
 * Professor Johnson
 * 9 February 2016
 *
 */

import java.util.Scanner;

public class HouseOfQuestions {
	
	// Constant Variables representing directions for matrix
	public static final int N = 0;
	public static final int S = 1;
	public static final int W = 2;
	public static final int E = 3;

	// Array containing location Descriptions
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
	
	public static String[] allItems = {
		"Area Map",
		"Guitar",
		"Calculator",
		"Beaker",
		"Plato's Manuscripts",
		"Starry Night Painting",
		"Great Gatsby",
		"U.S. History Book"
	};
	
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
	
	// Locale Array with instances of locations
	final static Locale[] LOCALES = {
		new Locale("Starting Room", LocDescrip[0],allItems[0]),
		new Locale("Music Room", LocDescrip[1],allItems[1]),
		new Locale("Math Room", LocDescrip[2], allItems[2]),
		new Locale("Science Room", LocDescrip[3], allItems[3]),
		new Locale("Philosophy Room", LocDescrip[4], allItems[4]),
		new Locale("Art Room", LocDescrip[5], allItems[5]),
		new Locale("English Room", LocDescrip[6], allItems[6]),
		new Locale("History Room", LocDescrip[7], allItems[7])
	};
	
	// Navigation matrix
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
	
	// This is the instance of the Player object 
	static Player currentPlayer = new Player("", 0);
	
	
	// Variables handle input by user
	static Scanner inputSource = new Scanner(System.in);
	static String userInput;
	
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
	
	// This method allow's player to grab items and store them in the inventory
	static void take(){
		int maxCount = 1;
		if (LOCALES[currentPlayer.location].item == "") {
			System.out.print("No items to find");
		} else {
			for (int i = 0; i< maxCount; i++){
				currentPlayer.inventory[i] = LOCALES[currentPlayer.location].item;
				System.out.println("You picked up a(n): " + LOCALES[currentPlayer.location].item);
				currentPlayer.score += 5;
				System.out.println("Score +5");
				System.out.println("Your score is: " + currentPlayer.score);;
				LOCALES[currentPlayer.location].item = "";
			}
		}
	}
	
	// This method displays the game map if player has obtained the map
	static void map(){
		if (LOCALES[0].item.equals("")) { 
			System.out.println("");
			System.out.println(gameMap);
		} else {
			System.out.println("You do not have a map yet");
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
		} else {
			System.out.println("Cannot go this way... Choose another path");
		}
		System.out.println(HouseOfQuestions.locToScene());
	}
	
	// Function that starts the game loop
	static void gameStart() {
		String locationScene = "";
		
		while (true) {
			// User input that is case-insensitive
			System.out.print("Where should I go?: ");
			userInput = inputSource.nextLine().trim().toUpperCase();

			// Game loops until user quits
			if (userInput.equals("N")) {
				HouseOfQuestions.move(N);
			} else if (userInput.equals("S")) {
				HouseOfQuestions.move(S);
			} else if (userInput.equals("W")) {
				HouseOfQuestions.move(W);
			} else if (userInput.equals("E")) {
				HouseOfQuestions.move(E);	
			} else if (userInput.equals("T")) {
				HouseOfQuestions.take();
			} else if (userInput.equals("M")) {
				HouseOfQuestions.map();
			} else if (userInput.equals("H")) {
				locationScene = "Explore by typing in 'n', 's', 'e', 'w'\n" + 
				"Type in 'q' to quit the game.\n" + 
				"Type in 't' to take an item that may be in a room" + 
				"Type in 'm' to display the game map if you found it";
			} else if (userInput.equals("Q")) {
				break;
			}else {
				System.out.println("Not a valid command");
				continue;
			}	
			System.out.println(locationScene);
		}
	} 
	
	// Function that displays welcome message and captures player's name
	static void gameIntro(){
		String enteredName;
		
		System.out.println("\n"+"House of Questions");
		System.out.println("------------------");
		System.out.print("\n" + "What's your name?: ");
		enteredName = inputSource.nextLine();
		currentPlayer.name = enteredName;
		System.out.println("\nYou, " + currentPlayer.name + ", wake up to find yourself inside of the "+
				"'House of Questions'\n"+"Nothing else to do but explore...\n");
		System.out.println(HouseOfQuestions.locToScene());
	}
	
	// Function that displays credits
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
