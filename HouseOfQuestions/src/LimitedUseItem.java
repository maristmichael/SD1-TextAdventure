
public class LimitedUseItem extends Item {
	int usesRemaining;
	public LimitedUseItem(String name, String descrip, String discovered, int uses) {
		super(name, descrip, discovered);
		this.usesRemaining = uses;
	}
}
