package core.util;

import core.conexion.model.AlertModel;
import core.controller.AlertDialog;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

/**
 * Created by WAMS-10 on 29/07/2017.
 */
public class AlertUtil extends ManagerFXML {

    private int eleccion;
    private String title;

    public AlertUtil(int eleccion, String title) {
        this.eleccion = eleccion;
        this.title = title;
        dilog();
    }

    private void dilog() {
        abrirStageStyle(Route.AlertDialog, "", Modality.NONE, null,
                false, StageStyle.TRANSPARENT,
                () -> elegir());
    }

    private void elegir() {
        AlertModel alertModel = new AlertModel(eleccion, title);
        AlertDialog dialog = ManagerFXML.getFxmlLoader().getController();
        dialog.initData(alertModel);
    }
}
