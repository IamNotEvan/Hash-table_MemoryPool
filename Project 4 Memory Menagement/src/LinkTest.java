import student.TestCase;

/**
 * This class tests the methods in
 * the Link class.
 * 
 * @author Rowan Cusano
 * @author Evan Lee
 * @version 2022.12.5
 *
 */
public class LinkTest extends TestCase {

    private Link link1;
    private Link link2;
    private Link link3;

    /**
     * This sets up the tests for this class.
     */
    public void setUp() {

        link3 = new Link("Link 3", null, null);
        String str1 = "Link1";
        link1 = new Link(str1, null, link3);
        link2 = new Link(link3, null);
    }


    /**
     * This tests the getter and setter
     * object methods.
     */
    public void testObject() {

        assertEquals("Link1", link1.getObject());
        link1.setObject(new String("Link 1"));
        assertEquals("Link 1", link1.getObject());
    }


    /**
     * This tests the getter and setter
     * element methods.
     */
    public void testElement() {

        assertEquals("Link1", link1.element());
        link1.setElement(new String("Link 1"));
        assertEquals("Link 1", link1.element());
    }


    /**
     * This tests the getter and setter
     * next methods.
     */
    public void testNext() {

        Link link4 = new Link("Link4", null, null);
        assertEquals(link3, link1.next());
        link1.setNext(link4);
        assertEquals(link4, link1.next());
    }


    /**
     * This tests the getter and setter
     * next methods.
     */
    public void testPrev() {

        Link link4 = new Link("Link4", null, null);
        assertEquals(link3, link2.prev());
        link2.setPrev(link4);
        assertEquals(link4, link2.prev());
    }
}