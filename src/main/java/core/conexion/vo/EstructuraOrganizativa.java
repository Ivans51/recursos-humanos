package core.conexion.vo;

/**
 * Created by WAMS-10 on 29/07/2017.
 */
public class EstructuraOrganizativa {

    private int idEstructura;
    private String departamento;
    private String funcion;
    private String jefeInmediato;
    private int FK_idUsuario;

    public int getIdEstructura() {
        return idEstructura;
    }

    public void setIdEstructura(int idEstructura) {
        this.idEstructura = idEstructura;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getJefeInmediato() {
        return jefeInmediato;
    }

    public void setJefeInmediato(String jefeInmediato) {
        this.jefeInmediato = jefeInmediato;
    }

    public int getFK_idUsuario() {
        return FK_idUsuario;
    }

    public void setFK_idUsuario(int FK_idUsuario) {
        this.FK_idUsuario = FK_idUsuario;
    }

    @Override
    public String toString() {
        return "EstructuraOrganizativa{" +
                "idEstructura=" + idEstructura +
                ", departamento='" + departamento + '\'' +
                ", funcion='" + funcion + '\'' +
                ", jefeInmediato='" + jefeInmediato + '\'' +
                ", FK_idUsuario=" + FK_idUsuario +
                '}';
    }
}
