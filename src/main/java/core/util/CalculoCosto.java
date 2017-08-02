package core.util;

/**
 * Created by WAMS-10 on 30/07/2017.
 */
public class CalculoCosto {

    private Double salarioIntegralDiario;

    private Double sueldoBasico;
    private Double precioUnidadTributaria;
    private Double paroForsozo;
    private Double IVSS;
    private Double FAO;
    private Integer añosServicios;

    public void setAñosServicios(Integer añosServicios) {
        this.añosServicios = añosServicios;
    }

    public void setSalarioIntegralDiario(Double salarioIntegralDiario) {
        this.salarioIntegralDiario = salarioIntegralDiario;
    }

    public void setSueldoBasico(Double sueldoBasico) {
        this.sueldoBasico = sueldoBasico;
    }

    public void setPrecioUnidadTributaria(Double precioUnidadTributaria) {
        this.precioUnidadTributaria = precioUnidadTributaria;
    }

    public void setParoForsozo(Double paroForsozo) {
        this.paroForsozo = paroForsozo;
    }

    public void setIVSS(Double IVSS) {
        this.IVSS = IVSS;
    }

    public void setFAO(Double FAO) {
        this.FAO = FAO;
    }

    public Double salarioBase() throws Myexception {
        if (sueldoBasico == null) {
            throw new Myexception("sueldo basico nulo");
        }
        return sueldoBasico / 30;
    }

    public Double cestaTicket() {
        return (precioUnidadTributaria * 17) * 30;
    }

    public Double utilidades() throws Myexception {
        Integer dias = 60, meses = 12;
        return ((dias / meses) / 30) * salarioBase();
    }

    public Double vacaciones() throws Myexception {
        añosServicios = añosServicios + 15;
        Double renumeracion = salarioBase() * añosServicios;
        return (((añosServicios) / 12) / 30) * salarioBase();
    }

    public Double salarioIntegral() throws Myexception {
        return salarioBase() + utilidades() + vacaciones();
    }

    public Double bonoVacacional() {
        añosServicios = añosServicios + 7;
        return sueldoBasico * añosServicios;
    }

    // TODO: 30/07/2017 Prestaciones sociales

    // TODO: 30/07/2017 Liquidacion

    public Double vales(Double diasRestantePago, Double diasLaboradas) throws Myexception {
        return (diasRestantePago - diasLaboradas) * salarioBase();
    }

    public Double antiguedadDespido() throws Myexception {
        getPagoAño();
        Double antiguadadDespido = null;
        Double meses = null;
        if (meses >= 3 && meses <= 6) {
            antiguadadDespido = getPagoAño() + (salarioBase() * 15);
        } else if (meses >= 7 && meses <= 12) {
            antiguadadDespido = getPagoAño() + (salarioBase() * 45);
        } else if (meses > 12) {
            antiguadadDespido = getPagoAño() + (salarioBase() * 60);
        }
        return antiguadadDespido;
    }

    private Double getPagoAño() throws Myexception {
        int count = 0;
        double v = 0;
        for (int i = 0; i < añosServicios; i++) {
            v = v + (salarioBase() * 2);
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
