package Gui;

import Service.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Pair;

public class FrameController extends Application {

    /**
     * Initializes the controller class.
     */
    @FXML
    Canvas canvas ;
    public static GraphicsContext gc ;
    @FXML 
    ToggleButton addQueue=new ToggleButton();
    @FXML 
    Button addTOQueue=new Button();
    @FXML 
    ToggleButton addMachine=new ToggleButton();
    @FXML 
    Button ConnectLine=new Button();
    @FXML
    Button start=new Button();
    @FXML    
    ColorPicker colours=new ColorPicker();
    @FXML 
    ChoiceBox<String> from=new ChoiceBox<>();
    @FXML 
    ChoiceBox<String> to=new ChoiceBox<>();
    @FXML 
    TextField queueNUM=new TextField();
    @FXML 
    TextField machineName=new TextField(); 
    static Controller controller=new Controller();
    
    @FXML
    Button s=new Button();
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent page=FXMLLoader.load(getClass().getResource("Frame.fxml"));
        for(Node node : page.getChildrenUnmodifiable()){
            if(node.getClass()==BorderPane.class){
                this.canvas = (Canvas)(((BorderPane)node).getCenter());
                gc = this.canvas.getGraphicsContext2D();
                gc.setLineWidth(1);
            }
        }
        primaryStage.setTitle("Simulation program");
        primaryStage.setScene(new Scene(page));
        primaryStage.show(); 
    }
    
   
    
    public void AddQueueAction(){
        String order="Q"+queueNUM.getText();
        if("Q0".equals(order)){
            from.getItems().add(order);
        }else{
            from.getItems().add(order);
            to.getItems().add(order);
        }
        controller.addQueue(order);
    }
    public void AddQueueColour(){
        Color colour=colours.getValue();
        controller.fillqueueZero(colour);
    }
    public void AddMachine(){
        String machine="M"+machineName.getText();
        from.getItems().add(machine);
        to.getItems().add(machine);
    }
    public void AddLineAction(){
        String fromText=from.getValue();
        String toText=to.getValue();
        if(fromText.charAt(0)=='M'&&toText.charAt(0)=='Q'){
            from.getItems().remove(fromText);
        }
        drawLine(new Pair(fromText,toText));
        controller.setConnection(fromText, toText);        
    }
    public void startAction(){
        controller.startTreads();
    }
    
    public void canvasPressed(MouseEvent e){
        if(addQueue.isSelected()){
            addQueue.setSelected(false);
            if(queueNUM.getText().isEmpty()){
                AlertBox.display("Enter the Queue name");
                return;
            }
            Rectangle rec = shapes.getRectangle(e.getX(), e.getY());
            gc.setFill(Color.CADETBLUE);
            gc.fillRect(e.getX(), e.getY(), rec.getWidth(), rec.getHeight());
            gc.strokeRect(e.getX(), e.getY(), rec.getWidth(), rec.getHeight());
            controller.addRectangle("Q"+queueNUM.getText(), rec);
            
        }else if(addMachine.isSelected()){
            addMachine.setSelected(false);
            if(machineName.getText().isEmpty()){
                AlertBox.display("Enter the Machine name");
                return;
            }
            Circle c = shapes.getCircle(e.getX(), e.getY());
            gc.setFill(Color.WHITE);
            gc.fillOval(c.getCenterX(), c.getCenterY(), c.getRadius(), c.getRadius());
            gc.strokeOval(c.getCenterX(), c.getCenterY(),c.getRadius(), c.getRadius());
            controller.addCircle("M"+machineName.getText(), c);
        }else{
            AlertBox.display("Nothing is selected to be drawn");
        }
    }
    
    public static void drawLine(Pair<String,String> line){
        Pair<Double,Double> start = controller.getPoint(line.getKey());
        Pair<Double,Double> end = controller.getPoint(line.getValue());
        gc.strokeLine(start.getKey(), start.getValue(), end.getKey(), end.getValue());
    }
    
    public static void reDraw(Iterator<Rectangle> rectangles,Hashtable<String,Circle> circles,
                              Hashtable<String,Color> colors ,Set<String> keys){
        gc.clearRect(0, 0, 1080, 790);   
        rectangles.forEachRemaining(rec ->{
            gc.setLineWidth(rec.getStrokeWidth());
            gc.setStroke(rec.getStroke());
            gc.setFill(Color.CADETBLUE);
            gc.fillRect(rec.getX(), rec.getY(), rec.getWidth(), rec.getHeight());
            gc.strokeRect(rec.getX(), rec.getY(), rec.getWidth(), rec.getHeight());
        });
        keys.forEach(key ->{
            Circle c = circles.get(key);
            gc.setLineWidth(c.getStrokeWidth());
            gc.setStroke(c.getStroke());
            gc.setFill(colors.get(key));
            gc.fillOval(c.getCenterX(), c.getCenterY(), c.getRadius(), c.getRadius());
            gc.strokeOval(c.getCenterX(), c.getCenterY(),c.getRadius(), c.getRadius());
        });
    } 
}
