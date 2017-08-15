package core.controller.gestion;

import com.jfoenix.controls.JFXButton;
import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.UsuarioDAO;
import core.conexion.vo.Usuario;
import core.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ivans on 7/17/2017.
 * Ready
 */
public class GestionUserCambioClave extends ManagerFXML implements Initializable {

    public TextField txtNombreUsuaio, txtPreguntaUna, txtRespuesta1, txtPreguntaDos, txtRespuesta2,
            txtNuevaClave, txtVerifiqueClave;
    public JFXButton btnValidarNombre, btnCancelar, btnGuardar, btnVerificar;

    private UsuarioDAO usuarioDAO = new UsuarioDAO(MyBatisConnection.getSqlSessionFactory());
    private Usuario usuario;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnGuardar.setDisable(true);
        btnGuardar.getStyleClass().add("button-disabled");
    }

    public void onValidarNombre(ActionEvent event) throws Myexception {
        try {
            validarNombre();
            txtPreguntaUna.setText(usuario.getPregunta1());
            txtPreguntaDos.setText(usuario.getPregunta2());
        } catch (Myexception ex) {
            System.out.println(ex.getMessage());
            txtNombreUsuaio.setText("No existe el usuario");
        }
    }

    private void validarNombre() throws Myexception {
        usuarioDAO = new UsuarioDAO(MyBatisConnection.getSqlSessionFactory());
        usuario = new Usuario();
        usuario.setNombreUsuario(txtNombreUsuaio.getText());
        usuario = usuarioDAO.nombreUsuario(usuario);
        if (usuario == null) {
            throw new Myexception("No existe el usuario");
        }
    }

    public void onVerificar(ActionEvent event) {
        try {
            String[] valorToCompare = {usuario.getRespuesta1(), usuario.getRespuesta2(), txtNuevaClave.getText()};
            Validar.compararStringIguales(valorToCompare, txtRespuesta1, txtRespuesta2, txtVerifiqueClave);
            btnGuardar.setDisable(false);
            btnGuardar.getStyleClass().remove("button-disabled");
            btnGuardar.getStyleClass().add("button");
        } catch (Myexception ex) {
            System.out.println(ex.getMessage());
            new AlertUtil(Estado.ERROR, ex.getMessage());
        }
    }

    public void onGuardar(ActionEvent event) throws Myexception {
        updateClave();
        abrirStage(Route.SessionUsuario, "Session Usuario", btnGuardar, null);
    }

    private void updateClave() throws Myexception {
        int idUsuario = usuario.getIdUsuario();
        usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        usuario.setClave(txtNuevaClave.getText());
        usuarioDAO.updateClave(usuario);
        if (usuario == null) {
            throw new Myexception("Clave Incorrrecta");
        }
    }

    public void onCancelar(ActionEvent event) {
        abrirStage(Route.MainNivelUsuario, "Niveles de usuario", btnCancelar, null);
    }
}
