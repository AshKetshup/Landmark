package com.ashketshup.TUI;

import java.io.IOException;

public class Output {
    /**
     * Writeln.
     *
     * @param line the line
     */
    public static void writeln(String line) {
        System.out.println(line);
    }

    /**
     * Writeln.
     *
     * @param line   the line
     */
    public static String hiddenLn(String line) {
        return "*".repeat(line.length());
    }

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
