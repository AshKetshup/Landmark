package com.ashketshup.Landmark.UIElements;

import com.ashketshup.Landmark.Interfaces.Foo;

/**
 * The type Option.
 */
public class Option {
    private String optionText;
    private Foo optionFunction;

    /**
     * Instantiates a new Option.
     *
     * @param text the opt text
     */
    public Option(String text) {
        this.optionText = text;
        this.optionFunction = null;
    }

    /**
     * Instantiates a new Option.
     *
     * @param text     the text
     * @param function the opt function
     */
    public Option(String text, Foo function) {
        this.optionText = text;
        this.optionFunction = function;
    }

    /**
     * Gets option function.
     *
     * @return the option function
     */
    public Foo getOptionFunction() {
        return optionFunction;
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return optionText;
    }

    /**
     * Sets function.
     *
     * @param function the function
     */
    public void setFunction(Foo function) {
        this.optionFunction = function;
    }

    /**
     * Sets text.
     *
     * @param text the text
     */
    public void setText(String text) {
        this.optionText = text;
    }
}
