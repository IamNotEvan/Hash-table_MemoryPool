/**
 * This class contains the methods and
 * fields for the Link class.
 * 
 * @author Rowan Cusano
 * @author Evan Lee
 * @version 2022.12.5
 *
 */
public class Link {

    private Object e;
    private Link next;
    private Link previous;

    /**
     * This is one constructor for the
     * class that takes in an object.
     * 
     * @param it
     *            The object to insert
     * @param inp
     *            The previous link
     * @param inn
     *            The next link
     */
    Link(Object it, Link inp, Link inn) {
        e = it;
        previous = inp;
        next = inn;
    }


    /**
     * This is another constructor that
     * creates a link with no object.
     * 
     * @param inp
     *            The previous link
     * @param inn
     *            The next link
     */
    Link(Link inp, Link inn) {
        previous = inp;
        next = inn;
    }


    /**
     * This gets the object of a link.
     * 
     * @return The object
     */
    public Object getObject() {
        return e;
    }


    /**
     * This sets the object of a link.
     * 
     * @param it
     *            The new object
     */
    public void setObject(Object it) {
        e = it;
    }


    /**
     * Get method for the data members
     * 
     * @return The element
     */
    public Object element() {
        return e;
    }


    /**
     * This sets the element of a
     * link.
     * 
     * @param it
     *            The new element
     * @return The new element
     */
    public Object setElement(Object it) {
        e = it;
        return e;
    }


    /**
     * This gets the next link.
     * 
     * @return The next link
     */
    public Link next() {
        return next;
    }


    /**
     * This sets the next link
     * 
     * @param nextval
     *            The next link
     * @return The next link
     */
    public Link setNext(Link nextval) {
        next = nextval;
        return next;
    }


    /**
     * This gets the previous link.
     * 
     * @return The previous link
     */
    public Link prev() {
        return previous;
    }


    /**
     * This sets the prev link
     * 
     * @param prevval
     *            The prev link
     * @return The prev link
     */
    public Link setPrev(Link prevval) {
        previous = prevval;
        return previous;
    }

}