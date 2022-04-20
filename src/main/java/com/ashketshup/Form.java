package com.ashketshup;

import java.util.ArrayList;
import java.util.Collection;

public class Form extends Screen<Component> {
    public Form(String title, Collection<Component> content, Collection<Command> commands, ScreenManager context) {
        super(title, content, commands, context);
    }

    public Form(String title, Collection<Component> content, ScreenManager context) {
        this(title, content, new ArrayList<>(), context);
    }

    public Form(Collection<Component> content, ScreenManager context) {
        this("Default Form Title", content, context);
    }
}
