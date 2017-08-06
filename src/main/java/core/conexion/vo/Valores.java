package core.conexion.vo;

import java.util.Date;

/**
 * Created by WAMS-10 on 29/07/2017.
 */
public class Valores {

    private int idValores;
    private double salario;
    private String precioUnidadTributaria;
    private String paroForzoso;
    private String FAO;
    private Date fecha;
    private int fk_id_usuario;

    public int getIdValores() {
        return idValores;
    }

    public void setIdValores(int idValores) {
        this.idValores = idValores;
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

    public int getFk_id_usuario() {
        return fk_id_usuario;
    }

    public void setFk_id_usuario(int fk_id_usuario) {
        this.fk_id_usuario = fk_id_usuario;
    }

    public Date getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "Valores{" +
                "idValores=" + idValores +
                ", salario='" + salario + '\'' +
                ", precioUnidadTributaria='" + precioUnidadTributaria + '\'' +
                ", paroForzoso='" + paroForzoso + '\'' +
                ", FAO='" + FAO + '\'' +
                ", fecha=" + fecha +
                ", fk_id_usuario=" + fk_id_usuario +
                '}';
    }
}
