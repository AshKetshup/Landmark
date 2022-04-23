
import com.ashketshup.Landmark.Screens.Article;
import com.ashketshup.Landmark.Navigation;
import com.ashketshup.Landmark.ScreenManager;

public class ArticleTest {

    public static void main(String[] args) {
        ScreenManager sM = new ScreenManager();
        Navigation nav = new Navigation(sM, 5);

        sM.addArticle(
            "welcome",
            Article.genArticleFromString(
                "Test Article Title by Ash",
                "Hello there, this is a text\n" +
                "And sure is an article from a string\n" +
                "\n" +
                "Signed by Ash\n",
                sM
            )
        );
        sM.bindScreen(sM.getArticle("welcome"));

        nav.loop();
    }
}
