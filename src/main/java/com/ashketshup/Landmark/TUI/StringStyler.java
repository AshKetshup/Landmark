package com.ashketshup.Landmark.TUI;

import java.util.ArrayList;

/**
 * The type String styler.
 * Class to make the use of escape characters easier.
 */
public class StringStyler {

    /**
     * The constant RESET.
     */
    public static final String RESET = "\033[0m";

    // region Modes for CLI
    /**
     * The constant NORMAL.
     */
    public final static int NORMAL = 0;
    /**
     * The constant BOLD.
     */
    public final static int BOLD = 1;
    /**
     * The constant DISABLED.
     */
    public final static int DISABLED = 2;
    /**
     * The constant ITALIC.
     */
    public final static int ITALIC = 3;
    /**
     * The constant UNDERLINE.
     */
    public final static int UNDERLINE = 4;
    /**
     * The constant BLINK.
     */
    public final static int BLINK = 5;
    /**
     * The constant INVERTED_BACKGROUND.
     */
    public final static int INVERTED_BACKGROUND = 7;
    /**
     * The constant INVERTED_FOREGROUND.
     */
    public final static int INVERTED_FOREGROUND = 8;
    /**
     * The constant STRIKETHROUGH.
     */
    public final static int STRIKETHROUGH = 9;
    // endregion

    // region Colors for CLI
    /**
     * The constant BLACK.
     */
    public final static int BLACK  = 30;
    /**
     * The constant RED.
     */
    public final static int RED    = 31;
    /**
     * The constant GREEN.
     */
    public final static int GREEN  = 32;
    /**
     * The constant YELLOW.
     */
    public final static int YELLOW = 33;
    /**
     * The constant BLUE.
     */
    public final static int BLUE   = 34;
    /**
     * The constant PURPLE.
     */
    public final static int PURPLE = 35;
    /**
     * The constant CYAN.
     */
    public final static int CYAN   = 36;
    /**
     * The constant WHITE.
     */
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
     * Instantiates a new String styler with common parameters.
     *
     * @param content the content
     */
    public StringStyler(String content) {
        this(content, StringStyler.WHITE, StringStyler.NORMAL);
    }

    /**
     * Sets style content.
     *
     * @param content the content
     * @return the this
     */
    public StringStyler setStyleContent(String content) {
        this.styleContent = content;

        return this;
    }

    /**
     * Sets style color.
     *
     * @param color  the color
     * @param bright the bright
     * @return this
     */
    public StringStyler setStyleColor(int color, boolean bright) {
        if (bright)
            color += 60;

        this.styleColor = color;
        return this;
    }

    private String translateStyleMode(int mode, int color) {
        return prefix + mode + ";" + color + sufix;
    }

    /**
     * Add style mode.
     *
     * @param mode the mode
     * @return this
     */
    public StringStyler addStyleMode(int mode) {
        this.styleContent =
            translateStyleMode(mode, this.styleColor) + this.styleContent;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder styles = new StringBuilder();
        for (Integer integer : this.styleMode)
            styles.append(translateStyleMode(integer, this.styleColor));

        return styles + styleContent + RESET;
    }
}