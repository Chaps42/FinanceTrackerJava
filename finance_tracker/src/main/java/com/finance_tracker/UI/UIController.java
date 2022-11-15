package com.finance_tracker.UI;

import java.io.IOException;

import javafx.application.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class UIController extends Application{
    TabPageController TabWindow = new TabPageController();
    private static Scene scene;
    private Stage Mystage;


    public UIController(){
        try{this.start(Mystage);}
        catch(IOException E){
        }
    }

    public void start(Stage stage) throws IOException{
        
        System.out.println("UIController Created");
        scene = new Scene(loadFXML("UI"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UIController.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();



    }}
