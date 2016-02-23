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
	public final int [][] map = {
			{1,-1,2,5},
			{-1,0,-1,-1},
			{-1,3,-1,0},
			{2,4,-1,-1},
			{3,-1,-1,-1},
			{6,-1,0,-1},
			{7,5,-1,-1},
			{-1,6,-1,-1}
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
				System.out.println("That is a wall, try somthing else");
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
		playerName = inputSource.nextLine();
		System.out.println("\nYou, " + playerName + ", wake up to find yourself inside of the "+
			"'House of Questions'\n"+"Nothing else to do but explore...\n");
	}
	
	// Function that displays credits
	static void gameCredits() {
		System.out.println("\n" +"Quitting game... ;(\n" + "Thank you for playing :)");
		System.out.println("\nCopyright Michael Gutierrez");
		System.out.println("===========================");
		System.out.println("Under the supervision of Professor Johnson");
	}
	
	public static void main(String[] args) {
		
		HouseOfQuestions.gameStart();
		HouseOfQuestions.gameCredits();
		inputSource.close();
	}
	
}
