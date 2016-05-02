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
	static Item batteries  = new Item("batteries", "a pair of Double-A batteries", "You see a pair of batteries on a lab table");
	static Item painting   = new Item("painting", "Van Gogh's famous famous painting","A familiar painting catches your eyes");
	static Item novel      = new Item("novel", "Great Gatsby, a famous book by F.Scott Fitzgerald", "You find your favorite novel of all time");
	static Item textbook   = new Item("textbook", "A thick book containing U.S. history", "You see a big and textbook on the table");
	static Item beard	   = new Item("beard", "A fake beard to wear for philosophizing", "You see a beard on the floor");
	static LimitedUseItem bottle     = new LimitedUseItem("bottle", "A water bottle", "You see a bottle", 3, "You drank some of the water");
	static LimitedUseItem calculator = new LimitedUseItem("calculator","A nifty TI-84 calculator", "You spot a nice calculator",
														  4,"The calulator turned off, the batteries must be drained");

	
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
		"You are now in a room with a giant globe with many history books surrounding it",
		"You enter a room with many dictionaries and novels all stacked neatly" + 
				"\nThe next path is closed off by a metal door inscribed with a drawing of a calculator",
		"What a beautiful room! It has many works of art scattered around"
	};
	
	// This is a Locale array with instances of locations
	public final static Locale[] LOCALES = {
		new Locale("Starting Room", LocDescrip[0]),
		new SecureLocale("Music Room", LocDescrip[1], false, false, 2),
		new Locale("Result Room", LocDescrip[2]),
		new SecureLocale("Math Room", LocDescrip[3], false, false, 2),
		new Locale("Kitchen", LocDescrip[4]),
		new SecureLocale("Science Room", LocDescrip[5], false, false, 2),
		new SecureLocale("Philosophy Room", LocDescrip[6], false, false, 2),
		new SecureLocale("History Room", LocDescrip[7], false, false, 2),
		new SecureLocale("English Room", LocDescrip[8], false, false, 2),
		new SecureLocale("Art Room", LocDescrip[9], false, false, 2)
	};
	
	
	// This SecureLocale array references the locations that are SecureLocales in LOCALES[]
	public final static SecureLocale[] SECURELOCS = new SecureLocale[] {
		(SecureLocale) LOCALES[1],
		(SecureLocale) LOCALES[3],
		(SecureLocale) LOCALES[5],
		(SecureLocale) LOCALES[6],
		(SecureLocale) LOCALES[7],
		(SecureLocale) LOCALES[8],
		(SecureLocale) LOCALES[9],
	};
	
	
	// This is the navigation matrix
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
	static void displayMap(){
		if (currentPlayer.inventory.contains(map)) {
			System.out.println(
				"                        Art  \n" +
				"                         |   \n" +
				"                         |   \n" +
				"  Kitchen     Music  English \n" +
				"     |         |         |   \n" +
				"     |         |         |   \n" +
				"   Math------Start---History \n" +
				"     |         |             \n" +
				"     |         |             \n" +
				"  Science     Result         \n" +
				"     |                       \n" +
				"     |                       \n" +
				"  Philosophy                 \n"
				);
		} else {
			System.out.println("You do not have a map");
		}
	}
	
	static String showHelp() {
		return 
			"\nExplore by entering 'n', 's', 'e', 'w'\n" + 
			"Enter 'q' : quits the game.\n" + 
			"Enter 'm' : displays the game map if you found it.\n" + 
			"Enter 'b' : backtrack to the previous room you were in.\n" +
			"Enter 'x' : examines the room your currently at.\n" +
			"Enter 'i' : info on your current status is displayed \n\n" +
			"Enter 't' + item name: takes an item that may be in a room.\n" + 
			"Enter 'd' + item name: drops an item by entering the exact name of it.\n" +
			"Enter 'u' + item name: uses an item.\n" +
			"Enter 'y' + any word : yells something out loud.\n";
	}
	
	static void showStatus() {
		System.out.print(currentPlayer.toString());
	}
	// This method looks at player's current location 
	static int from(int dir){
		int locId = currentPlayer.location;
		return MAP[locId][dir];
	}
	
	// This method moves player based on where they are at
	static void move(int direction) {
		int nextLoc = from(direction);
		
		if (!(nextLoc ==-1) && LOCALES[nextLoc] instanceof SecureLocale && LOCALES[nextLoc] == LOCALES[9]) {
			if (SecureLocale.canEnter(currentPlayer, calculator)) {
				currentPlayer.location = nextLoc;
				LOCALES[currentPlayer.location].visitCount++;
				playerTrail.dropCrumb(currentPlayer.location);
				System.out.println("The door suddenly opened allowing me to access the next room");
				System.out.println("\n"+HouseOfQuestions.locToScene());
			} else {
				System.out.println("\nThe door leading to the next room wont budge" + "\nMaybe I need to have an item...");
				System.out.println("You are in the " + LOCALES[currentPlayer.location].name);
			}
		} else if (!(nextLoc ==-1) && LOCALES[nextLoc] instanceof SecureLocale && LOCALES[nextLoc] == LOCALES[2]) {
			if (SecureLocale.canEnter(SECURELOCS)) {
				currentPlayer.location = nextLoc;
				LOCALES[currentPlayer.location].visitCount++;
				playerTrail.dropCrumb(currentPlayer.location);
				System.out.println("The wall magically dissapeared before your eyes, allowing you to enter the room");
				System.out.println("\n"+HouseOfQuestions.locToScene());
			}  else {
				System.out.println("\nThere is a wall blocking your path");
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
			} 
		} else {	
			currentPlayer.location = 0;
			trail.dropCrumb(currentPlayer.location);
			System.out.println("Your already at your final crumb on the trail you made.\n");
			System.out.println("You are in the " + LOCALES[currentPlayer.location].name);
		}
	}
	
	// This method checks to see if player has visited every location, and if so, player can choose victory.
	static boolean visitVictoryCheck() {
		int locsVisited = 0;
		
		for (int i = 0; i < LOCALES.length; i++) {
			if (LOCALES[i].visitCount != 0) {
				locsVisited++;
			}		
		}
		
		if (currentPlayer.ignoreVisitVictory == false) {
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
						currentPlayer.ignoreVisitVictory = true;
						System.out.println("*********************************************************");
						break;
					} else {
						System.out.print("Not a valid command\n" + "Yes or No?");
						continue;
					}
				}
			}
		}
		return false;
	}
	
	static boolean questionVictoryCheck() {
		if (Player.returnUserLoc(currentPlayer, LOCALES).equals(LOCALES[2])) {
			System.out.print("Final Question: Did you like this game?: ");
			while(true) {
				if(userInput.equals("YES")) {
					System.out.println("YOU " + currentPlayer.name.toUpperCase()+ " WIN!!!!!!!!!\n\n\n");
					return true;
				} else if (userInput.equals("NO")) {
					System.out.println("YOU LOOOSE\n\n\n");
					return false;
				} else {
					System.out.println("Not a valid answer");
					continue;
				}
			}
		}
		return false;
	}
		
	public Item findItem(Player user, Item item){
		if (user.inventory.contains(item)){
			return item;
		}
		return null;
	}
	
	// This method checks to see if player has taken too many steps, and if so, the player loses.
	static boolean outOfActions() {
		if (currentPlayer.actionCount == 0) {
			System.out.println("\n" +"The number on your right hand turned to 0...." + "\nYOU DIED....");
			return true;
		}
		return false;
	}
	
	// This method checks to see if player has guessed too many times when answering the location questions, if so the player loses
	static boolean outOfGuesses() {
		if (currentPlayer.questionCount == 0) {
			System.out.println("\n" +"The number on your left hand turned to 0...." + "\nYOU DIED....");
			return true;
		}
		return false;
	}
	// This method creates instances of Item and sets them in their proper location
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
	
	static void setQuizAndAnswers() {
		LOCALES[1].setQuestion("What genre of music does Michael Jackson perform?: ");
		LOCALES[1].setAnswer("POP");
		LOCALES[3].setQuestion("What is 761 − 347?: ");
		LOCALES[3].setAnswer("414");
		LOCALES[5].setQuestion("What eye color is typically dominant in humans?: ");
		LOCALES[5].setAnswer("BROWN");
		LOCALES[6].setQuestion("Who is Socrates's famous student: ");
		LOCALES[6].setAnswer("PLATO");
		LOCALES[7].setQuestion("In what city is the Statue of David located at?: ");
		LOCALES[7].setAnswer("FLORENCE");
		LOCALES[8].setQuestion("What is the word that is defined as 'a time of intense difficulty': ");
		LOCALES[8].setAnswer("CRISIS");
		LOCALES[9].setQuestion("Currently, how many amendments are in the U.S. constitution?: ");
		LOCALES[9].setAnswer("27");
	}
	
	// This method starts the game loop
	public static void gameStart() {
		String locationScene = "";
		setItems();
		setQuizAndAnswers();
		LOCALES[currentPlayer.location].visitCount++;
		playerTrail.dropCrumb(currentPlayer.location);
		
		while (true) {
			
			// User input that is case-insensitive
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
				Player.examine(currentPlayer,LOCALES);
			} else if (userInput.equals("M")) {
				displayMap();
			} else if (userInput.equals("I")) {
				showStatus();
			} else if (userInput.equals("H")) {
				locationScene = showHelp();
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
		System.out.println("\nHello " + currentPlayer.name + ",\n\nYou wake up to find yourself inside of the "+
			"'House of Questions'\n"+"Nothing else to do but explore...\n");
		System.out.println("On your right hand you see the number " + currentPlayer.actionCount + " branded on your skin");
		System.out.println("On your left hand you see the number " + currentPlayer.questionCount + " branded on your skin");
		System.out.println("Game Note: Enter 'h' for a list of commands\n");
		System.out.println(HouseOfQuestions.locToScene() + "\n");
	}
	
	// This method end the game and displays the game credits
	static void gameEnd() {
		System.out.println("\nThank you for playing :)");
		System.out.println("\nCopyright Michael Gutierrez");
		System.out.println("===========================");
		System.out.println("Under the supervision of Professor Johnson");
		inputSource.close();
	}
	
	public static void main(String[] args) {
		HouseOfQuestions.gameIntro();
		HouseOfQuestions.gameStart();
		HouseOfQuestions.gameEnd();
	}	
}
