package core.conexion.vo;

import java.util.Date;

/**
 * Created by WAMS-10 on 29/07/2017.
 */
public class Valores {

    private int idvalores;
    private double salario;
    private String precioUnidadTributaria;
    private String paroForzoso;
    private String FAO;
    private String IVSS;
    private Date fecha;
    private int fk_id_usuario;

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

    public String getPrecioUnidadTributaria() {
        return precioUnidadTributaria;
    }

    public void setPrecioUnidadTributaria(String precioUnidadTributaria) {
        this.precioUnidadTributaria = precioUnidadTributaria;
    }

    public String getParoForzoso() {
        return paroForzoso;
    }

    public void setParoForzoso(String paroForzoso) {
        this.paroForzoso = paroForzoso;
    }

    public String getFAO() {
        return FAO;
    }

    public void setFAO(String FAO) {
        this.FAO = FAO;
    }

    public String getIVSS() {
        return IVSS;
    }

    public void setIVSS(String IVSS) {
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

    @Override
    public String toString() {
        return "Valores{" +
                "idvalores=" + idvalores +
                ", salario=" + salario +
                ", precioUnidadTributaria='" + precioUnidadTributaria + '\'' +
                ", paroForzoso='" + paroForzoso + '\'' +
                ", FAO='" + FAO + '\'' +
                ", IVSS='" + IVSS + '\'' +
                ", fecha=" + fecha +
                ", fk_id_usuario=" + fk_id_usuario +
                '}';
    }
}
