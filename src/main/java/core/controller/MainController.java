package core.controller;

import com.jfoenix.controls.JFXButton;
import core.util.ManagerFXML;
import core.util.Myexception;
import core.util.Route;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ivans on 7/24/2017.
 */
public class MainController extends ManagerFXML implements Initializable {

    public JFXButton administrador, secretaria, invitado, empleados, salir;
    String[] routes = {Route.ScreenMainHome, Route.SessionUsuario, Route.SessionUsuario, Route.SessionEmpleado};

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleOpenStageAction(ActionEvent event) throws Myexception {

        navegarAbrirStageModel(event, routes, (route, btn) -> {

            abrirStage(route, "", btn, null);

        }, administrador, secretaria, invitado, empleados);

    }

    public void handlCloseStageAction(ActionEvent event) {
        cerrarStage(salir);
    }
}
