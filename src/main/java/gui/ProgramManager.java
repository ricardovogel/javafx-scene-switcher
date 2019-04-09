package gui;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class ProgramManager extends Application {
    static boolean testingMode = true;
    private static Stage primaryStage;
    private static Map<Scenes, ProgramScene> scenes = new HashMap<>();

    public static Map<Scenes, ProgramScene> getScenes() {
        return scenes;
    }

    public static void main(final String... args) {
        boolean testingMode = false;
        for (String string : args) {
            if (string.equals("testingMode")) {
                testingMode = true;
            }
        }
        ProgramManager.testingMode = testingMode;
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

    static void putScene(Scenes sceneEnum, ProgramScene sceneObject) {
        scenes.put(sceneEnum, sceneObject);
    }

    public void start(Stage stage) {
        primaryStage = stage;

        primaryStage.setHeight(GUIConfig.DEFAULT_HEIGHT);
        primaryStage.setWidth(GUIConfig.DEFAULT_WIDTH);

        generateScenes();

        openScene(GUIConfig.FIRSTSCENE);

        primaryStage.setResizable(GUIConfig.IS_RESIZABLE);
        if (testingMode) {
            testingMode = false;
        } else {
            primaryStage.show();
        }
    }

    private void generateScenes() {
        new Scene0();
        new Scene1();
    }

    private void addCSS(String path) {
        primaryStage.getScene().getStylesheets().add(
                ProgramManager.class.getResource(path).toExternalForm()
        );
    }

}
