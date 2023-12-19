package jfx.demo.Presentation;

import java.io.File;
import java.util.Optional;

import Controller.Management;
import Logic.Word;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuOptions extends Application {
    private TableView<Word> tabla = new TableView<Word>();

    private Management man;


    public MenuOptions(Management man) {
        this.man = man;
    }

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();

        primaryStage.setTitle("Diccionario");
        // Configurar el ícono de la ventana
        Image iconImage = new Image("file:" + "demo/src/prograIconos/libro.png");

        primaryStage.getIcons().add(iconImage);

        ObservableList<Word> tableWords = FXCollections.observableArrayList(man.returnlist());

        // Crear una tabla y columnas
        TableColumn<Word, Integer> IdColumn = new TableColumn<>("Id");
        TableColumn<Word, String> wordColumn = new TableColumn<>("Palabra");
        TableColumn<Word, String> definitionColumn = new TableColumn<>("Definicion");
        TableColumn<Word, String> translateColumn = new TableColumn<>("Traduccion");


        IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        wordColumn.setCellValueFactory(new PropertyValueFactory<>("word"));
        definitionColumn.setCellValueFactory(new PropertyValueFactory<>("definition"));
        translateColumn.setCellValueFactory(new PropertyValueFactory<>("translate"));


        tabla.getColumns().addAll(IdColumn, wordColumn, definitionColumn, translateColumn);

        // Agregar columna de botones

        TableColumn<Word, Void> accionesColumna = new TableColumn<>("Actions");
        accionesColumna.setCellFactory(param -> new BotonCelda());
        tabla.getColumns().add(accionesColumna);

        tabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        tabla.setItems(tableWords);
        // Crear un botón flotante

        ImageView iconoAgregar = new ImageView(new Image("file:" + "demo/src/prograIconos/anadir.png"));
        ImageView iconobuscar = new ImageView(new Image("file:" + "demo/src/prograIconos/lupa.png"));
        iconoAgregar.setFitWidth(32);
        iconoAgregar.setFitHeight(32);
        iconobuscar.setFitWidth(22);
        iconobuscar.setFitHeight(22);
        Button botonFlotante = new Button();
        Button botonFlotante2 = new Button();
        botonFlotante.getStyleClass().add("boton-flotante");
        botonFlotante.setGraphic(iconoAgregar);
        botonFlotante2.getStyleClass().add("boton-flotante");
        botonFlotante2.setGraphic(iconobuscar);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(tabla, botonFlotante, botonFlotante2);
        StackPane.setAlignment(botonFlotante, Pos.BOTTOM_RIGHT);
        StackPane.setAlignment(botonFlotante2, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(botonFlotante, new Insets(0, 10, 58, 0));
        StackPane.setMargin(botonFlotante2, new Insets(0, 10, 10, 0));
        root.setCenter(stackPane); // Agregar la StackPane al centro del BorderPane

        botonFlotante.setOnAction(event -> {

            AddWord aW= new AddWord(man);
            aW.mostrarVentana();
            primaryStage.close();


        });


        botonFlotante2.setOnAction(event -> {
            AddOptions addOptions = new AddOptions(man);
            addOptions.mostrarVentana();
            primaryStage.close();


        });

        // Crear la escena y mostrarla
        Scene scene = new Scene(root, 900, 600); // Usar el BorderPane como nodo raíz

        primaryStage.setOnCloseRequest(event -> {
            Portada pt= new Portada(man);
            pt.mostrarVentana();
        });


        scene.getStylesheets().add(new File("demo/src/main/styles/tabla.css").toURI().toString());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public class BotonCelda extends TableCell<Word, Void> {
        private final Button btnEliminar = new Button();
        private final Button btnModificar = new Button();

        public BotonCelda() {
            // Configura los íconos para los botones

            ImageView iconoEliminar = new ImageView(new Image("file:" + "demo/src/prograIconos/eliminar.png"));
            ImageView iconoModificar = new ImageView(new Image("file:" + "demo/src/prograIconos/editarB.png"));

            iconoEliminar.setFitWidth(16); // Ancho deseado
            iconoEliminar.setFitHeight(16); // Altura deseada

            iconoModificar.setFitWidth(16); // Ancho deseado
            iconoModificar.setFitHeight(16); // Altura deseada

            btnEliminar.setGraphic(iconoEliminar);
            btnModificar.setGraphic(iconoModificar);

            btnEliminar.setGraphic(iconoEliminar);
            btnModificar.setGraphic(iconoModificar);

            // Establece el estilo de los botones

            btnEliminar.getStyleClass().add("boton-eliminar");
            btnModificar.getStyleClass().add("boton-modificar");

            btnEliminar.setOnAction(event -> {
                Word word = getTableView().getItems().get(getIndex());
                // Mostrar una ventana emergente de confirmación
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmar Eliminacion");
                alert.setHeaderText(null);
                alert.setContentText("Esta seguro que desea eliminar esta palabra?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    man.deleteBinaryTreeWord(man.generatePosition(man.ConvertFirstToUppercase(word.getWord())), word.getId());
                    // Actualiza la tabla después de eliminar el grupo
                    tabla.getItems().remove(word);
                    alert.setContentText("Se elimino correctamente");
                }
            });

            btnModificar.setOnAction(event -> {
                Word word = getTableView().getItems().get(getIndex());
                modifyWord testTabla = new modifyWord(man.generatePosition(word.getWord()), word, man);

                Stage testTablaStage = new Stage();
                testTabla.start(testTablaStage);
                Stage stage = (Stage) btnModificar.getScene().getWindow();
                stage.close();

            });

            // Configura el contenido de las celdas para mostrar los botones
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                HBox botonesContainer = new HBox(btnEliminar, btnModificar);
                botonesContainer.setSpacing(5);
                setGraphic(botonesContainer);
            }
        }
    }

    public void addButton(Management man, Word newWord){

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


