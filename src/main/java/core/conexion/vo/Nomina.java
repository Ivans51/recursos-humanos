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
    private int diasFeriados;
    private double prestamo;
    private int diasNoLaborados;
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

    public int getDiasFeriados() {
        return diasFeriados;
    }

    public void setDiasFeriados(int diasFeriados) {
        this.diasFeriados = diasFeriados;
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
                ", diasHabiles=" + diasHabiles +
                ", diasDescanso=" + diasDescanso +
                ", bonoNocturno=" + bonoNocturno +
                ", diasFeriados=" + diasFeriados +
                ", prestamo=" + prestamo +
                ", diasNoLaborados=" + diasNoLaborados +
                ", FK_cedula_empleado='" + FK_cedula_empleado + '\'' +
                '}';
    }
}
