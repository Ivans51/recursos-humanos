package core.controller.screen;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

/**
 * Created by Ivans on 7/18/2017.
 */
public class ScreenFactura extends ManagerFXML implements Initializable {

    public JFXButton btnImprimir;
    public TextField txtFechaPago;
    public Label lblNombreApellido, lblCedula, lblSalarioDiario, lblCargo, lblFechaIngreso, lblSalarioMensual;
    public Label lblDiasDescanso, lblBonoNocturno, lblDiasFeriados, lblDiasHabilesCalc, lblDiasHabiles;
    public Label lblDiasDescansoCalc, lblBonoNocturnoCalc, lblDiasNoLaboradosCalc;
    public Label lblTotalAsignaciones, lblFaov, lblIVSS, lblParoForzoso, lblPrestamo, lblDiasNoLaborados;
    public Label lblTotalDeduciones,  lblTotal, lblDiasFeriadosCalc;

    private EmpleadoDAO empleadoDAO = new EmpleadoDAO(MyBatisConnection.getSqlSessionFactory());
    private Empleado empleado = new Empleado();
    private ContratacionDAO contratacionDAO = new ContratacionDAO(MyBatisConnection.getSqlSessionFactory());
    private Contratacion contratacion = new Contratacion();
    private NominaDAO nominaDAO = new NominaDAO(MyBatisConnection.getSqlSessionFactory());
    private Nomina nomina = new Nomina();
    private ValoresDAO valoresDAO = new ValoresDAO(MyBatisConnection.getSqlSessionFactory());
    private Valores valores = new Valores();
    private CalculoQuincena quincena;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            hacerConsulta();
            getCalcQuincena();
            setEmpleado();
            setNomina();
            setValores();
        } catch (Myexception myexception) {
            myexception.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void hacerConsulta() {
        empleado = empleadoDAO.selectById("123");
        nomina = nominaDAO.selectForeighKey("123");
        contratacion = contratacionDAO.selectByForeighKey("123");
        valores = valoresDAO.selectByIdLastDate();
    }

    private void getCalcQuincena(){
        quincena = new CalculoQuincena(valores.getSalario());
        quincena.setAsignaciones(nomina.getDiasHabiles(), nomina.getBonoNocturno(), nomina.getDiasFeriados());
        quincena.setDeduciones(nomina.getDiasNoLaborados(), valores.getFAO(), valores.getIVSS(),
                valores.getParoForzoso(), nomina.getPrestamo());
    }

    private void setEmpleado() throws ParseException {
        lblNombreApellido.setText(empleado.getNombreEmpleado());
        lblCedula.setText(empleado.getCedula());
        lblCargo.setText(empleado.getCargo());
        lblFechaIngreso.setText(FechaUtil.getDateFormat(contratacion.getFechaInicio()));
    }

    private void setNomina() throws Myexception {
        try {
            String calcDiasNoLaborados = String.valueOf(nomina.getDiasNoLaborados()) + "    " + quincena.getDiasNoLaborados();
            lblDiasNoLaboradosCalc.setText(calcDiasNoLaborados);
            lblDiasHabiles.setText(String.valueOf(nomina.getDiasHabiles()));
            lblDiasDescanso.setText(String.valueOf(nomina.getDiasDescanso()));
            lblBonoNocturno.setText(String.valueOf(nomina.getBonoNocturno()));
            lblDiasFeriados.setText(String.valueOf(nomina.getDiasFeriados()));
            lblSalarioDiario.setText(String.valueOf(quincena.getSalarioDiario()));
            lblDiasHabilesCalc.setText(String.valueOf(quincena.getDiasHabiles()));
            lblDiasDescansoCalc.setText(String.valueOf(quincena.getDiasDescanso()));
            lblBonoNocturnoCalc.setText(String.valueOf(quincena.getBonoNocturno()));
            lblDiasFeriadosCalc.setText(String.valueOf(quincena.getDiasFeriados()));
            lblTotalAsignaciones.setText(String.valueOf(quincena.getTotalAsignaciones()));
            lblFaov.setText(String.valueOf(quincena.getFAOV()));
            lblIVSS.setText(String.valueOf(quincena.getIVSS()));
            lblParoForzoso.setText(String.valueOf(quincena.getParoForzoso()));
            lblPrestamo.setText(String.valueOf(nomina.getPrestamo()));
            lblTotalDeduciones.setText(String.valueOf(quincena.getTotalDeduciones()));
            lblTotal.setText(String.valueOf(quincena.getTotalQuincena()));
        } catch (Myexception myexception) {
            myexception.printStackTrace();
        }
    }

    private void setValores() throws Myexception {
        lblSalarioMensual.setText(String.valueOf(valores.getSalario()));
    }

    public void onImprimir(ActionEvent event) {
        try {
            Validar.campoVacio(txtFechaPago);
            getDatosEmpleado();
        } catch (Myexception myexception) {
            new AlertUtil(Estado.ERROR, "Campo Vacío");
            myexception.printStackTrace();
        }
    }

    private void getDatosEmpleado() {
        txtFechaPago.getText();
    }
}
