package core.controller.session;

import core.util.ManagerFXML;
import core.util.Route;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by WAMS-10 on 17/07/2017.
 */
public class SessionLoading extends ManagerFXML implements Initializable {

    @FXML
    private ProgressBar barLoading;
    private ProgressIndicator pin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Task task = taskCreate(5);

        barLoading.progressProperty().bind(task.progressProperty());

        task.setOnSucceeded(event -> {
            System.out.println("succesfull");
            abrirStage(Route.ScreenMainHome, "Cars C.A.", barLoading, null);
        });

        Thread thread = new Thread(task);
        thread.start();
    }

    private Task taskCreate(int seconds){
        return new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i = 0; i < seconds; i++) {
                    Thread.sleep(1000);
                    updateProgress(i + 1, seconds);
                }
                return null;
            }
        };
    }
}
