package com.ashketshup.UIElements;

import com.ashketshup.Foo;
import com.ashketshup.TUI.StringStyler;

/**
 * The type Command.
 */
public class Command {
    private final boolean commandHidden;
    private final String commandTrigger;
    private final String commandDescription;
    private Foo commandFunction;

    /**
     * Instantiates a new Command.
     *
     * @param hidden      sets if command is hidden on screen
     * @param trigger     the trigger
     * @param description the description
     * @param function    the function
     */
    public Command(boolean hidden, String trigger, String description, Foo function) {
        this.commandHidden = hidden;
        this.commandTrigger = trigger;
        this.commandDescription = description;
        this.commandFunction = function;
    }

    /**
     * Instantiates a new Command.
     *
     * @param trigger     the trigger
     * @param description the description
     * @param function    the function
     */
    public Command(String trigger, String description, Foo function) {
        this(false, trigger, description, function);
    }

    /**
     * Instantiates a new Command.
     *
     * @param hidden   the hidden
     * @param trigger  the trigger
     * @param function the function
     */
    public Command(boolean hidden, String trigger, Foo function) {
        this(hidden, trigger, "", function);
    }

    /**
     * Instantiates a new Command.
     *
     * @param trigger  the trigger
     * @param function the function
     */
    public Command(String trigger, Foo function) {
        this(trigger, "", function);
    }

    /**
     * Gets command trigger.
     *
     * @return the command trigger
     */
    public String getCommandTrigger() {
        return commandTrigger;
    }

    /**
     * Gets command description.
     *
     * @return the command description
     */
    public String getCommandDescription() {
        return commandDescription;
    }

    /**
     * Gets command function.
     *
     * @return the command function
     */
    public Foo getCommandFunction() {
        return commandFunction;
    }

    public boolean isCommandHidden() {
        return commandHidden;
    }

    public boolean isCommandVisible() {
        return !commandHidden;
    }

    /**
     * Sets command function.
     *
     * @param commandFunction the command function
     */
    public void setCommandFunction(Foo commandFunction) {
        this.commandFunction = commandFunction;
    }

    @Override
    public String toString() {
        return new StringStyler(
            commandTrigger,
            StringStyler.WHITE,
            StringStyler.BOLD,
            true
        ) + " - " + commandDescription;
    }
}