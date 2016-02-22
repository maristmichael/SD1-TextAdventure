
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
	

}
