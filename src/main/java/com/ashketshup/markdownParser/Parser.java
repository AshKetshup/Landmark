package com.ashketshup.markdownParser;

import com.ashketshup.TUI.StringStyler;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static List<StringStyler> getMarkdownStyled(String content) {
        List<StringStyler> result = new ArrayList<>();

        // Translates each CodeBlock syntax into its style and returns List<Style>
        List<String> manip = new ArrayList<>(Style.styleCodeBlock(content));

        for (String s : manip) {
            if (Patterns.checkContains(s, Patterns.HN)) {
                int level = Patterns.getHLevel(s);
                result.add(Style.styleHn(s, level));
            } else if (Patterns.checkContains(s, Patterns.QUOTE))
                result.add(Style.styleQuote(s));
            else if (Patterns.checkContains(s, Patterns.TEXT))
                result.add(Style.styleText(s));
            else
                result.add(new StringStyler(""));
        }

        return result;
    }
}
