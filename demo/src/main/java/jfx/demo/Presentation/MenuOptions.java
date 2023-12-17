package jfx.demo.Presentation;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class MenuOptions extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuOptions.class.getResource("MenuOptions.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Menu De Opciones");
        stage.setScene(scene);
        stage.show();;
    }
}
