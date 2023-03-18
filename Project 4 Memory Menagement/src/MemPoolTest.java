import student.TestCase;

/**
 * This class tests all of the methods in
 * the MemPool class.
 * 
 * @author Rowan Cusano
 * @author Evan Lee
 * @version 2022.12.5
 *
 */
public class MemPoolTest extends TestCase {

    /**
     * This sets up the tests for the class.
     */
    private MemPool m1;

    /**
     * This sets up the tests for the class.
     */
    public void setUp() {
        m1 = new MemPool(32);
    }


    /**
     * This tests the get and set methods in
     * the class.
     */
    public void testGetAndSet() {
        assertEquals(m1.getSpace().length, 32);
        assertEquals(m1.getFreeBlocks().getListSize(), 1);
        byte[] bytes = new byte[34];
        m1.setSpace(bytes);
        assertEquals(m1.getSpace().length, 34);
        DoubleLL dl = new DoubleLL();
        m1.setFreeBlocks(dl);
        assertEquals(m1.getFreeBlocks().getListSize(), 0);
    }


    /**
     * This tests various situations of
     * removal from the pool
     */
    public void testRemoves() {
        String a1 = "a1";
        String a2 = "a22";
        String a3 = "a333";
        String a4 = "a4444";

        MemHandle a1h = m1.insert(a1);
        MemHandle a2h = m1.insert(a2);
        MemHandle a3h = m1.insert(a3);
        MemHandle a4h = m1.insert(a4);
        MemHandle a41h = m1.insert(a4);
        m1.printBlocks();
        m1.remove(a1h);
        m1.printBlocks();
        m1.remove(a3h);
        m1.printBlocks();

        MemHandle a42h = m1.insert(a4);

        m1.printBlocks();

    }


    /**
     * This tests various inserting
     * situations in the pool.
     */
    public void testInsert() {
        String a1 = "a1";
        String a2 = "a22";
        String a3 = "a333";
        String a4 = "a4444";
        byte[] a1b = new byte[34];
        m1.printBlocks();
        m1.expand();
        assertEquals(m1.getSpace().length, 64);
        assertEquals(m1.getFreeBlocks().getListSize(), 1);
        m1.printBlocks();
        // assertEquals(m1.bestFit(a1b).getLength(), 32);
// MemHandle a1h = m1.insert(a1);
// int a1p = a1h.getPos();
// int a1s = a1h.getSize();
// assertEquals(a1p, 0);
// assertEquals(a1s, a1.getBytes().length + 2);
        m1.expand();
    }


    /**
     * This tests more situations
     * of insertion into the pool.
     */
    public void testInsert2() {
        String a1 = "a1";
        String a2 = "a22";
        String a3 = "a333";
        String a4 = "a4444";
        byte[] a1b = new byte[20];

        MemHandle a1h = m1.insert(a1);
        int a1p = a1h.getPos();
        int a1s = a1h.getSize();
        assertEquals(a1p, 0);
        assertEquals(a1s, a1.getBytes().length + 2);
        assertEquals(a1s, 4);
        m1.printBlocks();

        MemHandle a2h = m1.insert(a2);
        int a2p = a2h.getPos();
        int a2s = a2h.getSize();
        assertEquals(a2p, 4);
        assertEquals(a2s, a2.getBytes().length + 2);
        assertEquals(a2s, 5);
        m1.printBlocks();

        m1.insert(a4);
        m1.printBlocks();

        m1.insert(a4);
        m1.printBlocks();

        m1.insert(a4);
        m1.printBlocks();

        m1.insert(a4);
        m1.printBlocks();

        assertTrue(m1.getStr(a2h).equals(a2));
        assertTrue(m1.hasDup(a2h, a2));
        assertFalse(m1.hasDup(a2h, a4));
    }


    /**
     * This tests more remove situations.
     */
    public void testRemove() {
        String a1 = "a1";
        String a2 = "a22";
        String a3 = "a333";
        String a4 = "a4444";

        MemHandle a1h = m1.insert(a1);
        MemHandle a2h = m1.insert(a2);
        MemHandle a3h = m1.insert(a3);
        MemHandle a4h = m1.insert(a4);
        assertEquals(m1.getFreeBlocks().getListSize(), 1);
        m1.printBlocks();
        // System.out.println("HERE!!!!!!!!!!!!!");
        assertTrue(m1.getStr(a1h).equals(a1));
        // System.out.println("remove a1h");
        m1.remove(a1h);
        m1.printBlocks();
        assertEquals(m1.getFreeBlocks().getListSize(), 2);

        // System.out.println("HERE!!!!!!!!!!!!!");
        m1.remove(a2h);
        m1.printBlocks();
    }


