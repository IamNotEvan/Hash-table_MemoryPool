import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This class allows for the given input
 * file to be parsed through. It calls
 * methods on both the MemPool and HashTables
 * providing inserting, removing, and printing
 * abilities. This class also contains most of the
 * System.out.print statements.
 * 
 * @author Rowan Cusano
 * @author Evan Lee
 * @version 2022.12.5
 *
 */
public class CommandParser {

    private HashTable songHT;
    private HashTable artistHT;
    private MemPool memPool;
    private File input;

    /**
     * This is the constructor for the class.
     * 
     * @param input1
     *            The input file
     * @param hashSize
     *            The initial hashtable size
     * @param memorySize
     *            the intial mempool size
     */
    CommandParser(File input1, int hashSize, int memorySize) {
        this.input = input1;
        memPool = new MemPool(memorySize);
        songHT = new HashTable(hashSize, memPool, "Song");
        artistHT = new HashTable(hashSize, memPool, "Artist");
    }


    /**
     * This method does the actual parsing of the file.
     * It also calls all the methods to run the program.
     * 
     * @throws FileNotFoundException
     *             If the input file is not found
     */
    public void readInput() throws FileNotFoundException {

        Scanner file = new Scanner(input);
        String command = "";

        while (file.hasNextLine()) {
            if (!file.hasNext()) {
                break;
            }
            command = file.next();
            switch (command) {
                case "print":

                    String stm = file.next();
                    if (stm.equals("blocks")) {
                        // print free block
                        memPool.printBlocks();
                        break;
                    }
                    else if (stm.equals("songs")) {
                        // print songHT
                        System.out.print(songHT.toString());
                        System.out.println("total songs: " + songHT
                            .tableSize());
                        break;
                    }
                    else {
                        // print artistHT
                        // this is when print artist
                        System.out.print(artistHT.toString());
                        System.out.println("total artists: " + artistHT
                            .tableSize());
                        break;
                    }

                case "remove":
                    String sta = file.next();
                    if (sta.equals("song")) {
                        // remove song from songHT and memPool
                        // if not there memhandle size -2
                        String songT = file.nextLine();
                        songT = songT.substring(1);
                        if (songHT.remove(songT).getPos() != -2) {
                            System.out.println("|" + songT
                                + "| is removed from the song database.");
                        }
                        else {
                            System.out.println("|" + songT
                                + "| does not exist in the song database.");
                        }
                        break;
                    }
                    else {
                        // remove artist from artistHT and memPool
                        String artistT = file.nextLine();
                        artistT = artistT.substring(1);
                        int pos = artistHT.remove(artistT).getPos();
                        if (pos != -2) {
                            System.out.println("|" + artistT
                                + "| is removed from the artist database.");
                        }
                        else {
                            System.out.println("|" + artistT
                                + "| does not exist in the artist database.");
                        }
                        break;
                    }

                case "insert":

                    String sANDa = file.nextLine();
                    sANDa = sANDa.stripLeading();

                    if (sANDa.contains("<")) {

                        StringTokenizer st = new StringTokenizer(sANDa);
                        String artistT = st.nextToken("<");
                        String sep = st.nextToken(">");
                        String songT = st.nextToken("\n");
                        songT = songT.substring(1);

                        if (this.artistHT.insert(artistT)) {
                            System.out.println("|" + artistT
                                + "| is added to the artist database.");

                        }
                        else {
                            System.out.println("|" + artistT
                                + "| duplicates a record "
                                + "already in the artist database.");
                        }

                        if (this.songHT.insert(songT)) {

                            System.out.println("|" + songT
                                + "| is added to the song database.");
                        }
                        else {
                            System.out.println("|" + songT
                                + "| duplicates a record "
                                + "already in the song database.");
                        }

                        break;
                    }
                    // inserting a song or artist
                    else {
                        // insert a song
                        if (sANDa.substring(0, 4).equals("song")) {
                            String song = sANDa.substring(5);

                            if (this.songHT.insert(song)) {

                                System.out.println("|" + song
                                    + "| is added to the song database.");
                            }
                            else {
                                System.out.println("|" + song
                                    + "| duplicates a record"
                                    + " already in the song database.");
                            }
                            break;
                        }
                        // else insert artist
                        else {
                            String artist = sANDa.substring(7);

                            if (this.artistHT.insert(artist)) {
                                System.out.println("|" + artist
                                    + "| is added to the artist database.");

                            }
                            else {
                                System.out.println("|" + artist
                                    + "| duplicates a record "
                                    + "already in the artist database.");
                            }
                            break;
                        }
                    }
            }
        }
        file.close();
    }
}