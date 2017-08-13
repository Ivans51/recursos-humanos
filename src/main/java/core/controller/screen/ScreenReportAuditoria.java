package core.controller.screen;

import com.itextpdf.text.DocumentException;
import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.AuditoriaDAO;
import core.conexion.vo.Auditoria;
import core.conexion.vo.Capacitacion;
import core.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Ivans on 7/31/2017.
 */
public class ScreenReportAuditoria extends ManagerFXML implements Initializable, PDFCreator.PDFTabla{


    public AnchorPane anchor;
    public TableColumn tbIdAuditoria, tbFecha, tbHora, tbIdUsuario, tbNombreUsuario, tbAccion;
    public TableView<Auditoria> tableAuditoria;

    public TableUtil table;
    private String[] tableS = {"idAuditoria", "fecha", "hora", "accion", "nombreUsuario", "FK_idUsuario"};
    private List<Auditoria> auditoriaList;
    private AuditoriaDAO auditoriaDAO = new AuditoriaDAO(MyBatisConnection.getSqlSessionFactory());
    public PDFCreator pdfCreator;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Iniciailizar tabla
        table = new TableUtil(Capacitacion.class, tableAuditoria);
        table.inicializarTabla(tableS, tbIdAuditoria, tbFecha, tbHora, tbIdUsuario, tbNombreUsuario, tbAccion);

        selectAuditoria();
        table.getListTable().addAll(auditoriaList);
    }

    private void selectAuditoria() {
        auditoriaList = auditoriaDAO.selectAll();
    }

    public void onImprimir(ActionEvent event) throws FileNotFoundException, DocumentException, ParseException {
        pdfCreator = new PDFCreator();
        String auditoria = "auditoria" + FechaUtil.getCurrentDate();
        pdfCreator.crearPDF(auditoria, "Listado de los movimientos", 6, this);
    }

    @Override
    public void addCellTable() {
        for (Auditoria auditoria : auditoriaList) {
            pdfCreator.getTabla().addCell(String.valueOf(auditoria.getIdAuditoria()));
            pdfCreator.getTabla().addCell(String.valueOf(auditoria.getFecha()));
            pdfCreator.getTabla().addCell(String.valueOf(auditoria.getHora()));
            pdfCreator.getTabla().addCell(String.valueOf(auditoria.getFK_idUsuario()));
            pdfCreator.getTabla().addCell(auditoria.getNombreUsuario());
            pdfCreator.getTabla().addCell(auditoria.getAccion());
        }
    }

    public void onCerrrar(ActionEvent event) {
        cambiarEscena(Route.ScreenHomeBackground, anchor);
    }
}