    /**
     * This continues to test remove to
     * make sure it works.
     */
    public void testRemove2() {
        String a1 = "a1";
        String a2 = "a22";
        String a3 = "a333";
        String a4 = "a4444";

        MemHandle a1h = m1.insert(a1);
        MemHandle a2h = m1.insert(a2);
        MemHandle a3h = m1.insert(a3);
        MemHandle a4h = m1.insert(a4);
        assertEquals(m1.getFreeBlocks().getListSize(), 1);
        m1.printBlocks();

        m1.remove(a2h);
        m1.printBlocks();
        assertEquals(m1.getFreeBlocks().getListSize(), 2);

        m1.remove(a1h);
        m1.printBlocks();

// System.out.println("HERE!!!!!!!!!!!!!");
// m1.remove(a2h);
// m1.printBlocks();
    }


    /**
     * Remove is being tested once again
     */
    public void testRemove3() {
        String a1 = "a1";
        String a2 = "a22";
        String a3 = "a333";
        String a4 = "a4444";

        MemHandle a1h = m1.insert(a1);
        MemHandle a2h = m1.insert(a2);
        MemHandle a3h = m1.insert(a3);
        MemHandle a4h = m1.insert(a4);
        assertEquals(m1.getFreeBlocks().getListSize(), 1);
        m1.printBlocks();

        m1.remove(a1h);
        m1.printBlocks();
        assertEquals(m1.getFreeBlocks().getListSize(), 2);

        m1.remove(a3h);
        m1.printBlocks();

        m1.remove(a2h);
        m1.printBlocks();
        assertEquals(m1.getStr(a4h), a4);
        assertTrue(m1.hasDup(a4h, a4));
        assertFalse(m1.hasDup(a1h, a1));
    }


    /**
     * This tests complex insertions
     * into the mempool.
     */
    public void testComplex() {
        String a1 = "a1";
        String a2 = "a22";
        String a3 = "a333";
        String a4 = "a4444";
        String a5 = "a55555";
        String a6 = "a666666";
        String a7 = "a7777777";
        String a8 = "a88888888";
        String h = "hello";

        MemHandle a1h = m1.insert(a1);
        m1.printBlocks();
        MemHandle a2h = m1.insert(a2);
        m1.printBlocks();
        MemHandle a3h = m1.insert(a3);
        m1.printBlocks();
        MemHandle a4h = m1.insert(a4);
        m1.printBlocks();
        MemHandle a5h = m1.insert(a5);
        m1.printBlocks();
        MemHandle a6h = m1.insert(a6);
        m1.printBlocks();
        MemHandle a7h = m1.insert(a7);
        m1.printBlocks();
        MemHandle a8h = m1.insert(a8);
        m1.printBlocks();
        MemHandle a81h = m1.insert(a8);
        MemHandle hh = m1.insert(h);
        m1.printBlocks();

        assertEquals(m1.getStr(hh), "hello");
        assertTrue(m1.hasDup(hh, h));

        assertEquals(m1.getStr(a1h), "a1");
        assertTrue(m1.hasDup(a1h, a1));

        assertEquals(m1.getStr(a2h), a2);
        assertTrue(m1.hasDup(a2h, a2));

        assertEquals(m1.getStr(a3h), a3);
        assertTrue(m1.hasDup(a3h, a3));

        assertEquals(m1.getStr(a4h), a4);
        assertTrue(m1.hasDup(a4h, a4));

        assertEquals(m1.getStr(a5h), a5);
        assertTrue(m1.hasDup(a5h, a5));

        assertEquals(m1.getStr(a6h), a6);
        assertTrue(m1.hasDup(a6h, a6));

        assertEquals(m1.getStr(a7h), a7);
        assertTrue(m1.hasDup(a7h, a7));

        assertEquals(m1.getStr(a8h), a8);
        assertTrue(m1.hasDup(a8h, a8));

    }
}