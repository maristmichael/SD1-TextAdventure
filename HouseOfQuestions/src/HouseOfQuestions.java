/**
 * @author Michael Gutierrez
 * CMPT 220L-114
 * Professor Johnson
 * 9 February 2016
 *
 */

import java.util.Scanner;

public class HouseOfQuestions {

	// Variable for player location
	static int playerLocation = 0;
	
	// Array containing location Descriptions
	static String[] LOCATIONS = {
		"This is a room whose walls are written with math equations",
		"You enter a room littered with many scientific journals and books",
		"This room contains many philosphical rhetoric inscribed on the walls",
		"What a beautiful room! It has many works of art scattered around",
		"You enter a room with a plethora of dictionaries and novels all stacked nicely",
		"You are now in a room with a giant globe and many history books surrounding it"
	};
	
	// Variables that holds updates scene for player and hold player's name
	static String locationScene;
	static String playerName;

	public static void main(String[] args) {
		// Variable Declarations
		Scanner inputSource = new Scanner(System.in);
		 String userInput;
		 String direction;
		
		
		System.out.println("\n"+"House of Questions");
		System.out.println("------------------");
		System.out.print("\n" + "What's your name?: ");
		playerName = inputSource.nextLine();
		System.out.println("\nYou, " + playerName + ", wake up to find yourself inside of the "+
			"'House of Questions'\n"+"Nothing else to do but explore...\n");
		
		
		while (true) {
			// User input that is case-insensitive
			System.out.print("Where should I go?: ");
			userInput = inputSource.nextLine().toUpperCase();
			
			// Game loop until user quits
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
				System.out.println("That is a wall, try somthing else");
				continue;
			}
			
			System.out.println(locationScene);
		}
		
		System.out.println("\n" +"Quitting game... ;(\n" + "Thank you for playing :)");
		System.out.println("\nCopyright Michael Gutierrez");
		System.out.println("===========================");
		System.out.println("Under the supervision of Professor Johnson");
		inputSource.close();
	
	
	}

	
	
	
}
