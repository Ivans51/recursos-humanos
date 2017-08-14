package core.controller.screen;

import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;
import com.sun.istack.internal.Nullable;
import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.ContratacionDAO;
import core.conexion.dao.EmpleadoDAO;
import core.conexion.dao.NominaDAO;
import core.conexion.dao.ValoresDAO;
import core.conexion.model.EmpleadoPago;
import core.conexion.vo.Contratacion;
import core.conexion.vo.Empleado;
import core.conexion.vo.Nomina;
import core.conexion.vo.Valores;
import core.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Ivans on 7/18/2017.
 */
public class ScreenReportEmpleado extends ManagerFXML implements Initializable, PDFCreator.PDFTabla {

    public AnchorPane anchor;
    public JFXButton btnCesta, btnSalarioIntegral, btnSalarioQuincenal, btnUtilidades, btnVales, btnCerrar;
    public TableColumn tbCedula, tbNombre, tbDireccion, tbFechaNac, tbCargos, tbStatus, tbSueldo, tbIngreso, tbMonto;
    public TableView<EmpleadoPago> tablaReportTotal;

    public TableUtil table;
    public EmpleadoPago empleadoPago;
    public CalculoLiquidacion calculoLiquidacion;

    private String[] tableS = {"cedula", "nombreEmpleado", "direccion", "fechaNacimiento", "cargo",
            "statusLaborando", "salario", "fechaInicio", "monto"};
    private List<Empleado> empleadoList;
    private List<Contratacion> contratacionList;
    private List<EmpleadoPago> empleadoPagoList;
    private List<Nomina> nominaList;
    private Valores valores;

    private EmpleadoDAO empleadoDAO = new EmpleadoDAO(MyBatisConnection.getSqlSessionFactory());
    private ContratacionDAO contratacionDAO = new ContratacionDAO(MyBatisConnection.getSqlSessionFactory());
    private ValoresDAO valoresDAO = new ValoresDAO(MyBatisConnection.getSqlSessionFactory());
    private NominaDAO nominaDAO = new NominaDAO(MyBatisConnection.getSqlSessionFactory());
    public PDFCreator pdfCreator;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Iniciailizar tabla
        table = new TableUtil(EmpleadoPago.class, tablaReportTotal);
        table.inicializarTabla(tableS, tbCedula, tbNombre, tbDireccion, tbFechaNac, tbCargos, tbStatus, tbSueldo, tbIngreso, tbMonto);

        selectValores();
        int mesesLaborando = FechaUtil.getMesesLaborando(contratacionList.get(0).getFechaInicio());
        calculoLiquidacion = new CalculoLiquidacion(valores.getSalario(), valores.getDiasUtilidades(), mesesLaborando);
        setDataTable(calculoLiquidacion.cestaTicket(valores.getPrecioUnidadTributaria()), null);
        table.getListTable().addAll(empleadoPagoList);
    }

    private void selectValores() {
        valores = valoresDAO.selectByIdLastDate();
        empleadoList = empleadoDAO.selectAll();
        contratacionList = contratacionDAO.selectAll();
        nominaList = nominaDAO.selectAll();
    }

    private void setDataTable(@Nullable double monto, @Nullable List<Nomina> nominaList) {
        for (int i = 0; i < empleadoList.size(); i++) {
            empleadoPago = new EmpleadoPago();
            empleadoPago.setCedula(empleadoList.get(i).getCedula());
            empleadoPago.setNombreEmpleado(empleadoList.get(i).getNombreEmpleado());
            empleadoPago.setDireccion(empleadoList.get(i).getDireccion());
            empleadoPago.setFechaNacimiento(empleadoList.get(i).getFechaNacimiento());
            empleadoPago.setCargo(contratacionList.get(i).getCargo());
            empleadoPago.setStatusLaborando(empleadoList.get(i).getStatusLaborando());
            empleadoPago.setSalario(contratacionList.get(i).getSalario());
            empleadoPago.setFechaInicio(contratacionList.get(i).getFechaInicio());
            if (nominaList != null)
                empleadoPago.setMonto(nominaList.get(i).getPrestamo());
            else
                empleadoPago.setMonto(monto);
            empleadoPagoList.add(empleadoPago);
        }
    }

    public void onCesta(ActionEvent event) {
        setDataTable(calculoLiquidacion.cestaTicket(valores.getPrecioUnidadTributaria()), null);
        tablaReportTotal.refresh();
    }

    public void onSalarioIntegral(ActionEvent event) throws Myexception {
        setDataTable(calculoLiquidacion.salarioIntegral(), null);
        tablaReportTotal.refresh();
    }

    public void onSalarioQuincenal(ActionEvent event) throws Myexception {
        CalculoQuincena calculoQuincena = new CalculoQuincena(valores.getSalario());
        double totalQuincena = calculoQuincena.getTotalQuincena();
        setDataTable(totalQuincena, null);
    }

    public void onUtilidades(ActionEvent event) throws Myexception {
        setDataTable(calculoLiquidacion.utilidades(), null);
        tablaReportTotal.refresh();
    }

    public void onVales(ActionEvent event) {
        setDataTable(Double.parseDouble(null), nominaList);
    }

    public void onCerrar(ActionEvent event) {
        cambiarEscena(Route.ScreenHomeBackground, anchor);
    }

    public void onImprimir(ActionEvent event) throws FileNotFoundException, DocumentException {
        pdfCreator = new PDFCreator();
        pdfCreator.crearPDF("Reporte de los empleados", "Lista de los empleados", 9, this);
    }

    @Override
    public void addCellTable() {
        for (EmpleadoPago pago : empleadoPagoList) {
            pdfCreator.getTabla().addCell(pago.getCedula());
            pdfCreator.getTabla().addCell(pago.getNombreEmpleado());
            pdfCreator.getTabla().addCell(pago.getDireccion());
            pdfCreator.getTabla().addCell(String.valueOf(pago.getFechaNacimiento()));
            pdfCreator.getTabla().addCell(pago.getCargo());
            pdfCreator.getTabla().addCell(String.valueOf(pago.getStatusLaborando()));
            pdfCreator.getTabla().addCell(String.valueOf(pago.getSalario()));
            pdfCreator.getTabla().addCell(String.valueOf(pago.getFechaInicio()));
            pdfCreator.getTabla().addCell(String.valueOf(pago.getMonto()));
        }
    }
}
