package com.ashketshup;

import com.ashketshup.UIElements.Command;
import com.ashketshup.UIElements.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Form extends Screen<Component> {
    public Foo onConfirmation;
    /* Default Commands */
    private final List<Command> defaultCommands;

    public Form(String title, Collection<Component> content, Collection<Command> commands, Foo onConfirmation, ScreenManager<?> context) {
        super(title, content, commands, context);

        this.onConfirmation = onConfirmation;

        // Get Form Commands
        defaultCommands = getDefaultCommands();

        super.appendCommands(defaultCommands);
    }

    public Form(String title, Collection<Component> content, Foo onConfirmation, ScreenManager<?> context) {
        this(title, content, new ArrayList<>(), onConfirmation, context);
    }

    public Form(Collection<Component> content, Foo onConfirmation, ScreenManager<?> context) {
        this("Default Form Title", content, onConfirmation, context);
    }

    public Form(String title, Collection<Component> content, ScreenManager<?> context) {
        this(title, content, new ArrayList<>(), () -> {}, context);
    }

    public Form(Collection<Component> content, ScreenManager<?> context) {
        this(content, () -> {}, context);
    }

    public void setAfterwards(Foo onConfirmation) {
        this.onConfirmation = onConfirmation;
    }

    public List<Command> getDefaultCommands() {
        return List.of(
            new Command(
                false,
                ":c",
                "Confirm",
                onConfirmation
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
