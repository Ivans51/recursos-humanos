package core.controller.screen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.ValoresDAO;
import core.conexion.vo.Valores;
import core.util.*;
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
public class ScreenInsertValores extends ManagerFXML implements Initializable, TableUtil.StatusControles {


    public AnchorPane anchorPane;
    public JFXTextField txtSalario, txtPrecioUnidad, txtParoForzoso, txtIVSS, txtFAOV, txtDiasUtilidades;
    public TableColumn tbSalario, tbPrecioUnidad, tbParoForzoso, tbIVSS, tbFAOV, tbDiasUtilidades;
    public TableView<Valores> tablaValores;
    public JFXButton btnCancelar, btnActualizar, btnInsertar;
    private TableUtil tableUtil;
    private String[] tableS = {"salario", "precioUnidadTributaria", "paroForzoso", "FAO", "IVSS", "diasUtilidades"};
    private List<Valores> valoresList;
    private Valores valores;
    private ValoresDAO valoresDAO = new ValoresDAO(MyBatisConnection.getSqlSessionFactory());


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnActualizar.setDisable(true);
        btnActualizar.getStyleClass().remove("button");
        btnActualizar.getStyleClass().add("button-disabled");
        valoresList = valoresDAO.selectAll();
        tableUtil = new TableUtil(Valores.class, tablaValores);
        tableUtil.inicializarTabla(tableS, tbSalario, tbPrecioUnidad, tbParoForzoso, tbIVSS, tbFAOV, tbDiasUtilidades);

        // Seleccionar las tuplas de la tabla de las listTable
        final ObservableList<Valores> tablaPersonaSel = tablaValores.getSelectionModel().getSelectedItems();
        tablaPersonaSel.addListener((ListChangeListener<Valores>) c -> tableUtil.seleccionarTabla(this));

        tableUtil.getListTable().addAll(valoresList);
    }

    public void onClickInsert(ActionEvent event) throws Myexception {
        getDataValores();
        int id = valoresDAO.insert(valores);
        Validar.limmpiarCampos(txtSalario, txtPrecioUnidad, txtParoForzoso, txtIVSS, txtFAOV, txtDiasUtilidades);
        Valores valores = valoresDAO.selectById(id);
        tableUtil.getListTable().add(valores);
        tablaValores.refresh();
    }

    private void getDataValores() {
        try {
            Validar.campoVacio(txtSalario, txtPrecioUnidad, txtParoForzoso, txtIVSS, txtFAOV, txtDiasUtilidades);
            Validar.isNumber(txtSalario.getText(), txtPrecioUnidad.getText(), txtParoForzoso.getText(),
                    txtIVSS.getText(), txtFAOV.getText(), txtDiasUtilidades.getText());
            valores = new Valores();
            valores.setSalario(Double.parseDouble(txtSalario.getText()));
            valores.setPrecioUnidadTributaria(Double.parseDouble(txtPrecioUnidad.getText()));
            valores.setParoForzoso(Double.parseDouble(txtParoForzoso.getText()));
            valores.setIVSS(Double.parseDouble(txtIVSS.getText()));
            valores.setFAO(Double.parseDouble(txtFAOV.getText()));
            valores.setDiasUtilidades(Integer.parseInt(txtDiasUtilidades.getText()));
        } catch (Myexception myexception) {
            myexception.printStackTrace();
            new AlertUtil(Estado.ERROR, myexception.getMessage());
        }
    }

    @Override
    public void setStatusControls() {
        if (tableUtil.getModel() != null){
            btnActualizar.setDisable(false);
            btnActualizar.getStyleClass().remove("button-disabled");
            btnActualizar.getStyleClass().add("button");
            valores = (Valores) tableUtil.getModel();
            txtSalario.setText(String.valueOf(valores.getSalario()));
            txtPrecioUnidad.setText(String.valueOf(valores.getPrecioUnidadTributaria()));
            txtParoForzoso.setText(String.valueOf(valores.getParoForzoso()));
            txtIVSS.setText(String.valueOf(valores.getIVSS()));
            txtFAOV.setText(String.valueOf(valores.getFAO()));
            txtDiasUtilidades.setText(String.valueOf(valores.getDiasUtilidades()));
            btnInsertar.setDisable(true);
        }
    }

    public void onClickUpdate(ActionEvent event) throws Myexception {
        getDataValores();
        valoresDAO.update(valores);
        Validar.limmpiarCampos(txtSalario, txtPrecioUnidad, txtParoForzoso, txtIVSS, txtFAOV, txtDiasUtilidades);
        valoresList = valoresDAO.selectAll();
        tableUtil.getListTable().addAll(valoresList);
        tablaValores.refresh();
    }

    public void onClickCancelar(ActionEvent event) {
        cambiarEscena(Route.ScreenHomeBackground, anchorPane);
    }
}
