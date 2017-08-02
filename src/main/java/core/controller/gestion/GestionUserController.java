package core.controller.gestion;

import core.conexion.model.SessionModel;
import core.util.ManagerFXML;
import core.util.Myexception;
import core.util.Route;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ivans on 7/16/2017.
 */
public class GestionUserController extends ManagerFXML implements Initializable{

    @FXML
    private Button recuperarClave, cambioClave, salir;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleCloseButtonAction(ActionEvent event) throws Myexception {

        if (event.getSource() == recuperarClave)
            openFXMLUserParent(1, "Recuperación de clave", recuperarClave);
        else if (event.getSource() == cambioClave)
            openFXMLUserParent(2, "Recuperación nombre de usuario", cambioClave);
        else if (event.getSource() == salir)
            cerrarStage(salir);
    }

    private void openFXMLUserParent(int selectPane, String titlePane, Button btn) throws Myexception {

        SessionModel model = new SessionModel();
        model.setSelectPane(selectPane);
        model.setTitlePane(titlePane);
        model.setDescriptionPane("Complete todos los campos del formulario");

        abrirStage(Route.GestionUsuarioParent, "ABC", btn, () -> {
            GestionUserParent display = ManagerFXML.getFxmlLoader().getController();
            display.setModel(model);
        });
    }
}
