package com.ashketshup;

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
    private final ArrayList<Command> defaultCommands = new ArrayList<>();
    private ScreenManager sM;

    /**
     * Instantiates a new Navigation.
     *
     * @param sM the screenManager
     * @param maxItems  the max items
     */
    public Navigation(ScreenManager sM, int maxItems) {
        this.maxAmountItems = maxItems;
        Pages.setMaxAmountItems(maxItems);
        // <editor-fold desc="List of Default Commands">
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
                    () -> { /* TODO Refresh */ }
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
        //  </editor-fold>
        this.sM = sM;

        loop();
    }

    /**
     * Gets max amount items.
     *
     * @return the max amount of items
     */
    public int getMaxAmountItems() {
        return maxAmountItems;
    }

    /**
     * Gets the list of default commands.
     *
     * @return the default commands
     */
    public List<Command> getDefaultCommands() {
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
                TUI.clearConsole();
            } catch (IOException | InterruptedException e) {
                TUI.createCritical(e.getMessage());
            }

            renderLoop();
            TUI.removeAllAlarms();
        } while ( inputListener(TUI.requestInput()) );
    }

    /**
     * Responsible to render onto the prompt.
     */
    private void renderLoop() {
        String render = "" +
            sM.getBindedScreen().toString() +
            "\n" + TUI.getAllAlarms();

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

        } else if (sM.getBindedScreen().getScreenContent().get(0).getClass().equals(Option.class)) {
            // Check if it is selecting an option
            for (int i = 0; i < sM.getBindedScreen().getScreenContent().size(); i++)
                if (s.equalsIgnoreCase(String.valueOf(i))) {
                    Option x = (Option) sM.getBindedScreen().getScreenContent().get(i);
                    x.getOptionFunction().apply();

                    // Disable the warning
                    toWarn = false;
                    break;
                }
        }

        // Warn if variable true
        if (toWarn)
            TUI.createWarning(String.format("The term '%s' is not recognized as a command/option.", s));

        return renderer;
    }

    private Article generateHelpArticle() {
        List<Command> navigationCommands = getDefaultCommands();
        List<Command> screenCommands = sM.getBindedScreen().getScreenCommands();

        ArrayList<TUI.StringStyler> content = new ArrayList<>();

        if (!navigationCommands.isEmpty()) {
            content.add(
                new TUI.StringStyler(
                    "Navigation Commands",
                    TUI.StringStyler.WHITE,
                    TUI.StringStyler.UNDERLINE,
                    true
                )
            );

            for (Command cmd : navigationCommands) {
                content.add(
                    new TUI.StringStyler(
                        cmd.toString(),
                        TUI.StringStyler.WHITE,
                        TUI.StringStyler.NORMAL
                    )
                );
            }
        }

        content.add(
            new TUI.StringStyler(
                "\n --- \n",
                TUI.StringStyler.WHITE,
                TUI.StringStyler.NORMAL
            )
        );

        if (!screenCommands.isEmpty()) {
            content.add(
                new TUI.StringStyler(
                    "Screen Commands",
                    TUI.StringStyler.WHITE,
                    TUI.StringStyler.UNDERLINE,
                    true
                )
            );

            for (Command cmd : screenCommands) {
                content.add(
                    new TUI.StringStyler(
                        cmd.toString(),
                        TUI.StringStyler.WHITE,
                        TUI.StringStyler.NORMAL
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
