package core.controller.screen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.CapacitacionDAO;
import core.conexion.vo.Capacitacion;
import core.conexion.vo.Contratacion;
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
public class ScreenTablaCapacitacion extends ManagerFXML implements Initializable, TableUtil.StatusControles  {

    public TableView<Capacitacion> tablaCapacitacion;
    public JFXTextField txtInstructor, txTipo, txtCedulaEmpleado, txtNombreEmpleado, txtDuracion;
    public JFXDatePicker dateFechaCulminacion;
    public TableColumn tbInstructor, tbTipo, tbCedula, tdNombre, tbFechaInicio;
    public JFXButton btnCancelar, btnActualizar;
    public AnchorPane anchorPane;

    public TableUtil table;
    private String[] tableS = {"instructor", "tipo", "cedulaEmpleado", "nombreEmpleado", "fechaInicio"};
    private List<Capacitacion> capacitacionList;
    private Capacitacion capacitacion;
    private CapacitacionDAO capacitacionDAO = new CapacitacionDAO(MyBatisConnection.getSqlSessionFactory());

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Iniciailizar tabla
        table = new TableUtil(Capacitacion.class, tablaCapacitacion);
        table.inicializarTabla(tableS, tbInstructor, tbTipo, tbCedula, tdNombre, tbFechaInicio);

        // Seleccionar las tuplas de la tabla de las listTable
        final ObservableList<Capacitacion> tablaPersonaSel = tablaCapacitacion.getSelectionModel().getSelectedItems();
        tablaPersonaSel.addListener((ListChangeListener<Capacitacion>) c -> table.seleccionarTabla(this));

        selectCapacitacion();
        table.getListTable().addAll(capacitacionList);
    }

    private void selectCapacitacion() {
        capacitacionList = capacitacionDAO.selectAll();
    }

    @Override
    public void setStatusControls() {
        if (table.getModel() != null) {
            capacitacion = (Capacitacion) table.getModel();
            // Pongo los textFields con los datos correspondientes
            txtInstructor.setText(capacitacion.getInstructor());
            txTipo.setText(capacitacion.getTipo());
            txtCedulaEmpleado.setText(capacitacion.getFK_cedula_empleado());
            txtNombreEmpleado.setText(capacitacion.getNombreEmpleado());
            txtDuracion.setText(capacitacion.getDuracion());
        }
    }

    public void onClickUpdate(ActionEvent event) {
        try {
            updateDatosContratacion();
        } catch (Myexception myexception) {
            myexception.printStackTrace();
        }
    }

    private void updateDatosContratacion() throws Myexception{
        getContratacionData();
        capacitacionDAO.update(capacitacion);
        selectCapacitacion();
        tablaCapacitacion.refresh();
    }

    private Capacitacion getContratacionData() {
        capacitacion.setInstructor(txtInstructor.getText());
        capacitacion.setTipo(String.valueOf(txTipo));
        capacitacion.setNombreEmpleado(txtNombreEmpleado.getText());
        capacitacion.setFechaInicio(FechaUtil.getDatePickentCurrent(dateFechaCulminacion));
        capacitacion.setDuracion(txtDuracion.getText());
        capacitacion.setFK_cedula_empleado(txtCedulaEmpleado.getText());
        return capacitacion;
    }

    public void onClickCancelar(ActionEvent event) {
        cambiarEscena(Route.ScreenHomeBackground, anchorPane);
    }
}
