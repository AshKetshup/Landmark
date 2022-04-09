package com.ashketshup;

import java.util.ArrayList;

/**
 * The type String styler.
 */
public class StringStyler {

    // Reset
    public static final String RESET = "\033[0m";

    // region Modes for CLI
    public final static int NORMAL = 0;
    public final static int BOLD = 1;
    public final static int MATE = 2;
    public final static int ITALIC = 3;
    public final static int UNDERLINE = 4;
    public final static int BLINK = 5;
    public final static int INVERTED_BACKGROUND = 7;
    // endregion
    // region Colors for CLI
    public final static int BLACK  = 30;
    public final static int RED    = 31;
    public final static int GREEN  = 32;
    public final static int YELLOW = 33;
    public final static int BLUE   = 34;
    public final static int PURPLE = 35;
    public final static int CYAN   = 36;
    public final static int WHITE  = 37;

    private final static int BRIGHT_STEP = 60;
    // endregion

    private static final String prefix = "\033[";
    private static final String sufix = "m";

    private int styleColor;
    private final ArrayList<Integer> styleMode = new ArrayList<>();
    private String styleContent = "";

    /**
     * Instantiates a new String styler.
     *
     * @param content the content
     * @param color   the color
     * @param mode    the mode
     */
    public StringStyler(String content, int color, int mode) {
        this.styleContent = content;
        this.styleColor   = color;
        this.styleMode.add(mode);
    }

    /**
     * Instantiates a new String styler.
     *
     * @param content the content
     * @param color   the color
     * @param mode    the mode
     * @param bright  the bright
     */
    public StringStyler(String content, int color, int mode, boolean bright) {
        this(content, color, mode);
        if (bright)
            this.styleColor += BRIGHT_STEP;
    }

    /**
     * Sets style content.
     *
     * @param content the content
     */
    public void setStyleContent(String content) {
        this.styleContent = content;
    }

    /**
     * Sets style color.
     *
     * @param color  the color
     * @param bright the bright
     */
    public void setStyleColor(int color, boolean bright) {
        if (bright)
            color += 60;

        this.styleColor = color;
    }

    private String translateStyleMode(int mode, int color) {
        return prefix + mode + ";" + color + sufix;
    }

    /**
     * Add style mode.
     *
     * @param mode the mode
     */
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