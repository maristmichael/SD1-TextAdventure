
public class Player {
	public String name;
	public String location;
	public String[] inventory;
	public int score;
	
	public Player(String name, String location) {
		this.name = name;
		this.location = location;
		this.score = 0;
		this.inventory = new String[7];
	}
	
	@Override
	public String toString() {
		return "Your name is " + this.name + "\n" +
				"You're current at " + this.location + "\n" +
				"Your score is: " + this.score + "\n" +
				"You have the following items: " + this.inventory;
	}

}
