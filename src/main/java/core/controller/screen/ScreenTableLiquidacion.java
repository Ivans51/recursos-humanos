package core.controller.screen;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ivans on 7/18/2017.
 */
public class ScreenTableLiquidacion implements Initializable {
    public AnchorPane anchor;
    public TableView tablaLiquidacion;
    public TableColumn tbIdUsuario, tbNombreUsuario, tbCorreo, tbNivelAcceso, tbCedula;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onCerrar(ActionEvent event) {

    }
}
