
/**
 * The Interface BreadcrumbStack.
 */
public interface BreadcrumbStack {
	
	/**
	 * Removes the top element of a non-empty stack
	 */

    public void pickupCrumb();

    /**
     * Adds elements to the top top of the stack 
     *
     * @param x is user location number
     */
    public void dropCrumb(int x);

    /**
     * Returns a reference to the top element of a stack
     *
     * @return the top element
     */
    public int currentCrumb();

    /**
     * Tests whether the stack contains any elements.
     *
     * @return true, if stack has any element
     */
    public boolean hasMoreCrumbs();
}