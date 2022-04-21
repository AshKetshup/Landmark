package com.ashketshup;

import com.ashketshup.TUI.StringStyler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Command> getNonHiddenCommands() {
        return screenCommands
            .stream()
            .filter(Command::isCommandHidden)
            .collect(Collectors.toList());
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

    @Override
    public String toString() {
        boolean isOption = screenContent.get(0).getClass().equals(Option.class);
        boolean isComponent = screenContent.get(0).getClass().equals(Component.class);
        StringBuilder sB = new StringBuilder();

        for (Command cmd : this.getNonHiddenCommands())
            sB.append(cmd.getCommandTrigger() + " - " + cmd.getCommandDescription());

        StringStyler help = new StringStyler(
            sB.toString() + "\n",
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
        List<T> printableContent = trimContent(screenContent);

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

            content.append(screenContent.get(i).toString())
                .append("\n");
        }

        return "" + help + title + pageInfo + content;
    }

}
