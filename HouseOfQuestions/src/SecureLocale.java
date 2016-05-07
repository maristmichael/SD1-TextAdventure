/**
 * @author Michael Gutierrez <michael.gutierrez2@marist.edu>
 * @version 1.0
 * CMPT 220L-114
 * Professor Johnson
 * 6 May 2016
 *
 */

/**
 * The Class SecureLocale
 */
public class SecureLocale extends Locale {
	
	/** Required item for certain SecureLocales */
	Item requiredItem;
	
	/** Question that is asked for certain SecureLocale */
	String question;
	
	/** Answer to question asked for certain SecureLocale */
	String answer;
	
	/** The number of attempts the player has left to answer questions in some SecureLocales*/
	int questionCount;
	
	/** Check to see if player has answered question correctly in some SecureLocales*/
	boolean questionCheck;
	
	/** Check to see if player has discovered */
	boolean questionFound;

	
	/**
	 * Method that sets the question in certain SecureLocale
	 *
	 * @param question the question that is asked
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	
	/**
	 * Method that sets the answer in certain SecureLocale
	 *
	 * @param answer the new answer
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	/**
	 * Method that checks if player has a certain item
	 *
	 * @param user the player
	 * @param requiredItem the required item
	 * @return true, if successful
	 */
	public static boolean canEnter(Player user, Item requiredItem) {
		for (int i = 0; i < user.inventory.size(); i++) {
			if (user.inventory.contains(requiredItem)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method that checks if player can enter a final SecureLocale if all questions have been answered
	 *
	 * @param locations the array containing all SecureLocale
	 * @return true, if successful
	 */
	public static boolean canEnter(SecureLocale[] locations) {
		for (int i = 0; i < locations.length; i++){
			if (locations[i].questionCheck == false) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Instantiates a new secure locale
	 *
	 * @param name the name
	 * @param description the description
	 * @param item the item required to enter the SecureLocale
	 */
	public SecureLocale(String name, String description, Item item) {
		super(name, description);
		this.requiredItem = item;
	}
	
	
	/**
	 * Instantiates a new secure locale
	 *
	 * @param name the name
	 * @param description the description
	 * @param questionCheck Boolean check to see if player has found question
	 * @param questionCount The number of attempts the player has left to answer questions
	 * @param questionFound Boolean check to see if player has answered question correctly
	 */
	public SecureLocale(String name, String description, boolean questionCheck, boolean questionFound, int questionCount) {
		super(name, description);
		this.questionCheck = questionCheck;
		this.questionCount = questionCount;
		this.questionFound = questionFound;
	}
}
