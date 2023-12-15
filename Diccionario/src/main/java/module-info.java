module com.example.diccionario {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.diccionario to javafx.fxml;
    exports com.example.diccionario;
}