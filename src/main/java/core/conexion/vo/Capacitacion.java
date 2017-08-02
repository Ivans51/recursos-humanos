package core.conexion.vo;

import java.util.Date;

/**
 * Created by WAMS-10 on 29/07/2017.
 */
public class Capacitacion {

    private int idCapacitacion;
    private String instructor;
    private String tipo;
    private String cedulaEmpleado;
    private String nombreEmpleado;
    private Date fechaInicio;
    private Date fechaCulminacion;
    private String duracion;
    private String FK_cedula_empleado;

    public int getIdCapacitacion() {
        return idCapacitacion;
    }

    public void setIdCapacitacion(int idCapacitacion) {
        this.idCapacitacion = idCapacitacion;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCedulaEmpleado() {
        return cedulaEmpleado;
    }

    public void setCedulaEmpleado(String cedulaEmpleado) {
        this.cedulaEmpleado = cedulaEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaCulminacion() {
        return fechaCulminacion;
    }

    public void setFechaCulminacion(Date fechaCulminacion) {
        this.fechaCulminacion = fechaCulminacion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getFK_cedula_empleado() {
        return FK_cedula_empleado;
    }

    public void setFK_cedula_empleado(String FK_cedula_empleado) {
        this.FK_cedula_empleado = FK_cedula_empleado;
    }

    @Override
    public String toString() {
        return "Capacitacion{" +
                "idCapacitacion=" + idCapacitacion +
                ", instructor='" + instructor + '\'' +
                ", tipo='" + tipo + '\'' +
                ", cedulaEmpleado='" + cedulaEmpleado + '\'' +
                ", nombreEmpleado='" + nombreEmpleado + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaCulminacion=" + fechaCulminacion +
                ", duracion='" + duracion + '\'' +
                ", FK_cedula_empleado='" + FK_cedula_empleado + '\'' +
                '}';
    }
}
