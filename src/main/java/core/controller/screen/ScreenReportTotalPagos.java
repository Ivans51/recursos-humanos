package core.controller.screen;

import com.jfoenix.controls.JFXButton;
import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.EmpleadoDAO;
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
public class ScreenReportTotalPagos extends ManagerFXML implements Initializable, TableUtil.StatusControles{
    public AnchorPane anchor;
    public JFXButton btnImprimir;
    public TableColumn tbCedula, tbNombre, tbCestaTicke, tbSalarioIntegral,tbQuincena, tbUtitilidades, tbVales, tbLiquidacion;
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
        table.inicializarTabla(tableS, tbCedula, tbNombre, tbCestaTicke, tbSalarioIntegral,tbQuincena, tbUtitilidades, tbVales, tbLiquidacion);

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

    public void onImprimir(ActionEvent event) {

    }
}
