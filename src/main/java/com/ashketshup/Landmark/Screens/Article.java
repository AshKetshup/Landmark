package com.ashketshup.Landmark.Screens;

import com.ashketshup.Landmark.ScreenManager;
import com.ashketshup.Landmark.TUI.Notifications;
import com.ashketshup.Landmark.TUI.StringStyler;
import com.ashketshup.Landmark.UIElements.Command;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Article.
 */
public class Article extends Screen<StringStyler> {
    /**
     * Instantiates a new Menu.
     *
     * @param title    the title
     * @param content  the content
     * @param commands the commands
     * @param context  the context
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

    /**
     * Gen article from file article.
     *
     * @param fileName   the file name
     * @param isMarkdown the is markdown
     * @param context    the context
     * @return the article
     */
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

    /**
     * Gen article from given string.
     *
     * @param articleTitle the article title
     * @param content      the content
     * @param context      the context
     * @return the article
     */
    public static Article genArticleFromString(String articleTitle, String content, ScreenManager context) {
        return new Article(
            articleTitle,
            toListStringStyler(content),
            context
        );
    }

    /**
     * Gets text from file.
     *
     * @param fileName the file name
     * @return the text from file
     */
    public static String getTextFromFile(String fileName) {
        List<String> result = null;
        Path filePath = Paths.get(fileName);
        String fileContent = "";

        try {
            fileContent = Files.readString(filePath);
        } catch (IOException e) {
            Notifications.createCritical(e.getMessage());
        }

        return fileContent;
    }

    /**
     * To list string styler list.
     *
     * @param originalContent the original content
     * @return the list
     */
    public static List<StringStyler> toListStringStyler(String originalContent) {
        return Arrays.stream(originalContent.split("\n")).map(StringStyler::new).collect(Collectors.toList());
    }
}
