package core.util;

import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.AuditoriaDAO;
import core.conexion.vo.Auditoria;
import java.text.ParseException;

/**
 * Created by Ivans on 8/11/2017.
 */
public class AuditoriaUtil {

    private AuditoriaDAO auditoriaDAO = new AuditoriaDAO(MyBatisConnection.getSqlSessionFactory());
    private Auditoria auditoria = new Auditoria();
    private String nombreUsuario = Storage.getUsuario().getNombreUsuario();
    private int idUsuario = Storage.getUsuario().getIdUsuario();

    public void insertar(String accion) {
        try {
            auditoria.setAccion(accion);
            auditoria.setFecha(FechaUtil.getCurrentDate());
            auditoria.setHora(FechaUtil.getHourMinutes());
            auditoria.setNombreUsuario(nombreUsuario);
            auditoria.setFK_idUsuario(idUsuario);
            auditoriaDAO.insert(auditoria);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void dataUser(String nombre, int id){

    }

}
