package core.conexion.model;

/**
 * Created by WAMS-10 on 27/07/2017.
 */
public class AlertModel {

    public int eleccion;

    public AlertModel(int eleccion) {
        this.eleccion = eleccion;
    }

    public int getEleccion() {
        return eleccion;
    }

    public void setEleccion(int eleccion) {
        this.eleccion = eleccion;
    }
}
