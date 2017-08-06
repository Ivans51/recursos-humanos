package core.controller.screen;

import com.jfoenix.controls.JFXButton;
import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.EmpleadoDAO;
import core.conexion.vo.Capacitacion;
import core.conexion.vo.Empleado;
import core.util.ManagerFXML;
import core.util.TableUtil;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Ivans on 7/18/2017.
 */
public class ScreenReportEmpleado extends ManagerFXML implements Initializable, TableUtil.StatusControles {

    public AnchorPane anchor;
    public JFXButton btnCesta, btnSalarioIntegral, btnSalarioQuincenal, btnUtilidades, btnVales, btnLiquidacion, btnCerrar;
    public TableColumn tbCedula, tbNombre, tbDireccion, tbFechaNac, tbCargos, tbStatus, tbSueldo, tbIngreso, tbFechaCulm;
    public TableView tablaReportTotal;

    public TableUtil table;
    private String[] tableS = {"cedula", "nombreEmpleado", "direccion", "fechaNacimiento", "cargo", "status"};
    private List<Empleado> empleadoList;
    private Empleado empleado;
    private EmpleadoDAO empleadoDAO = new EmpleadoDAO(MyBatisConnection.getSqlSessionFactory());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Iniciailizar tabla
        table = new TableUtil(Empleado.class, tablaReportTotal, null, null);
        table.inicializarTabla(tableS, tbCedula, tbNombre, tbDireccion, tbFechaNac, tbCargos, tbStatus, tbSueldo, tbIngreso, tbFechaCulm);

        // Seleccionar las tuplas de la tabla de las listTable
        final ObservableList<Empleado> tablaPersonaSel = tablaReportTotal.getSelectionModel().getSelectedItems();
        tablaPersonaSel.addListener((ListChangeListener<Empleado>) c -> table.seleccionarTabla(this));

        selectCapacitacion();
        table.getListTable().addAll(empleadoList);
    }

    private void selectCapacitacion() {
        empleadoList = empleadoDAO.selectAll();
    }

    @Override
    public void setStatusControls() {

    }

    public void onCesta(ActionEvent event) {

    }

    public void onSalarioIntegral(ActionEvent event) {

    }

    public void onSalarioQuincenal(ActionEvent event) {

    }

    public void onUtilidades(ActionEvent event) {

    }

    public void onVales(ActionEvent event) {

    }

    public void onLiquidaciones(ActionEvent event) {

    }

    public void onCerrar(ActionEvent event) {

    }
}
