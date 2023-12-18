package jfx.demo.Presentation;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import Logic.Word;
import  Controller.Management;


public class Start extends Application {

    private TableView<Word> tabla = new TableView<>();
    Management control= new Management();
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Diccionario");

        // Crear botones para cada opción
        Button btnAñadir = new Button("Añadir");
        Button btnBuscar = new Button("Buscar");
        Button btnModificar = new Button("Modificar");
        Button btnEliminar = new Button("Eliminar");
        Button btnListarPorLetra = new Button("Listar por Letra");
        Button btnListarTodas = new Button("Listar Todas");

        // Asignar eventos a los botones
        btnAñadir.setOnAction(e -> mostrarComponente("Añadir"));
        btnBuscar.setOnAction(e -> mostrarComponente("Buscar"));
        btnModificar.setOnAction(e -> mostrarComponente("Modificar"));
        btnEliminar.setOnAction(e -> mostrarComponente("Eliminar"));
        btnListarPorLetra.setOnAction(e -> mostrarComponente("Listar por Letra"));
        btnListarTodas.setOnAction(e -> mostrarComponente("Listar Todas"));


        double buttonWidth = 120.0;
        btnAñadir.setPrefWidth(buttonWidth);
        btnBuscar.setPrefWidth(buttonWidth);
        btnModificar.setPrefWidth(buttonWidth);
        btnEliminar.setPrefWidth(buttonWidth);
        btnListarPorLetra.setPrefWidth(buttonWidth);
        btnListarTodas.setPrefWidth(buttonWidth);

        // Establecer la altura mínima para todos los botones (puedes usar prefHeight si prefieres)
        double buttonHeight = 20.0; // Puedes ajustar el valor según tus necesidades
        btnAñadir.setMinHeight(buttonHeight);
        btnBuscar.setMinHeight(buttonHeight);
        btnModificar.setMinHeight(buttonHeight);
        btnEliminar.setMinHeight(buttonHeight);
        btnListarPorLetra.setMinHeight(buttonHeight);
        btnListarTodas.setMinHeight(buttonHeight);

        // Crear tabla

        ObservableList<Word> word = FXCollections.observableArrayList(control.returnlist());

        // Crear una tabla y columnas
        TableColumn<Word, String> IdColumn = new TableColumn<>("Id");
        TableColumn<Word, String> facultyColumn = new TableColumn<>("Word");
        TableColumn<Word, String> nombreGrupoColumn = new TableColumn<>("Definition");
        TableColumn<Word, String> siglaColumn = new TableColumn<>("Translate");

        IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        facultyColumn.setCellValueFactory(new PropertyValueFactory<>("word"));
        nombreGrupoColumn.setCellValueFactory(new PropertyValueFactory<>("definition"));
        siglaColumn.setCellValueFactory(new PropertyValueFactory<>("translate"));


        tabla.getColumns().addAll(IdColumn, facultyColumn, nombreGrupoColumn, siglaColumn);

        tabla.setItems(word);

        // Agregar botones al VBox
        VBox botonesContainer = new VBox(15);
        botonesContainer.setStyle("-fx-background-color: lightblue;"); // Color de fondo para la primera columna
        botonesContainer.getChildren().addAll(btnAñadir, btnBuscar, btnModificar, btnEliminar, btnListarPorLetra, btnListarTodas);


        VBox tablaContainer = new VBox();
        tablaContainer.setStyle("-fx-background-color: lightgreen;"); // Color de fondo para la segunda columna
        tablaContainer.getChildren().addAll(tabla);


        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(120);

        gridPane.add(botonesContainer, 0, 0);
        gridPane.add(tablaContainer, 1, 0);

        Scene scene = new Scene(gridPane, 700, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void mostrarComponente(String opcion) {
        // Aquí puedes implementar la lógica para mostrar los componentes según la opción seleccionada
        System.out.println("Seleccionaste: " + opcion);
    }


}


