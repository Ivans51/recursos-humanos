import core.util.ManagerFXML;
import core.util.Route;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Ivans on 7/9/2017.
 */
public class MainActivity extends Application {

    public static void main(String[] args) {
        launch(args);
        // System.out.println( System.getProperties());
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ManagerFXML managerFXML = new ManagerFXML();
        managerFXML.abrirStage(Route.MainNivelUsuario, "Session User", null, null);
    }
}
