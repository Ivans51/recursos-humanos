package core.controller.screen;

import com.itextpdf.text.DocumentException;
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

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

/**
 * Created by Ivans on 7/18/2017.
 */
public class ScreenFactura extends ManagerFXML implements Initializable, PDFCreator.PDFTabla {

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
    private PDFCreator pdfCreator;

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
            imprimir();
        } catch (Myexception myexception) {
            new AlertUtil(Estado.ERROR, "Campo Vacío");
            myexception.printStackTrace();
        }
    }

    private void imprimir(){
        try {
            pdfCreator = new PDFCreator();
            pdfCreator.setSegundoParrafo("Factura del empleado" + lblNombreApellido.getText());
            String name = "Factura del empleado " + empleado.getNombreEmpleado() + ".pdf";
            pdfCreator.crearPDF(name, "Cars CA", 4, this);
            if (Desktop.isDesktopSupported()) {
                try {
                    File myFile = new File("Factura del empleado.pdf");
                    Desktop.getDesktop().open(myFile);
                } catch (IOException ex) {
                    // no application registered for PDFs
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCellTable() {
        try {
            pdfCreator.getTabla().addCell("Nombre y Apellido");
            pdfCreator.getTabla().addCell(empleado.getNombreEmpleado());
            pdfCreator.getTabla().addCell("Cargo");
            pdfCreator.getTabla().addCell(empleado.getCargo());
            pdfCreator.getTabla().addCell("Cédula");
            pdfCreator.getTabla().addCell(empleado.getCedula());
            pdfCreator.getTabla().addCell("Fecha de Ingreso");
            pdfCreator.getTabla().addCell(String.valueOf(empleado.getFechaNacimiento()));
            pdfCreator.getTabla().addCell("Salario");
            pdfCreator.getTabla().addCell(String.valueOf(quincena.getSalarioDiario()));
            pdfCreator.getTabla().addCell("Salario Mensual");
            pdfCreator.getTabla().addCell(String.valueOf(valores.getSalario()));
            pdfCreator.getTabla().addCell("Asignaciones");
            pdfCreator.getTabla().addCell("");
            pdfCreator.getTabla().addCell("Deduciones");
            pdfCreator.getTabla().addCell("");
            pdfCreator.getTabla().addCell("Dias Habiles");
            pdfCreator.getTabla().addCell(String.valueOf(quincena.getDiasHabiles()));
            pdfCreator.getTabla().addCell("FAOV");
            pdfCreator.getTabla().addCell(String.valueOf(quincena.getFAOV()));
            pdfCreator.getTabla().addCell("Dias Descanso");
            pdfCreator.getTabla().addCell(String.valueOf(quincena.getDiasNoLaborados()));
            pdfCreator.getTabla().addCell("IVSS");
            pdfCreator.getTabla().addCell(String.valueOf(quincena.getIVSS()));
            pdfCreator.getTabla().addCell("Bono Nocturno");
            pdfCreator.getTabla().addCell(String.valueOf(quincena.getIVSS()));
            pdfCreator.getTabla().addCell("Paro Forzoso");
            pdfCreator.getTabla().addCell(String.valueOf(quincena.getBonoNocturno()));
            pdfCreator.getTabla().addCell("Dias Feriados Laborados");
            pdfCreator.getTabla().addCell(String.valueOf(quincena.getDiasFeriados()));
            pdfCreator.getTabla().addCell("Prestamo");
            pdfCreator.getTabla().addCell(String.valueOf(nomina.getPrestamo()));
            pdfCreator.getTabla().addCell("Total Asignaciones");
            pdfCreator.getTabla().addCell(String.valueOf(quincena.getTotalAsignaciones()));
            pdfCreator.getTabla().addCell("Total Deduciones");
            pdfCreator.getTabla().addCell(String.valueOf(quincena.getTotalDeduciones()));
            pdfCreator.getTabla().addCell("");
            pdfCreator.getTabla().addCell("");
            pdfCreator.getTabla().addCell("Total de Pago");
            pdfCreator.getTabla().addCell(String.valueOf(quincena.getTotalQuincena()));

        } catch (Myexception myexception) {
            myexception.printStackTrace();
        }
    }

    private void getDatosEmpleado() {
        txtFechaPago.getText();
    }
}
