package com.ashketshup;

public class Command {
    private boolean commandHidden;
    private String commandTrigger;
    private String commandDescription;
    private Foo commandFunction;

    public Command(boolean hidden, String trigger, String description, Foo function) {
        this.commandHidden = hidden;
        this.commandTrigger = trigger;
        this.commandDescription = description;
        this.commandFunction = function;
    }

    public Command(String trigger, String description, Foo function) {
        this(false, trigger, description, function);
    }

    public Command(boolean hidden, String trigger, Foo function) {
        this(hidden, trigger, "", function);
    }

    public Command(String trigger, Foo function) {
        this(trigger, "", function);
    }

    public String getCommandTrigger() {
        return commandTrigger;
    }

    public String getCommandDescription() {
        return commandDescription;
    }

    public Foo getCommandFunction() {
        return commandFunction;
    }

    public void setCommandTrigger(String commandTrigger) {
        this.commandTrigger = commandTrigger;
    }

    public void setCommandDescription(String commandDescription) {
        this.commandDescription = commandDescription;
    }

    public void setCommandFunction(Foo commandFunction) {
        this.commandFunction = commandFunction;
    }

    @Override
    public String toString() {
        return new TUI.StringStyler(
            commandTrigger,
            TUI.StringStyler.WHITE,
            TUI.StringStyler.BOLD,
            true
        ) + " - " + commandDescription;
    }
}