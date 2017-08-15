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
 * Created by Ivans on 7/31/2017.
 * Ready
 */
public class GestionUserRecuperar extends ManagerFXML implements Initializable {

    public TextField txtCorreo, txtNombreUsuario;
    public JFXButton btnSend, btnCancelar;
    private UsuarioDAO usuarioDAO = new UsuarioDAO(MyBatisConnection.getSqlSessionFactory());
    private Usuario usuario;
    // TODO: 8/13/2017 usuario y clave
    private String username = "RepuestosCarts21@Hotmail.com", password = "RHumanos21";

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onSend(ActionEvent event) {
        try {
            Validar.campoVacio(txtCorreo, txtNombreUsuario);
            usuario = new Usuario();
            usuario.setCorreo(txtCorreo.getText());
            usuario.setNombreUsuario(txtNombreUsuario.getText());
            usuario = usuarioDAO.selectByCorreo(usuario);
            SendEmail.doSendMail(username, password, usuario.getCorreo(),
                    "Respuestos Cart S 21 C.A.", "Su clave es: " + usuario.getClave());
            new AlertUtil(Estado.EXITOSA, "Contraseña Enviada");
        } catch (Myexception myexception) {
            myexception.printStackTrace();
            new AlertUtil(Estado.ERROR, myexception.getMessage());
        }
    }

    public void onCancelar(ActionEvent event) {
        abrirStage(Route.SessionUsuario, "Inicio de Sesion", btnCancelar, null);
    }
}
