package com.ashketshup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type Tui.
 */
public class TUI {
    private static final String CRITICAL_PREFIX = " ! ";
    private static final String VALID_PREFIX    = " ✓ ";
    private static final String WARNING_PREFIX  = " - ";
    private static final String TIP_PREFIX      = " | ";

    private static final ArrayList<String> criticals = new ArrayList<>();
    private static final ArrayList<String> valids    = new ArrayList<>();
    private static final ArrayList<String> warnings  = new ArrayList<>();
    private static final ArrayList<String> tips      = new ArrayList<>();

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
     * @param hidden the hidden
     */
    public static void writeln(String line, boolean hidden) {
        writeln("*".repeat(line.length()));
    }

    /**
     * Request input string.
     *
     * @return the string
     */
    public static String requestInput() {
        return new Scanner(System.in).nextLine();
    }

    /**
     * Gets all criticals.
     *
     * @return the all criticals
     */
    public static String getAllCriticals() {
        StringBuilder sumCriticals = new StringBuilder();

        for (String critical : TUI.criticals) {
            StringStyler x = new StringStyler(
                TUI.CRITICAL_PREFIX + critical,
                StringStyler.RED,
                StringStyler.BLINK,
                true
            );
            x.addStyleMode(StringStyler.UNDERLINE);

            sumCriticals.append(x).append("\n");
        }

        return sumCriticals.toString();
    }

    /**
     * Gets all valids.
     *
     * @return the all valids
     */
    public static String getAllValids() {
        StringBuilder sumValids = new StringBuilder();

        for (String valid : TUI.valids) {
            StringStyler x = new StringStyler(
                TUI.VALID_PREFIX + valid,
                StringStyler.GREEN,
                StringStyler.NORMAL,
                true
            );

            sumValids.append(x).append("\n");
        }

        return sumValids.toString();
    }

    /**
     * Gets all warnings.
     *
     * @return the all warnings
     */
    public static String getAllWarnings() {
        StringBuilder sumWarnings = new StringBuilder();

        for (String warning : TUI.warnings) {
            StringStyler x = new StringStyler(
                TUI.WARNING_PREFIX + warning,
                StringStyler.YELLOW,
                StringStyler.UNDERLINE,
                false
            );

            sumWarnings.append(x).append("\n");
        }

        return sumWarnings.toString();
    }

    /**
     * Gets all tips.
     *
     * @return the all tips
     */
    public static String getAllTips() {
        StringBuilder sumTips = new StringBuilder();

        for (String tip : TUI.tips) {
            StringStyler x = new StringStyler(
                TUI.TIP_PREFIX + tip,
                StringStyler.YELLOW,
                StringStyler.UNDERLINE,
                false
            );

            sumTips.append(x).append("\n");
        }

        return sumTips.toString();
    }

    /**
     * Gets all alarms.
     *
     * @return the all alarms
     */
    public static String getAllAlarms() {
        StringBuilder sumAlarms;
        sumAlarms = new StringBuilder("\n");

        if (!criticals.isEmpty()) {
            // Get all criticals as binking underlined red
            sumAlarms.append(getAllCriticals());
        }

        if (!valids.isEmpty()) {
            // Get all valids as green
            sumAlarms.append(getAllValids());
        }

        if (!warnings.isEmpty()) {
            // Get all warnings as yellow underlined
            sumAlarms.append(getAllWarnings());
        }

        if (!tips.isEmpty()) {
            // Get all tips as white
            sumAlarms.append(getAllTips());
        }

        return sumAlarms.toString();
    }

    private static void removeAllCriticals() {
        TUI.criticals.clear();
    }

    private static void removeAllValids() {
        TUI.valids.clear();
    }

    private static void removeAllWarnings() {
        TUI.warnings.clear();
    }

    private static void removeAllTips() {
        TUI.tips.clear();
    }

    /**
     * Create critical.
     *
     * @param s the s
     */
    public static void createCritical(String s) {
        TUI.criticals.add(s);
    }

    /**
     * Create valid.
     *
     * @param s the s
     */
    public static void createValid(String s) {
        TUI.valids.add(s);
    }

    /**
     * Create warning.
     *
     * @param s the s
     */
    public static void createWarning(String s) {
        TUI.warnings.add(s);
    }

    /**
     * Create tip.
     *
     * @param s the s
     */
    public static void createTip(String s) {
        TUI.tips.add(s);
    }

    /**
     * Remove all alarms.
     */
    public static void removeAllAlarms() {
        removeAllCriticals();
        removeAllValids();
        removeAllWarnings();
        removeAllTips();
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