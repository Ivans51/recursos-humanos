package core.controller.screen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import core.conexion.vo.Contratacion;
import core.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by WAMS-10 on 30/07/2017.
 */
public class ScreenContratoEmpleado extends ManagerFXML implements Initializable {

    public JFXTextField cedula, nombreApellido, cargo, salario;
    public JFXButton brbAgregar, btnCancelar;
    public AnchorPane anchorPane;
    public JFXComboBox nacionalidad;

    private ObservableList<String> listNacionalidadCombo = FXCollections.observableArrayList("V", "E");
    public static Contratacion contratacion = new Contratacion();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nacionalidad.setItems(listNacionalidadCombo);
    }

    public void clickAction(ActionEvent actionEvent) throws Myexception {
        if (actionEvent.getSource() == brbAgregar) {
            try {
                Validar.campoVacio(cedula, nombreApellido, cargo, salario);
                Validar.isNumber(cedula.getText(), salario.getText());
                Validar.isLetter(nombreApellido.getText());
                Validar.comboBoxVacio(nacionalidad);
                getContratacion();
                cambiarEscena(Route.ScreenAddEmpleados, anchorPane);
            } catch (Myexception ex) {
                new AlertUtil(Estado.ERROR, ex.getMessage());
                System.out.println(ex.getMessage());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (actionEvent.getSource() == btnCancelar)
            cambiarEscena(Route.ScreenHomeBackground, anchorPane);
    }

    private void getContratacion() throws ParseException {
        String nacionalidadText = nacionalidad.getSelectionModel().getSelectedItem().toString();
        String cedulaTxt = nacionalidadText + "-" + cedula.getText();
        contratacion.setCedula(cedulaTxt);
        contratacion.setNombre(nombreApellido.getText());
        Date currentDate = FechaUtil.getCurrentDate();
        contratacion.setFechaInicio(currentDate);
        contratacion.setFechaCulminacion(null);
        contratacion.setSalario(Double.parseDouble(null));
        contratacion.setCargo(cargo.getText());
    }
}
