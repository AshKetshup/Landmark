import com.ashketshup.Landmark.ScreenManager;
import com.ashketshup.Landmark.Screens.Menu;
import com.ashketshup.Landmark.UIElements.Option;

import java.util.List;

public class WelcomeMenu {
    private static ScreenManager screenManager;

    private static void quiz() {
        screenManager.bindScreen(screenManager.getForm("quiz_form"));
    }

    private static void guestArticle() {
        screenManager.bindScreen(screenManager.getArticle("guest_article"));
    }

    public static Menu generate(ScreenManager screenManager) {
        WelcomeMenu.screenManager = screenManager;

        return new Menu(
            "Welcome to Correio da Matina",
            List.of(
                new Option(
                    "Quiz",
                    WelcomeMenu::quiz
                ),
                new Option(
                    "Continue as Guest",
                    WelcomeMenu::guestArticle
                )
            ),
            screenManager
        );
    }
}