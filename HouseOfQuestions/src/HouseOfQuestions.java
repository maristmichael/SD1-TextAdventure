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
	
	// Navigation matrix
	public final int [][] MAP = {
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
	
	// Array containing location Descriptions
	static final String[] LocDescrip = {
			"This is the starting area, there are several paths to take",
			"In this room, you hear many melodies emanating from the walls.",
			"This is a room that has walls written with math equations",
			"You enter a room littered with many scientific journals and books",
			"This room contains many philosophical rhetoric inscribed on the walls",
			"What a beautiful room! It has many works of art scattered around",
			"You enter a room with many dictionaries and novels all stacked neatly",
			"You are now in a room with a giant globe with many history books surrounding it"
		};
	
	public static final Object [][] LOCATIONS = {
		Locale Start      = new Locale("Starting Room", LocDescrip[0],""),
		Locale Music      = new Locale("Music Room", LocDescrip[1],"Guitar"),
		Locale Math       = new Locale("Math Room", LocDescrip[2], "Calculator"),
		Locale Science    = new Locale("Science Room", LocDescrip[3], "Beaker"),
		Locale Philosophy = new Locale("Philosophy Room", LocDescrip[4], "Plato's manuscrips"),
		Locale Art        = new Locale("Art Room", LocDescrip[5], "Starry Night"),
		Locale English    = new Locale("English Room", LocDescrip[6], "Great Gatsby"),
		Locale History    = new Locale("History Room", LocDescrip[7], "U.S. History Book")
	};

	// Variables handle input by user
	static Scanner inputSource = new Scanner(System.in);
	static String userInput;
	
	// Function that starts the game loop
	static void gameStart() {
		
	/* while (true) {
			// User input that is case-insensitive
			System.out.print("Where should I go?: ");
			userInput = inputSource.nextLine().toUpperCase();
			
			// Game loops until user quits
			if (userInput.equals("N") && playerLocation == 4) {
				playerLocation = 5;
				locationScene = LOCATIONS[4];	
			} else if (userInput.equals("N") && playerLocation == 5) {
				playerLocation = 6;
				locationScene = LOCATIONS[5];
			} else if (userInput.equals("N") && playerLocation == 2) {
				playerLocation = 1;
				locationScene = LOCATIONS[0];
			} else if (userInput.equals("N") && playerLocation == 3) {
				playerLocation = 2;
				locationScene = LOCATIONS[1];
			} else if (userInput.equals("S") && playerLocation == 1) {
				playerLocation = 2;
				locationScene = LOCATIONS[1];
			} else if (userInput.equals("S") && playerLocation == 2) {
				playerLocation = 3;
				locationScene = LOCATIONS[2];
			} else if (userInput.equals("S") && playerLocation == 6) {
				playerLocation = 5;
				locationScene = LOCATIONS[4];
			} else if (userInput.equals("S") && playerLocation == 5) {
				playerLocation = 4;
				locationScene = LOCATIONS[3];
			} else if (userInput.equals("E") && playerLocation == 0) {
				playerLocation = 4;
				locationScene = LOCATIONS[3];
			} else if (userInput.equals("E") && playerLocation == 1) {
				playerLocation = 0;
				locationScene = "We are back where we woke up";
			} else if (userInput.equals("W") && playerLocation == 0) {
				playerLocation = 1;
				locationScene = LOCATIONS[0];
			} else if (userInput.equals("W") && playerLocation == 4) {
				playerLocation = 0;
				locationScene = "We are back where we woke up";
			} else if (userInput.equals("Q")) {
				break;
			} else if (userInput.equals("H")) {
				locationScene = "Explore by typing in 'n', 's', 'e', 'w'\n" + 
				"Type in 'q' to quit the game.\n";
			}else {
				System.out.println("That is a wall, try something else");
				continue;
			}	
			System.out.println(locationScene);
		} */
	} 
	
	// Function that displays welcome message and captures player's name
	static void gameIntro(){
		System.out.println("\n"+"House of Questions");
		System.out.println("------------------");
		System.out.print("\n" + "What's your name?: ");
		// playerName = inputSource.nextLine();
		// System.out.println("\nYou, " + playerName + ", wake up to find yourself inside of the "+
		//	"'House of Questions'\n"+"Nothing else to do but explore...\n");
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
