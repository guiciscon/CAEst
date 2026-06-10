package com.example.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader =
                new FXMLLoader(App.class.getResource("/view/HomeView.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("CAEst");
        stage.setScene(scene);
        stage.show();
    }
}