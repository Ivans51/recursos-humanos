package core.controller.screen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import core.conexion.vo.Contratacion;
import core.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by WAMS-10 on 30/07/2017.
 */
public class ScreenContratoEmpleado extends ManagerFXML implements Initializable {

    public JFXTextField cedula, nombreApellido, cargo, salario;
    public JFXDatePicker fechaInicio;
    public JFXButton brbAgregar, btnCancelar;
    public AnchorPane anchorPane;
    public static Contratacion contratacion = new Contratacion();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void clickAction(ActionEvent actionEvent) throws Myexception {
        if (actionEvent.getSource() == brbAgregar) {
            try {
                Validar.campoVacio(cedula, nombreApellido, cargo, salario);
                getContratacion();
                cambiarEscena(Route.ScreenAddEmpleados, anchorPane);
            } catch (Myexception ex) {
                System.out.println(ex.getMessage());
            }
        } else if (actionEvent.getSource() == btnCancelar)
            cambiarEscena(Route.ScreenHomeBackground, anchorPane);
    }

    private void getContratacion() {
        contratacion.setCedula(cedula.getText());
        contratacion.setNombre(nombreApellido.getText());
        contratacion.setFechaInicio(MananejadorDateUtil.getDatePickentCurrent(fechaInicio));
        contratacion.setFechaCulminacion(null);
        contratacion.setSalario(Double.parseDouble(Storage.getValores().getSalario()));
        contratacion.setCargo(cargo.getText());
    }
}
