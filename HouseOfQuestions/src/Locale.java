import java.util.ArrayList;

public class Locale {
	String name;
	String description;
	String question;
	String answer;
	ArrayList<Item>items;
	int visitCount;
	int questionCount;
	boolean questionCheck;
	boolean questionFound;
	
	public Locale(String name, String description, boolean questionCheck, boolean questionFound) {
		this.name = name;
		this.description = description;
		this.items = new ArrayList<Item>();
		this.visitCount = 0;
		this.questionCheck = questionCheck;
		this.questionCount = 3;
		this.questionFound = questionFound;
	};
	
	// This method creates and places new items into the Locale ArrayList
	public void placeItems(Item name) {
		this.items.add(name);   
	}
	
	// A more useful toString method
	@Override
	public String toString() {
		return this.description + "\n(You are in the " + this.name + ")";
	}
	
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}