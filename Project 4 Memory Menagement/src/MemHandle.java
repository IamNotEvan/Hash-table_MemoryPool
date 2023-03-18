/**
 * This class contains the MemHandle functions
 * and fields. A memhandle comprises of a position
 * and a size.
 * 
 * @author Rowan Cusano
 * @author Evan Lee
 * @version 2022.12.5
 *
 */
public class MemHandle {

    private int pos;
    private int size;

    /**
     * This is the constructor for the class.
     * 
     * @param memPos
     *            The position of the memhandle
     * @param byteSize
     *            The size of the memhandle
     */
    MemHandle(int memPos, int byteSize) {
        pos = memPos;
        size = byteSize;

    }


    /**
     * This gets the position of a
     * memhandle.
     * 
     * @return The position
     */
    public int getPos() {
        return pos;
    }


    /**
     * This sets the position of a memhandle.
     * 
     * @param pos
     *            The new position
     */
    public void setPos(int pos) {
        this.pos = pos;
    }


    /**
     * This gets the size of the memhandle.
     * 
     * @return the size
     */
    public int getSize() {
        return size;
    }


    /**
     * This method sets the size of the
     * memhandle.
     * 
     * @param size
     *            The new size
     */
    public void setSize(int size) {
        this.size = size;
    }

}