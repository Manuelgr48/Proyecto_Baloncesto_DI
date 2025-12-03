package edu.liceolapaz.mgr.jugadoresbasket.controller;

import edu.liceolapaz.mgr.jugadoresbasket.dao.UsuarioDAO;
import edu.liceolapaz.mgr.jugadoresbasket.dao.UsuarioDAOImpl;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    private UsuarioDAO usuarioDAO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        usuarioDAO = new UsuarioDAOImpl();

        Platform.runLater(() -> usernameField.requestFocus());
    }


    @FXML
    protected void onLoginButtonClick(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Atencion", "Por favor, rellena usuario y contraseña.");
            return;
        }

        try {
            if (usuarioDAO.validarCredenciales(username, password)) {
                System.out.println("Login correcto: " + username);


                mostrarAlerta(Alert.AlertType.INFORMATION, "Bienvenido", "Login correcto. Cargando sistema...");


            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error de Acceso", "Credenciales incorrectas");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error Crítico", "Fallo en la conexión con la base de datos.");
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String contenido) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private void cambiarEscena(ActionEvent event, String fxmlFile) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/liceolapaz/mgr/jugadoresbasket/" + fxmlFile));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}