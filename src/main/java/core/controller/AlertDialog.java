package core.controller;

import core.conexion.model.AlertModel;
import core.util.Estado;
import core.util.ManagerFXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by WAMS-10 on 27/07/2017.
 */
public class AlertDialog extends ManagerFXML implements Initializable {

    public Label descripcionAlert;
    public ImageView imagenAlert;
    public Label closeAlert;

    public void initData(AlertModel alertModel){
        elegirMensaje(alertModel.eleccion);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void elegirMensaje(int eleccion) {
        if (eleccion == Estado.EXITOSA)
            setInfoAlert("Modificación Exitosa", "../icons/DeleteMessage_96px.png");
        else if (eleccion == Estado.ERROR)
            setInfoAlert("Modificación Exitosa", "../icons/Ok_96px.png");
    }

    private void setInfoAlert(String value, String path) {
        descripcionAlert.setText(value);
        imagenAlert.setImage(getImageFile(path));
    }

    private Image getImageFile(String path) {
        File imageFile = new File(path);
        return new Image(imageFile.toURI().toString());
    }

    public void closeAlert(MouseEvent mouseEvent) {
        cerrarStage(closeAlert);
    }
}
