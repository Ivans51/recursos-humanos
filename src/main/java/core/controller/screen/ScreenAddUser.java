package core.controller.screen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.UsuarioDAO;
import core.conexion.vo.Empleado;
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
    public TableColumn tbIdUsuario, tbNombreUsuario, tbCorreo, tbNivelUsuario, tbCedula, tbStatus;
    public TableView<Usuario> tablaUsuarios;
    public TableUtil table;
    public Empleado empleado;
    public String activar = "Activar";
    public String desactivar = "Desactivar";
    private String[] tableS = {"idUsuario", "nombreUsuario", "correo", "nivelAcceso", "cedula", "status"};
    private ObservableList<String> listUserCombo = FXCollections.observableArrayList("1", "2", "3");
    private ObservableList<String> listNacionalidadCombo = FXCollections.observableArrayList("V", "E");
    private List<Usuario> usuarios;
    private Usuario usuario;
    private UsuarioDAO usuarioDAO = new UsuarioDAO(MyBatisConnection.getSqlSessionFactory());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initData();
        // Combobox
        nivelUsuario.setItems(listUserCombo);
        nacionalidad.setItems(listNacionalidadCombo);
        btnActualizar.setDisable(true);

        // Iniciailizar tabla
        table = new TableUtil(Usuario.class, tablaUsuarios);
        table.inicializarTabla(tableS, tbIdUsuario, tbNombreUsuario, tbCorreo, tbNivelUsuario, tbCedula, tbStatus);

        // Seleccionar las tuplas de la tabla de las listTable
        final ObservableList<Usuario> tablaPersonaSel = tablaUsuarios.getSelectionModel().getSelectedItems();
        tablaPersonaSel.addListener((ListChangeListener<Usuario>) c -> table.seleccionarTabla(this));

        // Llenar tabla
        selectUsuario();
        table.getListTable().addAll(usuarios);
    }

    private void initData() {
        if (ScreenAddEmpleado.empleado != null) {
            try {
                empleado = ScreenAddEmpleado.empleado;
                String cedulaValue = Validar.recuperarSegundaPalabra("-", empleado.getCedula());
                nacionalidad.setValue(cedulaValue);
                nivelUsuario.setValue(3);
                nacionalidad.setDisable(true);
                nivelUsuario.setDisable(true);
            } catch (Myexception myexception) {
                myexception.printStackTrace();
            }
        }
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
            Validar.isNumber(cedula.getText());
            Validar.comboBoxVacio(nacionalidad, nivelUsuario);
            insertUsuario();
        } catch (Myexception myexception) {
            myexception.printStackTrace();
            new AlertUtil(Estado.ERROR, myexception.getMessage());
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
            if (usuario.getStatus() == 3)
                btnEliminar.setText(activar);
            else if (usuario.getStatus() == 1)
                btnEliminar.setText(desactivar);
            else
                btnEliminar.setDisable(false);
            btnActualizar.setDisable(false);
            btnGuardar.setDisable(true);
            usuario = (Usuario) table.getModel();
            nombreUsuario.setText(usuario.getNombreUsuario());
            claveUsuario.setText(usuario.getClave());
            correoUsuario.setText(usuario.getCorreo());
            cedula.setText(usuario.getCedula());
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
        Validar.limmpiarCampos(nombreUsuario, claveUsuario, correoUsuario, cedula);
        selectUsuario();
        tablaUsuarios.refresh();
    }

    private Usuario getUsuarioData() {
        usuario.setNombreUsuario(nombreUsuario.getText());
        usuario.setClave(claveUsuario.getText());
        usuario.setCorreo(correoUsuario.getText());
        Integer nivelAcceso = Integer.valueOf(nivelUsuario.getSelectionModel().getSelectedItem().toString());
        usuario.setNivelAcceso(nivelAcceso);
        String cedulaTxt = nacionalidad.getSelectionModel().getSelectedItem().toString() + cedula.getText();
        usuario.setCedula(cedulaTxt);
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
        if (btnEliminar.getText().equals(activar))
            usuario.setStatus(3);
        else if (btnEliminar.getText().equals(desactivar))
            usuario.setStatus(1);
        usuarioDAO.updateStatus(usuario);
        usuarios = usuarioDAO.selectAll();
        table.getListTable().addAll(usuarios);
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
