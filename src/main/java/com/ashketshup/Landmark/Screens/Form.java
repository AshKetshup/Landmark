package com.ashketshup.Landmark.Screens;

import com.ashketshup.Landmark.Interfaces.Foo;
import com.ashketshup.Landmark.Interfaces.FooRecieve;
import com.ashketshup.Landmark.ScreenManager;
import com.ashketshup.Landmark.UIElements.Command;
import com.ashketshup.Landmark.UIElements.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The type Form.
 */
public class Form extends Screen<Component> {
    /**
     * The onConfirmation to implement function.
     */
    public FooRecieve<Collection<Component>> onConfirmation;
    /* Default Commands */
    private final List<Command> defaultCommands;

    /**
     * Instantiates a new Form.
     *
     * @param title          the title
     * @param content        the content
     * @param commands       the commands
     * @param onConfirmation the on confirmation
     * @param context        the context
     */
    public Form(String title, Collection<Component> content, Collection<Command> commands, FooRecieve<Collection<Component>> onConfirmation, ScreenManager context) {
        super(title, content, commands, context);

        this.onConfirmation = onConfirmation;

        // Get Form Commands
        defaultCommands = getDefaultCommands();

        super.appendCommands(defaultCommands);
    }

    /**
     * Instantiates a new Form.
     *
     * @param title          the title
     * @param content        the content
     * @param onConfirmation the on confirmation
     * @param context        the context
     */
    public Form(String title, Collection<Component> content, FooRecieve<Collection<Component>> onConfirmation, ScreenManager context) {
        this(title, content, new ArrayList<>(), onConfirmation, context);
    }

    /**
     * Instantiates a new Form.
     *
     * @param content        the content
     * @param onConfirmation the on confirmation
     * @param context        the context
     */
    public Form(Collection<Component> content, FooRecieve<Collection<Component>> onConfirmation, ScreenManager context) {
        this("Default Form Title", content, onConfirmation, context);
    }

    /**
     * Instantiates a new Form.
     *
     * @param title   the title
     * @param content the content
     * @param context the context
     */
    public Form(String title, Collection<Component> content, ScreenManager context) {
        this(title, content, new ArrayList<>(), x -> {}, context);
    }

    /**
     * Instantiates a new Form.
     *
     * @param content the content
     * @param context the context
     */
    public Form(Collection<Component> content, ScreenManager context) {
        this(content, x -> {}, context);
    }

    /**
     * Sets onConfirmation.
     *
     * @param onConfirmation the on confirmation
     */
    public void setOnConfirmation(FooRecieve<Collection<Component>> onConfirmation) {
        this.onConfirmation = onConfirmation;
    }

    /**
     * Gets onConfirmation implementation.
     *
     * @return the onConfirmation
     */
    public FooRecieve<Collection<Component>> getOnConfirmation() { return onConfirmation; }

    /**
     * Gets default commands for Forms.
     *
     * @return the default commands
     */
    public List<Command> getDefaultCommands() {
        return List.of(
            new Command(
                false,
                ":c",
                "Confirm",
                () -> { this.onConfirmation.apply(this.getScreenContent()); }
            ),
            new Command(
                false,
                ":ta",
                "Try Again",
                () -> {
                    Collection<Component> components = super.getScreenContent();
                    components.forEach(x -> x.setAnswer(""));
                }
            )
        );
    }
}
