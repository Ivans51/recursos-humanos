package core.controller.screen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.ContratacionDAO;
import core.conexion.dao.ValoresDAO;
import core.conexion.vo.Contratacion;
import core.conexion.vo.Valores;
import core.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.text.ParseException;
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
    public JFXComboBox cbCargo;
    public Contratacion contratacion;
    ObservableList<String> listUserCombo;
    private TableUtil tableUtil;
    private String[] tableS = {"salario", "precioUnidadTributaria", "paroForzoso", "FAO", "IVSS", "diasUtilidades"};
    private List<Valores> valoresList;
    private Valores valores;
    private ValoresDAO valoresDAO = new ValoresDAO(MyBatisConnection.getSqlSessionFactory());
    private ContratacionDAO contratacionDAO = new ContratacionDAO(MyBatisConnection.getSqlSessionFactory());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Validar.disabledControl(Estado.DISABLE, true, btnActualizar, btnInsertar);
            ClassUtil.addClass("button-disabled", btnInsertar, btnActualizar);
            valoresList = valoresDAO.selectAll();
            setComboBox();
            tableUtil = new TableUtil(Valores.class, tablaValores);
            tableUtil.inicializarTabla(tableS, tbSalario, tbPrecioUnidad, tbParoForzoso, tbIVSS, tbFAOV, tbDiasUtilidades);

            // Seleccionar las tuplas de la tabla de las listTable
            final ObservableList<Valores> tablaPersonaSel = tablaValores.getSelectionModel().getSelectedItems();
            tablaPersonaSel.addListener((ListChangeListener<Valores>) c -> tableUtil.seleccionarTabla(this));

            tableUtil.getListTable().addAll(valoresList);
        } catch (Myexception myexception) {
            myexception.printStackTrace();
        }
    }

    private void setComboBox() {
        List<Contratacion> contratacions = contratacionDAO.selectAllCargo();
        String[] cargos = new String[contratacions.size()];
        for (int i = 0; i < contratacions.size(); i++)
            cargos[i] = contratacions.get(i).getCargo();
        listUserCombo = FXCollections.observableArrayList(cargos);
        cbCargo.setItems(listUserCombo);
        cbCargo.valueProperty().addListener((observable, oldValue, newValue) -> {
            contratacion = contratacionDAO.selectByCargo(newValue.toString());
            txtSalario.setText(String.valueOf(contratacion.getSalario()));
            btnInsertar.setDisable(false);
            ClassUtil.removeClass("button-disabled", btnInsertar);
            ClassUtil.addClass("button", btnInsertar);
        });
    }

    public void onClickInsert(ActionEvent event) throws Myexception {
        try {
            getDataValores();
            int id = setQuery();
            Valores valores = valoresDAO.selectById(id);
            tableUtil.getListTable().add(valores);
            tablaValores.refresh();
            Validar.limmpiarCampos(txtSalario, txtPrecioUnidad, txtParoForzoso, txtIVSS, txtFAOV, txtDiasUtilidades);
        } catch (Myexception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private int setQuery() {
        int id = valoresDAO.insert(valores);
        Contratacion contratacion = new Contratacion();
        contratacion.setCargo(contratacion.getCargo());
        contratacion.setSalarioActual(Double.parseDouble(txtSalario.getText()));
        contratacionDAO.update(contratacion);
        return id;
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
            valores.setFecha(FechaUtil.getCurrentDate());
            valores.setDiasUtilidades(Integer.parseInt(txtDiasUtilidades.getText()));
            valores.setFk_id_usuario(Storage.getUsuario().getIdUsuario());

            contratacion = contratacionDAO.selectByCargo(cbCargo.getSelectionModel().getSelectedItem().toString());
            valores.setContratacion_idContratacion(contratacion.getIdContratacion());

        } catch (Myexception myexception) {
            myexception.printStackTrace();
            new AlertUtil(Estado.ERROR, myexception.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setStatusControls() {
        if (tableUtil.getModel() != null) {
            cbCargo.setDisable(true);
            btnInsertar.setDisable(true);;
            ClassUtil.removeClass("button", btnInsertar);
            ClassUtil.addClass("button-disabled", btnInsertar);
            btnActualizar.setDisable(false);
            btnActualizar.getStyleClass().remove("button-disabled");
            btnActualizar.getStyleClass().add("button");
            valores = (Valores) tableUtil.getModel();
            Contratacion contratacion = contratacionDAO.selectById(valores.getContratacion_idContratacion());
            cbCargo.setValue(contratacion.getCargo());
            String paro = ControlUtil.removeDecimal(valores.getParoForzoso());
            String ivss = ControlUtil.removeDecimal(valores.getIVSS());
            String fao = ControlUtil.removeDecimal(valores.getFAO());
            txtSalario.setText(String.valueOf(valores.getSalario()));
            txtPrecioUnidad.setText(String.valueOf(valores.getPrecioUnidadTributaria()));
            txtParoForzoso.setText(paro);
            txtIVSS.setText(ivss);
            txtFAOV.setText(fao);
            txtDiasUtilidades.setText(String.valueOf(valores.getDiasUtilidades()));
        }
    }

    public void onClickUpdate(ActionEvent event){
        try{
            getDataValores();
            valoresDAO.update(valores);
            cbCargo.setDisable(false);
            btnInsertar.setDisable(false);
            Validar.limmpiarCampos(txtSalario, txtPrecioUnidad, txtParoForzoso, txtIVSS, txtFAOV, txtDiasUtilidades);
            valoresList = valoresDAO.selectAll();
            tableUtil.getListTable().addAll(valoresList);
            tablaValores.refresh();
        } catch (Myexception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void onClickCancelar(ActionEvent event) {
        cambiarEscena(Route.ScreenHomeBackground, anchorPane);
    }
}
