package gui;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTimeout;

class ProgramManagerTest {
    @Test
    void programManagerTest() {
        boolean failedTest = assertTimeout(ofMinutes(1), () -> {
            ProgramManagerTestThread thread = new ProgramManagerTestThread();
            thread.start();
            while (thread.stillActive) {
                try {
                    Thread.sleep(0, 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return thread.failedTest;
        });
        assertFalse(failedTest);
    }

    class ProgramManagerTestThread extends Thread {
        volatile boolean stillActive = true;
        boolean failedTest = false;

        public void run() {
            new Thread(() -> ProgramManager.main("testingMode")).start();
            Stage stage = null;
            while (ProgramManager.testingMode) {
                try {
                    sleep(0, 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stage = ProgramManager.getPrimaryStage();
            }
            if (ProgramManager.getScenes() == null) {
                System.out.println("ProgramManager.getScenes() == null");
                failedTest = true;
            } else if (stage == null) {
                System.out.println("stage == null");
                failedTest = true;
            } else if (stage.getScene() == null) {
                System.out.println("stage.getScene() == null");
                failedTest = true;
            } else if (ProgramManager.getScenes() == null) {
                System.out.println("ProgramManager.getScenes()");
                failedTest = true;
            } else if (ProgramManager.getScenes().get(GUIConfig.FIRSTSCENE) == null) {
                System.out.println("ProgramManager.getScenes().get(GUIConfig.FIRSTSCENE) == null");
                failedTest = true;
            } else if (stage.getScene().getRoot() == null) {
                System.out.println("stage.getScene().getRoot() == null");
                failedTest = true;
            } else if (!stage.getScene().getRoot().equals(ProgramManager.getScenes().get(GUIConfig.FIRSTSCENE).getPrimaryParent())) {
                System.out.println("!stage.getScene().getRoot().equals(ProgramManager.getScenes().get(GUIConfig.FIRSTSCENE).getPrimaryParent())");
                failedTest = true;
            }
            this.stillActive = false;
        }
    }
}