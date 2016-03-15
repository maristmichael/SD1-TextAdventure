
public interface BreadcrumbStack {
	
	// Removes the top element of a non-empty stack.
    public void pickupCrumb();

    // Adds elements to the top top of the stack 
    public void dropCrumb(Object x);

    //Returns a reference to the top element of a stack
    public Object currentCrumb();

    // Tests whether the stack contains any elements.
    public boolean hasMoreCrumbs();
}