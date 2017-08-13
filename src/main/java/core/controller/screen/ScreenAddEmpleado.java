package core.controller.screen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.ContratacionDAO;
import core.conexion.dao.EmpleadoDAO;
import core.conexion.vo.Contratacion;
import core.conexion.vo.Empleado;
import core.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

/**
 * Created by Ivans on 7/18/2017.
 */
public class ScreenAddEmpleado extends ManagerFXML implements Initializable {

    public JFXTextField registroSS, direccion, cargo;
    public JFXDatePicker fechaNacimiento;
    public JFXButton btnAgregar, btnCancelar;
    public AnchorPane anchorPane;

    private ContratacionDAO contratacionDAO = new ContratacionDAO(MyBatisConnection.getSqlSessionFactory());
    private EmpleadoDAO empleadoDAO = new EmpleadoDAO(MyBatisConnection.getSqlSessionFactory());
    public static Empleado empleado = new Empleado();
    private Contratacion contratacion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initData();
    }

    public void initData() {
        contratacion = ScreenContratoEmpleado.contratacion;
        cargo.setText(contratacion.getCargo());
    }

    public void clickAction(ActionEvent actionEvent) throws Myexception {
        if (actionEvent.getSource() == btnAgregar) {
            try {
                Validar.campoVacio(registroSS, direccion, cargo);
                Validar.datePickerVacio(fechaNacimiento);
                Validar.datePickerRango(FechaUtil.getDatePickentCurrent(fechaNacimiento));
                insertarEmpleados();
                insertarCapacitacion();
                Validar.limmpiarCampos(registroSS, direccion, cargo);
                cambiarEscena(Route.ScreenAddUser, anchorPane);
            } catch (Myexception ex) {
                new AlertUtil(Estado.ERROR, ex.getMessage());
                System.out.println(ex.getMessage());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else
            cambiarEscena(Route.ScreenHomeBackground, anchorPane);
    }

    private void insertarCapacitacion() {
        empleado.setCedula(contratacion.getCedula());
        empleado.setNombreEmpleado(contratacion.getNombre());
        empleado.setFechaNacimiento(FechaUtil.getDatePickentCurrent(fechaNacimiento));
        empleado.setDireccion(direccion.getText());
        empleado.setCargo(cargo.getText());
        empleado.setRegistroSS(registroSS.getText());
        empleado.setStatusLaborando(0);
        int idUsuario = Storage.getUsuario().getIdUsuario();
        empleado.setFK_id_Usuario(idUsuario);
        empleadoDAO.insertEmpleado(empleado);
    }

    private void insertarEmpleados() {
        contratacion.setCedula(contratacion.getCedula());
        contratacion.setNombre(contratacion.getNombre());
        contratacion.setFechaInicio(contratacion.getFechaInicio());
        contratacion.setSalario(contratacion.getSalario());
        contratacion.setCargo(contratacion.getCargo());
        String cedula = Storage.getEmpleado().getCedula();
        contratacion.setEmpleado_cedula(cedula);
        contratacionDAO.insertContratacion(contratacion);
    }
}
