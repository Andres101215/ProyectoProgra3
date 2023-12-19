package jfx.demo.Presentation;


import Controller.Management;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

public class Portada extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Management man;
    public Portada(Management man) {
        this.man=man;
    }

    @Override
    public void start(Stage primaryStage) {
        // Configurar la imagen de fondo
        Image backgroundImage = new Image("file:" + "demo/src/prograIconos/Libro1.jpg");
        ImageView backgroundImageView = new ImageView(backgroundImage);
        Image iconImage = new Image("file:" + "demo/src/prograIconos/libro.png");
        primaryStage.getIcons().add(iconImage);

        // Configurar el título en la parte superior
        Text titleText = new Text("Diccionario");
        titleText.setFont(Font.font("Times New Roman", FontWeight.BOLD, 40));

        // Configurar el botón "Comenzar" en la parte inferior
        Button startButton = new Button("Comenzar");
        startButton.getStyleClass().add("button-enviar");
        startButton.setOnAction(e -> {

            MenuOptions mn= new MenuOptions(man);
            mn.mostrarVentana();
            primaryStage.close();

        });

        // Agregar un texto en la mitad
        Label middleTextLabel = new Label("Andres Felipe Puentes\n Luis Esteban Robelto");
        middleTextLabel.getStyleClass().add("middle-text");

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundImageView, createLayout(titleText, startButton, middleTextLabel));

        Scene scene = new Scene(stackPane, 500, 668);
        scene.getStylesheets().add(new File("demo/src/main/styles/portada.css").toURI().toString());



        primaryStage.setTitle("Programa de Diccionario");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createLayout(Text title, Button button, Label middleText) {
        VBox layout = new VBox(150);
        layout.setStyle("-fx-padding: 10;");
        layout.getChildren().addAll(title, middleText, button);
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        return layout;
    }

    public void mostrarVentana() {
        Stage stage = new Stage();
        try {
            start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
