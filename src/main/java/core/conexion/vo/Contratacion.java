package core.conexion.vo;

import java.util.Date;

/**
 * Created by WAMS-10 on 29/07/2017.
 */
public class Contratacion {

    private int idContratacion;
    private String cedula;
    private String nombre;
    private Date fechaInicio;
    private Date fechaCulminacion;
    private String cargo;
    private double salario;
    private String empleado_cedula;

    public int getIdContratacion() {
        return idContratacion;
    }

    public void setIdContratacion(int idContratacion) {
        this.idContratacion = idContratacion;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getEmpleado_cedula() {
        return empleado_cedula;
    }

    public void setEmpleado_cedula(String empleado_cedula) {
        this.empleado_cedula = empleado_cedula;
    }

    @Override
    public String toString() {
        return "Contratacion{" +
                "idContratacion=" + idContratacion +
                ", cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaCulminacion=" + fechaCulminacion +
                ", cargo='" + cargo + '\'' +
                ", salario=" + salario +
                ", Fk_cedula_empleado='" + empleado_cedula + '\'' +
                '}';
    }
}
