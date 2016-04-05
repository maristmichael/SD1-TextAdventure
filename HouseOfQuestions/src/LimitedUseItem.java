
public class LimitedUseItem extends Item {
	int usesRemaining;
	String afterUse;
	public LimitedUseItem(String name, String descrip, String discovered, int uses, String afterUse) {
		super(name, descrip, discovered);
		this.usesRemaining = uses;
		this.afterUse = afterUse;
	}
}
