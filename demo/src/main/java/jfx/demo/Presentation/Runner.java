package jfx.demo.Presentation;

import Controller.Management;
import javafx.application.Application;
import javafx.stage.Stage;


public class Runner extends Application {

    Management man = new Management();
    Portada main= new Portada(man);

    public static void main(String[] args) {

        launch(args);
    }
    public void start(Stage primaryStage) throws Exception{
            main.start(primaryStage);
        }

}
