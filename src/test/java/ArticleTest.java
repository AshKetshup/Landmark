import com.ashketshup.Article;

import java.util.Arrays;

public class ArticleTest {

    public static void main(String[] args) {
        Article.getMarkdownStyled(
            Arrays.asList(
                "# This one is H1\n",
                "### This one should be H3\n",
                "*italic* **bold** ***bold & italic***\n"
            )
        ).forEach(x -> {
            System.out.println(x.toString());
        });
    }
}
