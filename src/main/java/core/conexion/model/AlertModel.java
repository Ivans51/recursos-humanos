package core.conexion.model;

/**
 * Created by WAMS-10 on 27/07/2017.
 */
public class AlertModel {

    public int eleccion;
    private String title;

    public AlertModel(int eleccion, String title) {
        this.eleccion = eleccion;
        this.title = title;
    }

    public int getEleccion() {
        return eleccion;
    }

    public void setEleccion(int eleccion) {
        this.eleccion = eleccion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
