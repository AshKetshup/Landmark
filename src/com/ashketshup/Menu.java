package com.ashketshup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.ashketshup.TUI.StringStyler.*;
import com.ashketshup.TUI.StringStyler;

/**
 * The type Menu.
 * Represents a menu rendered in the TUI with Options, special Commands and a Title.
 */
public class Menu extends Screen<Option> {
    /**
     * Instantiates a new Menu.
     *
     * @param title    the title
     * @param options  the options
     * @param commands the commands
     */
    public Menu(String title, Collection<Option> options, Collection<Command> commands, ScreenManager context) {
        super(title, options, commands, context);
    }

    /**
     * Instantiates a new Menu.
     *
     * @param title   the title
     * @param options the options
     */
    public Menu(String title, Collection<Option> options, ScreenManager context) {
        this(title, options, new ArrayList<>(), context);
    }

    /**
     * Instantiates a new Menu.
     *
     * @param options the options
     */
    public Menu(Collection<Option> options, ScreenManager context) {
        this("Default Menu Title", options, context);
    }

    /**
     * Appends a new command to the menu.
     *
     * @param command     the command
     * @return the menu
     */
    public Menu appendCommand(Command command) {
        super.appendCommand(command);

        return this;
    }

    /**
     * Appends a new command to the menu.
     *
     * @param trigger     the command trigger
     * @param description the description
     * @param function    the function
     * @return the menu
     */
    public Menu appendCommand(String trigger, String description, Foo function) {
        return this.appendCommand(new Command(trigger, description, function));
    }

    /**
     * Gets menu title.
     *
     * @return the menu title
     */
    public String getMenuTitle() {
        return super.getScreenTitle();
    }

    /**
     * Gets list of menu options.
     *
     * @return the menu options
     */
    public List<Option> getMenuOptions() {
        return getScreenContent();
    }

    /**
     * Gets list of menu commands.
     *
     * @return the menu commands
     */
    public List<Command> getMenuCommands() {
        return super.getScreenCommands();
    }

    @Override
    public String toString() {
        StringStyler title = new StringStyler(
            getMenuTitle() + "\n",
            WHITE,
            UNDERLINE,
            true
        );

        StringStyler pageInfo = new StringStyler(
            pageToString() + "\n\n",
            WHITE,
            ITALIC
        );

        StringBuilder options = new StringBuilder();
        List<Option> printableOptions = trimContent(getMenuOptions());

        for (int i = 0; i < printableOptions.size(); i++) {
            String index = new StringStyler(
                String.valueOf(i + getCurrentPage() * Pages.getMaxAmountItems()),
                WHITE,
                BOLD,
                false
            ).toString();

            options.append(index)
                .append(" : ")
                .append(getMenuOptions().get(i).getText())
                .append("\n");
        }

        return "" + title + pageInfo + options;
    }

    /**
     * Checks if given index is available.
     *
     * @param i the given index
     * @return the boolean
     */
    private boolean isIndexAvailable(int i) {
        return (i >= 0 && i < getMenuOptions().size());
    }

    /**
     * Returns to last menu.
     */
    public void backMenuFunction() {
        super.getContext().unbindLastScreen();
    }

    /**
     * Exit menu function.
     * Exits the program.
     */
    public static void exitMenuFunction() {
        Navigation.setRenderer(false);
    }
}
