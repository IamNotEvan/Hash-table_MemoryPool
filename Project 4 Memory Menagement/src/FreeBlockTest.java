import student.TestCase;

/**
 * This is the test class for the Free
 * Block class.
 * 
 * @author Rowan Cusano
 * @author Evan Lee
 * @version 2022.12.5
 *
 */
public class FreeBlockTest extends TestCase {

    private FreeBlock block1;

    /**
     * This sets up the tests for the class
     */
    public void setUp() {
        block1 = new FreeBlock(0, 10);
    }


    /**
     * This tests the getter and setter
     * for the pos field.
     */
    public void testGetAndSetPos() {
        assertEquals(0, block1.getPos());
        block1.setPos(15);
        assertEquals(15, block1.getPos());
    }


    /**
     * This tests the getter and setter
     * for the length field.
     */
    public void testGetAndSetLength() {
        assertEquals(10, block1.getLength());
        block1.setLength(15);
        assertEquals(15, block1.getLength());
    }


    /**
     * This tests if the toString function
     * works properly.
     */
    public void testToString() {
        assertEquals("(0,10)", block1.toString());
        FreeBlock block2 = new FreeBlock(10, 20);
        assertEquals("(10,20)", block2.toString());
    }
}