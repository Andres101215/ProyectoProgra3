package jfx.demo.Presentation;

import Controller.Management;
import Logic.Word;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.File;
import java.util.Optional;

public class AddWord extends Application {

    private Management man;
    private Label messageLabel = new Label();
    private Label mensajeLabel = new Label();

    private String word = "";
    private String description = "";
    private String translate = "";
    private int id = 0;

    public AddWord(Management man) {
        this.man = man;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Añadir Palabra");
        Image iconImage = new Image("file:" + "demo/src/prograIconos/libro.png");

        primaryStage.getIcons().add(iconImage);

        VBox mainVBox = new VBox(15);
        mainVBox.setPadding(new Insets(15));

        Label wordLabel = new Label("Palabra*");
        Label definitionLabel = new Label("Definicion*  ");
        Label translateLabel = new Label("Traduccion*");

        TextField wordTextField = new TextField();
        wordTextField.setPromptText("Palabra:");


        TextField definitionTextField = new TextField();
        definitionTextField.setPromptText("Definicion:");

        TextField translateTextField = new TextField();
        translateTextField.setPromptText("Traduccion:");


        Button enviarButton = new Button("Enviar");

        HBox centerButtonHBox = new HBox();
        centerButtonHBox.setAlignment(Pos.CENTER);
        centerButtonHBox.getChildren().add(enviarButton);
        enviarButton.getStyleClass().add("button-enviar");

        HBox messageLabelHBox = new HBox(messageLabel);
        messageLabelHBox.setAlignment(Pos.CENTER);

        mainVBox.getChildren().addAll(
                new HBox(wordLabel, wordTextField),
                new HBox(definitionLabel, definitionTextField),
                new HBox(translateLabel, translateTextField),
                mensajeLabel, messageLabelHBox,
                centerButtonHBox);

        HBox.setMargin(wordLabel, new Insets(0, 20, 0, 0));
        HBox.setMargin(definitionLabel, new Insets(0, 30, 0, 0));
        HBox.setMargin(translateLabel, new Insets(0, 20, 0, 0));

        HBox.setHgrow(wordTextField, Priority.ALWAYS);
        HBox.setHgrow(definitionTextField, Priority.ALWAYS);
        HBox.setHgrow(translateTextField, Priority.ALWAYS);

        enviarButton.setOnAction(e -> {
            id = (man.generateAscciCode(wordTextField.getText()));
            word = wordTextField.getText();
            description = definitionTextField.getText();
            translate = translateTextField.getText();

            if (word.isBlank() || description.isBlank() || translate.isBlank()) {
                showErrorTimeline(wordTextField, messageLabel,
                        "Debe ingresar todos los datos ");
                return;
            } else if (!man.containCharacterSpecial(word)) {
                showErrorTimeline(wordTextField, messageLabel,
                        "Palabra inválida, no debe tener caracteres especiales.");
                return;
            } else if (word.isBlank()) {
                showErrorTimeline(wordTextField, messageLabel,
                        "Debe ingresar la palabra.");
                return;
            } else if (translate.isBlank()) {
                showErrorTimeline(translateTextField, messageLabel,
                        "Debe ingresar la traducción");
                return;
            } else if (description.isBlank()) {
                showErrorTimeline(definitionTextField, messageLabel,
                        "Debe ingresar la definición");
                return;
            } else if (man.validateWord(word)) {
                showErrorTimeline(definitionTextField, messageLabel,
                        "Esta palabra ya se encuentra registrada");
                return;
            }else if(!man.containCharacterSpecial(translate)){
                showErrorTimeline(wordTextField, messageLabel,
                        "Traduccion inválida, no debe tener caracteres especiales.");
                return;
            }else if(!man.containCharacterSpecial(description)){
                showErrorTimeline(wordTextField, messageLabel,
                        "Definicion inválida, no debe tener caracteres especiales.");
                return;
            }

            Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDialog.setTitle("Confirmación");
            confirmDialog.setHeaderText("¿Está seguro de guardar la nueva palabra?");
            confirmDialog.setContentText("Si confirma, los cambios se guardarán en la base de datos.");

            Optional<ButtonType> result = confirmDialog.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                word = man.ConvertFirstToUppercase(word);
                int aux= man.generatePosition(man.ConvertFirstToUppercase(word));
                Word newWord = new Word(id, word, description, translate);
                man.addBinaryTreeWord(aux,newWord);
                MenuOptions mO= new MenuOptions(man);
                mO.mostrarVentana();
                primaryStage.close();
            }
        });

        mainVBox.getStyleClass().add("container");
        mainVBox.setMaxSize(500, 500);

        // Organizar la disposición de elementos en la escena
        BorderPane root = new BorderPane();
        root.setCenter(mainVBox);
        root.getStyleClass().add("Window-background");

        Scene scene = new Scene(root, 1000, 600); // Ajustar el tamaño de la ventana según tus necesidades

        primaryStage.setOnCloseRequest(event -> {
            MenuOptions mn= new MenuOptions(man);
            mn.mostrarVentana();
        });

        scene.getStylesheets().add(new File("demo/src/main/styles/modifyWord.css").toURI().toString());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showErrorTimeline(TextField textField, Label errorLabel, String mensaje) {
        errorLabel.setTextFill(javafx.scene.paint.Color.RED);
        errorLabel.setText(mensaje);

        Duration duration = Duration.seconds(3);
        KeyFrame keyFrame = new KeyFrame(duration, event -> errorLabel.setText(""));
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(1);
        timeline.play();
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
