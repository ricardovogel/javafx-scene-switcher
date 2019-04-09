package gui;

import gui.ProgramManager;
import gui.ProgramScene;
import gui.Scenes;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;


public class Scene1 extends ProgramScene {
    Scene1() {
        super(Scenes.SCENE1);
    }

    @Override
    protected void generateScene() {
        Label label = new Label("Scene 1");
        AnchorPane.setTopAnchor(label, 10.0);
        AnchorPane.setLeftAnchor(label, 10.0);

        Button button = new Button("Go to Scene 0");
        button.setOnAction(e -> ProgramManager.openScene(Scenes.SCENE0));
        AnchorPane.setTopAnchor(button, 50.0);
        AnchorPane.setLeftAnchor(button, 10.0);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(label, button);
        this.setPrimaryParent(anchorPane);
    }

    @Override
    protected void setSceneTitle() {
        setSceneTitle("Scene 1");
    }
}
