package core.controller.session;

import com.jfoenix.controls.JFXButton;
import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.EmpleadoDAO;
import core.conexion.vo.Empleado;
import core.util.FechaUtil;
import core.util.ManagerFXML;
import core.util.Route;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ivans on 7/31/2017.
 */
public class SessionEmpleadoGestion extends ManagerFXML implements Initializable {

    public JFXButton estructuraOrga, hideVentana, terminarJornada;
    public Label labelRegistroEmpleado;

    EmpleadoDAO empleadoDAO = new EmpleadoDAO(MyBatisConnection.getSqlSessionFactory());
    private int idUsuario;
    private Empleado empleado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void initData(Empleado empleado) {
        this.empleado = empleado;
        String text = empleado.getNombreEmpleado() + " registrado el " + empleado.getHoraInicio();
        labelRegistroEmpleado.setText(text);
        terminarJornada.setVisible(empleado.isStatuSession());
    }

    public void onTerminar(MouseEvent mouseEvent) {
        statusEmpleado();
        abrirStage(Route.MainNivelUsuario, "Niveles de usuario", terminarJornada, null);
    }

    private void statusEmpleado() {
        idUsuario = empleado.getFK_id_Usuario();
        FechaUtil.getDifference(empleado.getHoraInicio());
        int hours = (int) FechaUtil.getDiffHours();
        String dateFormatTime = FechaUtil.getDateFormatTime();

        empleado = new Empleado();
        empleado.setHoraInicio(dateFormatTime);
        empleado.setStatusLaborando(0);
        empleado.setHorasTrabajadas(hours);
        empleado.setFK_id_Usuario(idUsuario);
        empleadoDAO.updateStatusTrabajo(empleado);
    }

    public void onEstructura(MouseEvent mouseEvent) {
        abrirStage(Route.ScreenShowEstructura, "Estructura Organizativa", null, null);
    }

    public void onClose(MouseEvent mouseEvent) {
        abrirStage(Route.MainNivelUsuario, "Niveles de usuario", hideVentana, null);
    }
}
