package gui;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class ProgramManager extends Application {
    private static Stage primaryStage;
    private static Map<Scenes, ProgramScene> scenes = new HashMap<>();

    public static void main(final String... args) {
        launch(args);
    }

    static Stage getPrimaryStage() {
        return primaryStage;
    }

    static void openScene(final Scenes sceneToOpen) {
        ProgramScene scene = scenes.get(sceneToOpen);
        if (scene != null) {
            scene.switchTo();
        } else {
            System.out.println("================= SCENE NOT FOUND =================");
        }
    }

    public void start(Stage stage) {
        primaryStage = stage;

        primaryStage.setHeight(GUIConfig.DEFAULT_HEIGHT);
        primaryStage.setWidth(GUIConfig.DEFAULT_WIDTH);

        generateScenes();

        openScene(GUIConfig.FIRSTSCENE);

        primaryStage.setResizable(GUIConfig.IS_RESIZABLE);
        primaryStage.show();
    }

    private void generateScenes() {
        new Scene0();
        new Scene1();
    }

    static void putScene(Scenes sceneEnum, ProgramScene sceneObject){
        scenes.put(sceneEnum, sceneObject);
    }

    private void addCSS(String path) {
        primaryStage.getScene().getStylesheets().add(
                ProgramManager.class.getResource(path).toExternalForm()
        );
    }

}
