package jfx.demo.Presentation;

import java.io.File;
import java.util.Optional;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import Controller.Management;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import Logic.Word;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class modifyWord extends Application {
    private String word = "";
    private String description = "";
    private String translate = "";
    private int id=0;
    private Management man;
    private Label mensajeLabel = new Label();
    private Label messageLabel = new Label();

    private Word words;
    private int pos = 0;

    public modifyWord(int pos, Word word, Management man) {
        this.pos = pos;
        this.man = man;
        this.words = word;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Modificar Palabra");
        // Configurar el ícono de la ventana
        Image iconImage = new Image("file:" + "demo/src/prograIconos/libro.png");
        primaryStage.getIcons().add(iconImage);

        VBox mainVBox = new VBox(15); // Espacio entre elementos
        mainVBox.setPadding(new Insets(15)); // Margen exterior

        Label IDLabel = new Label("Id*");
        Label wordLabel = new Label("Palabra*");
        Label definitionLabel = new Label("Definicion*  ");
        Label translateLabel = new Label("Traduccion*");

        // Crear los campos de entrada de datos

        TextField IdTextField = new TextField();
        IdTextField.setPromptText("Id:");
        IdTextField.setText(String.valueOf(words.getId()));
        IdTextField.setEditable(false);

        TextField wordTextField = new TextField();
        wordTextField.setPromptText("Palabra:");
        wordTextField.setText(words.getWord());

        TextField definitionTextField = new TextField();
        definitionTextField.setPromptText("Definicion:");
        definitionTextField.setText(words.getDefinition());

        TextField translateTextField = new TextField();
        translateTextField.setPromptText("Traduccion:");
        translateTextField.setText(words.getTranslate());

        Button enviarButton = new Button("Enviar");

        HBox centerButtonHBox = new HBox();
        centerButtonHBox.setAlignment(Pos.CENTER);
        centerButtonHBox.getChildren().add(enviarButton);
        enviarButton.getStyleClass().add("button-enviar");

        HBox messageLabelHBox = new HBox(messageLabel);
        messageLabelHBox.setAlignment(Pos.CENTER);

        mainVBox.getChildren().addAll(
                new HBox(IDLabel, IdTextField),
                new HBox(wordLabel, wordTextField),
                new HBox(definitionLabel, definitionTextField), // Agrupa etiqueta y TextField
                new HBox(translateLabel, translateTextField),
                mensajeLabel, messageLabelHBox,
                centerButtonHBox);

        HBox.setMargin(IDLabel, new Insets(0, 20, 0, 0)); // Margen derecho de 10 píxeles
        HBox.setMargin(wordLabel, new Insets(0, 20, 0, 0)); // Margen derecho de 10 píxeles
        HBox.setMargin(definitionLabel, new Insets(0, 30, 0, 0));
        HBox.setMargin(translateLabel, new Insets(0, 20, 0, 0));

        HBox.setHgrow(IdTextField, Priority.ALWAYS);
        HBox.setHgrow(wordTextField, Priority.ALWAYS);
        HBox.setHgrow(definitionTextField, Priority.ALWAYS);
        HBox.setHgrow(translateTextField, Priority.ALWAYS);

        enviarButton.setOnAction(e -> {
            id=Integer.parseInt(IdTextField.getText());
            word = wordTextField.getText();
            description = definitionTextField.getText();
            translate = translateTextField.getText();

            if (word.isBlank() || description.isBlank() || translate.isBlank()) {
                showErrorTimeline(wordTextField, messageLabel,
                        "Debe ingresar todos los datos ");
                return;
            } else if (!man.containCharacterSpecial(word)) {
                showErrorTimeline(wordTextField, messageLabel,
                        "Palabra invalida, no debe tener caracteres especiales.");
                return;
            } else if (word.isBlank()) {
                showErrorTimeline(wordTextField, messageLabel,
                        "Debe ingresar la palabra.");
                return;

            } else if (translate.isBlank()) {
                showErrorTimeline(translateTextField, messageLabel,
                        "Debe ingresar la traduccion");
                return;
            } else if (description.isBlank()) {
                showErrorTimeline(definitionTextField, messageLabel,
                        "Debe ingresar la definicion");
                return;
            }else if(man.validateWord(word) && !words.getWord().equalsIgnoreCase(word)){
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
            // Crear un diálogo de confirmación
            Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmDialog.setTitle("Confirmation");
            confirmDialog.setHeaderText("Are you sure to save the changes?");
            confirmDialog.setContentText("If you confirm, the changes will be saved to the database.");

            Optional<ButtonType> result = confirmDialog.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                int aux= man.generatePosition(man.ConvertFirstToUppercase(word));
                Word wordaux=new Word(man.generateAscciCode(word),man.ConvertFirstToUppercase(word),description,translate);
                man.deleteBinaryTreeWord(man.generatePosition(words.getWord()), words.getId());
                man.addBinaryTreeWord(aux,wordaux);
                IdTextField.clear();
                wordTextField.clear();
                definitionTextField.clear();
                translateTextField.clear();

                MenuOptions testTabla = new MenuOptions(man);
                testTabla.start(new Stage());
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

    private void mostrarError(TextField textField, String mensaje) {
        textField.getStyleClass().add("error-field");
        messageLabel.setTextFill(javafx.scene.paint.Color.RED);
        messageLabel.setText(mensaje);

    }

    public void showErrorTimeline(TextField textField, Label errorLabel, String mensaje) {
        errorLabel.setTextFill(javafx.scene.paint.Color.RED);
        errorLabel.setText(mensaje);

        Duration duration = Duration.seconds(3);
        KeyFrame keyFrame = new KeyFrame(duration, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                errorLabel.setText("");
            }
        });
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(1);
        timeline.play();
    }
}
