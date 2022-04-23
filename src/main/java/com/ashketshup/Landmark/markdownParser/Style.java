package com.ashketshup.Landmark.markdownParser;

import com.ashketshup.Landmark.TUI.StringStyler;
import static com.ashketshup.Landmark.markdownParser.Patterns.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Style {
    private static Theme activeTheme;

    public Style(Theme theme) {
        activeTheme = theme;
    }

    public static StringStyler styleHn(String s, int level) {
        Matcher m = Patterns.HN.matcher(s);
        StringStyler result = null;

        if (m.find()) {
            String content = s.substring(m.start(), m.end());
            switch (level) {
                case 1:
                    result = activeTheme.styleH1(content);
                    break;
                case 2:
                    result = activeTheme.styleH2(content);
                    break;
                case 3:
                    result = activeTheme.styleH3(content);
                    break;
                case 4:
                    result = activeTheme.styleH4(content);
                    break;
                case 5:
                    result = activeTheme.styleH5(content);
                    break;
                case 6:
                    result = activeTheme.styleH6(content);
                    break;
                default:
                    result = activeTheme.styleText(content);
            }
        }

        return result;
    }

    public static StringStyler styleQuote(String s) {
        Matcher m = Patterns.QUOTE.matcher(s);
        StringStyler result = null;

        if (m.find()) {
            String content = s.substring(m.start(), m.end());

            result = activeTheme.styleQuote(content);
        }

        return result;
    }

    public static StringStyler styleBold(String s) {
        String result = checkForMoreStyles(s);

        return activeTheme.styleBold(result);
    }

    public static StringStyler styleItalic(String s) {
        String result = checkForMoreStyles(s);

        return activeTheme.styleItalic(result);
    }

    public static StringStyler styleCode(String s) {
        String result = checkForMoreStyles(s);

        return activeTheme.styleCode(result);
    }

    public static StringStyler styleStrikethrough(String s) {
        String result = checkForMoreStyles(s);

        return activeTheme.styleStrikethrough(result);
    }

    public static StringStyler styleText(String s) {
        String result = checkForMoreStyles(s);

        return activeTheme.styleText(result);
    }

    public static List<String> styleCodeBlock(String s) {
        List<String> result = new ArrayList<>();

        Matcher m = CODEBLOCK.matcher(s);
        boolean hasMatch = m.find();

        if (hasMatch) {
            String matched = m.group(1);

            String[] stringArray = {
                s.substring(0, m.start()),
                s.substring(m.end(), s.length() - 1)
            };

            result.addAll(Arrays.asList(stringArray[0].split("\n")));

            for (String x : matched.split("\n"))
                result.add(Style.activeTheme.styleCodeBlock(matched).toString());

            result.addAll(styleCodeBlock(stringArray[1]));

        }

        return result;
    }

    private static String checkForMoreStyles(String s) {
        Matcher nextPatternMatch = getNextPattern(s).matcher(s);
        String result = "";

        if (nextPatternMatch.find()) {
            String[] stringArray = {
                s.substring(0, nextPatternMatch.start()),
                s.substring(nextPatternMatch.end(), s.length() - 1)
            };

            String matched = nextPatternMatch.group(1);

            return stringArray[0] + selectStyle(matched, nextPatternMatch.pattern()) + styleText(stringArray[1]).toString();
        } else
            return s;
    }

    private static String selectStyle(String s, Pattern p) {
        Pattern[] patterns = { BOLD, ITALIC, STRIKETHROUGH, CODE, TEXT };

        for (int i = 0; i < patterns.length; i++)
            if (patterns[i].equals(p))
                switch (i+1) {
                    case 1:
                        return styleBold(s).toString();
                    case 2:
                        return styleItalic(s).toString();
                    case 3:
                        return styleStrikethrough(s).toString();
                    case 4:
                        return styleCode(s).toString();
                    case 5:
                    default:
                        break;
                }

        return s;
    }
}
