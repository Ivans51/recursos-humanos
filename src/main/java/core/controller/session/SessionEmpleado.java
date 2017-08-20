package core.controller.session;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.EmpleadoDAO;
import core.conexion.vo.Empleado;
import core.conexion.vo.Usuario;
import core.consultas.LoginUser;
import core.util.*;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by WAMS-10 on 29/07/2017.
 * Ready
 */
public class SessionEmpleado extends ManagerFXML implements Initializable {

    public JFXButton acceder, cancelarEmpleado;
    public JFXTextField nombre;
    public JFXPasswordField clave;
    public Empleado empleado = new Empleado();
    private EmpleadoDAO empleadoDAO = new EmpleadoDAO(MyBatisConnection.getSqlSessionFactory());
    private LoginUser loginUser = new LoginUser();
    private Usuario usuario = new Usuario();
    private int idUsuario;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onClickAcceder(MouseEvent mouseEvent) {
        try {
            Validar.campoVacio(nombre, clave);
            usuario = loginUser.iniciarSession(nombre.getText(), clave.getText());
            seleccionandoEmpleado();
        } catch (Myexception myexception) {
            myexception.printStackTrace();
            new AlertUtil(Estado.ERROR, myexception.getMessage());
        }
    }

    private void seleccionandoEmpleado() {
        try {
            selectStatusEmpleado();
            statusEmpleado();
        } catch (Myexception myexception) {
            myexception.printStackTrace();
            empleado.setStatuSession(true);
        } finally {
            abrirStage(Route.SessionEmpleadoGestion, "Gestion de la cuenta empleado", acceder, () -> {
                SessionEmpleadoGestion display = getFxmlLoader().getController();
                display.initData(empleado);
            });
        }
    }

    private void selectStatusEmpleado() throws Myexception {
        empleado = empleadoDAO.selectByIdUser(usuario.getIdUsuario());
        // Terminar la jornada
        if (empleado.getStatusLaborando() == 1)
            throw new Myexception("No update");
    }

    // Empezo la jornada
    private void statusEmpleado() {
        idUsuario = empleado.getFK_id_Usuario();
        int horasTrabajadas = empleado.getHorasTrabajadas();
        empleado = new Empleado();
        String dateFormatTime = FechaUtil.getDateFormatTime();
        empleado.setHoraInicio(dateFormatTime);
        empleado.setStatusLaborando(1);
        empleado.setHorasTrabajadas(horasTrabajadas);
        empleado.setFK_id_Usuario(idUsuario);
        empleado.setStatuSession(false);
        empleadoDAO.updateStatusTrabajo(empleado);
    }

    public void onClose(MouseEvent mouseEvent) {
        abrirStage(Route.MainNivelUsuario, "Niveles del usuario", cancelarEmpleado, null);
    }
}
