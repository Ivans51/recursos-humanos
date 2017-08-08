package core.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by Ivans on 8/3/2017.
 */
public class TableUtil<T, V> {

    public TableColumn[] tableColumns;
    private T model;
    private ObservableList<T> listTable;
    private TableView<T> tablaUsuarios;
    private int posicionPersonaEnTabla;

    public TableUtil(T model, TableView<T> tablaUsuarios) {
        this.model = model;
        this.tablaUsuarios = tablaUsuarios;
    }

    /**
     * Método para inicializar la tabla
     *
     * @param s
     * @param columns
     */
    public void inicializarTabla(String[] s, TableColumn... columns) {
        for (int i = 0; i < columns.length; i++) {
            columns[i].setCellValueFactory(new PropertyValueFactory<T, V>(s[i]));
        }
        listTable = FXCollections.observableArrayList();
        this.tablaUsuarios.setItems(listTable);
    }

    /**
     * Método para poner en los textFields la tupla que seleccionemos
     */
    public void seleccionarTabla(@Nullable StatusControles statusControles) {
        model = getTablaSeleccionada(tablaUsuarios);
        posicionPersonaEnTabla = listTable.indexOf(model);
        if (statusControles != null)
            statusControles.setStatusControls();
    }

    /**
     * PARA SELECCIONAR UNA CELDA DE LA TABLA
     *
     * @param tablaUsuarios
     */
    public T getTablaSeleccionada(TableView tablaUsuarios) {
        if (tablaUsuarios != null) {
            List<T> tabla = tablaUsuarios.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final T tablaSeleccionada = tabla.get(0);
                return tablaSeleccionada;
            }
        }
        return null;
    }

    public TableColumn[] getTableColumns() {
        return tableColumns;
    }

    public T getModel() {
        return model;
    }

    public int getPosicionPersonaEnTabla() {
        return posicionPersonaEnTabla;
    }

    public ObservableList<T> getListTable() {
        return listTable;
    }

    public interface StatusControles {
        void setStatusControls();
    }

    public interface TableForeignKeig {
        void llenarRelacionTabla(String[] s, int i, int ststartSecondModel, TableColumn... columns);
    }

}
