package core.controller.screen;

import com.itextpdf.text.*;
import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.AuditoriaDAO;
import core.conexion.vo.Auditoria;
import core.conexion.vo.Usuario;
import core.util.Myexception;
import core.util.PDFCreator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Ivans on 7/18/2017.
 */
public class ScreenTableAuditoria implements Initializable, PDFCreator.PDFTabla {

    private PDFCreator pdfcreator = new PDFCreator();
    // TODO: 30/07/2017 Ejemplo de tabla
    // TODO: 30/07/2017 Hacer Relacion
    public TableColumn tbIdAuditoria, tbFecha, tbHora, tbIdUsuario, tbNombreUsuario, tbCedula;
    public TableView tableAuditoria;
    public Label nombre;
    private ObservableList<Auditoria> list;
    private List<Auditoria> auditorias;
    // private Auditoria auditoria;
    private AuditoriaDAO auditoriaDAO = new AuditoriaDAO(MyBatisConnection.getSqlSessionFactory());
    public String DEST = "simple_table.pdf";

    /**
     * Método para inicializar la tabla
     */
    private void inicializarTablaUsuarios() {

        tbIdAuditoria.setCellValueFactory(new PropertyValueFactory<Usuario, String>("idAuditoria"));
        tbFecha.setCellValueFactory(new PropertyValueFactory<Usuario, String>("fecha"));
        tbHora.setCellValueFactory(new PropertyValueFactory<Usuario, String>("hora"));
        tbIdUsuario.setCellValueFactory(new PropertyValueFactory<Usuario, String>("accion"));
        //tbNombreUsuario.setCellValueFactory(new PropertyValueFactory<Usuario, String>(""));
        tbCedula.setCellValueFactory(new PropertyValueFactory<Usuario, String>("FK_idUsuario"));
        list = FXCollections.observableArrayList();
        tableAuditoria.setItems(this.list);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inicializarTablaUsuarios();
        try {
            selectUsuario();
            list.addAll(auditorias);
        } catch (Myexception ex) {
            System.out.println(ex.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


    /**
     * Llenar la tabla
     *
     * @throws Myexception
     */
    private void selectUsuario() throws Myexception, FileNotFoundException, DocumentException {
        auditorias = auditoriaDAO.selectAll();
    }

    public void click(MouseEvent mouseEvent) throws FileNotFoundException, DocumentException {
        pdfcreator.crearPDF("fichero.pdf", "Esto es el primer párrafo, normalito", 3, this);
    }

    @Override
    public void addCellTable() {
        for (int i = 0; i < auditorias.size(); i++) {
            PDFCreator.getTabla().addCell(auditorias.get(i).getAccion());
            PDFCreator.getTabla().addCell(String.valueOf(auditorias.get(i).getFecha()));
            PDFCreator.getTabla().addCell(String.valueOf(auditorias.get(i).getHora()));
        }
    }
}
