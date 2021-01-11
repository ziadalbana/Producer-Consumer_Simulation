/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;


import Service.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class FrameController extends Application implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML 
    Button addQueue=new Button();
    @FXML 
    Button addTOQueue=new Button();
    @FXML 
    Button addMachine=new Button();
    @FXML 
    Button ConnectLine=new Button();
    @FXML 
    ChoiceBox<String> colours=new ChoiceBox<>();
    @FXML 
    ChoiceBox<String> from=new ChoiceBox<>();
    @FXML 
    ChoiceBox<String> to=new ChoiceBox<>();
    @FXML 
    TextField queueNUM=new TextField(); 
    
    
    @FXML
    Canvas canvas = new Canvas();
    GraphicsContext gc = canvas.getGraphicsContext2D();
    List<Circle> circles = new ArrayList<>();
    List<Rectangle> squares = new ArrayList<>();
    
    @FXML
    ToggleButton btn = new ToggleButton("Draw");
    @FXML 
    Button bs=new Button();
    
    @FXML 
    TextField machineName=new TextField(); 
    Strucuture data=new Strucuture();
      @Override
     public void start(Stage primaryStage) throws Exception {
        Parent page=FXMLLoader.load(getClass().getResource("Frame.fxml"));
        primaryStage.setTitle("Simulation program");
        primaryStage.setScene(new Scene(page));
        gc.setLineWidth(1);
        primaryStage.show();
        
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colours.getItems().addAll("red","Yellow","Blue","Orange");
        colours.getSelectionModel().select(0);
    } 
    public void AddQueueAction(){
        int order=Integer.parseInt(queueNUM.getText());
        if(order==0){
            from.getItems().add("Q0");
        }else{
            from.getItems().add("Q"+order);
            to.getItems().add("Q"+order);
        }
        data.addQueue(order);
    }
    public void AddQueueColour(){
        String colour=colours.getValue();
        data.fillqueueZero(colour);
    }
    public void AddMachine(){
        String machine=machineName.getText();
        from.getItems().add(machine);
        to.getItems().add(machine);
    }
    public void AddLineAction(){
        String fromText=from.getValue();
        String toText=to.getValue();
        if(fromText.toLowerCase().charAt(0)=='m'&&toText.toLowerCase().charAt(0)=='q'){
        from.getItems().remove(fromText);
        }
        data.setConnection(fromText, toText);        
    }
    public void canvasPressed(MouseEvent e){
        if(btn.isSelected()){
            btn.setSelected(false);
            System.out.println(e.getX());
            System.out.println(e.getY());
            squares.add(new Rectangle());
            gc.setFill(Color.BLACK);
            squares.get(squares.size()-1).setX(e.getX());
            squares.get(squares.size()-1).setY(e.getY());
            squares.get(squares.size()-1).setWidth(100);
            squares.get(squares.size()-1).setHeight(100);
            gc.fillRect(squares.get(squares.size()-1).getX(), squares.get(squares.size()-1).getY(),
                    squares.get(squares.size()-1).getWidth(), squares.get(squares.size()-1).getHeight());
            gc.strokeRect(squares.get(squares.size()-1).getX(), squares.get(squares.size()-1).getY(),
                    squares.get(squares.size()-1).getWidth(), squares.get(squares.size()-1).getHeight());
        }else{System.out.println("no");}
    }
    public void btnsPressed(MouseEvent e){
         gc.clearRect(0, 0, 1080, 790);
            
        for(Rectangle r:squares){
            gc.setLineWidth(r.getStrokeWidth());
            gc.setStroke(r.getStroke());
            gc.setFill(Color.BLUE);
            gc.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
            gc.strokeRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
        }
    }
    
    
}
