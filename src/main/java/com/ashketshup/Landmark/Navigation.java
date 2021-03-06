package com.ashketshup.Landmark;

import com.ashketshup.Landmark.Screens.Article;
import com.ashketshup.Landmark.Screens.Form;
import com.ashketshup.Landmark.Screens.Menu;
import com.ashketshup.Landmark.TUI.Notifications;
import com.ashketshup.Landmark.TUI.Input;
import com.ashketshup.Landmark.TUI.Output;
import com.ashketshup.Landmark.TUI.StringStyler;
import com.ashketshup.Landmark.UIElements.Command;
import com.ashketshup.Landmark.UIElements.Component;
import com.ashketshup.Landmark.UIElements.Option;

import java.io.IOException;
import java.util.*;

/**
 * The type Navigation.
 * Capable to manage menus and render to the screen a Text Based User Interface.
 * Manages the callStack. (History of what Menu's have been used)
 */
public class Navigation {
    private static boolean renderer = true;
    private static int maxAmountItems;
    private static final ArrayList<Command> defaultCommands = new ArrayList<>();
    private final ScreenManager sM;

    /**
     * Instantiates a new Navigation.
     *
     * @param sM the screenManager
     * @param maxItems  the max items
     */
    public Navigation(ScreenManager sM, int maxItems) {
        Navigation.maxAmountItems = maxItems;

        // region List of Default Commands
        defaultCommands.addAll(
            Arrays.asList(
                new Command(
                    true,
                    ":q",
                    "Quit",
                    () -> {
                        Navigation.setRenderer(false);
                    }
                ),
                new Command(
                    true,
                    ":b",
                    "back",
                    sM::unbindLastScreen
                ),
                new Command(
                    true,
                    ":su",
                    "Scroll Up",
                    () -> { sM.getBindedScreen().lastPage(); }
                ),
                new Command(
                    true,
                    ":sd",
                    "Scroll Down",
                    () -> { sM.getBindedScreen().nextPage(); }
                ),
                new Command(
                    true,
                    ":r",
                    "Refresh Screen",
                    () -> { }
                ),
                new Command(
                    true,
                    ":f",
                    "Filter Options",
                    () -> { sM.getBindedScreen().setFilter(Input.readString("Find: ")); }
                ),
                new Command(
                    false,
                    ":h",
                    "Help",
                    () -> {
                        sM.bindScreen(generateHelpArticle());
                    }
                )
            )
        );
        //  endregion

        this.sM = sM;
    }

    /**
     * Gets max amount items.
     *
     * @return the max amount of items
     */
    public static int getMaxAmountItems() {
        return Navigation.maxAmountItems;
    }

    /**
     * Gets the list of default commands.
     *
     * @return the default commands
     */
    public static List<Command> getDefaultCommands() {
        return defaultCommands;
    }

    /**
     * Sets renderer.
     *
     * @param renderer the renderer (false to end program).
     */
    public static void setRenderer(boolean renderer) {
        Navigation.renderer = renderer;
    }

    /**
     * Sets max amount items.
     *
     * @param maxAmountItems the max amount items
     */
    public static void setMaxAmountItems(int maxAmountItems) {
        Navigation.maxAmountItems = maxAmountItems;
    }

    /**
     * Invokes the Loop.
     */
    public void loop() {
        do {
            try {
                Output.clearConsole();
            } catch (IOException | InterruptedException e) {
                Notifications.createCritical(e.getMessage());
            }

            renderLoop();
            Notifications.removeAllNotifications();
        } while ( inputListener(Input.tryAsString()) );
    }

    /**
     * Responsible to render onto the prompt.
     */
    private void renderLoop() {
        String render = "" +
            sM.getBindedScreen().toString() +
            "\n" + Notifications.getAllNotifications();

        System.out.println(render);
    }

    /**
     * Input listener boolean.
     *
     * Listens to the Input and dynamically answers.
     * Checks for Options and pre-defined Commands.
     *
     * @param s the given input
     * @return the boolean (true if able to continue render false to stop)
     */
    public boolean inputListener(String s) {
        boolean toWarn = true;

        boolean isMenu = sM.getBindedScreen() instanceof Menu;
        boolean isForm = sM.getBindedScreen() instanceof Form;

        if (s.isEmpty())
            return renderer;

        if (s.charAt(0) == ':') {
            // Check if it is using an available command
            ArrayList<Command> allAvailableCommands = new ArrayList<>(defaultCommands);
            allAvailableCommands.addAll(sM.getBindedScreen().getScreenCommands());

            for (Command availableCommand : allAvailableCommands)
                if (availableCommand.getCommandTrigger().equalsIgnoreCase(s)) {
                    availableCommand.getCommandFunction().apply();

                    // Disable the warning
                    toWarn = false;
                    break;
                }

        } else if (isMenu || isForm) {
            // Check if it is selecting an option
            for (int i = 0; i < sM.getBindedScreen().getScreenContent().size(); i++)
                if (s.equalsIgnoreCase(String.valueOf(i))) {
                    if (isMenu) {
                        Option x = (Option) sM.getBindedScreen().getScreenContent().get(i);
                        x.getOptionFunction().apply();
                    } else {
                        Component x = (Component) sM.getBindedScreen().getScreenContent().get(i);
                        x.apply();
                    }

                    // Disable the warning
                    toWarn = false;
                    break;
                }
        }

        // Warn if variable true
        if (toWarn)
            Notifications.createWarning(String.format("The term '%s' is not recognized as a command/option.", s));

        return renderer;
    }

    /**
     * Generates an help article, containing all commands available in the current screen.
     *
     * @return the article
     */
    private Article generateHelpArticle() {
        List<Command> navigationCommands = getDefaultCommands();
        List<Command> screenCommands = sM.getBindedScreen().getScreenCommands();

        ArrayList<StringStyler> content = new ArrayList<>();

        if (!navigationCommands.isEmpty()) {
            content.add(
                new StringStyler(
                    "Navigation Commands",
                    StringStyler.WHITE,
                    StringStyler.UNDERLINE,
                    true
                )
            );

            for (Command cmd : navigationCommands) {
                content.add(
                    new StringStyler(
                        cmd.toString(),
                        StringStyler.WHITE,
                        StringStyler.NORMAL
                    )
                );
            }
        }

        content.add(
            new StringStyler(
                "\n --- \n",
                StringStyler.WHITE,
                StringStyler.NORMAL
            )
        );

        if (!screenCommands.isEmpty()) {
            content.add(
                new StringStyler(
                    "Screen Commands",
                    StringStyler.WHITE,
                    StringStyler.UNDERLINE,
                    true
                )
            );

            for (Command cmd : screenCommands) {
                content.add(
                    new StringStyler(
                        cmd.toString(),
                        StringStyler.WHITE,
                        StringStyler.NORMAL
                    )
                );
            }
        }

        return new Article(
            "Help Page",
            content,
            sM
        );
    }
}
