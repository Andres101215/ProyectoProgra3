package jfx.demo.Presentation;

import Controller.Management;
import Logic.Word;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;

public class LetterTable extends Application {
    private TableView<Word> tabla = new TableView<>();
    private Management man;
    private String letra;

    public LetterTable(Management man, String letra) {
        this.man = man;
        this.letra = letra;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Letras por " + letra + " :");
        Image iconImage = new Image("file:" + "demo/src/prograIconos/libro.png");
        primaryStage.getIcons().add(iconImage);

        ObservableList<Word> tableWords = FXCollections.observableArrayList(man.returnlistByletter(man.generatePosition(letra)));

        TableColumn<Word, Integer> IdColumn = new TableColumn<>("Id");
        TableColumn<Word, String> wordColumn = new TableColumn<>("Palabra");
        TableColumn<Word, String> definitionColumn = new TableColumn<>("Definicion");
        TableColumn<Word, String> translateColumn = new TableColumn<>("Traduccion");

        IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        wordColumn.setCellValueFactory(new PropertyValueFactory<>("word"));
        definitionColumn.setCellValueFactory(new PropertyValueFactory<>("definition"));
        translateColumn.setCellValueFactory(new PropertyValueFactory<>("translate"));

        tabla.getColumns().addAll(IdColumn, wordColumn, definitionColumn, translateColumn);
        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabla.setItems(tableWords);

        // Agregar el TableView al centro del BorderPane
        BorderPane root = new BorderPane();
        root.setCenter(tabla);

        ImageView iconoVolver = new ImageView(new Image("file:" + "demo/src/prograIconos/atras.png"));
        iconoVolver.setFitWidth(22);
        iconoVolver.setFitHeight(22);

        Button botonFlotante = new Button();
        botonFlotante.getStyleClass().add("boton-flotante");
        botonFlotante.setGraphic(iconoVolver);
        StackPane.setMargin(botonFlotante, new Insets(0, 10, 10, 0));

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(tabla, botonFlotante);
        StackPane.setAlignment(botonFlotante, Pos.BOTTOM_RIGHT);
        root.setCenter(stackPane);

        botonFlotante.setOnAction(event -> {

            MenuOptions mn= new MenuOptions(man);
            mn.mostrarVentana();
            primaryStage.close();

        });


        Scene scene = new Scene(root, 700, 600);
        scene.getStylesheets().add(new File("demo/src/main/styles/tabla.css").toURI().toString());
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
