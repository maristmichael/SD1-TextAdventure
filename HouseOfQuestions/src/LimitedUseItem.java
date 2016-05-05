
/**
 * The Class LimitedUseItem
 */
public class LimitedUseItem extends Item {
	
	/** variable used to hold number of uses remaining for item */
	int usesRemaining;
	
	/** variable that denotes what happens after item is used */
	String afterUse;
	
	/**
	 * Instantiates a new limited use item
	 *
	 * @param name the name of the LimitedUseItem
	 * @param descrip the description of the LimitedUseItem
	 * @param discovered boolean that tells whether the LimitedUseItemitem has been found
	 * @param uses the total number of uses of the LimitedUseItem
	 * @param afterUse the string that displays after player uses the item
	 */
	public LimitedUseItem(String name, String descrip, String discovered, int uses, String afterUse) {
		super(name, descrip, discovered);
		this.usesRemaining = uses;
		this.afterUse      = afterUse;
	}
}
