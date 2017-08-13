package core.controller.screen;

import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;
import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.EmpleadoDAO;
import core.conexion.dao.NominaDAO;
import core.conexion.dao.ValoresDAO;
import core.conexion.model.EmpleadoTotalPago;
import core.conexion.vo.Empleado;
import core.conexion.vo.Nomina;
import core.conexion.vo.Valores;
import core.util.*;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
public class ScreenReportTotalPagos extends ManagerFXML implements Initializable, PDFCreator.PDFTabla{
    public AnchorPane anchor;
    public JFXButton btnImprimir;
    public TableColumn tbCedula, tbNombre, tbCestaTicke, tbSalarioIntegral,tbQuincena, tbUtitilidades, tbVales;
    public TableView<EmpleadoTotalPago> tablaReportTotal;

    public TableUtil table;
    private String[] tableS = {"cedula", "nombreEmpleado", "cestaTicket", "salarioIngtegral", "quincena", "utilidades", "vales"};
    private List<Empleado> empleadoList;
    private List<Nomina> nominaList;
    private List<EmpleadoTotalPago>  empleadoTotalPagos;
    private EmpleadoTotalPago empleadoTotalPago;
    private Valores valores;
    private Empleado empleado;
    public CalculoLiquidacion calculoLiquidacion;

    private EmpleadoDAO empleadoDAO = new EmpleadoDAO(MyBatisConnection.getSqlSessionFactory());
    private ValoresDAO valoresDAO = new ValoresDAO(MyBatisConnection.getSqlSessionFactory());
    private NominaDAO nominaDAO = new NominaDAO(MyBatisConnection.getSqlSessionFactory());
    public PDFCreator pdfCreator;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Iniciailizar tabla
            calculoLiquidacion = new CalculoLiquidacion(valores.getSalario(), valores.getDiasUtilidades());
            table = new TableUtil(EmpleadoTotalPago.class, tablaReportTotal);
            table.inicializarTabla(tableS, tbCedula, tbNombre, tbCestaTicke, tbSalarioIntegral,tbQuincena, tbUtitilidades, tbVales);
            initSelect();
            setDataTable();
            table.getListTable().addAll(empleadoList);
        } catch (Myexception myexception) {
            myexception.printStackTrace();
        }
    }

    private void initSelect() {
        empleadoList = empleadoDAO.selectAll();
        nominaList = nominaDAO.selectAll();
        valores = valoresDAO.selectByIdLastDate();
    }

    private void setDataTable() throws Myexception {
        CalculoQuincena calculoQuincena = new CalculoQuincena(valores.getSalario());
        double totalQuincena = calculoQuincena.getTotalQuincena();
        for (int i = 0; i < empleadoList.size(); i++) {
            empleadoTotalPago = new EmpleadoTotalPago();
            empleadoTotalPago.setCedula(empleadoList.get(i).getCedula());
            empleadoTotalPago.setNombreEmpleado(empleadoList.get(i).getNombreEmpleado());
            empleadoTotalPago.setCestaTicket(calculoLiquidacion.cestaTicket(valores.getPrecioUnidadTributaria()));
            empleadoTotalPago.setSalarioIngtegral(calculoLiquidacion.salarioIntegral());
            empleadoTotalPago.setQuincena(totalQuincena);
            empleadoTotalPago.setUtilidades(calculoLiquidacion.utilidades());
            empleadoTotalPago.setVales(nominaList.get(i).getPrestamo());
            empleadoTotalPagos.add(empleadoTotalPago);
        }
    }

    public void onImprimir(ActionEvent event) throws FileNotFoundException, DocumentException {
        pdfCreator = new PDFCreator();
        pdfCreator.crearPDF("Totald de pagos a empleados", "Listado de empleados", 7, this);
    }

    @Override
    public void addCellTable() {
        for (EmpleadoTotalPago totalPago : empleadoTotalPagos) {
            pdfCreator.getTabla().addCell(totalPago.getCedula());
            pdfCreator.getTabla().addCell(totalPago.getNombreEmpleado());
            pdfCreator.getTabla().addCell(String.valueOf(totalPago.getCestaTicket()));
            pdfCreator.getTabla().addCell(String.valueOf(totalPago.getQuincena()));
            pdfCreator.getTabla().addCell(String.valueOf(totalPago.getSalarioIngtegral()));
            pdfCreator.getTabla().addCell(String.valueOf(totalPago.getUtilidades()));
            pdfCreator.getTabla().addCell(String.valueOf(totalPago.getVales()));
        }
    }

    public void onCerrar(ActionEvent event) {
        cambiarEscena(Route.ScreenHomeBackground, anchor);
    }
}
