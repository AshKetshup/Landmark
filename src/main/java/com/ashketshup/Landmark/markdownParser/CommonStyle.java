package com.ashketshup.Landmark.markdownParser;

import com.ashketshup.Landmark.TUI.StringStyler;

import java.util.ArrayList;
import java.util.List;

public class CommonStyle implements Theme {

    public CommonStyle() { }

    @Override
    public StringStyler styleH1(String s) {
        return new StringStyler(
            s,
            StringStyler.WHITE,
            StringStyler.UNDERLINE,
            true
        ).addStyleMode(StringStyler.BOLD)
         .addStyleMode(StringStyler.ITALIC);
    }

    @Override
    public StringStyler styleH2(String s) {
        return new StringStyler(
            s,
            StringStyler.WHITE,
            StringStyler.UNDERLINE,
            true
        );
    }

    @Override
    public StringStyler styleH3(String s) {
        return new StringStyler(
            s,
            StringStyler.WHITE,
            StringStyler.ITALIC,
            true
        );
    }

    @Override
    public StringStyler styleH4(String s) {
        return new StringStyler(
            s,
            StringStyler.WHITE,
            StringStyler.ITALIC
        ).addStyleMode(StringStyler.UNDERLINE);
    }

    @Override
    public StringStyler styleH5(String s) {
        return new StringStyler(
            s,
            StringStyler.WHITE,
            StringStyler.NORMAL
        );
    }

    @Override
    public StringStyler styleH6(String s) {
        return styleH5(s);
    }

    @Override
    public StringStyler styleQuote(String s) {
        return new StringStyler(
            s,
            StringStyler.WHITE,
            StringStyler.INVERTED_BACKGROUND
        );
    }

    @Override
    public StringStyler styleBold(String s) {
        return new StringStyler(
            s,
            StringStyler.WHITE,
            StringStyler.BOLD
        );
    }

    @Override
    public StringStyler styleItalic(String s) {
        return new StringStyler(
            s,
            StringStyler.WHITE,
            StringStyler.ITALIC
        );
    }

    @Override
    public StringStyler styleStrikethrough(String s) {
        return new StringStyler(
            s,
            StringStyler.WHITE,
            StringStyler.STRIKETHROUGH
        );
    }

    @Override
    public StringStyler styleCode(String s) {
        return new StringStyler(
            s,
            StringStyler.GREEN,
            StringStyler.NORMAL
        );
    }

    @Override
    public List<StringStyler> styleCodeBlock(String s) {
        String[] codeLines = s.split("\n");
        List<StringStyler> result = new ArrayList<>();
        for (String line : codeLines) {
            result.add(
                new StringStyler(
                    s,
                    StringStyler.GREEN,
                    StringStyler.NORMAL
                )
            );
        }

        return result;
    }

    @Override
    public StringStyler styleText(String s) {
        return new StringStyler(s);
    }
}
