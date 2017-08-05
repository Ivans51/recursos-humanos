package core.controller.screen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.EmpleadoDAO;
import core.conexion.dao.NominaDAO;
import core.conexion.vo.Empleado;
import core.conexion.vo.Nomina;
import core.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ivans on 7/18/2017.
 */
public class ScreenGestionEmpleado extends ManagerFXML implements Initializable {


    public JFXTextField txtCedula, txtNombreApellido, txtCargo, txtEstatus;
    public JFXDatePicker txtDateFecha;
    public JFXButton btnModificaEMpleado, btnActualizarTodos, btnActualizarDeduciones;
    public Spinner txtDiasHabiles, txtBonosNocturno, txtBonoLealtad, txtDiasDescanso, txtDiasFeriados, txtDiasNoLaborados;
    public JFXTextField txtFaov, txtIVSS, txtParoForzoso, txtPrestamo;

    private EmpleadoDAO empleadoDAO = new EmpleadoDAO(MyBatisConnection.getSqlSessionFactory());
    private NominaDAO nominaDAO = new NominaDAO(MyBatisConnection.getSqlSessionFactory());
    private Empleado empleadoG;
    private Nomina nomina;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Validar.disableControl(txtCedula);
        Validar.entradaNumerica(txtPrestamo);
        startNomina();
        startEmpleado();
        startSpinner();
    }

    private void startSpinner() {
        int[] iniciarValor = {
                nomina.getDiasHabiles(),
                (int) nomina.getBonoNocturno(),
                (int) nomina.getBonoLealtad(),
                nomina.getDiasDescanso(),
                nomina.getDiasFeriados(),
                nomina.getDiasNoLaborados()
        };
        ControlUtil.spinnerValue(iniciarValor, txtDiasHabiles, txtBonosNocturno, txtBonoLealtad, txtDiasDescanso,
                txtDiasFeriados, txtDiasNoLaborados);
    }

    private void startEmpleado() {
        if (ScreenTableEmpleados.empleado != null)
            empleadoG = ScreenTableEmpleados.empleado;
        txtCedula.setText(empleadoG.getCedula());
        txtNombreApellido.setText(empleadoG.getNombreEmpleado());
        txtCargo.setText(empleadoG.getCargo());
        // txtDateFecha.set
        txtEstatus.setText(empleadoG.getStatus());
    }

    private void startNomina() {
        nomina = nominaDAO.selectById(1);
        txtFaov.setText(String.valueOf(nomina.getFaov()));
        txtIVSS.setText(String.valueOf(nomina.getIvss()));
        txtParoForzoso.setText(String.valueOf(nomina.getParoForzoso()));
        txtPrestamo.setText(String.valueOf(nomina.getPrestamo()));
    }

    public void onModificarEmpleado(ActionEvent event) throws Myexception {
        try {
            Validar.campoVacio(txtCedula, txtNombreApellido, txtCargo, txtEstatus);
            getDatosEmpleado();
            new AlertUtil(Estado.EXITOSA, "Datos Actualizados");
        } catch (Myexception myexception) {
            myexception.printStackTrace();
            new AlertUtil(Estado.ERROR, myexception.getMessage());
        }
    }

    private void getDatosEmpleado() throws Myexception {
        Empleado empleadoG = new Empleado();
        empleadoG.setCedula(txtCedula.getText());
        empleadoG.setNombreEmpleado(txtNombreApellido.getText());
        empleadoG.setFechaNacimiento(FechaUtil.getDatePickentCurrent(txtDateFecha));
        empleadoG.setCargo(txtCargo.getText());
        empleadoG.setStatus(txtEstatus.getText());
        empleadoDAO.updateDatosEmpleado(empleadoG);
    }

    public void onActualizarAsignaciones(ActionEvent event) {
        getDatosAsignaciones();
    }

    private void getDatosAsignaciones() {
        nomina = new Nomina();
        nomina.setDiasHabiles((int) txtDiasHabiles.getValue());
        nomina.setDiasDescanso((int) txtDiasDescanso.getValue());
        nomina.setBonoNocturno((int) txtBonosNocturno.getValue());
        nomina.setBonoLealtad((int) txtBonoLealtad.getValue());
        nomina.setDiasFeriados((int) txtDiasFeriados.getValue());
        nomina.setCedula(empleadoG.getCedula());
        nominaDAO.updateAsignaciones(nomina);
    }

    public void onActualizarDeduciones(ActionEvent event) {
        try {
            Validar.campoVacio(txtPrestamo);
            getDatosDeduciones();
        } catch (Myexception myexception) {
            myexception.printStackTrace();
            new AlertUtil(Estado.ERROR, "LLene los campos de las asignaciones");
        }
    }

    private void getDatosDeduciones() {
        nomina = new Nomina();
        nomina.setFaov(Double.parseDouble(txtFaov.getText()));
        nomina.setIvss(Double.parseDouble(txtIVSS.getText()));
        nomina.setParoForzoso(Double.parseDouble(txtParoForzoso.getText()));
        nomina.setPrestamo(Double.parseDouble(txtPrestamo.getText()));
        nomina.setCedula(empleadoG.getCedula());
        nominaDAO.updateAsignaciones(nomina);
    }
}
