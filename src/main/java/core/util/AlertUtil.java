package core.util;

import core.conexion.model.AlertModel;
import core.controller.AlertDialog;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

/**
 * Created by WAMS-10 on 29/07/2017.
 */
public class AlertUtil extends ManagerFXML{

    public int tipo;

    public AlertUtil(int tipo) throws Myexception {
        this.tipo = tipo;
        dilog();
    }

    public void dilog() throws Myexception {
        abrirStageStyle(Route.AlertDialog, "", Modality.NONE, null,
                false, StageStyle.TRANSPARENT,
                () ->elegir());
    }

    public void elegir(){
        AlertModel alertModel = new AlertModel (tipo);
        AlertDialog dialog = ManagerFXML.getFxmlLoader().getController();
        dialog.initData(alertModel);
    }
}
