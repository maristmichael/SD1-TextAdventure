
public class BreadcrumbTrail implements BreadcrumbStack {

	
	private static class Breadcrumb {
		int data;
		Breadcrumb link;
		
		Breadcrumb(int x) {
			this.data = x;
		}
	}

	private Breadcrumb top = null;
	
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
