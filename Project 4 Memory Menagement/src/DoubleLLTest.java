import student.TestCase;

/**
 * This class tests all of the methods in the
 * DoublyLL class.
 * 
 * @author Rowan Cusano
 * @author Evan Lee
 * @version 2022.12.5
 *
 */
public class DoubleLLTest extends TestCase {

    private DoubleLL list;

    /**
     * This sets up the tests for the class.
     */
    public void setUp() {
        list = new DoubleLL();
    }


    /**
     * This tests a variety of getters and setters
     */
    public void test1() {
        assertTrue(list.isEmpty());
        assertEquals(list.getListSize(), 0);

        assertTrue(list.insert(1));
        assertEquals(list.getListSize(), 1);
        assertEquals(list.getLinkAtIndex(0).getObject(), 1);

        assertTrue(list.insert(2));
        assertEquals(list.getListSize(), 2);
        assertEquals(list.getLinkAtIndex(0).getObject(), 2);
        assertEquals(list.getLinkAtIndex(1).getObject(), 1);

        assertTrue(list.insert(3));
        assertEquals(list.getListSize(), 3);
        assertEquals(list.getLinkAtIndex(0).getObject(), 2);
        assertEquals(list.getLinkAtIndex(1).getObject(), 3);
        assertEquals(list.getLinkAtIndex(2).getObject(), 1);
        assertEquals(list.getCurr().getObject(), 1);

        assertEquals(list.prev().getObject(), 3);
        assertEquals(list.next().getObject(), 1);

        assertEquals(list.getLinkAtIndex(0).getObject(), 2);
        assertEquals(list.remove(), 2);
        assertEquals(list.getListSize(), 2);
        assertEquals(list.getLinkAtIndex(0).getObject(), 3);
        assertEquals(list.getLinkAtIndex(1).getObject(), 1);
    }


    /**
     * This also tests a variety of getters and setters
     */
    public void test2() {
        assertTrue(list.isEmpty());
        assertEquals(list.getListSize(), 0);

        assertTrue(list.insert(1));
        assertEquals(list.getListSize(), 1);
        assertTrue(list.insert(2));
        assertEquals(list.getListSize(), 2);

        assertTrue(list.insert(3));
        assertEquals(list.getListSize(), 3);

        assertEquals(list.getLinkAtIndex(0).getObject(), 3);
        assertEquals(list.getLinkAtIndex(1).getObject(), 2);
        assertEquals(list.getLinkAtIndex(2).getObject(), 1);

        assertEquals(list.getLinkAtIndex(2).getObject(), 1);
        assertEquals(list.prev().getObject(), 2);

        list.moveToLast();
        assertEquals(list.getCurr().getObject(), 1);
        assertTrue(list.append(4));
        assertEquals(list.getLinkAtIndex(3).getObject(), 4);

        list.search(2);
        assertEquals(list.getCurr().getObject(), 2);
        assertEquals(list.remove(), 2);
        assertEquals(list.getLinkAtIndex(0).getObject(), 3);
        assertEquals(list.getLinkAtIndex(1).getObject(), 1);
        assertEquals(list.getLinkAtIndex(2).getObject(), 4);

    }

}