
package Gui;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author Ziad Elabd
 */
public class shapes {
    
    public static Rectangle getRectangle(double xClick,double yClick){
        Rectangle rec = new Rectangle();
        rec.setX(xClick);
        rec.setY(yClick);
        rec.setWidth(75);
        rec.setHeight(30);
       return rec;
    }
    
    public static Circle getCircle(double xClick, double yClick){
        Circle c = new Circle();
            c.setCenterX(xClick-25);
            c.setCenterY(yClick-25);
            c.setRadius(50);
            return c;
    }
}
