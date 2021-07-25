package org.greentracker.scene;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Main extends Application {
    public static final int WINDOW_WIDTH = 960;
    public static final int WINDOW_HEIGHT = 540;

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            String resourcesPath = Objects.requireNonNull(getClass().getResource("/")).toString();
            Parent mainWindow = FXMLLoader.load(new URL(resourcesPath + "MainWindow.fxml"));

            primaryStage.setTitle("GreenTracker");
            primaryStage.setScene(new Scene(mainWindow, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
