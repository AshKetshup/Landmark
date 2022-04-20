package com.ashketshup.TUI;

import java.util.ArrayList;

public class Alarms {
    private static final String CRITICAL_PREFIX = " ! ";
    private static final String VALID_PREFIX    = " ✓ ";
    private static final String WARNING_PREFIX  = " - ";
    private static final String TIP_PREFIX      = " | ";

    private static final ArrayList<String> criticals = new ArrayList<>();
    private static final ArrayList<String> valids    = new ArrayList<>();
    private static final ArrayList<String> warnings  = new ArrayList<>();
    private static final ArrayList<String> tips      = new ArrayList<>();
    
    /**
     * Gets all criticals.
     *
     * @return the all criticals
     */
    public static String getAllCriticals() {
        StringBuilder sumCriticals = new StringBuilder();

        for (String critical : Alarms.criticals) {
            StringStyler x = new StringStyler(
                Alarms.CRITICAL_PREFIX + critical,
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

        for (String valid : Alarms.valids) {
            StringStyler x = new StringStyler(
                Alarms.VALID_PREFIX + valid,
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

        for (String warning : Alarms.warnings) {
            StringStyler x = new StringStyler(
                Alarms.WARNING_PREFIX + warning,
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

        for (String tip : Alarms.tips) {
            StringStyler x = new StringStyler(
                Alarms.TIP_PREFIX + tip,
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
        Alarms.criticals.clear();
    }

    private static void removeAllValids() {
        Alarms.valids.clear();
    }

    private static void removeAllWarnings() {
        Alarms.warnings.clear();
    }

    private static void removeAllTips() {
        Alarms.tips.clear();
    }

    /**
     * Create critical.
     *
     * @param s the s
     */
    public static void createCritical(String s) {
        Alarms.criticals.add(s);
    }

    /**
     * Create valid.
     *
     * @param s the s
     */
    public static void createValid(String s) {
        Alarms.valids.add(s);
    }

    /**
     * Create warning.
     *
     * @param s the s
     */
    public static void createWarning(String s) {
        Alarms.warnings.add(s);
    }

    /**
     * Create tip.
     *
     * @param s the s
     */
    public static void createTip(String s) {
        Alarms.tips.add(s);
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
}
