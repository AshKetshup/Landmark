package com.ashketshup.Landmark.markdownParser;

import com.ashketshup.Landmark.TUI.StringStyler;

import java.util.List;

public interface Theme {
    StringStyler styleH1(String s);
    StringStyler styleH2(String s);
    StringStyler styleH3(String s);
    StringStyler styleH4(String s);
    StringStyler styleH5(String s);
    StringStyler styleH6(String s);
    StringStyler styleQuote(String s);

    StringStyler styleBold(String s);
    StringStyler styleItalic(String s);
    StringStyler styleStrikethrough(String s);
    StringStyler styleCode(String s);

    List<StringStyler> styleCodeBlock(String s);

    StringStyler styleText(String s);
}
