module jfx.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
                            
    opens jfx.demo.Presentation to javafx.fxml;
    exports jfx.demo.Presentation;
    opens Logic to javafx.base;
}