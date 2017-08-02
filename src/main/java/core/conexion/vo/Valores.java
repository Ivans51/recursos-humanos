package core.conexion.vo;

/**
 * Created by WAMS-10 on 29/07/2017.
 */
public class Valores {

    private int idValores;
    private String salario;
    private String precioUnidadTributaria;
    private String paroForzoso;
    private String FAO;
    private int fk_id_usuario;

    public int getIdValores() {
        return idValores;
    }

    public void setIdValores(int idValores) {
        this.idValores = idValores;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
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

    @Override
    public String toString() {
        return "Valores{" +
                "idValores=" + idValores +
                ", salario='" + salario + '\'' +
                ", precioUnidadTributaria='" + precioUnidadTributaria + '\'' +
                ", paroForzoso='" + paroForzoso + '\'' +
                ", FAO='" + FAO + '\'' +
                ", fk_id_usuario=" + fk_id_usuario +
                '}';
    }
}
