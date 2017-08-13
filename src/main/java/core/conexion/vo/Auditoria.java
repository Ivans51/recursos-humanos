package core.conexion.vo;

import java.util.Date;

/**
 * Created by WAMS-10 on 29/07/2017.
 */
public class Auditoria {

    private int idAuditoria;
    private Date fecha;
    private int hora;
    private String accion;
    private String nombreUsuario;
    private int FK_idUsuario;

    public int getIdAuditoria() {
        return idAuditoria;
    }

    public void setIdAuditoria(int idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public int getFK_idUsuario() {
        return FK_idUsuario;
    }

    public void setFK_idUsuario(int FK_idUsuario) {
        this.FK_idUsuario = FK_idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public String toString() {
        return "Auditoria{" +
                "idAuditoria=" + idAuditoria +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", accion='" + accion + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", FK_idUsuario=" + FK_idUsuario +
                '}';
    }
}
