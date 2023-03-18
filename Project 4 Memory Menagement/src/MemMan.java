import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class is the main of the project.
 * It will call a command parser that runs through
 * the input file and will call methods.
 * 
 * @author Rowan Cusano
 * @author Evan Lee
 * @version 2022.12.5
 *
 */
public class MemMan {

    /**
     * This is the main method that will run
     * the entire project.
     * 
     * @param args
     *            The array of string arguments
     * @throws FileNotFoundException
     *             If the file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {

        String hashSize = args[0];
        String blockSize = args[1];
        String fileName = args[2];

        File inputFile = new File(fileName);
        int hSize = Integer.parseInt(hashSize);
        int bSize = Integer.parseInt(blockSize);

        CommandParser command = new CommandParser(inputFile, hSize, bSize);

        command.readInput();
    }

}
