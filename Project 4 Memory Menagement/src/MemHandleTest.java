import student.TestCase;

/**
 * This tests the MemHandle class
 * and methods.
 * 
 * @author Rowan Cusano
 * @author Evan Lee
 * @version 2022.12.5
 *
 */
public class MemHandleTest extends TestCase {

    private MemHandle handle1;

    /**
     * This sets up the tests for the class
     */
    public void setUp() {
        handle1 = new MemHandle(0, 10);
    }


    /**
     * This tests the getter and setter
     * for the pos field.
     */
    public void testGetAndSetPos() {
        assertEquals(0, handle1.getPos());
        handle1.setPos(15);
        assertEquals(15, handle1.getPos());
    }


    /**
     * This tests the getter and setter
     * for the size field.
     */
    public void testGetAndSetLength() {
        assertEquals(10, handle1.getSize());
        handle1.setSize(15);
        assertEquals(15, handle1.getSize());
    }
}