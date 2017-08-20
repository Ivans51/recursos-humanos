package core.util;

import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.AuditoriaDAO;
import core.conexion.vo.Auditoria;
import java.text.ParseException;

/**
 * Created by Ivans on 8/11/2017.
 */
public class AuditoriaUtil {

    private AuditoriaDAO auditoriaDAO;
    private Auditoria auditoria;
    private String nombreUsuario = Storage.getUsuario().getNombreUsuario();
    private int idUsuario = Storage.getUsuario().getIdUsuario();

    public AuditoriaUtil(String nombreUsuario, int idUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.idUsuario = idUsuario;
        auditoria = new Auditoria();
        auditoriaDAO = new AuditoriaDAO(MyBatisConnection.getSqlSessionFactory());
    }

    public void insertar(String accion) {
        try {
            auditoria.setAccion(accion);
            auditoria.setFecha(FechaUtil.getCurrentDate());
            //auditoria.setHora(FechaUtil.getHourMinutes());
            auditoria.setNombreUsuario(nombreUsuario);
            auditoria.setFK_idUsuario(idUsuario);
            auditoriaDAO.insert(auditoria);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
