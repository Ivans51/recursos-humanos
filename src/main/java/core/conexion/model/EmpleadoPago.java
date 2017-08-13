package core.conexion.model;

import java.util.Date;

/**
 * Created by Ivans on 8/12/2017.
 */
public class EmpleadoPago {
    private String cedula;
    private String nombreEmpleado;
    private String direccion;
    private Date fechaNacimiento;
    private String cargo;
    private int statusLaborando;
    private double salario;
    private Date fechaInicio;
    private double monto;

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getStatusLaborando() {
        return statusLaborando;
    }

    public void setStatusLaborando(int statusLaborando) {
        this.statusLaborando = statusLaborando;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "EmpleadoPago{" +
                "cedula='" + cedula + '\'' +
                ", nombreEmpleado='" + nombreEmpleado + '\'' +
                ", direccion='" + direccion + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", cargo='" + cargo + '\'' +
                ", statusLaborando=" + statusLaborando +
                ", salario=" + salario +
                ", fechaInicio=" + fechaInicio +
                ", monto=" + monto +
                '}';
    }
}
