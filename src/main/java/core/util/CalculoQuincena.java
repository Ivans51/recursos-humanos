package core.util;

/**
 * Created by Ivans on 8/6/2017.
 */
public class CalculoQuincena {

    public double diasLaborados;
    public double diasNocturno;
    public double diasFeriados;
    public double diasNoLaborados;
    public double FAOV;
    public double IVSS;
    public double paroForzoso;
    private double sueldoBasico;
    private double vales;

    public CalculoQuincena(double sueldoBasico) {
        this.sueldoBasico = sueldoBasico;
    }

    public void setAsignaciones(double diasLaborados, double diasNocturno, double diasFeriados) {
        this.diasLaborados = diasLaborados;
        this.diasNocturno = diasNocturno;
        this.diasFeriados = diasFeriados;
    }

    public void setDeduciones(double diasNoLaborados, double FAOV, double IVSS, double paroForzoso, double vales) {
        this.diasNoLaborados = diasNoLaborados;
        this.FAOV = FAOV;
        this.IVSS = IVSS;
        this.paroForzoso = paroForzoso;
        this.vales = vales;
    }

    public double getSalarioDiario() throws Myexception {
        if (sueldoBasico == 0)
            throw new Myexception("sueldo basico nulo");
        return sueldoBasico / 30;
    }

    public double getDiasHabiles() throws Myexception {
        return diasLaborados * getSalarioDiario();
    }

    public double getDiasDescanso() throws Myexception {
        return 4 * getSalarioDiario();
    }

    public double getBonoNocturno() throws Myexception {
        return 0.30 * getSalarioDiario() * diasNocturno;
    }

    public double setBonoVentas() throws Myexception{
        return 0;
    }

    // TODO: 8/6/2017 recuperar bono de ventas
    public double getBonoVentas() throws Myexception {
        return 0;
    }

    public double getDiasFeriados() throws Myexception {
        return (diasFeriados * getSalarioDiario() * 2);
    }

    /**
     * Obtener Asignaciones
     * @return
     * @throws Myexception
     */

    public double getTotalAsignaciones() throws Myexception {
        return getDiasHabiles() + getDiasDescanso() + getBonoNocturno() + getBonoVentas() + getDiasFeriados();
    }

    public double getFAOV() throws Myexception {
        return FAOV * getTotalAsignaciones() / 100;
    }

    public double getIVSS() throws Myexception {
        return IVSS * getTotalAsignaciones() / 100;
    }

    public double getParoForzoso() throws Myexception {
        return paroForzoso * getTotalAsignaciones() / 100;
    }

    public double getDiasNoLaborados() throws Myexception {
        return diasNoLaborados * getSalarioDiario();
    }

    public double setVales(double diasRestantePago, double diasLaborados) throws Myexception {
        return (diasRestantePago - diasLaborados) * getSalarioDiario();
    }

    /**
     * Obtener total Deduciones
     * @return
     * @throws Myexception
     */
    public double getTotalDeduciones() throws Myexception {
        return getFAOV() + getIVSS() + getParoForzoso() + vales + getDiasNoLaborados();
    }

    /**
     * Obterner Total Quincena
     * @return
     * @throws Myexception
     */
    public double getTotalQuincena() throws Myexception {
        return getTotalAsignaciones() - getTotalDeduciones();
    }

}
