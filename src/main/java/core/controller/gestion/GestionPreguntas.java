package core.controller.gestion;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.UsuarioDAO;
import core.conexion.vo.Usuario;
import core.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by WAMS-10 on 29/07/2017.
 */
public class GestionPreguntas extends ManagerFXML implements Initializable {

    public JFXButton btnEnviar, btnCancelar;
    public TextField respuestaUno, respuestaDos;
    public JFXComboBox preguntaUno, preguntaDos;
    ObservableList<String> listUno = FXCollections.observableArrayList(
            "A qué hora del día nació",
            "Lugar de nacimiento de la madre",
            "últimos 4 números de la CI de su esposo");
    ObservableList<String> listDos = FXCollections.observableArrayList(
            "Segundo nombre de su esposo",
            "Profesor favorito",
            "Nombre del mejor amigo de su infancia");
    private Usuario usuario;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preguntaUno.setItems(listUno);
        preguntaDos.setItems(listDos);
    }

    public void clicAction(MouseEvent mouseEvent) throws Myexception {
        if (mouseEvent.getSource() == btnCancelar)
            abrirStage(Route.SessionUsuario, "Inicio de Session", btnCancelar, null);
        else {
            try {
                Validar.campoVacio(respuestaDos, respuestaUno);
                iniciarSession();
                abrirStage(Route.SessionUsuario, "Inicio de Session", btnEnviar, null);
            } catch (Myexception ex) {
                System.out.println(ex.getMessage());
                new AlertUtil(Estado.ERROR);
            }
        }
    }

    private void iniciarSession() throws Myexception {
        UsuarioDAO usuarioDAO = new UsuarioDAO(MyBatisConnection.getSqlSessionFactory());
        usuario = new Usuario();
        // usuario.setIdUsuario(idUsuario);
        usuario.setIdUsuario(Storage.getUsuario().getIdUsuario());
        usuario.setPregunta1(preguntaUno.getSelectionModel().getSelectedItem().toString());
        usuario.setRespuesta1(respuestaUno.getText());
        usuario.setPregunta2(preguntaDos.getSelectionModel().getSelectedItem().toString());
        usuario.setRespuesta2(respuestaDos.getText());
        usuario.setStatus(1);
        usuarioDAO.updatePreguntas(usuario);
    }
}
