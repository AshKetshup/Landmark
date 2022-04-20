
import com.ashketshup.TUI.StringStyler;
import com.ashketshup.markdownParser.CommonStyle;
import com.ashketshup.markdownParser.Parser;
import com.ashketshup.markdownParser.Style;

import java.util.List;

public class ArticleTest {

    public static void main(String[] args) {
        Style style = new Style(new CommonStyle());
        List<StringStyler> x = Parser.getMarkdownStyled(
            new StringBuilder("# This one is H1\n")
                .append("### This one should be H3\n")
                .append("*italic* **bold** ***bold & italic***\n")
                .append("```java\n")
                .append("Some code {\n")
                .append("    return some Shit;\n")
                .append("}\n")
                .append("```\n")
                .toString()
        );

        for (StringStyler s : x){
            System.out.println(s.toString());
        }
    }
}
