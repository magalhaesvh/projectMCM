/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmcm;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projectmcm.controller.FXMLLoginPrincipalController;

public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/FXMLLoginPrincipal.fxml"));     
        Parent root = (Parent)fxmlLoader.load();        
        Scene scene = new Scene(root);
        FXMLLoginPrincipalController controller = fxmlLoader.<FXMLLoginPrincipalController>getController();       
        stage.setScene(scene);
        stage.setTitle("Aluguel de carros - Project MCM");
        stage.setResizable(false);
        controller.setStage(stage);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }    
}
