import java.util.ArrayList;

public class Locale {
	String name;
	String description;
	String question;
	String answer;
	ArrayList<Item>items;
	boolean hasExamined;
	int visitCount;
	
	public Locale(String name, String description) {
		this.name = name;
		this.description = description;
		this.items = new ArrayList<Item>();
		this.hasExamined = false;
		this.visitCount = 0;
	};
	
	// This method creates and places new items into the Locale ArrayList
	public void placeItems(Item name) {
		this.items.add(name);   
	}
	
	// This method sets the room's question
	public void setQuestion(String question) {
		this.question = question;
	}
	
	// This method sets the room's answer
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	// A more useful toString method
		@Override
		public String toString() {
			return this.description + "\n*You are in the " + this.name + "*";
		}
}