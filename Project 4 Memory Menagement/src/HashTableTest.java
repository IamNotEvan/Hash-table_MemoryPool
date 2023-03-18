import student.TestCase;

/**
 * This tests all of the public and
 * private methods in the HashTable clas.
 * 
 * @author Rowan Cusano
 * @author Even Lee
 * @version 2022.12.5
 *
 */
public class HashTableTest extends TestCase {

    private HashTable table1;

    /**
     * This sets up the tests for the class
     */
    public void setUp() {
        MemPool pool1 = new MemPool(20);
        table1 = new HashTable(10, pool1, "Song");
    }


    /**
     * This tests the getTable method.
     */
    public void testGetTable() {

        assertNotNull(table1.getTable());
        assertEquals(10, table1.getTable().length);
    }


    /**
     * This tests that the find function
     * returns the proper value, even after
     * more inserts and removes.
     */
    public void testFind() {

        table1.insert("hello");
        table1.insert("world");
        table1.insert("again");
        table1.insert("help");
        table1.insert("me");
        System.out.print(table1.toString());

        assertEquals(-1, table1.find("why"));
        assertEquals(7, table1.find("hello"));
        assertEquals(8, table1.find("world"));

        table1.remove("hello");
        System.out.print(table1.toString());
        assertEquals(-1, table1.find("hello"));
        table1.remove("again");
        System.out.print(table1.toString());
        assertEquals(-1, table1.find("again"));

        table1.insert("hello");
        assertEquals(7, table1.find("hello"));
        System.out.print(table1.toString());

        table1.insert("again");
        assertEquals(1, table1.find("again"));
    }


    /**
     * This tests the insert and
     * remove functions after multiple
     * runs of each
     */
    public void testComplex() {

        assertTrue(table1.insert("hello"));
        assertEquals(table1.tableSize(), 1);
        assertTrue(table1.insert("world"));
        assertEquals(table1.tableSize(), 2);
        table1.insert("again");
        table1.insert("help");
        table1.insert("me");

        assertEquals(-2, table1.remove("wrong").getPos());
        assertEquals(0, table1.remove("hello").getPos());
        assertEquals(7, table1.remove("world").getPos());

        assertTrue(table1.insert("hello"));
// assertEquals(0, table1.remove("hello").getPos());
    }
}