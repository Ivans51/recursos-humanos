package core.util;

import core.conexion.connection.MyBatisConnection;
import core.conexion.dao.AuditoriaDAO;
import core.conexion.vo.Auditoria;
import java.text.ParseException;

/**
 * Created by Ivans on 8/11/2017.
 */
public class AuditoriaUtil {

    AuditoriaDAO auditoriaDAO = new AuditoriaDAO(MyBatisConnection.getSqlSessionFactory());
    Auditoria auditoria = new Auditoria();

    private void insertar(String accion) throws ParseException {
        auditoria.setAccion(accion);
        auditoria.setFecha(FechaUtil.getCurrentDate());
        auditoria.setHora(FechaUtil.getHourMinutes());
        auditoria.setNombreUsuario(Storage.getUsuario().getNombreUsuario());
        auditoria.setFK_idUsuario(Storage.getUsuario().getIdUsuario());
        auditoriaDAO.insert(auditoria);
    }

}
