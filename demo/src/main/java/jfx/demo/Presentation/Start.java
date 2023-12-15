package jfx.demo.Presentation;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Start extends Application {
    private static StringProperty username = new SimpleStringProperty("");


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login Form");
        // Configurar el ícono de la ventana
       // Image iconImage = new Image("file:" + "src\\prograIconos\\descarga.jpg");

       // primaryStage.getIcons().add(iconImage);

        // Crear un StackPane como nodo raíz y agregar elementos a él
        StackPane root = new StackPane();

        // Crear un grid para organizar los elementos del formulario
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Etiquetas y campos de entrada

        Label usernameLabel = new Label("Usuario:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Contraseña:");
        PasswordField passwordField = new PasswordField();

        // CheckBox para mostrar/ocultar contraseña
        CheckBox showPasswordCheckBox = new CheckBox("Mostrar Contraseña");
        HBox hbShowPassword = new HBox(10);
        hbShowPassword.setAlignment(javafx.geometry.Pos.BOTTOM_RIGHT);
        hbShowPassword.getChildren().add(showPasswordCheckBox);
        grid.add(hbShowPassword, 1, 2);

        // Manejador de eventos para el CheckBox
        showPasswordCheckBox.setOnAction(e -> {
            if (showPasswordCheckBox.isSelected()) {
                passwordField.setManaged(false);
                passwordField.setVisible(false);
                TextField textField = new TextField(passwordField.getText());
                textField.setId("visiblePassword");
                grid.add(textField, 1, 1);
            } else {
                TextField textField = (TextField) grid.lookup("#visiblePassword");
                passwordField.setText(textField.getText());
                grid.getChildren().remove(textField);
                passwordField.setManaged(true);
                passwordField.setVisible(true);
            }
        });

        // Botón de inicio de sesión
        Button loginButton = new Button("Iniciar Sesión");

        // Manejador de eventos para el botón de inicio de sesión
        loginButton.setOnAction(e -> {
            String enteredUsername = usernameField.getText();
            String password = passwordField.getText();

            // Simplemente verifica si el nombre de usuario y la contraseña no están vacíos
            if (!enteredUsername.isEmpty() && !password.isEmpty() && enteredUsername.length() > 2) {



                primaryStage.close();
            } else {
                showAlert("Error de Inicio de Sesión",
                        "Por favor, ingrese su nombre de usuario y contraseña correctamente.");
            }
        });

        // Agregar elementos al grid
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 3);

        // Agregar el grid al StackPane
        root.getChildren().add(grid);

        Scene scene = new Scene(root, 300, 200);

        // Establecer el color de fondo de la ventana principal (Stage)
        root.setStyle("-fx-background-color: white;-fx-font-family: Tahoma; -fx-font-size: 12; -fx-font-color: black;");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

