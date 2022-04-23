package com.ashketshup.Landmark.markdownParser;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Patterns {
    public static final Pattern HN = Pattern.compile("^\\s*#+ *(.+)", Pattern.MULTILINE);
    public static final Pattern BOLD = Pattern.compile("[\\*\\*(.+)\\*\\*]|[\\_\\_(.+)\\_\\_]", Pattern.MULTILINE);
    public static final Pattern ITALIC = Pattern.compile("[\\*(.+)\\*]|[\\_(.+)\\_]", Pattern.MULTILINE);
    public static final Pattern STRIKETHROUGH = Pattern.compile("~~(.+)~~", Pattern.MULTILINE);
    public static final Pattern CODE = Pattern.compile("`(.+)`", Pattern.MULTILINE);
    public static final Pattern CODEBLOCK = Pattern.compile("```\\w*\n((.+|\n*)+)```", Pattern.MULTILINE);
    public static final Pattern QUOTE = Pattern.compile("^\\s*>+ *(.+)", Pattern.MULTILINE);
    public static final Pattern TEXT = Pattern.compile("(.+)", Pattern.MULTILINE);

    public static final Pattern HLEVEL = Pattern.compile("^\\s*(#+)", Pattern.MULTILINE);

    public static boolean checkContains(String s, Pattern p) {
        Matcher m = p.matcher(s);
        return m.find();
    }

    public static int getHLevel(String s) {
        Matcher m = HLEVEL.matcher(s);
        if (m.find())
            return m.end() - m.start();

        return 0;
    }

    public static Pattern getNextPattern(String s) {
        Pattern[] patterns = { BOLD, ITALIC, STRIKETHROUGH, CODE, TEXT };
        Pattern nextPattern = null;

        /**
         * TODO: Change this from finding what is the next one to pick to a priority list
         */
        for (Pattern p : patterns) {
            Matcher m = p.matcher(s);
            if (m.find()) {
                nextPattern = p;
                break;
            }
        }

        return nextPattern;
    }
}
