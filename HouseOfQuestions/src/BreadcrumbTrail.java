
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
	public void pickupCrumb(){
		this.top = this.top.link;
	}
	
	class Breadcrumb {
		Object data;
		Breadcrumb link;
		
		Breadcrumb(Object x, Breadcrumb n) {
			this.data = x;
			this.link = n;
		}
		
	}
}
