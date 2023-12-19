package jfx.demo.Presentation;

import Controller.Management;
import Logic.Word;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddOptions extends Application {

    private TableView<Word> tablaBuscado = new TableView<>();
    private Management man;

    public AddOptions(Management man) {
        this.man = man;
    }

    private static final int BUTTONS_PER_ROW = 3;

        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) throws Exception {

            Image iconImage = new Image("file:" + "demo/src/prograIconos/libro.png");
            primaryStage.getIcons().add(iconImage);
            primaryStage.setTitle("Buscar Palabras\n\n");
            primaryStage.setWidth(500);
            primaryStage.setHeight(300);

            Label palabra = new Label("Buscar palabra: \n\n");
            palabra.setStyle("-fx-font-weight: bold;");
            TextField palabraTextField = new TextField();

            Button buscarButton = new Button("Buscar");

            buscarButton.setOnAction(event -> {
                String palabraBuscada = palabraTextField.getText();


            });

            HBox busquedaBox = new HBox(10, palabraTextField, buscarButton);
            busquedaBox.setAlignment(Pos.CENTER);

            Label mensajeLabel = new Label("Seleccione una letra \n\n");
            mensajeLabel.setStyle("-fx-font-weight: bold;");
            Label o = new Label("Para ver las palabras correspondientes:\n\n\n");

            Label yo= new Label("ó\n");

            // Botones para cada letra del abecedario
            HBox letrasButtons = new HBox(5);
            letrasButtons.setAlignment(Pos.CENTER);  // Centrar los botones

            VBox rowContainer = new VBox(5);
            rowContainer.setAlignment(Pos.CENTER);

            for (char letra = 'A'; letra <= 'Z'; letra++) {
                Button letraButton = new Button(String.valueOf(letra));
                letraButton.setOnAction(event -> {
                    // Lógica para la letra seleccionada
                    String letraSeleccionada = ((Button) event.getSource()).getText();
                    LetterTable lT = new LetterTable(man, letraSeleccionada);
                    lT.mostrarVentana();
                    primaryStage.close();

                });

                rowContainer.getChildren().add(letraButton);

                // Si hemos alcanzado BUTTONS_PER_ROW, crea una nueva fila
                if (rowContainer.getChildren().size() == BUTTONS_PER_ROW) {
                    letrasButtons.getChildren().add(rowContainer);
                    rowContainer = new VBox(5);
                    rowContainer.setAlignment(Pos.CENTER);
                }
            }

            // Asegurarse de agregar la última fila si no está completa
            if (!rowContainer.getChildren().isEmpty()) {
                letrasButtons.getChildren().add(rowContainer);
            }


            // VBox principal que contiene todos los elementos
            VBox root = new VBox(10);
            root.setPadding(new Insets(10));
            root.setAlignment(Pos.CENTER);  // Centrar el VBox principal
            root.getChildren().addAll(palabra, busquedaBox, yo, mensajeLabel, o, letrasButtons);

            Scene scene = new Scene(root);

            primaryStage.setOnCloseRequest(event -> {
                MenuOptions mn= new MenuOptions(man);
                mn.mostrarVentana();
            });


            primaryStage.setScene(scene);
            primaryStage.show();


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
