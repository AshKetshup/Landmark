package com.ashketshup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.ashketshup.TUI.StringStyler.*;
import com.ashketshup.TUI.StringStyler;

public class Screen<T> extends Pages {
    private String screenTitle = "";
    private final List<T> screenContent = new ArrayList<>();
    private final List<Command> screenCommands = new ArrayList<>();
    private final ScreenManager context;

    /**
     * Instantiates a new Screen.
     *
     * @param title    the title
     * @param content  the content
     * @param commands the commands
     */
    public Screen(String title, Collection<T> content, Collection<Command> commands, ScreenManager context) {
        super(content.size());

        this.screenContent.addAll(content);
        this.screenCommands.addAll(commands);
        this.screenTitle = title;
        this.context = context;

        setPagesAmount(content.size());
    }

    /**
     * Instantiates a new Screen.
     *
     * @param title   the title
     * @param content the options
     */
    public Screen(String title, Collection<T> content, ScreenManager context) {
        this(title, content, new ArrayList<Command>(), context);
    }

    /**
     * Instantiates a new Screen.
     *
     * @param content the options
     */
    public Screen(Collection<T> content, ScreenManager context) {
        this("Default Screen Title", content, context);
    }

    /**
     * Appends a new command to the screen.
     *
     * @param command     the command
     * @return the screen
     */
    public Screen<T> appendCommand(Command command) {
        this.screenCommands.add(command);

        return this;
    }

    /**
     * Appends a new command to the screen.
     *
     * @param trigger     the command trigger
     * @param description the description
     * @param function    the function
     * @return the screen
     */
    public Screen<T> appendCommand(String trigger, String description, Foo function) {
        return this.appendCommand(new Command(trigger, description, function));
    }

    /**
     * Gets screen title.
     *
     * @return the screen title
     */
    public String getScreenTitle() {
        return screenTitle;
    }

    /**
     * Gets list of screen options.
     *
     * @return the screen options
     */
    public List<T> getScreenContent() {
        return screenContent;
    }

    /**
     * Gets list of screen commands.
     *
     * @return the screen commands
     */
    public List<Command> getScreenCommands() {
        return screenCommands;
    }

    @Override
    public String toString() {
        boolean x = screenContent.get(0).getClass().equals(Option.class);

        StringStyler title = new StringStyler(
            this.screenTitle + "\n",
            WHITE,
            UNDERLINE,
            true
        );

        StringStyler pageInfo = new StringStyler(
            pageToString() + "\n\n",
            WHITE,
            ITALIC
        );

        StringBuilder content = new StringBuilder();
        List<T> printableContent = trimContent(screenContent);

        for (int i = 0; i < printableContent.size(); i++) {
            String index = new StringStyler(
                String.valueOf(
                    x ? i : "" +
                    getCurrentPage() * Pages.getMaxAmountItems()
                ),
                WHITE,
                BOLD,
                false
            ).toString();

            content.append(index)
                .append(x ? " : " : "")
                .append(screenContent.get(i).toString())
                .append("\n");
        }

        return "" + title + pageInfo + content;
    }

    public ScreenManager getContext() {
        return context;
    }

    /**
     * Checks if given index is available.
     *
     * @param i the given index
     * @return the boolean
     */
    private boolean isIndexAvailable(int i) {
        return (i >= 0 && i < screenContent.size());
    }

}
