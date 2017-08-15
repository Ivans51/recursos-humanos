package core.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Ivans on 7/17/2017.
 */
public class Estado {

    // Alert
    public static final int EXITOSA = 1, ERROR = 2;
    public static final int PRIMERIZO = 0, TRUE = 1;

    // Estato de los controles
    public static final int DISABLE = 1;
    public static final int VISIBLE = 2;
    public static final int EDITABLE = 3;

    // Status Empleado
    public static final int NOLABORANDO = 0;
    public static final int TRABAJANDO = 1;
    public static final int DESPEDIDO = 2;
    public static final int DEPERMISO = 3;

    // Status Usuario
    public static final int PRIMIERIZO = 0;
    public static final int ADMINISTRADOR = 1;
    public static final int SECRETARIA = 2;
    public static final int INVITADO = 3;
    public static final int EMPLEADO = 4;
}
