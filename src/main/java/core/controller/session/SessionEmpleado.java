package core.controller.session;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import core.util.LoginUser;
import core.util.Myexception;
import core.util.Storage;
import core.util.Validar;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

import static core.util.Storage.usuario;

/**
 * Created by WAMS-10 on 29/07/2017.
 */
public class SessionEmpleado implements Initializable {

    public JFXButton acceder, cancelarEmpleado;
    public JFXTextField nombre, clave;
    private LoginUser loginUser = new LoginUser();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onClickAcceder(MouseEvent mouseEvent) {
        try {
            Validar.campoVacio(nombre, clave);
            usuario = loginUser.iniciarSession(nombre.getText(), clave.getText());

            validarStatus();
        } catch (Myexception myexception) {
            myexception.printStackTrace();
        }
    }

    public void onHandleClick(MouseEvent mouseEvent) {

    }
}
