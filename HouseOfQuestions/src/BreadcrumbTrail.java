
public class BreadcrumbTrail implements BreadcrumbStack {

	private Breadcrumb top;
	
	public BreadcrumbTrail() {
		this.top = null;
	}
	
	@Override
	public void dropCrumb(int x) {
		Breadcrumb newCrumb = new Breadcrumb(x, this.top);
		this.top = newCrumb;
		System.out.println("\nYou dropped a breadcrumb in case you're lost\n");
	}
	
	@Override
	public void pickupCrumb() {
		if (this.top == null) { 
			System.out.println("Cant go back anymore");
		} else {
			this.top = this.top.link;
		}
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

class Breadcrumb {
	int data;
	Breadcrumb link;
	
	Breadcrumb(int x, Breadcrumb n) {
		this.data = x;
		this.link = n;
	}
}
