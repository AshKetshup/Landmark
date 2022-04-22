package com.ashketshup.UIElements;

import com.ashketshup.Foo;
import com.ashketshup.TUI.Input;
import com.ashketshup.TUI.Output;

public class Component implements Foo {
    private final boolean hidden;
    private final boolean required;
    private final String prompt;
    private String answer = "";

    public Component(String prompt, boolean hidden, boolean required) {
        this.required = required;
        this.hidden = hidden;
        this.prompt = prompt;
    }

    @Override
    public void apply() {
        this.answer = hidden ? Input.readHidden(prompt) : Input.readString(prompt);
    }

    public void setAnswer(String newAnswer) { this.answer = newAnswer; }

    @Override
    public String toString() {
        String printableAnswer = hidden ? Output.hiddenLn(answer) : answer;

        return prompt + " > " + printableAnswer;
    }
}