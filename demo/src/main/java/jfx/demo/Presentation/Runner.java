package jfx.demo.Presentation;

import javafx.application.Application;
import javafx.stage.Stage;


public class Runner extends Application {


    Start main= new Start();

    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage primaryStage) throws Exception{
            main.start(new Stage());
        }

}
