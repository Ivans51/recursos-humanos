package core.controller.screen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.ContratacionDAO;
import core.conexion.dao.EmpleadoDAO;
import core.conexion.dao.NominaDAO;
import core.conexion.dao.ValoresDAO;
import core.conexion.vo.Contratacion;
import core.conexion.vo.Empleado;
import core.conexion.vo.Nomina;
import core.conexion.vo.Valores;
import core.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ivans on 7/18/2017.
 */
public class ScreenGestionEmpleado extends ManagerFXML implements Initializable {

    private EmpleadoDAO empleadoDAO = new EmpleadoDAO(MyBatisConnection.getSqlSessionFactory());
    private NominaDAO nominaDAO = new NominaDAO(MyBatisConnection.getSqlSessionFactory());
    private ValoresDAO valoresDAO = new ValoresDAO(MyBatisConnection.getSqlSessionFactory());
    private ContratacionDAO contratacionDAO = new ContratacionDAO(MyBatisConnection.getSqlSessionFactory());

    public AnchorPane anchor;
    public JFXTextField txtCedula, txtNombreApellido, txtCargo, txtEstatus;
    public JFXDatePicker txtDateFecha;
    public JFXButton btnModificaEMpleado, btnActualizarTodos, btnActualizarDeduciones;
    public Spinner txtDiasHabiles, txtBonosNocturno, txtDiasDescanso, txtDiasFeriados, txtDiasNoLaborados;
    public JFXTextField txtFaov, txtIVSS, txtParoForzoso, txtPrestamo, txtSalario;

    private Empleado empleadoG;
    private Nomina nomina;
    private Valores valores;
    private Contratacion contrato;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Validar.disabledControl(Estado.DISABLE, true, txtCedula, txtEstatus);
            Validar.entradaNumerica(txtPrestamo);
            startNomina();
            startEmpleado();
            startContratacion();
            startValores();
            startSpinner();
        } catch (Myexception myexception) {
            myexception.printStackTrace();
        }
    }

    private void startEmpleado() {
        if (ScreenTableEmpleados.empleado != null) {
            if (ScreenTableEmpleados.empleado.getCedula() != null) {
                empleadoG = ScreenTableEmpleados.empleado;
                txtCedula.setText(empleadoG.getCedula());
                txtNombreApellido.setText(empleadoG.getNombreEmpleado());
                txtCargo.setText(empleadoG.getCargo());
            }
        }
    }

    private void startContratacion() {
        contrato = contratacionDAO.selectByForeighKey("123");
        txtSalario.setText(String.valueOf(contrato.getSalarioActual()));
    }

    private void startNomina() {
        nomina = nominaDAO.selectForeighKey("123");
        txtPrestamo.setText(String.valueOf(nomina.getPrestamo()));
    }

    private void startValores() {
        valores = valoresDAO.selectByIdLastDate();
        txtFaov.setText(String.valueOf(valores.getFAO()));
        txtIVSS.setText(String.valueOf(valores.getIVSS()));
        txtParoForzoso.setText(String.valueOf(valores.getParoForzoso()));
    }

    private void startSpinner() {
        int[] iniciarValor = {
                nomina.getDiasHabiles(),
                nomina.getBonoNocturno(),
                nomina.getDiasDescanso(),
                nomina.getDiasFeriados(),
                nomina.getDiasNoLaborados()
        };
        ControlUtil.spinnerValue(iniciarValor, txtDiasHabiles, txtBonosNocturno, txtDiasDescanso,
                txtDiasFeriados, txtDiasNoLaborados);
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
        empleadoDAO.updateDatosEmpleado(empleadoG);
        if (empleadoDAO == null) throw new Myexception("Empleado vacío");
    }

    public void onActualizarAsignaciones(ActionEvent event) {
        try {
            getDatosAsignaciones();
        } catch (Myexception myexception) {
            myexception.printStackTrace();
            new AlertUtil(Estado.ERROR, myexception.getMessage());
        }
    }

    private void getDatosAsignaciones() throws Myexception {
        nomina = new Nomina();
        nomina.setDiasHabiles((int) txtDiasHabiles.getValue());
        nomina.setDiasDescanso((int) txtDiasDescanso.getValue());
        nomina.setBonoNocturno((int) txtBonosNocturno.getValue());
        nomina.setDiasFeriados((int) txtDiasFeriados.getValue());
        nominaDAO.updateAsignaciones(nomina);
        if (nominaDAO == null) throw new Myexception("Nomina vacía");
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

    private void getDatosDeduciones() throws Myexception {
        nomina = new Nomina();
        nomina.setPrestamo(Double.parseDouble(txtPrestamo.getText()));
        nominaDAO.updateAsignaciones(nomina);
        if (nominaDAO == null) throw new Myexception("Nomina vacía");
    }

    public void onCancelar(ActionEvent event) {
        cambiarEscena(Route.ScreenTableEmpleados, anchor);
    }
}
