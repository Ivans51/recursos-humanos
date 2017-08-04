package core.controller.screen;

import com.jfoenix.controls.JFXButton;
import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.EmpleadoDAO;
import core.conexion.vo.Contratacion;
import core.conexion.vo.Empleado;
import core.conexion.vo.Usuario;
import core.util.ManagerFXML;
import core.util.Myexception;
import core.util.Route;
import core.util.TableUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Ivans on 7/18/2017.
 */
public class ScreenTableEmpleados extends ManagerFXML implements Initializable, TableUtil.StatusControles {

    public AnchorPane anchorPane;
    public TextField txtBuscarCedula;
    public JFXButton btnCerrar, btnBuscar;
    public TableView tableEmpleado;
    public TableColumn tbCedula, tbNombre, tbDireccion, tbFechaNac, tbCargos, tbStatus, tbSueldo, tbIngreso, tbCulminuacion;
    private EmpleadoDAO empleadoDAO = new EmpleadoDAO(MyBatisConnection.getSqlSessionFactory());
    private String[] columS = {
            "cedula", "nombreEmpleado", "direccion", "fechaNacimiento", "cargo",
            "status", "contratacion"};

    private List<Empleado> empleados;
    private TableUtil<Empleado, String, Empleado, Contratacion> table;
    public static Empleado empleado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Iniciailizar tabla
        table = new TableUtil(Empleado.class, tableEmpleado, Empleado.class, Contratacion.class);

        table.inicializarTablaMultiple(columS, 6, (s, i, startSecondModel, columns) -> {
            columns[i].setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Empleado, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Empleado, String> param) {
                    String cargo = param.getValue().getContratacion().getCargo();
                    return new SimpleStringProperty(cargo);
                }
            });
        }, tbCedula, tbNombre, tbDireccion, tbFechaNac, tbCargos, tbStatus, tbSueldo, tbIngreso, tbCulminuacion);

        final ObservableList<Usuario> tablaSelecionada = tableEmpleado.getSelectionModel().getSelectedItems();
        tablaSelecionada.addListener((ListChangeListener<Usuario>) c -> table.seleccionarTabla(this));
        // Llenar Tabla
        selectAllEmpleadoContrato();
        table.getListTable().addAll(empleados);
        doubleClickRow();
    }

    private void selectAllEmpleadoContrato() {
        empleados = empleadoDAO.selectAllEmpleadoContrato();
    }

    public void onBuscar(ActionEvent event) throws Myexception {
        empleado = new Empleado();
        empleado = empleadoDAO.selectByEmpleadoContrato(txtBuscarCedula.getText());
        table.getListTable().add(empleado);
        tableEmpleado.refresh();
    }

    @Override
    public void setStatusControls() {

    }

    private void doubleClickRow() {
        tableEmpleado.setRowFactory(tv -> {
            TableRow<Empleado> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Empleado rowData = row.getItem();
                    cambiarEscena(Route.ScreenTableLiquidacion, anchorPane);
                    System.out.println(rowData);
                }
            });
            return row;
        });
    }

    public void onCerrar(ActionEvent event) {
        cambiarEscena(Route.ScreenHomeBackground, anchorPane);
    }
}
