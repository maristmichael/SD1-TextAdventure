
public class BreadcrumbTrail implements BreadcrumbStack {

	private Breadcrumb top;
	
	public BreadcrumbTrail(){
		this.top = null;
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
