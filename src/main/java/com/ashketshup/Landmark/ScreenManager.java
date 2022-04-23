package com.ashketshup.Landmark;

import com.ashketshup.Landmark.Screens.Article;
import com.ashketshup.Landmark.Screens.Form;
import com.ashketshup.Landmark.Screens.Menu;
import com.ashketshup.Landmark.Screens.Screen;

import java.util.HashMap;
import java.util.Stack;

/**
 * The type Screen manager.
 */
public class ScreenManager {
    private final Stack<Screen<?>> callStack = new Stack<>();
    private final HashMap<String, Menu> menuHashMap = new HashMap<>();
    private final HashMap<String, Article> articleHashMap = new HashMap<>();
    private final HashMap<String, Form> formHashMap = new HashMap<>();

    // IDEA: Tornar a stack para strings e depois apenas quando fazêmos o getBindScreen ir buscar aos HashMaps

    /**
     * Instantiates a new Screen manager.
     */
    public ScreenManager() {    }

    /**
     * Add menu.
     *
     * @param key     the key
     * @param newMenu the new menu
     */
    public void addMenu(String key, Menu newMenu) {
        menuHashMap.put(key, newMenu);
    }

    /**
     * Add article.
     *
     * @param key        the key
     * @param newArticle the new article
     */
    public void addArticle(String key, Article newArticle) {
        articleHashMap.put(key, newArticle);
    }

    /**
     * Add form.
     *
     * @param key     the key
     * @param newForm the new form
     */
    public void addForm(String key, Form newForm) {
        formHashMap.put(key, newForm);
    }

    /**
     * Gets menu from menuHashMap.
     *
     * @param key the key
     * @return the menu
     */
    public Menu getMenu(String key) {
        return menuHashMap.get(key);
    }

    /**
     * Gets article from articleHashMap.
     *
     * @param key the key
     * @return the article
     */
    public Article getArticle(String key) {
        return articleHashMap.get(key);
    }

    /**
     * Gets form from formHashMap.
     *
     * @param key the key
     * @return the form
     */
    public Form getForm(String key) {
        return formHashMap.get(key);
    }

    /**
     * Gets binded screen.
     *
     * @return the binded screen
     */
    public Screen<?> getBindedScreen() {
        return callStack.peek();
    }

    /**
     * Gets the call stack.
     *
     * @return the call stack
     */
    public Stack<Screen<?>> getCallStack() {
        return this.callStack;
    }

    /**
     * Binds a given menu.
     *
     * @param <E>    the type parameter
     * @param screen the given screen
     */
    public <E extends Screen<?>> void bindScreen(E screen) {
        this.callStack.push(screen);
    }

    /**
     * Unbind last menu.
     * If the callStack becomes empty it ends the navigation loop.
     */
    public void unbindLastScreen() {
        this.callStack.pop();

        // Ends program in case of last screen
        if (this.callStack.empty())
            Navigation.setRenderer(false);
    }
}
