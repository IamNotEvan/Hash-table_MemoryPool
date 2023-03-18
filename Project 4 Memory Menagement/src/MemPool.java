/**
 * This class contains the methods and fields
 * for the MemPool. The MemPool contains the
 * memory used by the program.
 * 
 * @author Rowan Cusano
 * @author Evan Lee
 * @version 2022.12.5
 *
 */
public class MemPool {

    private byte[] space;
    private DoubleLL freeBlocks;
    private int size1;

    /**
     * This is the constructor for the
     * MemPool class.
     * 
     * @param poolSize
     *            The initial size of the pool
     */
    MemPool(int poolSize) {
        this.size1 = poolSize;
        setSpace(new byte[poolSize]);
        setFreeBlocks(new DoubleLL());
        FreeBlock fBlock = new FreeBlock(0, poolSize);
        freeBlocks.insert(fBlock);
    }


    /**
     * This gets the space array containing
     * the memory.
     * 
     * @return The space array
     */
    public byte[] getSpace() {
        return space;
    }


    /**
     * This sets the space array to
     * a different one if necessary.
     * 
     * @param space
     *            The new array
     */
    public void setSpace(byte[] space) {
        this.space = space;
    }


    /**
     * This gets the free block list.
     * 
     * @return the list of free blocks
     */
    public DoubleLL getFreeBlocks() {
        return freeBlocks;
    }


    /**
     * This allows for the free block list
     * to be replaced.
     * 
     * @param freeBlocks
     *            The new list
     */
    public void setFreeBlocks(DoubleLL freeBlocks) {
        this.freeBlocks = freeBlocks;
    }


    /**
     * This inserts a string into the mempool.
     * 
     * @param str
     *            The string to insert
     * @return The MemHandle of the inserted
     */
    public MemHandle insert(String str) {

        // new bytes will have the byte array based on string
        byte[] tempBytes = str.getBytes();
        int byteSize = tempBytes.length + 2;
        byte[] kBytes = new byte[2];
        byte[] newBytes = new byte[byteSize];
        System.arraycopy(kBytes, 0, newBytes, 0, 2);
        System.arraycopy(tempBytes, 0, newBytes, 2, byteSize - 2);

        FreeBlock fb = this.bestFit(newBytes);
        if (fb == null) {
            fb = this.bestFit(newBytes);
        }
        freeBlocks.search(fb);
        freeBlocks.remove();

        if (fb.getLength() > byteSize) {
            FreeBlock newFB = new FreeBlock(fb.getPos() + byteSize, fb
                .getLength() - byteSize);
            this.insertInFBL(newFB);
        }
        System.arraycopy(newBytes, 0, space, fb.getPos(), byteSize);
        MemHandle mH = new MemHandle(fb.getPos(), byteSize);

        return mH;
    }


    /**
     * This inserts into the free block list.
     * 
     * @param fb
     *            The freeblock to insert
     */
    public void insertInFBL(FreeBlock fb) {
        if (this.merge(fb)) {
        }
        else {
            freeBlocks.moveToLast();
            if (freeBlocks.isEmpty()) {
                freeBlocks.append(fb);
            }
            else if (((FreeBlock)freeBlocks.getCurr().getObject()).getPos() < fb
                .getPos()) {
                freeBlocks.append(fb);
            }
            else {
                freeBlocks.moveToStart();
                int size = freeBlocks.getListSize();
                for (int i = 0; i < size; i++) {
                    int pos = fb.getPos();
                    if (((FreeBlock)freeBlocks.getCurr().getObject())
                        .getPos() > pos) {

                        freeBlocks.insert(fb);
                        break;
                    }
                    freeBlocks.next();
                }
            }
        }
    }


    /**
     * This merges free blocks together.
     * 
     * @param fb
     *            The freeblock to merge
     * @return True if merged, false if not
     */
    public boolean merge(FreeBlock fb) {
        boolean merge = false;
        int sPos = fb.getPos();
        int ePos = sPos + fb.getLength();
        freeBlocks.moveToStart();
        for (int i = 0; i < freeBlocks.getListSize(); i++) {
            FreeBlock f = ((FreeBlock)freeBlocks.getCurr().getObject());
            int s = f.getPos();
            int e = s + f.getLength();
            if (e == sPos) {

                f.setLength(ePos - s);
                merge = true;

            }
            if (ePos == s) {
                if (merge) {
                    freeBlocks.remove();
                    FreeBlock prev = (FreeBlock)freeBlocks.prev().getObject();
                    prev.setLength(prev.getLength() + f.getLength());
                }
                else {
                    merge = true;
                    FreeBlock newF = (FreeBlock)freeBlocks.getCurr()
                        .getObject();
                    newF.setPos(sPos);
                    newF.setLength(fb.getLength() + f.getLength());
                }

            }
            freeBlocks.next();

        }

        return merge;
    }


