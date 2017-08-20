package core.controller.screen;

import com.jfoenix.controls.JFXButton;
import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.EmpleadoDAO;
import core.conexion.model.EmpleadoContratacion;
import core.conexion.vo.Empleado;
import core.util.*;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Ivans on 7/18/2017.
 */
public class ScreenTableEmpleados extends ManagerFXML implements Initializable, TableUtil.StatusControles {

    public AnchorPane anchorPane;
    public TextField txtBuscarCedula;
    public JFXButton btnCerrar, btnBuscar, btnDetalles, btnLiquidar, btnFactura;
    public TableView<EmpleadoContratacion> tableEmpleado;
    public TableColumn tbCedula, tbNombre, tbDireccion, tbFechaNac, tbCargos, tbStatus, tbSueldo, tbIngreso, tbCulminuacion;
    public static Empleado empleado;
    private List<Empleado> empleados;
    private EmpleadoDAO empleadoDAO = new EmpleadoDAO(MyBatisConnection.getSqlSessionFactory());
    private String[] columS = {"cedula", "nombreEmpleado", "direccion", "fechaNac", "cargo",
            "statusActual", "sueldo", "fechaIngreso", "fechaCulminacion"};

    private List<EmpleadoContratacion> empleadoContratacions = new ArrayList<>();
    private EmpleadoContratacion empleadoContratacion = new EmpleadoContratacion();
    private TableUtil<EmpleadoContratacion, String> table;
    public String idCedula;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stateButton(true, "button-disabled", "button-disabled");
        table = new TableUtil(EmpleadoContratacion.class, tableEmpleado);
        table.inicializarTabla(columS, tbCedula, tbNombre, tbDireccion, tbFechaNac, tbCargos, tbStatus, tbSueldo, tbIngreso, tbCulminuacion);

        final ObservableList<EmpleadoContratacion> tablaSelecionada = tableEmpleado.getSelectionModel().getSelectedItems();
        tablaSelecionada.addListener((ListChangeListener<EmpleadoContratacion>) c -> table.seleccionarTabla(this));

        selectAllEmpleadoContrato();
        table.getListTable().addAll(empleadoContratacions);
        // doubleClickRow();
    }

    private void selectAllEmpleadoContrato() {
        empleados = empleadoDAO.selectAllEmpleadoContrato();
        for (int i = 0; i < empleados.size(); i++) {
            try {
                String fechaNac = FechaUtil.getDateFormat(empleados.get(i).getFechaNacimiento());
                String fechaIngreso = FechaUtil.getDateFormat(empleados.get(i).getContratacion().getFechaInicio());
                String fechaCulminacion = FechaUtil.getDateFormat(empleados.get(i).getContratacion().getFechaCulminacion());
                empleadoContratacion = new EmpleadoContratacion();
                empleadoContratacion.setCedula(empleados.get(i).getCedula());
                empleadoContratacion.setNombreEmpleado(empleados.get(i).getNombreEmpleado());
                empleadoContratacion.setDireccion(empleados.get(i).getDireccion());
                empleadoContratacion.setFechaNac(fechaNac);
                empleadoContratacion.setCargo(empleados.get(i).getCargo());
                empleadoContratacion.setStatusActual(String.valueOf(empleados.get(i).getStatusLaborando()));
                empleadoContratacion.setSueldo(String.valueOf(empleados.get(i).getContratacion().getSalario()));
                empleadoContratacion.setFechaIngreso(fechaIngreso);
                empleadoContratacion.setFechaCulminacion(fechaCulminacion);
                empleadoContratacions.add(empleadoContratacion);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public void onBuscar(ActionEvent event) throws Myexception {
        table.getListTable().removeAll(empleadoContratacions);
        empleado = new Empleado();
        empleado = empleadoDAO.selectByEmpleadoContrato(txtBuscarCedula.getText());
        selectEmpleadoContratacion();
        table.getListTable().add(empleadoContratacion);
        tableEmpleado.refresh();
    }

    private void selectEmpleadoContratacion() {
        try {
            String fechaNac = FechaUtil.getDateFormat(empleado.getFechaNacimiento());
            String fechaIngreso = FechaUtil.getDateFormat(empleado.getContratacion().getFechaInicio());
            String fechaCulminacion = FechaUtil.getDateFormat(empleado.getContratacion().getFechaCulminacion());
            empleadoContratacion.setCedula(empleado.getCedula());
            empleadoContratacion.setNombreEmpleado(empleado.getNombreEmpleado());
            empleadoContratacion.setDireccion(empleado.getDireccion());
            empleadoContratacion.setFechaNac(fechaNac);
            empleadoContratacion.setCargo(empleado.getCargo());
            empleadoContratacion.setStatusActual(String.valueOf(empleado.getStatusLaborando()));
            empleadoContratacion.setSueldo(String.valueOf(empleado.getContratacion().getSalario()));
            empleadoContratacion.setFechaIngreso(fechaIngreso);
            empleadoContratacion.setFechaCulminacion(fechaCulminacion);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void stateButton(boolean value, String removeClass, String addClass) {
        btnDetalles.setDisable(value);
        btnLiquidar.setDisable(value);
        ClassUtil.removeClass(removeClass, btnDetalles, btnLiquidar);
        ClassUtil.addClass(addClass, btnDetalles, btnLiquidar);
    }

    @Override
    public void setStatusControls() {
        stateButton(false, "button-disabled", "button-disabled");
    }

    public void onDetalles(ActionEvent event) {
        abrirSceneByEmpleado(Route.ScreenGestionEmpleado);
    }

    public void onLiquidar(ActionEvent event) {
        abrirSceneByEmpleado(Route.ScreenTableLiquidacion);
    }

    public void onFactura(ActionEvent actionEvent) {
        abrirSceneByEmpleado(Route.ScreenFactura);
    }

    private void abrirSceneByEmpleado(String loadRoot) {
        empleadoContratacion = table.getTablaSeleccionada(tableEmpleado);
        idCedula = empleadoContratacion.getCedula();
        empleado = empleadoDAO.selectById(idCedula);
        cambiarEscena(loadRoot, anchorPane);
    }

    public void onCerrar(ActionEvent event) {
        cambiarEscena(Route.ScreenHomeBackground, anchorPane);
    }
}
