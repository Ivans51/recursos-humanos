package core.conexion.vo;

/**
 * Created by WAMS-10 on 29/07/2017.
 */
public class Nomina {

    private int idNomina;
    private String nombreEmpleado;
    private int diasHabiles;
    private int diasDescanso;
    private int bonoNocturno;
    private int bonoLealtad;
    private int diasFeriados;
    private double totalAsignaciones;
    private double faov;
    private double ivss;
    private double paroForzoso;
    private double prestamo;
    private int diasNoLaborados;
    private double totalDeduciones;
    private double salarioTotal;
    private String cedula;
    private String FK_cedula_empleado;

    public int getIdNomina() {
        return idNomina;
    }

    public void setIdNomina(int idNomina) {
        this.idNomina = idNomina;
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

    public int getBonoLealtad() {
        return bonoLealtad;
    }

    public void setBonoLealtad(int bonoLealtad) {
        this.bonoLealtad = bonoLealtad;
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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getFK_cedula_empleado() {
        return FK_cedula_empleado;
    }

    public void setFK_cedula_empleado(String FK_cedula_empleado) {
        this.FK_cedula_empleado = FK_cedula_empleado;
    }

    @Override
    public String toString() {
        return "Nomina{" +
                "idNomina=" + idNomina +
                ", nombreEmpleado='" + nombreEmpleado + '\'' +
                ", getDiasHabiles=" + diasHabiles +
                ", getDiasDescanso=" + diasDescanso +
                ", getBonoNocturno=" + bonoNocturno +
                ", bonoLealtad=" + bonoLealtad +
                ", getDiasFeriados=" + diasFeriados +
                ", getTotalAsignaciones=" + totalAsignaciones +
                ", faov=" + faov +
                ", ivss=" + ivss +
                ", getParoForzoso=" + paroForzoso +
                ", prestamo=" + prestamo +
                ", getDiasNoLaborados=" + diasNoLaborados +
                ", getTotalDeduciones=" + totalDeduciones +
                ", salarioTotal=" + salarioTotal +
                ", cedula='" + cedula + '\'' +
                ", FK_cedula_empleado='" + FK_cedula_empleado + '\'' +
                '}';
    }
}
