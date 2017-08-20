package core.controller.screen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.SeleccionPersonalDAO;
import core.conexion.vo.SeleccionPersonal;
import core.conexion.vo.Usuario;
import core.util.*;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Ivans on 8/5/2017.
 */
public class ScreenTablaSeleccionPersonal extends ManagerFXML implements Initializable, TableUtil.StatusControles {

    public JFXTextField txtNombreCandidato, txtCedula, txtDireccion, txtTelefono, txtPuesto, txtDisponibillidad;
    public TableView<SeleccionPersonal> tablaSeleccion;
    public TableColumn tbNombre, tbCedula, tbDireccion, tbTelefono, tbPuesto, tbDisponibildad;
    public JFXButton btnCancelar, btnActualizar, btnInsertar;
    public AnchorPane anchorPane;

    public TableUtil table;
    private String[] tableS = {"nombreCandidato", "cedula", "direccion", "telefono", "puestoPostulacion", "disponibilidad"};
    private List<SeleccionPersonal> seleccionPersonals;
    private SeleccionPersonal seleccionPersonal = new SeleccionPersonal();
    private SeleccionPersonalDAO seleccionPersonalDAO = new SeleccionPersonalDAO(MyBatisConnection.getSqlSessionFactory());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Iniciailizar tabla
        table = new TableUtil(SeleccionPersonal.class, tablaSeleccion);
        table.inicializarTabla(tableS, tbNombre, tbCedula, tbDireccion, tbTelefono, tbPuesto, tbDisponibildad);

        // Seleccionar las tuplas de la tabla de las listTable
        final ObservableList<SeleccionPersonal> tablaPersonaSel = tablaSeleccion.getSelectionModel().getSelectedItems();
        tablaPersonaSel.addListener((ListChangeListener<SeleccionPersonal>) c -> table.seleccionarTabla(this));

        selectCapacitacion();
        table.getListTable().addAll(seleccionPersonals);
    }

    private void selectCapacitacion() {
        seleccionPersonals = seleccionPersonalDAO.selectAll();
    }

    public void onClickInsert(ActionEvent event) {
        insertDatos();
    }

    private void insertDatos() {
        try {
            Validar.campoVacio(txtNombreCandidato, txtCedula, txtDireccion, txtTelefono, txtPuesto, txtDisponibillidad);
            Validar.entradaNumerica(txtDisponibillidad);
            Validar.isLetterSpeed(txtNombreCandidato.getText());
            int id = seleccionPersonalDAO.insert(getContratacionSeleccion());
            SeleccionPersonal usuarioId = seleccionPersonalDAO.selectById(id);
            table.getListTable().add(usuarioId);
            tablaSeleccion.refresh();
        } catch (Myexception myexception) {
            myexception.printStackTrace();
        }
    }

    public void onClickUpdate(ActionEvent event) {
        try {
            updateDatosSeleccion();
        } catch (Myexception myexception) {
            myexception.printStackTrace();
        }
    }

    private void updateDatosSeleccion() throws Myexception {
        seleccionPersonalDAO.update(getContratacionSeleccion());
        selectCapacitacion();
        tablaSeleccion.refresh();
    }

    private SeleccionPersonal getContratacionSeleccion() {
        seleccionPersonal.setCedula(txtCedula.getText());
        seleccionPersonal.setNombreCandidato(txtNombreCandidato.getText());
        seleccionPersonal.setDireccion(String.valueOf(txtDireccion.getText()));
        seleccionPersonal.setTelefono(txtTelefono.getText());
        seleccionPersonal.setPuestoPostulacion(txtPuesto.getText());
        seleccionPersonal.setDisponibilidad(Integer.parseInt(txtDisponibillidad.getText()));
        seleccionPersonal.setFK_idUsuario(Storage.getUsuario().getIdUsuario());
        return seleccionPersonal;
    }

    @Override
    public void setStatusControls() {
        if (table.getModel() != null) {
            seleccionPersonal = (SeleccionPersonal) table.getModel();
            // Pongo los textFields con los datos correspondientes
            txtNombreCandidato.setText(seleccionPersonal.getNombreCandidato());
            txtCedula.setText(seleccionPersonal.getCedula());
            txtDireccion.setText(seleccionPersonal.getDireccion());
            txtTelefono.setText(seleccionPersonal.getTelefono());
            txtPuesto.setText(seleccionPersonal.getPuestoPostulacion());
            txtDisponibillidad.setText(String.valueOf(seleccionPersonal.getDisponibilidad()));
        }
    }

    public void onClickCancelar(ActionEvent event) {
        cambiarEscena(Route.ScreenHomeBackground, anchorPane);
    }
}
