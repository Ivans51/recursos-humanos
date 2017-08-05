package core.controller.session;

import com.jfoenix.controls.JFXTextField;
import core.conexion.vo.Usuario;
import core.consultas.LoginUser;
import core.util.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ivans on 7/15/2017.
 */
public class SesionUser extends ManagerFXML implements Initializable {

    private final LoginUser loginUser = new LoginUser();
    @FXML
    public JFXTextField nombre, clave;
    public Button bthAcceder, btnCancelar;
    public Label txtGestionUsuario;
    private Usuario usuario;
    private String sessionLoading = Route.SessionLoading;
    private String preguntas = Route.GestionUsuarioPreguntas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void clickLogin(MouseEvent mouseEvent) throws Myexception {
        try {
            Validar.campoVacio(nombre, clave);
            usuario = loginUser.iniciarSession(nombre.getText(), clave.getText());
            Storage.setUsuario(usuario);
            validarStatus();
        } catch (Myexception ex) {
            System.out.println(ex.getMessage());
            new AlertUtil(Estado.ERROR, ex.getMessage());
        }
    }

    private void validarStatus() throws Myexception {
        switch (usuario.getStatus()) {
            case Estado.PRIMERIZO:
                abrirStage(preguntas, "Preguntas de Seguridad", bthAcceder, null);
                break;
            case Estado.EXITOSA:
                abrirStage(sessionLoading, "Preguntas de Seguridad", bthAcceder, null);
                break;
        }
    }

    public void clickGestionUsuario(MouseEvent mouseEvent) throws Myexception {
        abrirStage(Route.GestionUsuario, "Gestion de Usuario", txtGestionUsuario, null);
    }

    public void clickClose(MouseEvent mouseEvent) {
        cerrarStage(btnCancelar);
    }
}
