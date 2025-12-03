package edu.liceolapaz.mgr.jugadoresbasket;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("login-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Login - Basketball Players Management");
        stage.setScene(scene);
        stage.show();
    }

    public void onLoginButtonClick(ActionEvent actionEvent) {
    }
}