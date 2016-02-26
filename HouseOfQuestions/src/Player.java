
public class Player {
	public String name;
	public int location = 0;
	public String[] inventory;
	public int score;
	
	public Player(String name, int location) {
		this.name = name;
		this.score = 0;
		this.inventory = new String[8];
		this.location = location;
	}
	
	@Override
	public String toString() {
		return "Your name is " + this.name + "\n" +
				"You're current at " + this.location + "\n" +
				"Your score is: " + this.score + "\n" +
				"You have the following items: " + this.inventory;
	}

}
