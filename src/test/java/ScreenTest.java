import com.ashketshup.Component;
import com.ashketshup.Form;
import com.ashketshup.Navigation;
import com.ashketshup.ScreenManager;

import java.util.Arrays;

public class ScreenTest {
    public static void main(String[] args) {
        ScreenManager<?> sM = new ScreenManager<>();
        Navigation nav = new Navigation(sM, 5);

        declareScreens(sM);
        sM.bindScreen(sM.getForm("form1"));

        nav.loop();
    }

    private static void declareScreens(ScreenManager<?> sM) {
        sM.addForm(
            "form1",
            new Form(
                "Title of Form 1",
                Arrays.asList(
                    new Component("username", false),
                    new Component("password", true)
                ),
                sM
            )
        );
    }
}
