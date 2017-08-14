package core.controller.screen;

import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;
import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.ContratacionDAO;
import core.conexion.dao.EmpleadoDAO;
import core.conexion.dao.ValoresDAO;
import core.conexion.vo.Contratacion;
import core.conexion.vo.Empleado;
import core.conexion.vo.Valores;
import core.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Ivans on 8/6/2017.
 */
public class ScreenLiquidacion extends ManagerFXML implements Initializable {

    public AnchorPane anchor;
    public JFXButton btnImprimir, btnCerrar;
    public Label lblNombreApellido, lblCedula, lblUltimoSalario, lblCargo, lblFechaIngreso, lblFechaRetiro;
    public Label lblAntiguedad, lblVacaciones, lblBonoVacacional, lblUtilidades, lblIntereses;
    public Label lblTotalDeduciones, lblAntiguedadAlicuotas, lblVacacionesAlicuota, lblBonoVacacionalAlicuota;
    public Label lblUtilidadesAlicuota, lblAntiguedadTotal, lblVacacionesTotal, lblBonoVacacionalTotal;
    public Label lblUtilidadTotal, lblInteresesTotal, lblINCETotal, lblLiquidacionTotal;
    public CalculoLiquidacion liquidacion;
    private EmpleadoDAO empleadoDAO = new EmpleadoDAO(MyBatisConnection.getSqlSessionFactory());
    private ValoresDAO valoresDAO = new ValoresDAO(MyBatisConnection.getSqlSessionFactory());
    private ContratacionDAO contratacionDAO = new ContratacionDAO(MyBatisConnection.getSqlSessionFactory());
    private Empleado empleado;
    private Valores valores;
    private List<Valores> valoresList;
    private Contratacion contratacion;
    public int diasUtilidades;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initData();
        int mesesLaborando = FechaUtil.getMesesLaborando(contratacion.getFechaInicio());
        liquidacion = new CalculoLiquidacion(valores.getSalario(), getDiasUtilidades(), mesesLaborando);
        setDataEmpleado();
        setDataLiquidacion();
    }

    private void initData() {
        String cedulaEmpleado = "123";
        empleado = empleadoDAO.selectById(cedulaEmpleado);
        valores = valoresDAO.selectByIdLastDate();
        valoresList = valoresDAO.selectAll();
        contratacion = contratacionDAO.selectByForeighKey(cedulaEmpleado);
    }

    private void setDataEmpleado() {
        try {
            lblNombreApellido.setText(empleado.getNombreEmpleado());
            lblCedula.setText(empleado.getCedula());
            lblUltimoSalario.setText(String.valueOf(valores.getSalario()));
            lblCargo.setText(contratacion.getCargo());
            lblFechaIngreso.setText(String.valueOf(contratacion.getFechaInicio()));
            String dateFormat = FechaUtil.getDateFormat(FechaUtil.getCurrentDate());
            lblFechaRetiro.setText(dateFormat);
            lblAntiguedad.setText("No tengo idea");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void setDataLiquidacion() {
        try {
            lblVacaciones.setText(String.valueOf(liquidacion.salarioDiario()));
            lblBonoVacacional.setText(String.valueOf(liquidacion.salarioDiario()));
            lblUtilidades.setText("No tengo idea");
            lblIntereses.setText("No tengo Idea");
            lblTotalDeduciones.setText(String.valueOf(liquidacion.totalDeduciones()));
            lblAntiguedadAlicuotas.setText(String.valueOf(getDiasUtilidades()));
            lblVacacionesAlicuota.setText(String.valueOf(liquidacion.vacaciones()));
            lblBonoVacacionalAlicuota.setText(String.valueOf(liquidacion.bonoVacacional()));
            lblUtilidadesAlicuota.setText("No tengo idea");
            lblAntiguedadTotal.setText(String.valueOf(getDiasUtilidades() * liquidacion.salarioDiario()));
            lblVacacionesTotal.setText(String.valueOf(liquidacion.vacaciones() * liquidacion.salarioDiario()));
            lblBonoVacacionalTotal.setText(String.valueOf(liquidacion.bonoVacacional() * liquidacion.salarioDiario()));
            lblUtilidadTotal.setText("No tengo idea");
            lblInteresesTotal.setText("No tengo idea");
            lblINCETotal.setText("No tengo Idea");
            lblLiquidacionTotal.setText(String.valueOf(liquidacion.total()));
        } catch (Myexception myexception) {
            myexception.printStackTrace();
        }
    }

    public void onImprimir(ActionEvent event) throws FileNotFoundException, DocumentException {
        PDFCreator pdfCreator = new PDFCreator();
        pdfCreator.crearPDF("CARS 2.0", "Liquidación del empleado", 4, () -> {
           pdfCreator.getTabla().addCell("Nombre y Apellido");
           pdfCreator.getTabla().addCell(lblNombreApellido.getText());
           pdfCreator.getTabla().addCell("Fecha de Ingreso");
           pdfCreator.getTabla().addCell(lblFechaIngreso.getText());
           pdfCreator.getTabla().addCell("Cedula de Identidad");
           pdfCreator.getTabla().addCell(lblCedula.getText());
           pdfCreator.getTabla().addCell("Fecha de Retiro");
           pdfCreator.getTabla().addCell(lblFechaRetiro.getText());
           pdfCreator.getTabla().addCell("Cargo");
           pdfCreator.getTabla().addCell(lblCargo.getText());
           // Calculo de liquidación
           pdfCreator.getTabla().addCell("Descripción");
           pdfCreator.getTabla().addCell("Salario / Devengado");
           pdfCreator.getTabla().addCell("Dias / Alic");
           pdfCreator.getTabla().addCell("Total BS.");
           pdfCreator.getTabla().addCell("Antiguedad");
           pdfCreator.getTabla().addCell(lblAntiguedad.getText());
           pdfCreator.getTabla().addCell(lblAntiguedadAlicuotas.getText());
           pdfCreator.getTabla().addCell(lblAntiguedadTotal.getText());
           pdfCreator.getTabla().addCell("Vacaciones");
           pdfCreator.getTabla().addCell(lblVacaciones.getText());
           pdfCreator.getTabla().addCell(lblVacacionesAlicuota.getText());
           pdfCreator.getTabla().addCell(lblVacacionesTotal.getText());
           pdfCreator.getTabla().addCell("Bono Vacacional");
           pdfCreator.getTabla().addCell(lblBonoVacacional.getText());
           pdfCreator.getTabla().addCell(lblBonoVacacionalAlicuota.getText());
           pdfCreator.getTabla().addCell(lblBonoVacacionalTotal.getText());
           pdfCreator.getTabla().addCell("Utilidades");
           pdfCreator.getTabla().addCell(lblUtilidades.getText());
           pdfCreator.getTabla().addCell(lblUtilidadesAlicuota.getText());
           pdfCreator.getTabla().addCell(lblUtilidadTotal.getText());
           pdfCreator.getTabla().addCell("Interes Prestaciones");
           pdfCreator.getTabla().addCell("");
           pdfCreator.getTabla().addCell("");
           pdfCreator.getTabla().addCell(lblInteresesTotal.getText());
           pdfCreator.getTabla().addCell("Total Asignaciones");
           pdfCreator.getTabla().addCell("");
           pdfCreator.getTabla().addCell("");
           pdfCreator.getTabla().addCell(lblTotalDeduciones.getText());
           pdfCreator.getTabla().addCell("DEDUCIONES");
           pdfCreator.getTabla().addCell("");
           pdfCreator.getTabla().addCell("");
           pdfCreator.getTabla().addCell("");
           pdfCreator.getTabla().addCell("Total Deduciones");
           pdfCreator.getTabla().addCell("");
           pdfCreator.getTabla().addCell("");
           pdfCreator.getTabla().addCell(lblTotalDeduciones.getText());
           pdfCreator.getTabla().addCell("Neto a Cobrar");
           pdfCreator.getTabla().addCell("");
           pdfCreator.getTabla().addCell("");
           pdfCreator.getTabla().addCell(lblLiquidacionTotal.getText());
        });
    }

    public void onCerrar(ActionEvent event) {
        cambiarEscena(Route.ScreenHomeBackground, anchor);
    }
    public int getDiasUtilidades() {
        int mesesLaborando = FechaUtil.getMesesLaborando(contratacion.getFechaInicio());
        if (mesesLaborando < 3){
            diasUtilidades = 5;
        } else if (mesesLaborando >= 3 && mesesLaborando <= 12){
            int cont = 0;
            while(cont <= mesesLaborando){
                cont += 3;
                diasUtilidades += 15;
            }
        } else {
            int años = mesesLaborando / 12;
            return diasUtilidades = años * 60;
        }
        return 0;
    }
}
