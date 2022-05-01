import com.ashketshup.Landmark.UIElements.Component;
import com.ashketshup.Landmark.Screens.Form;
import com.ashketshup.Landmark.Navigation;
import com.ashketshup.Landmark.ScreenManager;

import java.util.Arrays;

public class ScreenTest {
    public static void main(String[] args) {
        ScreenManager sM = new ScreenManager();
        Navigation nav = new Navigation(sM, 5);

        declareScreens(sM);
        sM.bindScreen(sM.getForm("form1"));

        nav.loop();
    }

    private static void declareScreens(ScreenManager sM) {
        sM.addForm(
            "form1",
            new Form(
                "Title of Form 1",
                Arrays.asList(
                    new Component("username", false, true),
                    new Component("password", false, true)
                ),
                x -> {},
                sM
            )
        );
    }
}
