
package Gui;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


/**
 *
 * @author Ziad Elabd
 */
public class test extends Application{
    ToggleButton rectbtn = new ToggleButton("Rectange");
    ToggleButton circlebtn = new ToggleButton("Circle");
    ToggleButton[] toolsArr = { rectbtn, circlebtn};
    ToggleGroup tools = new ToggleGroup();
    VBox btns = new VBox(10);
    Canvas canvas = new Canvas(1080, 790);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    List<Circle> circles = new ArrayList<>();
    List<Rectangle> squares = new ArrayList<>();
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        for (ToggleButton tool : toolsArr) {
            tool.setMinWidth(90);
            tool.setToggleGroup(tools);
            tool.setCursor(Cursor.HAND);
        }
        btns.getChildren().addAll( rectbtn, circlebtn);
        btns.setPadding(new Insets(5));
        btns.setStyle("-fx-background-color: #999");
        btns.setPrefWidth(100);
        
        gc.setLineWidth(1);
        canvas.setOnMousePressed(this::canvasPressed);
        btns.setOnMousePressed(this::btnsPressed);
        BorderPane pane = new BorderPane();
        pane.setLeft(btns);
        pane.setCenter(canvas);
    
        Scene scene = new Scene(pane, 1200, 800);
        
        primaryStage.setTitle("Paint");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    public void canvasPressed(MouseEvent e){
        if(rectbtn.isSelected()){
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
        if(rectbtn.isSelected()){
            gc.clearRect(0, 0, 1080, 790);
            
            for(Rectangle r:squares){
                gc.setLineWidth(r.getStrokeWidth());
                gc.setStroke(r.getStroke());
                gc.setFill(Color.BLUE);
                gc.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
                gc.strokeRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
            }
        }else{System.out.println("no");}
    }
    
}
