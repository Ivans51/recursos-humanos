package core.controller.screen;

import javafx.scene.control.Control;

public class ClassUtil {

    public static void addClass(String nameClass, Control... control) {
        for (Control ctr : control)
            ctr.getStyleClass().add(nameClass);
    }

    public static void removeClass(String nameClass, Control... control) {
        for (Control ctr : control)
            ctr.getStyleClass().remove(nameClass);
    }
}