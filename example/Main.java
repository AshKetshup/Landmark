package com.ashketshup;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ScreenManager sM = new ScreenManager();
        Navigation nav = new Navigation(sM, 3);

        // region Menus & Articles Declarations
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
                    new StringStyler(
                        "Oh shit, isto está a funcionar!\n",
                        StringStyler.WHITE,
                        StringStyler.BLINK,
                        true
                    ),
                    new StringStyler(
                        "Teste, teste",
                        StringStyler.WHITE,
                        StringStyler.NORMAL,
                        false
                    )
                ),
                sM
            )
        );
        // endregion

        sM.bindScreen(sM.getMenu("welcome"));
        nav.loop();
    }
}