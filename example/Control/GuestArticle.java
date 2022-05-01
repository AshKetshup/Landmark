import com.ashketshup.Landmark.ScreenManager;
import com.ashketshup.Landmark.Screens.Article;
import com.ashketshup.Landmark.TUI.StringStyler;

import java.util.List;

public class GuestArticle {
    private static ScreenManager screenManager;

    public static Article generate(ScreenManager screenManager) {
        LastNewsFromTopicArticle.screenManager = screenManager;

        return new Article(
            "Guest Article",
            List.of(
                new StringStyler("Line by line, this is one"),
                new StringStyler("Now this one is in bold and the second", StringStyler.WHITE, StringStyler.BOLD)
            ),
            screenManager
        );
    }
}