/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    
    public static void display(String message){
        Stage window=new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Message");
        window.isAlwaysOnTop();
        window.show();
        //button
        Button button=new Button();
        button.setText("close");
        button.setOnAction(e->window.close());
        //message
        Label label1=new Label();
        label1.setText(message);
        //layout
        VBox vbox=new VBox(30);
        vbox.getChildren().addAll(label1,button);
        vbox.setAlignment(Pos.CENTER);
        //scene
        Scene s1=new Scene(vbox,400,200);
        window.setScene(s1);
        window.centerOnScreen();
        window.show();
        
        
    }
}