package Gui;

import Service.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
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
    ChoiceBox<String> colours=new ChoiceBox<>();
    @FXML 
    ChoiceBox<String> from=new ChoiceBox<>();
    @FXML 
    ChoiceBox<String> to=new ChoiceBox<>();
    @FXML 
    TextField queueNUM=new TextField();
    @FXML 
    TextField machineName=new TextField(); 
    Controller controller=new Controller();
    
    //shapes
    List<Circle> circles = new ArrayList<>();
    List<Rectangle> squares = new ArrayList<>();
    
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
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colours.getItems().addAll("red","Yellow","Blue","Orange");
        colours.getSelectionModel().select(0);
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
        String colour=colours.getValue();
        controller.fillqueueZero(colour);
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
        controller.setConnection(fromText, toText);        
    }
    public void startAction(){
        controller.startTreads();
    }
    public void canvasPressed(MouseEvent e){
        if(addQueue.isSelected()){
            addQueue.setSelected(false);
           //System.out.println(e.getX());
           //System.out.println(e.getY());
            squares.add(new Rectangle());
            gc.setFill(Color.CADETBLUE);
            squares.get(squares.size()-1).setX(e.getX());
            squares.get(squares.size()-1).setY(e.getY());
            squares.get(squares.size()-1).setWidth(100);
            squares.get(squares.size()-1).setHeight(100);
            gc.fillRect(squares.get(squares.size()-1).getX(), squares.get(squares.size()-1).getY(),
                    squares.get(squares.size()-1).getWidth(), squares.get(squares.size()-1).getHeight());
            gc.strokeRect(squares.get(squares.size()-1).getX(), squares.get(squares.size()-1).getY(),
                    squares.get(squares.size()-1).getWidth(), squares.get(squares.size()-1).getHeight());
        }else if(addMachine.isSelected()){
            addMachine.setSelected(false);
            circles.add(new Circle());
            //circles.get(circles.size()-1).setFill(Paint.valueOf(White));
            gc.setFill(Color.WHITE);
            circles.get(circles.size()-1).setCenterX(e.getX()-50);
            circles.get(circles.size()-1).setCenterY(e.getY()-50);
            circles.get(circles.size()-1).setRadius(100);
            gc.fillOval(circles.get(circles.size()-1).getCenterX(), circles.get(circles.size()-1).getCenterY(),
                    circles.get(circles.size()-1).getRadius(), circles.get(circles.size()-1).getRadius());
            gc.strokeOval(circles.get(circles.size()-1).getCenterX(), circles.get(circles.size()-1).getCenterY(),
                    circles.get(circles.size()-1).getRadius(), circles.get(circles.size()-1).getRadius());
        }else{
            System.out.println("no");
        }
    }
    
//    public void reDraw(){
//         this.gc.clearRect(0, 0, 1080, 790);   
//        for(Rectangle r:squares){
//            this.gc.setLineWidth(r.getStrokeWidth());
//            this.gc.setStroke(r.getStroke());
//            this.gc.setFill(Color);
//            this.gc.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
//            this.gc.strokeRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
//        }
//    }   
}
