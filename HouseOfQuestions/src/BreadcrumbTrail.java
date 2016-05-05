
/**
 * The Class BreadcrumbTrail
 */
public class BreadcrumbTrail implements BreadcrumbStack {

	
	/**
	 * The Class Breadcrumb
	 */
	private static class Breadcrumb {
		
		/** Variable that holds user location number*/
		int data;
		
		/** The link to next Breadcrumb */
		Breadcrumb link;
		
		/**
		 * Instantiates a new Breadcrumb
		 *
		 * @param x takes in user location number
		 */
		Breadcrumb(int x) {
			this.data = x;
		}
	}

	/** The top of the BreadcrumbTrail */
	private Breadcrumb top = null;
	
	/**
	 * Instantiates a new BreadcrumbTrail
	 */
	public BreadcrumbTrail() {
		this.top = null;
	}
	

	@Override
	public void dropCrumb(int x) {
		Breadcrumb newCrumb = new Breadcrumb(x);
		newCrumb.link = top;
		top = newCrumb;
		System.out.println("\nYou dropped a breadcrumb in case you're lost\n");
	}
	

	@Override
	public void pickupCrumb() {
		this.top = this.top.link;
		
		System.out.println("\nYou followed your breadcrumb trail back a room");
	}


	@Override
	public int currentCrumb() {
		if(this.top == null) {
			return -1;
		}
		return this.top.data;
	}
	

	@Override
	public boolean hasMoreCrumbs(){
		return (this.top != null);
	}	
}
