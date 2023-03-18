/**
 * This class contains the methods for
 * the free block class. A free block contains
 * a position and a length.
 * 
 * @author Rowan Cusano
 * @author Evan Lee
 * @version 2022.12.5
 *
 */
public class FreeBlock {

    private int pos;
    private int length;

    /**
     * This is the constructor for the class.
     * 
     * @param pos1
     *            The position of the free block
     * @param length1
     *            The length of the free block
     */
    FreeBlock(int pos1, int length1) {
        this.setPos(pos1);
        this.setLength(length1);
    }


    /**
     * This gets the position of
     * the free block.
     * 
     * @return The position
     */
    public int getPos() {
        return pos;
    }


    /**
     * This sets the position of the
     * free block.
     * 
     * @param pos
     *            The new position
     */
    public void setPos(int pos) {
        this.pos = pos;
    }


    /**
     * This gets the length of
     * the free block.
     * 
     * @return The length
     */
    public int getLength() {
        return length;
    }


    /**
     * This sets the length of
     * the free block.
     * 
     * @param length
     *            The new length
     */
    public void setLength(int length) {
        this.length = length;
    }


    /**
     * This returns a string version of
     * the free block
     * 
     * @return The string representation
     */
    public String toString() {
        return "(" + this.pos + "," + this.length + ")";
    }

}