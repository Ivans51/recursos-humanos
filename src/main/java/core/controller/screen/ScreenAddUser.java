package core.controller.screen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.UsuarioDAO;
import core.conexion.vo.Usuario;
import core.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Ivans on 7/18/2017.
 */
public class ScreenAddUser extends ManagerFXML implements Initializable, TableUtil.StatusControles {

    public AnchorPane anchorPane;
    public JFXTextField nombreUsuario, claveUsuario, correoUsuario, cedula;
    public JFXComboBox nivelUsuario, nacionalidad;
    public JFXButton btnGuardar, btnCancelar, btnEliminar, btnActualizar;
    public TableView<Usuario> tablaUsuarios;
    public TableColumn tbIdUsuario, tbNombreUsuario, tbCorreo, tbNivelUsuario, tbCedula, tbStatus;
    public TableUtil table;

    private String[] tableS = {"idUsuario", "nombreUsuario", "correo", "nivelAcceso", "cedula", "status"};
    private ObservableList<String> listUserCombo = FXCollections.observableArrayList("1", "2", "3");
    private ObservableList<String> listNacionalidadCombo = FXCollections.observableArrayList("V", "E");

    private List<Usuario> usuarios;
    private Usuario usuario;

    private UsuarioDAO usuarioDAO = new UsuarioDAO(MyBatisConnection.getSqlSessionFactory());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Combobox
        nivelUsuario.setItems(listUserCombo);
        nacionalidad.setItems(listNacionalidadCombo);

        // Iniciailizar tabla
        table = new TableUtil(Usuario.class, tablaUsuarios, null,null);
        table.inicializarTabla(tableS, tbIdUsuario, tbNombreUsuario, tbCorreo, tbNivelUsuario, tbCedula, tbStatus);

        // Seleccionar las tuplas de la tabla de las listTable
        final ObservableList<Usuario> tablaPersonaSel = tablaUsuarios.getSelectionModel().getSelectedItems();
        tablaPersonaSel.addListener((ListChangeListener<Usuario>) c -> table.seleccionarTabla(this));

        // Llenar tabla
        selectUsuario();
        table.getListTable().addAll(usuarios);
    }

    /**
     * Llenar la tabla
     */
    private void selectUsuario() {
        usuarios = usuarioDAO.selectAll();
    }

    public void onClickGuardar(ActionEvent event) throws ParseException {
        try {
            Validar.campoVacio(nombreUsuario, claveUsuario, correoUsuario, cedula);
            insertUsuario();
        } catch (Myexception myexception) {
            myexception.printStackTrace();
        }
    }

    private void insertUsuario() throws Myexception, ParseException {
        usuarioDAO = new UsuarioDAO(MyBatisConnection.getSqlSessionFactory());
        usuario = new Usuario();
        getUsuarioData();
        usuario.setPregunta1("");
        usuario.setRespuesta1("");
        usuario.setPregunta2("");
        usuario.setRespuesta2("");
        usuario.setFechaCreacion(FechaUtil.getCurrentDate());
        usuario.setStatus(0);
        int id = usuarioDAO.inserUsuario(usuario);
        Usuario usuarioId = usuarioDAO.selectById(id);
        table.getListTable().add(usuarioId);
        tablaUsuarios.refresh();
    }

    @Override
    public void setStatusControls() {
        if (table.getModel() != null) {
            usuario = (Usuario) table.getModel();
            // Pongo los textFields con los datos correspondientes
            nombreUsuario.setText(usuario.getNombreUsuario());
            claveUsuario.setText(usuario.getClave());
            correoUsuario.setText(usuario.getCorreo());
            cedula.setText(usuario.getCedula());
            /*Image image = new Image(path);
            imagen.setImage(image);*/
            // Pongo los botones en su estado correspondiente
            btnGuardar.setDisable(false);
        }
    }

    public void onClickUpdate(ActionEvent event) {
        try {
            updateDatosUsuario();
        } catch (Myexception myexception) {
            myexception.printStackTrace();
        }
    }

    private void updateDatosUsuario() throws Myexception {
        getUsuarioData();
        usuarioDAO.updateDatosUsuarios(usuario);
        selectUsuario();
        tablaUsuarios.refresh();
    }

    private Usuario getUsuarioData() {
        usuario.setNombreUsuario(nombreUsuario.getText());
        usuario.setClave(claveUsuario.getText());
        usuario.setCorreo(correoUsuario.getText());
        //usuario.setNivelAcceso(Integer.parseInt(nivelUsuario.getSelectionModel().getSelectedItem().toString()));
        usuario.setNivelAcceso(1);
        usuario.setCedula(cedula.getText());
        return usuario;
    }

    public void onClickUpdateStatus(ActionEvent event) {
        try {
            updateStatusUsuario();
            // eleminarUsuario();
        } catch (Myexception myexception) {
            myexception.printStackTrace();
        }
    }

    private void updateStatusUsuario() throws Myexception {
        usuario.setStatus(3);
        usuarioDAO.updateStatus(usuario);
        selectUsuario();
        tablaUsuarios.refresh();
    }

    private void eleminarUsuario() throws Myexception {
        usuarioDAO.delete(usuario.getIdUsuario());
        table.getListTable().remove(getUsuarioData());
        tablaUsuarios.refresh();
    }

    public void onClickCancelar(ActionEvent event) {
        cambiarEscena(Route.ScreenHomeBackground, anchorPane);
    }
}
