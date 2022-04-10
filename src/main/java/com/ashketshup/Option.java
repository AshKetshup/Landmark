package com.ashketshup;

public class Option {
    private String optionText;
    private Foo optionFunction;

    public Option(String optText) {
        this.optionText = optText;
        this.optionFunction = null;
    }

    public Option(String optText, Foo optFunction) {
        this.optionText = optText;
        this.optionFunction = optFunction;
    }

    public Foo getOptionFunction() {
        return optionFunction;
    }

    public String getText() {
        return optionText;
    }

    public void setFunction(Foo function) {
        this.optionFunction = function;
    }

    public void setText(String text) {
        this.optionText = text;
    }
}