    /**
     * This expands the mempool when necessary.
     */
    public void expand() {
        int size = space.length;
        byte[] newSpace = new byte[size + this.size1];
        System.arraycopy(space, 0, newSpace, 0, space.length);
        space = newSpace;
        FreeBlock fb = new FreeBlock(size, this.size1);
        boolean ab = this.merge(fb);
        if (!ab) {
            freeBlocks.append(fb);
        }
        System.out.println("Memory pool expanded to be " + (size + this.size1)
            + " bytes.");
    }


    /**
     * This removes a memhandle from the pool
     * Free a block at the position specified by theHandle.
     * Merge adjacent free blocks.
     * 
     * @param theHandle
     *            The memhandle to remove
     */
    public void remove(MemHandle theHandle) {
        int pos = theHandle.getPos();
        int size = theHandle.getSize();
        FreeBlock fb = new FreeBlock(pos, size);
        this.insertInFBL(fb);
        byte[] fBytes = new byte[size];
        System.arraycopy(fBytes, 0, space, pos, size);

    }


    /**
     * This checks if a memhandle and string are
     * already in the pool.
     * 
     * @param theHandle
     *            The handle to check
     * @param str
     *            The string to check
     * @return True if in there, false if not
     */
    public boolean hasDup(MemHandle theHandle, String str) {
        if (getStr(theHandle).equals("na")) {
            return false;
        }
        else if (getStr(theHandle).equals(str)) {
            return true;
        }
        return false;
    }


    /**
     * This returns the string representation
     * of a memhanlde
     * 
     * @param mh
     *            The memhandle to get
     * @return The string of the memhandle
     */
    public String getStr(MemHandle mh) {
        if (mh.getPos() == -1) {
            return "na";
        }
        int pos = mh.getPos() + 2;
        int size = mh.getSize() - 2;
        byte[] bytes = new byte[size];

        System.arraycopy(space, pos, bytes, 0, size);

        String newStr = new String(bytes);

        return newStr;
    }


    /**
     * This returns the best fit free block
     * that should be inserted into.
     * 
     * @param bytes
     *            The bytes to be inserted
     * @return The best free block in the list
     */
    public FreeBlock bestFit(byte[] bytes) {
        int min = 99999999;
        int tmin = 1;
        FreeBlock returnFB = null;
        if (freeBlocks.isEmpty()) {
            this.expand();
            return null;
        }
        else {
            int byteLength = bytes.length;
            freeBlocks.moveToStart();
            for (int i = 0; i < freeBlocks.getListSize(); i++) {
                FreeBlock fb = (FreeBlock)freeBlocks.getCurr().getObject();
                int fbSize = fb.getLength();
                if (fbSize >= byteLength) {
                    tmin = fbSize - byteLength;
                    if (tmin <= min) {
                        min = tmin;
                        returnFB = (FreeBlock)freeBlocks.getCurr().getObject();
                    }
                }
                freeBlocks.next();
            }
        }

        if (returnFB == null) {
            this.expand();
            returnFB = this.bestFit(bytes);
        }

        return returnFB;
    }


    /**
     * This creates a string representation
     * of the mempool.
     * 
     * @return The string of the pool
     */
    public String printBlocks() {
        if (freeBlocks.isEmpty()) {

            String empty = new String("(" + space.length + ",0)");
            System.out.println(empty); 
            return empty;
        }
        StringBuilder str = new StringBuilder("");
        freeBlocks.moveToStart();
        for (int i = 0; i < freeBlocks.getListSize() - 1; i++) {
            String newS = freeBlocks.getCurr().getObject().toString();
            str.append(newS);
            str.append(" -> ");
            freeBlocks.next();
        }
        freeBlocks.moveToLast();
        String lastS = freeBlocks.getCurr().getObject().toString();
        str.append(lastS);
        System.out.println(str.toString());
        return str.toString();
    }

}