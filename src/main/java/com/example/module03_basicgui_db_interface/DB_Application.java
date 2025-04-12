package com.example.module03_basicgui_db_interface;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;

public class DB_Application extends Application {
    private Stage primaryStage;
    private FadeTransition fadeIn;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        showScene1(); // Start with splash screen
    }

    private void showScene1() {
        try {
            URL splashResource = getClass().getResource("splash_screen.fxml");
            if (splashResource == null) {
                System.err.println("Cannot find splash_screen.fxml");
                return;
            }

            Parent root = FXMLLoader.load(splashResource);
            Scene scene = new Scene(root, 850, 560);

            URL styleResource = getClass().getResource("styling/style.css");
            if (styleResource != null) {
                scene.getStylesheets().add(styleResource.toExternalForm());
            }

            primaryStage.setScene(scene);
            primaryStage.show();

            // Add delay before changing scene
            javafx.application.Platform.runLater(() -> {
                try {
                    Thread.sleep(2000); // 2 second delay
                    changeScene();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeScene() {
        try {
            URL mainResource = getClass().getResource("db_interface_gui.fxml");
            if (mainResource == null) {
                System.err.println("Cannot find db_interface_gui.fxml");
                return;
            }

            Parent newRoot = FXMLLoader.load(mainResource);
            Scene currentScene = primaryStage.getScene();
            Parent currentRoot = currentScene.getRoot();

            // Setup fade out transition
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), currentRoot);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);

            // Create new scene
            Scene newScene = new Scene(newRoot, 850, 560);

            // Add light theme
            URL lightThemeResource = getClass().getResource("styling/light-theme.css");
            if (lightThemeResource != null) {
                newScene.getStylesheets().add(lightThemeResource.toExternalForm());
            }

            // Initialize fade in transition
            fadeIn = new FadeTransition(Duration.seconds(2), newRoot);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);

            fadeOut.setOnFinished(e -> {
                newRoot.setOpacity(0);
                primaryStage.setScene(newScene);
                fadeIn.play();
            });

            fadeOut.play();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}