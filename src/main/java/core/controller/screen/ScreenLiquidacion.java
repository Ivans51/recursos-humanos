package core.controller.screen;

import com.jfoenix.controls.JFXButton;
import core.util.ManagerFXML;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ivans on 8/6/2017.
 */
public class ScreenLiquidacion extends ManagerFXML implements Initializable{

    public JFXButton btnImprimir;
    public Label lblNombreApellido, lblCedula, lblUltimoSalario, lblCargo, lblFechaIngreso, lblFechaRetiro;
    public Label lblAntiguedad, lblVacaciones, lblBonoVacacional, lblUtilidades, lblIntereses;
    public Label lblTotalDeduciones, lblAntiguedadAlicuotas, lblVacacionesAlicuota, lblBonoVacacionalAlicuota;
    public Label lblUtilidadesAlicuota, lblAntiguedadTotal, lblVacacionesTotal, lblBonoVacacionalTotal;
    public Label lblUtilidadTotal, lblInteresesTotal, lblINCETotal, lblLiquidacionTotal;
    public JFXButton btnCerrar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onImprimir(ActionEvent event) {

    }

    public void onCerrar(ActionEvent event) {

    }
}
