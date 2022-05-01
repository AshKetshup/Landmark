package com.ashketshup.Landmark.Screens;

import com.ashketshup.Landmark.Navigation;
import com.ashketshup.Landmark.Pages;
import com.ashketshup.Landmark.ScreenManager;
import com.ashketshup.Landmark.TUI.StringStyler;
import com.ashketshup.Landmark.UIElements.Command;
import com.ashketshup.Landmark.UIElements.Component;
import com.ashketshup.Landmark.UIElements.Option;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Screen.
 *
 * @param <T> the type parameter
 */
public class Screen<T> extends Pages {
    private String screenTitle = "";
    private final List<T> screenContent = new ArrayList<>();
    private final List<Command> screenCommands = new ArrayList<>();
    private final ScreenManager context;
    private String filter;

    /**
     * Instantiates a new Screen.
     *
     * @param title    the title
     * @param content  the UI elements
     * @param commands the commands
     * @param context  the context
     */
    public Screen(String title, Collection<T> content, Collection<Command> commands, ScreenManager context) {
        super(content.size());

        this.screenContent.addAll(content);
        this.screenCommands.addAll(commands);
        this.screenTitle = title;
        this.context = context;
        this.filter = "";
    }

    /**
     * Instantiates a new Screen.
     *
     * @param title   the title
     * @param content the UI elements
     * @param context the context
     */
    public Screen(String title, Collection<T> content, ScreenManager context) {
        this(title, content, new ArrayList<>(), context);
    }

    /**
     * Instantiates a new Screen.
     *
     * @param content the UI elements
     * @param context the context
     */
    public Screen(Collection<T> content, ScreenManager context) {
        this("Default Screen Title", content, context);
    }

    /**
     * Appends a new command to the screen.
     *
     * @param command the command
     * @return the screen
     */
    public Screen<T> appendCommand(Command command) {
        this.screenCommands.add(command);

        return this;
    }

    /**
     * Appends a new command list to the screen.
     *
     * @param commands Collection of Command
     * @return the screen
     */
    public Screen<T> appendCommands(Collection<Command> commands) {
        commands.forEach(this::appendCommand);

        return this;
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

    /**
     * Gets non hidden commands.
     *
     * @return the non hidden commands
     */
    public List<Command> getNonHiddenCommands() {
        List<Command> res = new ArrayList<>(Navigation.getDefaultCommands());
        res.addAll(getScreenCommands());

        return res
            .stream()
            .filter(Command::isCommandVisible)
            .collect(Collectors.toList());
    }

    /**
     * Gets context.
     *
     * @return the context
     */
    public ScreenManager getContext() {
        return context;
    }

    /**
     * Sets filter.
     *
     * @param filter the filter
     */
    public void setFilter(String filter) {
        this.filter = filter;
    }

    /**
     * Gets filter.
     *
     * @return the filter
     */
    public String getFilter() {
        return filter;
    }

    /**
     * Filters a List by its setted filter
     * @param content Content of given screen
     * @return List of T
     */
    private List<T> filter(List<T> content) {
        return content
            .stream()
            .filter(x -> { return x.toString().contains(filter); })
            .collect(Collectors.toList());
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

    @Override
    public String toString() {
        boolean isOption = this instanceof Menu;
        boolean isComponent = this instanceof Form;
        boolean isStringStyler = this instanceof Article;
        StringBuilder sB = new StringBuilder();

        for (Command cmd : this.getNonHiddenCommands())
            sB.append(cmd).append("    ");

        StringStyler help = new StringStyler(
            sB + "\n",
            StringStyler.WHITE,
            StringStyler.DISABLED
        );

        StringStyler title = new StringStyler(
            this.screenTitle + "\n",
            StringStyler.WHITE,
            StringStyler.UNDERLINE,
            true
        );

        StringStyler pageInfo = new StringStyler(
            pageToString() + "\n\n",
            StringStyler.WHITE,
            StringStyler.ITALIC
        );

        StringBuilder content = new StringBuilder();
        List<T> printableContent= trimContent(filter.isEmpty() ? screenContent : filter(screenContent));

        for (int i = 0; i < printableContent.size(); i++) {
            String index = new StringStyler(
                String.valueOf(
                    isOption || isComponent ? i : "" +
                    getCurrentPage() * Navigation.getMaxAmountItems()
                ),
                StringStyler.WHITE,
                StringStyler.BOLD,
                false
            ).toString();

            if (isOption || isComponent)
                content.append(index).append(" : ");

            content.append(printableContent.get(i).toString())
                .append("\n");
        }

        return help + "\n" + title + pageInfo + content;
    }
}
