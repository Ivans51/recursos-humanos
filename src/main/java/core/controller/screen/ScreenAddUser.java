package core.controller.screen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.UsuarioDAO;
import core.conexion.vo.Usuario;
import core.util.ManagerFXML;
import core.util.Myexception;
import core.util.Route;
import core.util.Validar;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Ivans on 7/18/2017.
 */
public class ScreenAddUser extends ManagerFXML implements Initializable {

    public AnchorPane anchorPane;
    public JFXTextField nombreUsuario, claveUsuario, correoUsuario, cedula;
    public JFXComboBox nivelUsuario, nacionalidad;
    public TableColumn tbIdUsuario, tbNombreUsuario, tbCorreo, tbNivelUsuario, tbCedula, tbStatus;
    public JFXButton btnGuardar, btnCancelar, btnEliminar, btnActualizar;
    public TableView<Usuario> tablaUsuarios;
    private ObservableList<Usuario> listTable;
    private ObservableList<String> listUserCombo = FXCollections.observableArrayList("1", "2", "3");
    private ObservableList<String> listNacionalidadCombo = FXCollections.observableArrayList("V", "E");
    private List<Usuario> usuarios;
    private Usuario usuario;
    private UsuarioDAO usuarioDAO = new UsuarioDAO(MyBatisConnection.getSqlSessionFactory());
    private int posicionPersonaEnTabla;
    private final ListChangeListener<Usuario> selectorTablaPersonas = c -> ponerPersonaSeleccionada();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inicializarTablaUsuarios();
        nivelUsuario.setItems(listUserCombo);
        nacionalidad.setItems(listNacionalidadCombo);

        // Seleccionar las tuplas de la tabla de las listTable
        final ObservableList<Usuario> tablaPersonaSel = tablaUsuarios.getSelectionModel().getSelectedItems();
        tablaPersonaSel.addListener(selectorTablaPersonas);
        try {
            selectUsuario();
            listTable.addAll(usuarios);
        } catch (Myexception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Método para inicializar la tabla
     */
    private void inicializarTablaUsuarios() {
        tbIdUsuario.setCellValueFactory(new PropertyValueFactory<Usuario, String>("idUsuario"));
        tbNombreUsuario.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombreUsuario"));
        tbCorreo.setCellValueFactory(new PropertyValueFactory<Usuario, String>("correo"));
        tbNivelUsuario.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nivelAcceso"));
        tbCedula.setCellValueFactory(new PropertyValueFactory<Usuario, String>("cedula"));
        tbStatus.setCellValueFactory(new PropertyValueFactory<Usuario, String>("status"));
        listTable = FXCollections.observableArrayList();
        tablaUsuarios.setItems(this.listTable);
    }

    /**
     * Llenar la tabla
     *
     * @throws Myexception
     */
    private void selectUsuario() throws Myexception {
        usuarios = usuarioDAO.selectAll();
    }

    public void onClickGuardar(ActionEvent event) {
        try {
            Validar.campoVacio(nombreUsuario, claveUsuario, correoUsuario, cedula);
            insertUsuario();
        } catch (Myexception myexception) {
            myexception.printStackTrace();
        }
    }

    private void insertUsuario() throws Myexception {
        usuarioDAO = new UsuarioDAO(MyBatisConnection.getSqlSessionFactory());
        usuario = new Usuario();
        getUsuarioData();
        usuario.setPregunta1("");
        usuario.setRespuesta1("");
        usuario.setPregunta2("");
        usuario.setRespuesta2("");
        Timestamp date = new Timestamp(new Date().getTime());
        usuario.setFechaCreacion(date);
        usuario.setStatus(0);
        int id = usuarioDAO.inserUsuario(usuario);
        Usuario usuarioId = usuarioDAO.selectById(id);
        listTable.add(usuarioId);
        tablaUsuarios.refresh();
    }

    public void onClickUpdate(ActionEvent event) {
        try {
            updateDatosUsuario();
            updateStatusUsuario();
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

    private void updateStatusUsuario() throws Myexception {
        usuario.setStatus(3);
        usuarioDAO.updateStatus(usuario);
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

    public void onClickEliminar(ActionEvent event) {
        try {
            eleminarUsuario();
        } catch (Myexception myexception) {
            myexception.printStackTrace();
        }
    }

    private void eleminarUsuario() throws Myexception {
        usuarioDAO.delete(usuario.getIdUsuario());
        listTable.remove(posicionPersonaEnTabla);
        tablaUsuarios.refresh();
    }

    /**
     * Método para poner en los textFields la tupla que seleccionemos
     */
    private void ponerPersonaSeleccionada() {
        usuario = getTablaPersonasSeleccionada(tablaUsuarios);
        posicionPersonaEnTabla = listTable.indexOf(usuario);

        if (usuario != null) {
            // Pongo los textFields con los datos correspondientes
            nombreUsuario.setText(usuario.getNombreUsuario());
            claveUsuario.setText(usuario.getCedula());
            correoUsuario.setText(usuario.getCorreo());
            cedula.setText(usuario.getCedula());
            /*Image image = new Image(path);
            imagen.setImage(image);*/
            // Pongo los botones en su estado correspondiente
            btnGuardar.setDisable(false);
        }
    }

    /**
     * PARA SELECCIONAR UNA CELDA DE LA TABLA
     *
     * @param tablaUsuarios
     */
    public Usuario getTablaPersonasSeleccionada(TableView tablaUsuarios) {
        if (tablaUsuarios != null) {
            List<Usuario> tabla = tablaUsuarios.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final Usuario competicionSeleccionada = tabla.get(0);
                return competicionSeleccionada;
            }
        }
        return null;
    }

    public void onClickCancelar(ActionEvent event) {
        cambiarEscena(Route.ScreenHomeBackground, anchorPane);
    }
}
