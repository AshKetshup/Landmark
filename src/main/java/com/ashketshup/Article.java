package com.ashketshup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.text.TextContentRenderer;

public class Article extends Screen<StringStyler> {
    /**
     * Instantiates a new Menu.
     *
     * @param title    the title
     * @param content  the content
     * @param commands the commands
     * @param context the context
     */
    public Article(String title, Collection<StringStyler> content, Collection<Command> commands, ScreenManager context) {
        super(title, content, commands, context);
    }

    /**
     * Instantiates a new Menu.
     *
     * @param title   the title
     * @param content the content
     * @param context the context
     */
    public Article(String title, Collection<StringStyler> content, ScreenManager context) {
        this(title, content, new ArrayList<>(), context);
    }

    /**
     * Instantiates a new Menu.
     *
     * @param content the content
     * @param context the context
     */
    public Article(Collection<StringStyler> content, ScreenManager context) {
        this("Default Article Title", content, context);
    }

    public static Article genArticle(String fileName, boolean isMarkdown, ScreenManager context) {
        return new Article(
            "",
            isMarkdown
            ? getMarkdownStyled(getTextFromFile(fileName))
            : toListStringStyler(getTextFromFile(fileName)),
            context
        );
    }

    public static List<String> getTextFromFile(String fileName) {
        List<String> result = null;

        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            result = lines.collect(Collectors.toList());
        } catch (IOException e) {
            TUI.createCritical(e.getMessage());
        }

        return result;
    }

    public static List<StringStyler> toListStringStyler(List<String> originalContent) {
        return originalContent.stream().map(StringStyler::new).collect(Collectors.toList());
    }

    public static List<StringStyler> getMarkdownStyled(List<String> content) {
        List<StringStyler> result = new ArrayList<>();

        Parser parser = Parser.builder().build();
        for (String line : content) {
            Node document = parser.parse(line);
            TextContentRenderer renderer = TextContentRenderer.builder().build();

            result.add(new StringStyler(
                renderer.render(document)
            ));
        }

        return result;
    }
}
