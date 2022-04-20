package com.ashketshup;

import com.ashketshup.TUI.Input;
import com.ashketshup.TUI.Output;

public class Component implements Foo{
    private final boolean hidden;
    private final String prompt;
    private String answer = "";

    public Component(String prompt, boolean hidden) {
        this.prompt = prompt;
        this.hidden = hidden;
    }

    @Override
    public void apply() {
        answer = hidden ? Input.readHidden(prompt) : Input.readString(prompt);
    }

    @Override
    public String toString() {
        String printableAnswer = hidden ? Output.hiddenLn(answer) : answer;

        return prompt + " > " + printableAnswer;
    }
}