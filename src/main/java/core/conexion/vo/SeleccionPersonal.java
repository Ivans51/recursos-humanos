package core.conexion.vo;

/**
 * Created by WAMS-10 on 29/07/2017.
 */
public class SeleccionPersonal {

    private int idSeleccion;
    private String nombreCandidato;
    private String cedula;
    private String direccion;
    private String telefono;
    private String puestoPostulacion;
    private int disponibilidad;
    private int FK_idUsuario;

    public int getIdSeleccion() {
        return idSeleccion;
    }

    public void setIdSeleccion(int idSeleccion) {
        this.idSeleccion = idSeleccion;
    }

    public String getNombreCandidato() {
        return nombreCandidato;
    }

    public void setNombreCandidato(String nombreCandidato) {
        this.nombreCandidato = nombreCandidato;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPuestoPostulacion() {
        return puestoPostulacion;
    }

    public void setPuestoPostulacion(String puestoPostulacion) {
        this.puestoPostulacion = puestoPostulacion;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public int getFK_idUsuario() {
        return FK_idUsuario;
    }

    public void setFK_idUsuario(int FK_idUsuario) {
        this.FK_idUsuario = FK_idUsuario;
    }

    @Override
    public String toString() {
        return "SeleccionPersonal{" +
                "idSeleccion=" + idSeleccion +
                ", nombreCandidato='" + nombreCandidato + '\'' +
                ", cedula='" + cedula + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", puestoPostulacion='" + puestoPostulacion + '\'' +
                ", disponibilidad=" + disponibilidad +
                ", FK_idUsuario=" + FK_idUsuario +
                '}';
    }
}
