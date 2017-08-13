package core.conexion.model;

/**
 * Created by Ivans on 8/12/2017.
 */
public class EmpleadoTotalPago {

    private String cedula;
    private String nombreEmpleado;
    private double cestaTicket;
    private double salarioIngtegral;
    private double quincena;
    private double utilidades;
    private double vales;

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

    public double getCestaTicket() {
        return cestaTicket;
    }

    public void setCestaTicket(double cestaTicket) {
        this.cestaTicket = cestaTicket;
    }

    public double getSalarioIngtegral() {
        return salarioIngtegral;
    }

    public void setSalarioIngtegral(double salarioIngtegral) {
        this.salarioIngtegral = salarioIngtegral;
    }

    public double getQuincena() {
        return quincena;
    }

    public void setQuincena(double quincena) {
        this.quincena = quincena;
    }

    public double getUtilidades() {
        return utilidades;
    }

    public void setUtilidades(double utilidades) {
        this.utilidades = utilidades;
    }

    public double getVales() {
        return vales;
    }

    public void setVales(double vales) {
        this.vales = vales;
    }
}
