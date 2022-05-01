public class Main {
    ScreenManager sM;
    Navigation nav;

    public static void main(String[] args) {
        sM = new ScreenManager();
        nav = new Navigation(sM, 10);

        declare();

        sM.bindScreen(sM.getMenu("welcome_menu"));
        nav.loop();
    }

    private static void declare() {
        sM.addMenu("welcome_menu", WelcomeMenu.generate(sM));
        sM.addForm("quiz_form", QuizForm.generate(sM));
        sM.addArticle("guest_article", GuestArticle.generate(sM));
    }
}