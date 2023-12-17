package jfx.demo.Presentation;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Start extends Application {


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Portada del Libro");

        VBox root = new VBox();
        root.setSpacing(20);
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        Text titleText = new Text("Diccionario UPTC");
        titleText.setFont(Font.font("Arial", 30));


        Text sloganText = new Text("Descubre una nueva aventura");
        sloganText.setFont(Font.font("Arial", 18));


        Button startButton = new Button("Empezar->");
        startButton.setOnAction(e -> {

            primaryStage.close();
        });

        root.getChildren().addAll(titleText, sloganText, startButton);

        Scene scene = new Scene(root, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
