package core.controller.screen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.ContratacionDAO;
import core.conexion.dao.EmpleadoDAO;
import core.conexion.vo.Contratacion;
import core.conexion.vo.Empleado;
import core.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ivans on 7/18/2017.
 */
public class ScreenAddEmpleado extends ManagerFXML implements Initializable {

    public JFXTextField cedula, registroSS, direccion, cargo, status;
    public JFXDatePicker fechaNacimiento;
    public JFXButton btnAgregar, btnCancelar;
    public AnchorPane anchorPane;

    private ContratacionDAO contratacionDAO = new ContratacionDAO(MyBatisConnection.getSqlSessionFactory());
    private EmpleadoDAO empleadoDAO = new EmpleadoDAO(MyBatisConnection.getSqlSessionFactory());
    private Empleado empleado = new Empleado();
    private Contratacion contratacion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("hola");
        initData();
    }

    public void initData() {
        contratacion = ScreenContratoEmpleado.contratacion;
        cedula.setText(contratacion.getCedula());
        cargo.setText(contratacion.getCargo());
    }

    public void clickAction(ActionEvent actionEvent) throws Myexception {
        if (actionEvent.getSource() == btnAgregar) {
            try {
                Validar.campoVacio(cedula, registroSS, direccion, cargo, status);
                insertarEmpleados();
                insertarCapacitacion();
                Validar.limmpiarCampos(cedula, registroSS, direccion, cargo, status);
                // new AlertUtil(Estado.EXITOSA);
                cambiarEscena(Route.ScreenHomeBackground, anchorPane);
            } catch (Myexception ex) {
                System.out.println(ex.getMessage());
            }
        } else
            cambiarEscena(Route.ScreenHomeBackground, anchorPane);
    }

    private void insertarCapacitacion() {
        empleado.setCedula(cedula.getText());
        empleado.setNombreEmpleado(contratacion.getNombre());
        empleado.setFechaNacimiento(MananejadorDateUtil.getDatePickentCurrent(fechaNacimiento));
        empleado.setDireccion(direccion.getText());
        empleado.setCargo(cargo.getText());
        empleado.setRegistroSS(registroSS.getText());
        empleado.setStatus("0");
        int idUsuario = Storage.getUsuario().getIdUsuario();
        empleado.setFK_id_Usuario(idUsuario);
        empleadoDAO.insertEmpleado(empleado);
    }

    private void insertarEmpleados() {
        contratacion.setCedula(cedula.getText());
        contratacion.setNombre(contratacion.getNombre());
        contratacion.setFechaInicio(contratacion.getFechaInicio());
        contratacion.setSalario(contratacion.getSalario());
        contratacion.setCargo(contratacion.getCargo());
        String cedula = Storage.getEmpleado().getCedula();
        contratacion.setEmpleado_cedula(cedula);
        contratacionDAO.insertContratacion(contratacion);
    }
}
