package core.controller;

import com.jfoenix.controls.JFXButton;
import core.conexion.vo.Empleado;
import core.conexion.vo.Usuario;
import core.conexion.vo.Valores;
import core.util.ManagerFXML;
import core.util.Route;
import core.util.Storage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by WAMS-10 on 17/07/2017.
 */
public class ScreenMainHome extends ManagerFXML implements Initializable {

    private String[] routes = {
            Route.ScreenContratoEmpleado,
            Route.MainNivelUsuario,
            Route.ScreenTableUser,
            Route.ScreenAddUser,
            Route.ScrenAbout
    };

    private String[] routesEmpleados = {
            Route.ScreenContratoEmpleado,
            Route.ScreenGestionEmpleado,
            Route.ScreenTableLiquidacion,
            Route.ScreenTableEmpleados,
            Route.ScreenTableCapacitacion,
            Route.ScreenTableSeleccion
    };

    private String[] routesReport = {
            Route.ScreenReportEmpleado,
            Route.ScreenReportTotalPagos,
            Route.ScreenFactura
    };
    // private Usuario usuario = Storage.getUsuario();

    public JFXButton nombreUsuario;
    public TitledPane titleAdministrar, titleReport, titleAyuda, titleValores;
    public Label adminAgregar, adminGestion, adminLiquidaciones, adminBuscarEmpleados, adminCapacitacion, adminSeleccionPersonal;
    public Label reportFiltrado, reportPagos, reportFactura;
    public MenuItem itemAddEmpleados, itemOpcionesReport, itemOpcionesCerrar,
            itemToolUsuarios, itemToolRegistroSistema, itemToolManual, itemToolEstructura, itemSalir;
    @FXML
    public AnchorPane rootPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // nombreUsuario.setText(usuario.getNombreUsuario());

        Empleado empleado = new Empleado();
        empleado.setCedula("123");
        Storage.setEmpleado(empleado);

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario("Ivans");
        usuario.setIdUsuario(23);
        Storage.setUsuario(usuario);

        Valores valores = new Valores();
        valores.setSalario(25000);
        Storage.setValores(valores);

        nombreUsuario.setText(Storage.getUsuario().getNombreUsuario());
    }

    /* Styles Panel iquierdo */
    public void onHandleEvent(MouseEvent mouseEvent) {
        titleAdministrar.getStyleClass().add("title-click");
    }

    /* MenuItem Event */
    public void onHandleMenuItem(ActionEvent event) {
        cambiarSceneMultiple(event, routes, loadRoot ->
                        cambiarEscena(loadRoot, rootPane),
                itemAddEmpleados, itemOpcionesReport, itemToolUsuarios, itemToolRegistroSistema,
                itemToolManual, itemToolEstructura);
    }

    public void onHandleMenuItemCerrar(ActionEvent event) {
        abrirStage(Route.MainNivelUsuario, "Niveles de Usuario", null, null);
    }


    public void onHandleMenuItemSalir(ActionEvent actionEvent) {
        // TODO: 8/2/2017 how to close stage in menu item
        //cerrarStage(itemSalir);
    }

    public void abrirGestionEmpleado(MouseEvent mouseEvent) {
        cambiarSceneMultiple(mouseEvent, routesEmpleados, loadRoot ->
                        cambiarEscena(loadRoot, rootPane), adminAgregar, adminGestion,
                adminLiquidaciones, adminBuscarEmpleados, adminCapacitacion, adminSeleccionPersonal
        );
    }

    public void abrirReport(MouseEvent mouseEvent) {
        cambiarSceneMultiple(mouseEvent, routesReport, loadRoot ->
                        cambiarEscena(loadRoot, rootPane),
                reportFiltrado, reportPagos, reportFactura
        );
    }

    public void abrirActualizarValores(MouseEvent mouseEvent) {
        cambiarEscena(Route.ScreenInsertValores, rootPane);
    }

    public void abrirAyuda(MouseEvent mouseEvent) {
        cambiarEscena(Route.ScrenAbout, rootPane);
    }
}
