package core.conexion.vo;

import java.util.Date;
import java.util.List;

/**
 * Created by WAMS-10 on 29/07/2017.
 */
public class Empleado {

    private String cedula;
    private String nombreEmpleado;
    private Date fechaNacimiento;
    private String direccion;
    private String cargo;
    private String registroSS;
    private String horaInicio;
    private int horasTrabajadas;
    private int statusLaborando;
    private int diasLaborados;
    private int FK_id_Usuario;
    private Contratacion contratacion;
    private List<Contratacion> contratacions;
    private double calculo;
    private boolean statuSession;

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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getRegistroSS() {
        return registroSS;
    }

    public void setRegistroSS(String registroSS) {
        this.registroSS = registroSS;
    }

    public int getFK_id_Usuario() {
        return FK_id_Usuario;
    }

    public void setFK_id_Usuario(int FK_id_Usuario) {
        this.FK_id_Usuario = FK_id_Usuario;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public int getStatusLaborando() {
        return statusLaborando;
    }

    public void setStatusLaborando(int statusLaborando) {
        this.statusLaborando = statusLaborando;
    }

    public Contratacion getContratacion() {
        return contratacion;
    }

    public void setContratacion(Contratacion contratacion) {
        this.contratacion = contratacion;
    }

    public List<Contratacion> getContratacions() {
        return contratacions;
    }

    public void setContratacions(List<Contratacion> contratacions) {
        this.contratacions = contratacions;
    }

    public double getCalculo() {
        return calculo;
    }

    public void setCalculo(double calculo) {
        this.calculo = calculo;
    }

    public int getDiasLaborados() {
        return diasLaborados;
    }

    public void setDiasLaborados(int diasLaborados) {
        this.diasLaborados = diasLaborados;
    }

    public boolean isStatuSession() {
        return statuSession;
    }

    public void setStatuSession(boolean statuSession) {
        this.statuSession = statuSession;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "cedula='" + cedula + '\'' +
                ", nombreEmpleado='" + nombreEmpleado + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", direccion='" + direccion + '\'' +
                ", cargo='" + cargo + '\'' +
                ", registroSS='" + registroSS + '\'' +
                ", horaInicio='" + horaInicio + '\'' +
                ", horasTrabajadas=" + horasTrabajadas +
                ", statusLaborando=" + statusLaborando +
                ", diasLaborados=" + diasLaborados +
                ", FK_id_Usuario=" + FK_id_Usuario +
                ", contratacion=" + contratacion +
                ", contratacions=" + contratacions +
                ", calculo=" + calculo +
                '}';
    }
}
