package core.util;

import com.sun.istack.internal.Nullable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.EventObject;

/**
 * Created by Ivans on 7/20/2017.
 */
public class ManagerFXML {

    public static FXMLLoader fxmlLoader;

    public ManagerFXML() {
    }

    /**
     * return el fxmlLoader
     *
     * @return
     */
    public static FXMLLoader getFxmlLoader() {
        return fxmlLoader;
    }

    /**
     * Cambiar una especifica escena
     *
     * @param loadRoot
     * @param parentPane
     */
    public void cambiarEscena(String loadRoot, Pane parentPane) {
        Pane childPane = null;
        try {
            childPane = FXMLLoader.load(getClass().getResource(loadRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
        parentPane.getChildren().setAll(childPane);
    }

    /**
     * Reutilizar Vistas, seleccionar escena
     *
     * @param numPane
     * @param parentPane
     */
    public void cambiarMultiplesEscenas(int numPane, Pane parentPane, int[] selectPane, String[] routes) {
        for (int i = 0; i < selectPane.length; i++)
            if (numPane == selectPane[i])
                cambiarEscena(routes[i], parentPane);
    }

    public void navegarAbrirStageModel(EventObject event, String[] route,
                                       OpenStageModel openStageModel, Control... control) {

        Control[] click = setClick(control);
        for (int i = 0; i < click.length; i++) {
            if (event.getSource() == click[i]) {
                openStageModel.openStageInteface(route[i], click[i]);
                break;
            }
        }
    }

    public Control[] setClick(Control... control) {
        Control[] click = new Control[control.length];
        for (int i = 0; i < control.length; i++)
            click[i] = control[i];
        return click;
    }

    public void cambiarSceneMultiple(EventObject event, String[] route, CambiarScene cambiarScene, Object... control) {

        Object[] click = setClick(control);
        for (int i = 0; i < click.length; i++) {
            if (event.getSource() == click[i]) {
                cambiarScene.cambiarEscenaInterface(route[i]);
                break;
            }
        }
    }

    public Object[] setClick(Object... control) {
        Object[] click = new Object[control.length];
        for (int i = 0; i < control.length; i++)
            click[i] = control[i];
        return click;
    }

    /**
     * Abre otro stage y cierra el actual solo si el boton es distinto de nulo
     *
     * @param route
     * @param title   tile of the window
     * @param control if is distinct of null close stage
     * @param model   if is distinct of null you can send model data to another stage
     */
    public void abrirStage(String route, String title, @Nullable Control control, @Nullable ModelData model) {
        fxmlLoader = new FXMLLoader(getClass().getResource(route));
        Stage stage = new Stage();
        stage.setTitle(title);
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.sizeToScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
        if (control != null)
            cerrarStage(control);
        if (model != null)
            model.setModelData();
    }

    /**
     * Abre otro scenes y cierra el actual si btn es distinto de nulo
     *
     * @param route
     * @param modality   set controls modal of the Window
     * @param control    if is distinct of null close stage
     * @param model      if is distinct of null you can send model data to another stage
     * @param resizable
     * @param stageStyle
     */
    public void abrirStageStyle(String route, String title, Modality modality, @Nullable Control control,
                                boolean resizable, StageStyle stageStyle, @Nullable ModelData model) {

        fxmlLoader = new FXMLLoader(getClass().getResource(route));
        Stage stage = new Stage();
        stage.initModality(modality);
        stage.setTitle(title);
        stage.initStyle(stageStyle);
        stage.setResizable(resizable);
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
        if (control != null)
            cerrarStage(control);
        if (model != null)
            model.setModelData();
    }

    /**
     * Envio el modelo de dato
     */
    public interface ModelData {
        void setModelData();
    }

    public interface OpenStageModel {
        void openStageInteface(String route, @Nullable Control btn);
    }

    public interface CambiarScene {
        void cambiarEscenaInterface(String loadRoot);
    }

    /**
     * Cierra el Stage
     *
     * @param btn
     */
    public static void cerrarStage(Control btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

}
