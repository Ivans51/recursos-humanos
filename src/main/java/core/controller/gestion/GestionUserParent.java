package core.controller.gestion;

import core.conexion.model.SessionModel;
import core.util.ManagerFXML;
import core.util.Route;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ivans on 7/17/2017.
 */
public class GestionUserParent extends ManagerFXML implements Initializable {

    private SessionModel model;
    String[] route = {
            Route.GestionUsuarioClave,
            Route.GestionUsuarioCambio,
            Route.GestionUsuarioParent,
            Route.GestionUsuarioParent
    };

    int[] selectPane = {
            SessionModel.RECUPERARCLAVE,
            SessionModel.CAMBIOUSUARIO,
            SessionModel.CARGANDO,
            SessionModel.BIENVENIDO};

    @FXML
    Label titlePane, descripcionPane;

    @FXML
    AnchorPane rootPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setModel(SessionModel model) {
        this.model = model;
        selectPane();
    }

    private void selectPane() {
        cambiarMultiplesEscenas(model.getSelectPane(), rootPane, selectPane, route);
        loadData();
    }

    private void loadData() {
        titlePane.setText(model.getTitlePane());
        descripcionPane.setText(model.getDescriptionPane());
    }

}
