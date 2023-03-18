/**
 * This is the Hash Table class. It contains all
 * the methods neccessary to run a hash table. It
 * can insert (using an sfold alogrithm), remove, probe,
 * and grow.
 * 
 * @author Rowan Cusano
 * @author Evan Lee
 * @version 2022.11.30
 *
 */
public class HashTable {

    private MemHandle[] hashTable;
    private int size;
    private int capacity;
    private MemPool memMan;
    private String type;

    /**
     * This is the constructor for the class.
     * 
     * @param tableSize
     *            The size of the hashtable
     * @param memMan1
     *            The mempool containing memory
     * @param type1
     *            The type of the hashtable
     */
    public HashTable(int tableSize, MemPool memMan1, String type1) {

        capacity = 0;
        size = tableSize;
        hashTable = new MemHandle[size];
        memMan = memMan1;
        type = type1;
    }


    /**
     * This inserts a string into the hash table.
     * 
     * @param str
     *            The string to be inserted
     * @return True if inserted, false if not
     */
    public boolean insert(String str) {

        if (find(str) != -1) {
            return false;
            // this would be duplicate if inserted
        }

        // Grow table when capacity is 50%
        if (capacity == (size / 2)) {
            growTable();
        }

        MemHandle handle = memMan.insert(str);

        int position = sFoldHash(str);
        int newPos = collision(position);

        hashTable[newPos] = handle;
        capacity++;

        return true;
    }


    /**
     * Checks if there is a collision and
     * returns a new position if there is one.
     * If there is no collision, the original
     * position is returned.
     * 
     * @param pos
     *            The position to check
     * @return The position to insert at
     */
    private int collision(int pos) {

        if (hashTable[pos] == null) {
            return pos;
        }
        // Check if tombstone
        else if (hashTable[pos].getPos() == -1) {
            return pos;
        }
        // Not tombstone, but not null
        else { // if (hashTable[pos].getPos() != -1) {
            return quadraticProbing(pos);
        }
    }


    private int quadraticProbing(int pos) {

        int i = 1;
        boolean found = false;
        int quadPos = 0;

        while (!found) {

            quadPos = pos + (i * i);

            if (quadPos >= size) {
                quadPos = quadPos % size;
            }

            if (hashTable[quadPos] == null || hashTable[quadPos]
                .getPos() == -1) {
                found = true;
            }
            i++;
        }

        return quadPos;
    }


    /**
     * This removes a string from the hashtable.
     * 
     * @param str
     *            The string to be removed
     * @return The MemHandle of the removed string
     */
    public MemHandle remove(String str) {

        int find = find(str);

        if (find == -1) {
            return new MemHandle(-2, 0);
        }

        MemHandle removed = hashTable[find];
        MemHandle tombstone = new MemHandle(-1, 0);
        hashTable[find] = tombstone;
        memMan.remove(removed);
        capacity--;

        return removed;
    }


    /**
     * This function grows the table when it
     * reaches 50% capacity.
     */
    private void growTable() {

        MemHandle[] copy = new MemHandle[size];
        for (int j = 0; j < copy.length; j++) {
            if (hashTable[j] != null) {
                copy[j] = hashTable[j];
            }
        }

        hashTable = new MemHandle[size * 2];
        size = size * 2;

        for (int i = 0; i < copy.length; i++) {
            if (copy[i] != null && copy[i].getPos() != -1) {
                String rehash = memMan.getStr(copy[i]);
                int position = sFoldHash(rehash);
                int newPos = collision(position);
                hashTable[newPos] = copy[i];
            }
        }
        System.out.print(type + " hash table size doubled.\n");
    }


    /**
     * This gets the hashTable.
     * 
     * @return the hashtable
     */
    public MemHandle[] getTable() {
        return hashTable;
    }


    /**
     * This checks if a string is already in
     * the table and returns its position.
     * It returns -1 if not found.
     * 
     * @param str
     *            The string to find
     * @return The position of the string, -1 if not there
     */
    public int find(String str) {

        int home = sFoldHash(str);
        int position = home;

        int probeNum = 1;
        boolean probing = false;
        while (!probing) 
        {

            if (hashTable[position] == null) 
            {
                probing = true;
            }
            else {

                if (memMan.hasDup(hashTable[position], str)) {

                    return position;
                    // the handle matches a string in mempool
                }

                position = home + (probeNum * probeNum);
                probeNum++;

                if (position >= size) {
                    position = position % size;
                }
            }
        }

        return -1;
    }


    /**
     * This returns a string of the entries
     * in the hashtable
     * 
     * @return The string of the entries
     */
    public String toString() {

        String str = "";

        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null && hashTable[i].getPos() != -1) {
                String name = memMan.getStr(hashTable[i]);
                str += "|" + name + "| " + i + "\n";
            }
        }

        return str;
    }


    /**
     * Returns the number of items in the table
     * 
     * @return the number of items
     */
    public int tableSize() {
        return capacity;
    }


    /**
     * This takes in a string and calculates its
     * position in the hash table.
     * 
     * @param str
     *            The string to calculate
     * @return The position in the table
     * @author Patrick Sullivan
     */
    public int sFoldHash(String str) {

        int intLength = str.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char[] c = str.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }
        char[] c = str.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }
        return (int)Math.abs(sum % hashTable.length);
    }
}