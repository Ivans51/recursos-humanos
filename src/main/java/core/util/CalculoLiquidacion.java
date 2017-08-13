package core.util;

/**
 * Created by WAMS-10 on 30/07/2017.
 */
public class CalculoLiquidacion {

    public int diasSalarioIntegral;
    private Double sueldoBasico;
    private Integer añosServicios;
    private Integer diasUtilidades;
    private int mesesTrabajados;

    public CalculoLiquidacion(Double sueldoBasico, Integer diasUtilidades, int mesesTrabajados) {
        this.sueldoBasico = sueldoBasico;
        this.diasUtilidades = diasUtilidades;
        this.mesesTrabajados = mesesTrabajados;
        elegirMetodoPS();
    }

    public void setAñosServicios(Integer añosServicios) {
        this.añosServicios = añosServicios;
    }

    public void setSueldoBasico(Double sueldoBasico) {
        this.sueldoBasico = sueldoBasico;
    }

    public Double salarioDiario() throws Myexception {
        if (sueldoBasico == null)
            throw new Myexception("sueldo basico nulo");
        return sueldoBasico / 30;
    }

    public Double cestaTicket(Double precioUnidadTributaria) {
        return (precioUnidadTributaria * 17) * 30;
    }

    public Double utilidades() throws Myexception {
        return (diasUtilidades / 360) * salarioDiario();
    }

    public Double vacaciones() throws Myexception {
        añosServicios = añosServicios + 15;
        return (añosServicios / 360) * salarioDiario();
    }

    public Double salarioIntegral() throws Myexception {
        return salarioDiario() + utilidades() + vacaciones();
    }

    public Double totalAsignaciones() throws Myexception{
        return vacaciones() + bonoVacacional() + utilidades();
    }

    public Double totalDeduciones() throws Myexception{
        return 0.0;
    }

    public Double bonoVacacional() {
        añosServicios = añosServicios + 7;
        return sueldoBasico * añosServicios;
    }

    public int elegirMetodoPS() {
        if (mesesTrabajados < 3)
            diasSalarioIntegral = getDiasSalarioIntegralMeses();
        else if (mesesTrabajados >= 3 && mesesTrabajados <= 12)
            diasSalarioIntegral = getDiasSalarioIntegralTrimestral();
        else if (mesesTrabajados > 12)
            diasSalarioIntegral = getDiasSalarioIntegralAnual();
        return diasSalarioIntegral;
    }

    /**
     * Calcular los dias por cada mes
     * @return
     */
    private int getDiasSalarioIntegralMeses() {
        for (int i = 0; i < mesesTrabajados; i++)
            diasSalarioIntegral += 5;
        return diasSalarioIntegral;
    }

    /**
     * Calcular los dias por cada trimestre hasta el cuarto trimestre
     * @return
     */
    public int getDiasSalarioIntegralTrimestral() {
        for (int i = 0; i < mesesTrabajados; i++)
            diasSalarioIntegral += 15;
        return diasSalarioIntegral;
    }

    /**
     * Calcular los dias por año
     * @return
     */
    private int getDiasSalarioIntegralAnual() {
        for (int i = 0; i < mesesTrabajados; i++)
            diasSalarioIntegral += 30;
        return diasSalarioIntegral;
    }

    public Double total() throws Myexception {
        return totalAsignaciones() - totalDeduciones();
    }

    public Double antiguedadDespido() throws Myexception {
        getPagoAño();
        Double antiguedadDespido = null;
        Double meses = null;
        if (meses >= 3 && meses <= 6) {
            antiguedadDespido = getPagoAño() + (salarioDiario() * 15);
        } else if (meses >= 7 && meses <= 12) {
            antiguedadDespido = getPagoAño() + (salarioDiario() * 45);
        } else if (meses > 12) {
            antiguedadDespido = getPagoAño() + (salarioDiario() * 60);
        }
        return antiguedadDespido;
    }

    private Double getPagoAño() throws Myexception {
        int count = 0;
        double v = 0;
        for (int i = 0; i < añosServicios; i++) {
            v = v + (salarioDiario() * 2);
            count++;
            if (count == 30)
                return v;
            break;
        }
        return v;
    }

    public Double antiguadPrestamo() throws Myexception {
        Double diasLaborados = null;
        return (salarioIntegral() / diasLaborados) * 5;
    }

}
