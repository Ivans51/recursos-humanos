package core.controller.session;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import core.conexion.vo.Usuario;
import core.consultas.LoginUser;
import core.util.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ivans on 7/15/2017.
 * Ready
 */
public class SesionUser extends ManagerFXML implements Initializable {

    private final LoginUser loginUser = new LoginUser();
    @FXML
    public JFXTextField nombre;
    public JFXPasswordField clave;
    public Label txtGestionUsuario;
    public JFXButton btnRegresar, bthAcceder, btnCancelar;

    private Usuario usuario;
    private String sessionLoading = Route.SessionLoading;
    private String preguntas = Route.GestionUsuarioPreguntas;

    private AuditoriaUtil auditoriaUtil;

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
                auditoria();
                break;
            case Estado.ADMINISTRADOR:
                abrirStage(sessionLoading, "Repuestos Cars 21", bthAcceder, null);
                auditoria();
                break;
            case Estado.SECRETARIA:
                abrirStage(sessionLoading, "Repuestos Cars 21", bthAcceder, null);
                auditoria();
                break;
            case Estado.INVITADO:
                abrirStage(sessionLoading, "Repuestos Cars 21", bthAcceder, null);
                auditoria();
                break;
            case Estado.EMPLEADO:
                throw new Myexception("Acceso denegado, \n Seleccione Empleado en Gestión de sessión");
        }
    }

    private void auditoria() throws Myexception {
        auditoriaUtil = new AuditoriaUtil(usuario.getNombreUsuario(), usuario.getIdUsuario());
        auditoriaUtil.insertar("Registro usuario " + usuario.getNombreUsuario());;
        usuario.setNombreUsuario(usuario.getNombreUsuario());
        usuario.setIdUsuario(usuario.getIdUsuario());
        usuario.setNivelAcceso(usuario.getNivelAcceso());
        Storage.setUsuario(usuario);
    }

    public void clickGestionUsuario(MouseEvent mouseEvent) throws Myexception {
        abrirStage(Route.GestionUsuario, "Gestion de Usuario", txtGestionUsuario, null);
    }

    public void onRegresar(MouseEvent mouseEvent) {
        abrirStage(Route.ScreenMainHome, "Respuestos Cart S 21 C.A.", btnRegresar, null);
    }

    public void clickClose(MouseEvent mouseEvent) {
        cerrarStage(btnCancelar);
    }
}
