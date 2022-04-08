package com.ashketshup;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        ScreenManager sM = new ScreenManager();

        sM.addMenu(
            "welcome",
            new Menu(
                "Bem-vindo ao Correio da Matina",
                Arrays.asList(
                    new Option("Option 1", () -> {
                        sM.bindScreen(sM.getArticle("artigo1"));
                    }),
                    new Option("Option 2", () -> {}),
                    new Option("Option 3", () -> {})
                ),
                sM
            )
        );

        sM.addArticle(
            "artigo1",
            new Article(
                "Artigo 1 - Artigo de teste",
                Arrays.asList(
                    new TUI.StringStyler(
                        "Oh shit, isto está a funcionar!\n",
                        TUI.StringStyler.WHITE,
                        TUI.StringStyler.BLINK,
                        true
                    ),
                    new TUI.StringStyler(
                        "Teste, teste",
                        TUI.StringStyler.WHITE,
                        TUI.StringStyler.NORMAL,
                        false
                    )
                ),
                sM
            )
        );

        sM.bindScreen(sM.getMenu("welcome"));

        new Navigation(sM, 3);
    }
}
