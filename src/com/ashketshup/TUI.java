package com.ashketshup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TUI {
    public static class StringStyler {

        // Reset
        public static final String RESET = "\033[0m";

        // <editor-fold desc="Modes for CLI">
        public final static int NORMAL = 0;
        public final static int BOLD = 1;
        public final static int MATE = 2;
        public final static int ITALIC = 3;
        public final static int UNDERLINE = 4;
        public final static int BLINK = 5;
        public final static int INVERTED_BACKGROUND = 7;
        // </editor-fold>

        // <editor-fold desc="Colors for CLI">
        public final static int BLACK  = 30;
        public final static int RED    = 31;
        public final static int GREEN  = 32;
        public final static int YELLOW = 33;
        public final static int BLUE   = 34;
        public final static int PURPLE = 35;
        public final static int CYAN   = 36;
        public final static int WHITE  = 37;

        private final static int BRIGHT_STEP = 60;
        // </editor-fold>

        private static final String prefix = "\033[";
        private static final String sufix = "m";

        private int styleColor;
        private final ArrayList<Integer> styleMode = new ArrayList<>();
        private String styleContent = "";

        public StringStyler(String content, int color, int mode) {
            this.styleContent = content;
            this.styleColor   = color;
            this.styleMode.add(mode);
        }

        public StringStyler(String content, int color, int mode, boolean bright) {
            this(content, color, mode);
            if (bright)
                this.styleColor += BRIGHT_STEP;
        }

        public void setStyleContent(String content) {
            this.styleContent = content;
        }

        public void setStyleColor(int color, boolean bright) {
            if (bright)
                color += 60;

            this.styleColor = color;
        }

        private String translateStyleMode(int mode, int color) {
            return prefix + mode + ";" + color + sufix;
        }

        public void addStyleMode(int mode) {
            this.styleContent =
                translateStyleMode(mode, this.styleColor) + this.styleContent;
        }

        public String toString() {
            StringBuilder styles = new StringBuilder();
            for (Integer integer : this.styleMode)
                styles.append(translateStyleMode(integer, this.styleColor));

            return styles + styleContent + RESET;
        }
    }

    private static final String CRITICAL_PREFIX = " ! ";
    private static final String VALID_PREFIX    = " ✓ ";
    private static final String WARNING_PREFIX  = " - ";
    private static final String TIP_PREFIX      = " | ";

    private static final ArrayList<String> criticals = new ArrayList<>();
    private static final ArrayList<String> valids    = new ArrayList<>();
    private static final ArrayList<String> warnings  = new ArrayList<>();
    private static final ArrayList<String> tips      = new ArrayList<>();

    public static void writeln(String line) {
        System.out.println(line);
    }

    public static void writeln(String line, boolean hidden) {
        writeln("*".repeat(line.length()));
    }

    public static String requestInput() {
        return new Scanner(System.in).nextLine();
    }

    public static String getAllCriticals() {
        StringBuilder sumCriticals = new StringBuilder(TUI.CRITICAL_PREFIX);

        for (String critical : TUI.criticals) {
            StringStyler x = new StringStyler(
                critical,
                StringStyler.RED,
                StringStyler.BLINK,
                true
            );
            x.addStyleMode(StringStyler.UNDERLINE);

            sumCriticals.append(x).append("\n");
        }

        return sumCriticals.toString();
    }

    public static String getAllValids() {
        StringBuilder sumValids = new StringBuilder(TUI.VALID_PREFIX);

        for (String valid : TUI.valids) {
            StringStyler x = new StringStyler(
                valid,
                StringStyler.GREEN,
                StringStyler.NORMAL,
                true
            );

            sumValids.append(x).append("\n");
        }

        return sumValids.toString();
    }

    public static String getAllWarnings() {
        StringBuilder sumWarnings = new StringBuilder(TUI.WARNING_PREFIX);

        for (String warning : TUI.warnings) {
            StringStyler x = new StringStyler(
                warning,
                StringStyler.YELLOW,
                StringStyler.UNDERLINE,
                false
            );

            sumWarnings.append(x).append("\n");
        }

        return sumWarnings.toString();
    }

    public static String getAllTips() {
        StringBuilder sumTips = new StringBuilder(TUI.TIP_PREFIX);

        for (String tip : TUI.tips) {
            StringStyler x = new StringStyler(
                tip,
                StringStyler.YELLOW,
                StringStyler.UNDERLINE,
                false
            );

            sumTips.append(x).append("\n");
        }

        return sumTips.toString();
    }

    public static String getAllAlarms() {
        StringBuilder sumAlarms;
        sumAlarms = new StringBuilder("\n");

        // Get all criticals as binking underlined red
        sumAlarms.append(getAllCriticals());

        // Get all valids as green
        sumAlarms.append(getAllValids());

        // Get all warnings as yellow underlined
        sumAlarms.append(getAllWarnings());

        // Get all tips as white
        sumAlarms.append(getAllTips());

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

    public static void createCritical(String s) {
        TUI.criticals.add(s);
    }

    public static void createValid(String s) {
        TUI.valids.add(s);
    }

    public static void createWarning(String s) {
        TUI.warnings.add(s);
    }

    public static void createTip(String s) {
        TUI.tips.add(s);
    }

    public static void removeAllAlarms() {
        removeAllCriticals();
        removeAllValids();
        removeAllWarnings();
        removeAllTips();
    }

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