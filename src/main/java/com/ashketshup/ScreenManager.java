package com.ashketshup;

import java.util.HashMap;
import java.util.Stack;

public class ScreenManager<T> {
    private final Stack<Screen<T>> callStack = new Stack<>();
    private final HashMap<String, Menu> menuHashMap = new HashMap<>();
    private final HashMap<String, Article> articleHashMap = new HashMap<>();
    private final HashMap<String, Form> formHashMap = new HashMap<>();

    // IDEA: Tornar a stack para strings e depois apenas quando fazêmos o getBindScreen ir buscar aos HashMaps

    public ScreenManager() {    }

    public void addMenu(String key, Menu newMenu) {
        menuHashMap.put(key, newMenu);
    }

    public void addArticle(String key, Article newArticle) {
        articleHashMap.put(key, newArticle);
    }

    public void addForm(String key, Form newForm) {
        formHashMap.put(key, newForm);
    }

    public Menu getMenu(String key) {
        return menuHashMap.get(key);
    }

    public Article getArticle(String key) {
        return articleHashMap.get(key);
    }

    public Form getForm(String key) {
        return formHashMap.get(key);
    }

    /**
     * Gets binded screen.
     *
     * @return the binded screen
     */
    public Screen<T> getBindedScreen() {
        return callStack.peek();
    }

    /**
     * Gets the call stack.
     *
     * @return the call stack
     */
    public Stack<Screen<T>> getCallStack() {
        return this.callStack;
    }

    /**
     * Binds a given menu.
     *
     * @param screen the given screen
     */
    public <E extends Screen> void bindScreen(E screen) {
        this.callStack.push(screen);
    }

    /**
     * Unbind last menu.
     */
    public void unbindLastScreen() {
        this.callStack.pop();

        // Ends program in case of last screen
        if (this.callStack.empty())
            Navigation.setRenderer(false);
    }
}
