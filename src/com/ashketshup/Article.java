package com.ashketshup;

import java.util.ArrayList;
import java.util.Collection;

public class Article extends Screen<TUI.StringStyler> {
    /**
     * Instantiates a new Menu.
     *
     * @param title    the title
     * @param content  the content
     * @param commands the commands
     * @param context the context
     */
    public Article(String title, Collection<TUI.StringStyler> content, Collection<Command> commands, ScreenManager context) {
        super(title, content, commands, context);
    }

    /**
     * Instantiates a new Menu.
     *
     * @param title   the title
     * @param content the content
     * @param context the context
     */
    public Article(String title, Collection<TUI.StringStyler> content, ScreenManager context) {
        this(title, content, new ArrayList<>(), context);
    }

    /**
     * Instantiates a new Menu.
     *
     * @param content the content
     * @param context the context
     */
    public Article(Collection<TUI.StringStyler> content, ScreenManager context) {
        this("Default Menu Title", content, context);
    }
}
