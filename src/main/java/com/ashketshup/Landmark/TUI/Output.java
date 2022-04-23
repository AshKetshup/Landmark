package com.ashketshup.Landmark.TUI;

import java.io.IOException;
import java.util.Collections;

/**
 * The type Output.
 */
public class Output {
    /**
     * Writes given line.
     * Prints '\n' character at end of string.
     *
     * @param line the line
     */
    public static void writeln(String line) {
        System.out.println(line);
    }

    /**
     * Write line as hidden.
     * Returns a string of '*' of same size as given string.
     *
     * @param line the line
     * @return the string
     */
    public static String hiddenLn(String line) {
        return String.join("", Collections.nCopies(line.length(), "*"));
    }

    /**
     * Write a single line without '\n' at end.
     *
     * @param line the line
     */
    public static void write(String line) {
        System.out.print(line);
    }

    /**
     * Clear console.
     *
     * @throws IOException          the io exception
     * @throws InterruptedException the interrupted exception
     */
    public static void clearConsole() throws IOException, InterruptedException {

        String os = System.getProperty("os.name");
        ProcessBuilder pb;

        if (os.toLowerCase().contains("windows"))
            pb = new ProcessBuilder("cmd", "/c", "cls");
        else
            pb = new ProcessBuilder("clear");

        Process startProcess = pb.inheritIO().start();

        startProcess.waitFor();


        System.out.println("\033[H\033[2J");
    }
}
