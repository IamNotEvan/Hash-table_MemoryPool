

/**
 * This class contains all methods for
 * a Doubly Linked List. This is used to hold
 * the free blocks for this program.
 * 
 * @author Rowan Cusano
 * @author Evan Lee
 * @author openDSA
 * @version 2022.12.5
 *
 */
public class DoubleLL {

    private Link head;
    private Link tail;
    private Link curr;
    private int listSize;

    /**
     * This is the constructor for the class.
     */
    public DoubleLL() {

        head = new Link(null, tail);
        tail = new Link(head, null);
        // curr = new Link(head ,null);
        curr = tail;
        setListSize(0);
    }


    /**
     * This method return the link at current pointer
     * 
     * @return
     *         the link
     */
    public Link getCurr() {
        return this.curr;
    }


    /**
     * This returns true if the list
     * is empty and false if not.
     * 
     * @return True if empty, false if not
     */
    public boolean isEmpty() {
        return listSize == 0;
    }


    /**
     * This method moves the current pointer
     * to the link after the head
     */
    public void moveToStart() {
        curr = head.next();
    }


    /**
     * TThis method moves the current pointer
     * to the link before the tail
     */
    public void moveToLast() {
        curr = tail.prev();
    }


    /**
     * This gets a link at a specific
     * index in the list.
     * 
     * @param index
     *            The index to get at
     * @return The link at the index
     */
    public Link getLinkAtIndex(int index) {
        this.moveToStart();
        for (int i = 0; i < index; i++) {
            curr = curr.next();
        }
        return this.getCurr();
    }


    /**
     * This inserts a link into the list
     * with the given object at the current spot.
     * 
     * @param it
     *            The object to be added
     * @return True if added, false if not
     */
    public boolean insert(Object it) {
        curr = new Link(it, curr.prev(), curr);
        curr.prev().setNext(curr);
        curr.next().setPrev(curr);
        setListSize(getListSize() + 1);
        return true;
    }


    /**
     * This adds a link with the given object
     * to the back of the list.
     * 
     * @param it
     *            The object to be added
     * @return True if added, false if not
     */
    public boolean append(Object it) {
        tail.setPrev(new Link(it, tail.prev(), tail));
        tail.prev().prev().setNext(tail.prev());
        if (curr == tail) {
            curr = tail.prev();
        }
        setListSize(getListSize() + 1);
        return true;
    }


    /**
     * This removes the link and object
     * at the current pointer.
     * 
     * @return The removed object
     */
    public Object remove() {
        if (curr == tail) {
            return null;
        }

        Object it = curr.element();
        curr.prev().setNext(curr.next());
        curr.next().setPrev(curr.prev());
        curr = curr.next();
        setListSize(getListSize() - 1);
        return it;
    }


    /**
     * This moves the current back one link.
     * 
     * @return The link moved to
     */
    public Link prev() {
        if (curr.prev() != head) {
            curr = curr.prev();
        }
        return curr;
    }


    /**
     * Move pointer to the next link
     * 
     * @return The link moved to
     */
    public Link next() {
        if (curr != tail) {
            curr = curr.next();
        }
        return curr;
    }


    /**
     * This finds an object in the list.
     * 
     * @param it
     *            The object to find
     */
    public void search(Object it) {

        if (!this.isEmpty()) {
            this.moveToStart();
            for (int i = 0; i < this.getListSize(); i++) {
                if (it.equals(this.getCurr().getObject())) {
                    break;
                }
                this.next();
            }
        }
    }


    /**
     * This gets the size of the list.
     * 
     * @return The size
     */
    public int getListSize() {
        return listSize;
    }


    /**
     * This sets the size list.
     * 
     * @param listSize
     *            The new size
     */
    public void setListSize(int listSize) {
        this.listSize = listSize;
    }

}