package it.unibo.oop.lab.mvcio;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 
 */
public class Controller {

    private static final String PATH = System.getProperty("user.home") 
            + System.getProperty("file.separator") + "output.txt";

    /*
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * Implement this class with:
     * 
     * 1) A method for setting a File as current file
     */
    /**
     * setting new default File.
     */
    private File current = new File(PATH); 

    /**
     * @param fl
     *          the new file to track
     */
    public void setCurrentFile(final File fl) {
        this.current = fl;
    }
     /* 2) A method for getting the current File
     */ 
    /**
     * @return current file
     */
    public File getFile() {
        return  this.current;
    }
     /* 3) A method for getting the path (in form of String) of the current File
     */
    /**
     * @return pathToFile
     */
    public String getPath() {
        return this.current.getAbsolutePath();
    }
     /* 4) A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     */
    /**
     * Writes on files the input String.
     * 
     * @param s String
     *          the string to write on file
     * @throws IOException
     *
     */
    public void writeStringOnFile(final String s) throws IOException {
        try (PrintStream ps = new PrintStream(this.current)) {
            ps.print(s);
        }
    }
     /* 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */
}
