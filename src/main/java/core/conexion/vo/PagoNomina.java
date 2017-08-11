package core.conexion.vo;

import java.util.Date;

/**
 * Created by WAMS-10 on 29/07/2017.
 */
public class PagoNomina {

    private int idNominaPago;
    private String nombreEmpleado;
    private int diasHabiles;
    private int diasDescanso;
    private int bonoNocturno;
    private int diasFeriados;
    private double totalAsignaciones;
    private double faov;
    private double ivss;
    private double paroForzoso;
    private double prestamo;
    private int diasNoLaborados;
    private double totalDeduciones;
    private double salarioTotal;
    private Date fecha;
    private String FK_cedula_empleado;

    public int getIdNominaPago() {
        return idNominaPago;
    }

    public void setIdNominaPago(int idNominaPago) {
        this.idNominaPago = idNominaPago;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public int getDiasHabiles() {
        return diasHabiles;
    }

    public void setDiasHabiles(int diasHabiles) {
        this.diasHabiles = diasHabiles;
    }

    public int getDiasDescanso() {
        return diasDescanso;
    }

    public void setDiasDescanso(int diasDescanso) {
        this.diasDescanso = diasDescanso;
    }

    public int getBonoNocturno() {
        return bonoNocturno;
    }

    public void setBonoNocturno(int bonoNocturno) {
        this.bonoNocturno = bonoNocturno;
    }

    public int getDiasFeriados() {
        return diasFeriados;
    }

    public void setDiasFeriados(int diasFeriados) {
        this.diasFeriados = diasFeriados;
    }

    public double getTotalAsignaciones() {
        return totalAsignaciones;
    }

    public void setTotalAsignaciones(double totalAsignaciones) {
        this.totalAsignaciones = totalAsignaciones;
    }

    public double getFaov() {
        return faov;
    }

    public void setFaov(double faov) {
        this.faov = faov;
    }

    public double getIvss() {
        return ivss;
    }

    public void setIvss(double ivss) {
        this.ivss = ivss;
    }

    public double getParoForzoso() {
        return paroForzoso;
    }

    public void setParoForzoso(double paroForzoso) {
        this.paroForzoso = paroForzoso;
    }

    public double getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(double prestamo) {
        this.prestamo = prestamo;
    }

    public int getDiasNoLaborados() {
        return diasNoLaborados;
    }

    public void setDiasNoLaborados(int diasNoLaborados) {
        this.diasNoLaborados = diasNoLaborados;
    }

    public double getTotalDeduciones() {
        return totalDeduciones;
    }

    public void setTotalDeduciones(double totalDeduciones) {
        this.totalDeduciones = totalDeduciones;
    }

    public double getSalarioTotal() {
        return salarioTotal;
    }

    public void setSalarioTotal(double salarioTotal) {
        this.salarioTotal = salarioTotal;
    }

    public String getFK_cedula_empleado() {
        return FK_cedula_empleado;
    }

    public void setFK_cedula_empleado(String FK_cedula_empleado) {
        this.FK_cedula_empleado = FK_cedula_empleado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "PagoNomina{" +
                "idNomina=" + idNominaPago +
                ", nombreEmpleado='" + nombreEmpleado + '\'' +
                ", diasHabiles=" + diasHabiles +
                ", diasDescanso=" + diasDescanso +
                ", bonoNocturno=" + bonoNocturno +
                ", diasFeriados=" + diasFeriados +
                ", totalAsignaciones=" + totalAsignaciones +
                ", faov=" + faov +
                ", ivss=" + ivss +
                ", paroForzoso=" + paroForzoso +
                ", prestamo=" + prestamo +
                ", diasNoLaborados=" + diasNoLaborados +
                ", totalDeduciones=" + totalDeduciones +
                ", salarioTotal=" + salarioTotal +
                ", fecha=" + fecha +
                ", FK_cedula_empleado='" + FK_cedula_empleado + '\'' +
                '}';
    }
}
