package com.ashketshup;

import com.ashketshup.TUI.Alarms;
import com.ashketshup.TUI.StringStyler;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    public static Article genArticleFromFile(String fileName, boolean isMarkdown, ScreenManager context) {
        return new Article(
            fileName,
            toListStringStyler(getTextFromFile(fileName)),
            // isMarkdown
            // ? Parser.getMarkdownStyled(getTextFromFile(fileName))
            // : toListStringStyler(getTextFromFile(fileName)),
            context
        );
    }

    public static Article genArticleFromString(String articleTitle, String content, ScreenManager context) {
        return new Article(
            articleTitle,
            toListStringStyler(content),
            context
        );
    }

    public static String getTextFromFile(String fileName) {
        List<String> result = null;
        Path filePath = Paths.get(fileName);
        String fileContent = "";

        try {
            fileContent = Files.readString(filePath);
        } catch (IOException e) {
            Alarms.createCritical(e.getMessage());
        }

        return fileContent;
    }

    public static List<StringStyler> toListStringStyler(String originalContent) {
        return Arrays.stream(originalContent.split("\n")).map(StringStyler::new).collect(Collectors.toList());
    }
}
