import com.ashketshup.Landmark.ScreenManager;
import com.ashketshup.Landmark.Screens.Form;
import com.ashketshup.Landmark.TUI.Notifications;
import com.ashketshup.Landmark.UIElements.Component;

import java.util.Collection;
import java.util.List;

public class QuizForm {
    private static void control(Collection<Component> components) {
        List<Component> componentList = (List<Component>) components;

        if (components.stream().filter(Component::isRequired).allMatch(Component::isAnswerEmpty))
            Notifications.createWarning("Required camps are not filled.");
        else
            doSomething(componentList.get(0).getAnswer(), componentList.get(1).getAnswer());
    }

    public static Form generate(ScreenManager screenManager) {
        CreateNewsForm.screenManager = screenManager;

        return new Form(
            "Quiz",
            List.of(
                new Component("Name", false, true), // componentsList.get(0)
                new Component("Age", false, true)   // componentsList.get(1)
            ),
            QuizForm::control,
            screenManager
        );
    }
}