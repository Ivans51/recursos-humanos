package core.controller.screen;

import com.itextpdf.text.DocumentException;
import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.UsuarioDAO;
import core.conexion.vo.Usuario;
import core.util.ManagerFXML;
import core.util.PDFCreator;
import core.util.Route;
import core.util.TableUtil;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Ivans on 7/18/2017.
 */
public class ScreenTableUser extends ManagerFXML implements Initializable, PDFCreator.PDFTabla {

    public AnchorPane anchor;
    public TableColumn tbIdUsuario, tbNombreEmpleado, tbCorreo, tbStatus, tbFechaCreacion, tbCedula;
    public TableView<Usuario> tableUsuario;

    public PDFCreator pdfcreator;
    TableUtil tableUtil;
    private List<Usuario> usuarios;
    private String[] tableS = {"idUsuario", "nombreUsuario", "correo", "status", "fechaCreacion", "cedula"};
    private UsuarioDAO usuarioDAO = new UsuarioDAO(MyBatisConnection.getSqlSessionFactory());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableUtil = new TableUtil(Usuario.class, tableUsuario);
        tableUtil.inicializarTabla(tableS, tbIdUsuario, tbNombreEmpleado, tbCorreo, tbStatus, tbFechaCreacion, tbCedula);
        selectUsuario();
        tableUtil.getListTable().addAll(usuarios);
    }

    private void selectUsuario() {
        usuarios = usuarioDAO.selectAll();
    }

    public void onImprimir(ActionEvent event) throws FileNotFoundException, DocumentException {
        pdfcreator = new PDFCreator();
        pdfcreator.crearPDF("fichero.pdf", "Esto es el primer párrafo", 6, this);
    }

    @Override
    public void addCellTable() {
        for (int i = 0; i < usuarios.size(); i++) {
            pdfcreator.getTabla().addCell(String.valueOf(usuarios.get(i).getIdUsuario()));
            pdfcreator.getTabla().addCell(usuarios.get(i).getNombreUsuario());
            pdfcreator.getTabla().addCell(usuarios.get(i).getCorreo());
            pdfcreator.getTabla().addCell(String.valueOf(usuarios.get(i).getStatus()));
            pdfcreator.getTabla().addCell(String.valueOf(usuarios.get(i).getFechaCreacion()));
            pdfcreator.getTabla().addCell(usuarios.get(i).getCedula());
        }
    }

    public void onCerrar(ActionEvent event) {
        cambiarEscena(Route.ScreenHomeBackground, anchor);
    }
}
