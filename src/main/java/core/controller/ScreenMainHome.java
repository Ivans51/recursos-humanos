package core.controller;

import com.jfoenix.controls.JFXButton;
import core.conexion.vo.Empleado;
import core.conexion.vo.Usuario;
import core.conexion.vo.Valores;
import core.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by WAMS-10 on 17/07/2017.
 * Falta validar status user
 */
public class ScreenMainHome extends ManagerFXML implements Initializable {

    // Menu Item
    private String[] routes = {
            Route.ScreenContratoEmpleado,
            Route.MainNivelUsuario,
            Route.ScreenTableUser,
            Route.ScreenAddUser,
            Route.ScrenAbout
    };

    // Title Pane
    private String[] routesEmpleados = {
            Route.ScreenContratoEmpleado,
            Route.ScreenTableEmpleados,
            Route.ScreenTableCapacitacion,
            Route.ScreenTableSeleccion
    };

    // Title Pane
    private String[] routesReport = {
            Route.ScreenReportEmpleado,
            Route.ScreenReportTotalPagos
    };

    public JFXButton nombreUsuario;
    public TitledPane titleAdministrar, titleReport, titleAyuda;
    public Label adminAgregar, adminBuscarEmpleados, adminCapacitacion, adminSeleccionPersonal;
    public Label reportFiltrado, reportPagos;
    public MenuItem itemAddEmpleados, itemOpcionesBackup, itemOpcionesCerrar,
            itemToolUsuarios, itemToolRegistroSistema, itemToolManual, itemToolEstructura, itemSalir;
    @FXML
    public AnchorPane rootPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Empleado empleado = new Empleado();
            empleado.setCedula("123");
            Storage.setEmpleado(empleado);

            Usuario usuario = new Usuario();
            usuario.setNombreUsuario("Ivans");
            usuario.setIdUsuario(27);
            Storage.setUsuario(usuario);

            Valores valores = new Valores();
            valores.setSalario(25000);
            Storage.setValores(valores);

            validarStateUser();
            nombreUsuario.setText(Storage.getUsuario().getNombreUsuario());

        } catch (Myexception myexception) {
            myexception.printStackTrace();
        }
    }

    private void validarStateUser() throws Myexception {
        switch (Storage.getUsuario().getStatus()){
            case Estado.ADMINISTRADOR:
                break;
            case Estado.SECRETARIA:
                Validar.disabledControl(Estado.VISIBLE, true, itemToolRegistroSistema);
                FuncionesUtil.removePositionArray(routes, 4);
                break;
            case Estado.INVITADO:
                Validar.disabledControl(Estado.VISIBLE, true, itemToolRegistroSistema);
                FuncionesUtil.removePositionArray(routes, 4);
                break;
            case Estado.EMPLEADO:
                Validar.disabledControl(Estado.VISIBLE, true, itemToolRegistroSistema);
                FuncionesUtil.removePositionArray(routes, 4);
                break;
        }
    }

    /* Styles Panel iquierdo */
    public void onHandleEvent(MouseEvent mouseEvent) {
        titleAdministrar.getStyleClass().add("title-click");
    }

    /* MenuItem Event */
    public void onHandleMenuItem(ActionEvent event) {
        cambiarSceneMultiple(event, routes, loadRoot ->
                        cambiarEscena(loadRoot, rootPane),
                itemAddEmpleados, itemOpcionesCerrar, itemToolUsuarios, itemToolRegistroSistema,
                itemToolManual, itemToolEstructura);
    }

    public void onHandleMenuItemCerrar(ActionEvent event) {
        abrirStage(Route.MainNivelUsuario, "Niveles de Usuario", null, null);
    }


    public void onHandleMenuItemSalir(ActionEvent actionEvent) {
        Node  source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void abrirGestionEmpleado(MouseEvent mouseEvent) {
        cambiarSceneMultiple(mouseEvent, routesEmpleados, loadRoot ->
                        cambiarEscena(loadRoot, rootPane), adminAgregar,
                adminBuscarEmpleados, adminCapacitacion, adminSeleccionPersonal
        );
    }

    public void abrirReport(MouseEvent mouseEvent) {
        cambiarSceneMultiple(mouseEvent, routesReport, loadRoot ->
                        cambiarEscena(loadRoot, rootPane),
                reportFiltrado, reportPagos
        );
    }

    public void abrirAyuda(MouseEvent mouseEvent) {
        cambiarEscena(Route.ScrenAbout, rootPane);
    }

    public void onBackup(ActionEvent event) throws URISyntaxException {
        BackupBaseDato backupBaseDato = new BackupBaseDato("rrhh", "root", "", ScreenMainHome.class);
        backupBaseDato.backupdbtosql();
        // backupBaseDato.restoredbfromsql("rrhh");
    }
}
