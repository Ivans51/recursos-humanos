package core.conexion.model;

/**
 * Created by Ivans on 7/17/2017.
 */
public class SessionModel {

    public final static int RECUPERARCLAVE = 1;
    public final static int CAMBIOUSUARIO = 2;
    public final static int CARGANDO = 3;
    public final static int BIENVENIDO = 4;
    private int selectPane;
    public String titlePane, descriptionPane;

    public int getSelectPane() {
        return selectPane;
    }

    public void setSelectPane(int selectPane) {
        this.selectPane = selectPane;
    }

    public String getTitlePane() {
        return titlePane;
    }

    public void setTitlePane(String titlePane) {
        this.titlePane = titlePane;
    }

    public String getDescriptionPane() {
        return descriptionPane;
    }

    public void setDescriptionPane(String descriptionPane) {
        this.descriptionPane = descriptionPane;
    }
}
