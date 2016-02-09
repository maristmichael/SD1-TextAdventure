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
	
	
	public static void main(String[] args) {
		// Variable Declarations
		Scanner inputSource = new Scanner(System.in);
		String userInput;
		String direction;
		
		
		System.out.println("\n"+"House of Questions");
		System.out.println("------------------"+"\n");
		System.out.println("You wake up to find yourself inside of the "+
			"'House of Questions'\n"+"Nothing else to do but explore...\n");
		
		
		while (true) {
			// User input that is case-insensitive
			System.out.print("Where should I go?: ");
			userInput = inputSource.nextLine().toUpperCase();
			
			// Game loop until user quits
			if (userInput.equals("N")) {
				direction = "North";
			} else if (userInput.equals("S")) {
				direction = "South";
			} else if (userInput.equals("E")) {
				direction = "East";
			} else if (userInput.equals("W")) {
				direction = "West";
			} else if (userInput.equals("Q")) {
				break;
			} else {
				System.out.println("Not a valid command!\n");
				continue;
			}
			
			System.out.println("You moved " + direction + ".\n");
		}
		
		System.out.println("Quitting game... ;(");
		inputSource.close();
	
	
	}

	
	
	
}
