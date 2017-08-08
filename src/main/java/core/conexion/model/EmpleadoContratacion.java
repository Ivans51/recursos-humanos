package core.conexion.model;

/**
 * Created by Ivans on 8/6/2017.
 */
public class EmpleadoContratacion {

    private String cedula;
    private String nombreEmpleado;
    private String direccion;
    private String fechaNac;
    private String cargo;
    private String statusActual;
    private String sueldo;
    private String fechaIngreso;
    private String fechaCulminacion;

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

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getStatusActual() {
        return statusActual;
    }

    public void setStatusActual(String statusActual) {
        this.statusActual = statusActual;
    }

    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaCulminacion() {
        return fechaCulminacion;
    }

    public void setFechaCulminacion(String fechaCulminacion) {
        this.fechaCulminacion = fechaCulminacion;
    }
}
