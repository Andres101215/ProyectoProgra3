module jfx.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
                            
    opens jfx.demo to javafx.fxml;
    exports jfx.demo.Presentation;
}