package gui;

import javafx.scene.Parent;
import javafx.scene.Scene;

public abstract class ProgramScene {
    private Scenes sceneEnum;
    private String sceneTitle;
    private Parent primaryParent;

    ProgramScene(Scenes sceneEnum) {
        this.sceneEnum = sceneEnum;
        ProgramManager.putScene(this.sceneEnum, this);
        generateScene();
        setSceneTitle();
    }

    public Scenes getSceneEnum() {
        return sceneEnum;
    }

    public String getSceneTitle() {
        return sceneTitle;
    }

    void setSceneTitle(final String titleToSet) {
        this.sceneTitle = String.format("%s - %s", GUIConfig.PROGRAM_NAME, titleToSet);
    }

    public Parent getPrimaryParent() {
        return primaryParent;
    }

    void setPrimaryParent(Parent primaryParent) {
        this.primaryParent = primaryParent;
    }

    protected abstract void generateScene();

    protected abstract void setSceneTitle();

    void switchTo() {
        if (ProgramManager.getPrimaryStage() == null) {
            System.out.println("Primary stage doesn't exist.");
            return;
        }

        if (ProgramManager.getPrimaryStage().getScene() != null) {
            if (ProgramManager.getPrimaryStage().getScene().getRoot().equals(this.primaryParent)) {
                return;
            }
            ProgramManager.getPrimaryStage().getScene().setRoot(this.primaryParent);
        } else {
            ProgramManager.getPrimaryStage().setScene(new Scene(
                    this.primaryParent,
                    ProgramManager.getPrimaryStage().getWidth(),
                    ProgramManager.getPrimaryStage().getHeight()
            ));
        }
        ProgramManager.getPrimaryStage().setTitle(sceneTitle);
    }
}
