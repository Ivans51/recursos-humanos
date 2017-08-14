package core.conexion.vo;

import java.util.Date;

/**
 * Created by WAMS-10 on 29/07/2017.
 */
public class Valores {

    private int idvalores;
    private double salario;
    private double precioUnidadTributaria;
    private double paroForzoso;
    private double FAO;
    private double IVSS;
    private Date fecha;
    private int diasUtilidades;
    private int fk_id_usuario;
    private int contratacion_idContratacion;

    public int getIdvalores() {
        return idvalores;
    }

    public void setIdvalores(int idvalores) {
        this.idvalores = idvalores;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getPrecioUnidadTributaria() {
        return precioUnidadTributaria;
    }

    public void setPrecioUnidadTributaria(double precioUnidadTributaria) {
        this.precioUnidadTributaria = precioUnidadTributaria;
    }

    public double getParoForzoso() {
        return paroForzoso;
    }

    public void setParoForzoso(double paroForzoso) {
        this.paroForzoso = paroForzoso;
    }

    public double getFAO() {
        return FAO;
    }

    public void setFAO(double FAO) {
        this.FAO = FAO;
    }

    public double getIVSS() {
        return IVSS;
    }

    public void setIVSS(double IVSS) {
        this.IVSS = IVSS;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getFk_id_usuario() {
        return fk_id_usuario;
    }

    public void setFk_id_usuario(int fk_id_usuario) {
        this.fk_id_usuario = fk_id_usuario;
    }

    public int getDiasUtilidades() {
        return diasUtilidades;
    }

    public void setDiasUtilidades(int diasUtilidades) {
        this.diasUtilidades = diasUtilidades;
    }

    public int getContratacion_idContratacion() {
        return contratacion_idContratacion;
    }

    public void setContratacion_idContratacion(int contratacion_idContratacion) {
        this.contratacion_idContratacion = contratacion_idContratacion;
    }

    @Override
    public String toString() {
        return "Valores{" +
                "idvalores=" + idvalores +
                ", salario=" + salario +
                ", precioUnidadTributaria=" + precioUnidadTributaria +
                ", paroForzoso=" + paroForzoso +
                ", FAO=" + FAO +
                ", IVSS=" + IVSS +
                ", fecha=" + fecha +
                ", diasUtilidades=" + diasUtilidades +
                ", fk_id_usuario=" + fk_id_usuario +
                ", contratacion_idContratacion=" + contratacion_idContratacion +
                '}';
    }
}
