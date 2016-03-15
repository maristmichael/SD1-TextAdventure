
public class BreadcrumbTrail implements BreadcrumbStack {

	private Breadcrumb top;
	
	public BreadcrumbTrail(){
		this.top = null;
	}
	
	@Override
	public void dropCrumb(Object x){
		Breadcrumb newCrumb = new Breadcrumb(x, this.top);
		this.top = newCrumb;
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
	public Object currentCrumb(){
		return this.top.data;
	}
	
	@Override
	public boolean hasMoreCrumbs(){
		return (this.top == null);
	}	
}

class Breadcrumb {
	Object data;
	Breadcrumb link;
	
	Breadcrumb(Object x, Breadcrumb n) {
		this.data = x;
		this.link = n;
	}
}
