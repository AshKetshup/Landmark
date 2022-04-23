package com.ashketshup.Landmark.TUI;

import java.util.ArrayList;

/**
 * The type Notifications.
 */
public class Notifications {
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

        for (String critical : Notifications.criticals) {
            StringStyler x = new StringStyler(
                Notifications.CRITICAL_PREFIX + critical,
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

        for (String valid : Notifications.valids) {
            StringStyler x = new StringStyler(
                Notifications.VALID_PREFIX + valid,
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

        for (String warning : Notifications.warnings) {
            StringStyler x = new StringStyler(
                Notifications.WARNING_PREFIX + warning,
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

        for (String tip : Notifications.tips) {
            StringStyler x = new StringStyler(
                Notifications.TIP_PREFIX + tip,
                StringStyler.YELLOW,
                StringStyler.UNDERLINE,
                false
            );

            sumTips.append(x).append("\n");
        }

        return sumTips.toString();
    }

    /**
     * Gets all notifications.
     *
     * @return the all notifications
     */
    public static String getAllNotifications() {
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

    /**
     * Remove all criticals.
     */
    public static void removeAllCriticals() {
        Notifications.criticals.clear();
    }

    /**
     * Remove all valids.
     */
    public static void removeAllValids() {
        Notifications.valids.clear();
    }

    /**
     * Remove all warnings.
     */
    public static void removeAllWarnings() {
        Notifications.warnings.clear();
    }

    /**
     * Remove all tips.
     */
    public static void removeAllTips() {
        Notifications.tips.clear();
    }

    /**
     * Create critical.
     *
     * @param s the s
     */
    public static void createCritical(String s) {
        Notifications.criticals.add(s);
    }

    /**
     * Create valid.
     *
     * @param s the s
     */
    public static void createValid(String s) {
        Notifications.valids.add(s);
    }

    /**
     * Create warning.
     *
     * @param s the s
     */
    public static void createWarning(String s) {
        Notifications.warnings.add(s);
    }

    /**
     * Create tip.
     *
     * @param s the s
     */
    public static void createTip(String s) {
        Notifications.tips.add(s);
    }

    /**
     * Remove all notifications.
     */
    public static void removeAllNotifications() {
        removeAllCriticals();
        removeAllValids();
        removeAllWarnings();
        removeAllTips();
    }
}
