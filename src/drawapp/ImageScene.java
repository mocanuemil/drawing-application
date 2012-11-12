package drawapp;

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcBuilder;
import javafx.scene.shape.EllipseBuilder;
import javafx.scene.shape.LineBuilder;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.TextBuilder;

/**
 *
 * @author geo
 */
public class ImageScene {
    private Scene scene;
    private Group group;
    private Color colour;
    private ArrayList<Group> elements;
    
    public ImageScene(Scene scene){
        this.scene= scene;
        this.group= new Group();
        this.colour=Color.BLACK;
        elements = new ArrayList<>();
    }
    
    public Scene getScene(){
        return scene;
    }
    
    public Group getGroup(){
        return group;
    }
    
    public ArrayList<Group> getElements(){
        return elements;
    }
    
    public void setBackgroundColour(Color colour){
        scene.setFill(colour);
    }
    
    public void setColour(Color colour){
        this.colour=colour;
    }
    
    public void drawLine(int x1, int y1, int x2, int y2){
        Node line= LineBuilder.create()
                .stroke(colour)
                .fill(Color.TRANSPARENT)
                .startX(x1)
                .startY(y1)
                .endX(x2)
                .endY(y2)
                .build();
         Group save = new Group();
         save.getChildren().add(line);
         elements.add(save);
         group.getChildren().add(line);
    }
    
     public void drawRect(int x1, int y1, int x2, int y2){
         Node rectangle= RectangleBuilder.create()
                 .stroke(colour)
                 .fill(Color.TRANSPARENT)
                 .x(x1)
                 .y(y1)
                 .width(x2)
                 .height(y2)
                 .build();
         Group save = new Group();
         save.getChildren().add(rectangle);
         elements.add(save);
         group.getChildren().add(rectangle);
     }
     
     public void fillRect(int x1, int y1, int x2, int y2){
         Node rectangle= RectangleBuilder.create()
                 .stroke(colour)
                 .fill(colour)
                 .x(x1)
                 .y(y1)
                 .width(x2)
                 .height(y2)
                 .build();
         Group save = new Group();
         save.getChildren().add(rectangle);
         elements.add(save);
         group.getChildren().add(rectangle);
     }
     
     public void drawString(int x,int y, String s){
         Node text= TextBuilder.create()
                 .focusTraversable(true)
                 .font(new Font(16))
                 .x(x)
                 .y(y)
                 .stroke(colour)
                 .text(s)
                 .build();
         Group save = new Group();
         save.getChildren().add(text);
         elements.add(save);
         group.getChildren().add(text);
     }
     
     public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle){
         Node arc= ArcBuilder.create()
                 .stroke(colour)
                 .fill(Color.TRANSPARENT)
                 .centerX(x)
                 .centerY(y)
                 .startAngle(startAngle)
                 .radiusX(width)
                 .radiusY(height)
                 .length(arcAngle)
                 .build();
         Group save = new Group();
         save.getChildren().add(arc);
         elements.add(save);
         group.getChildren().add(arc);
     }
     
     public void drawOval(int x, int y, int width, int height){
         Node ellipse= EllipseBuilder.create()
                 .stroke(colour)
                 .centerX(x)
                 .centerY(y)
                 .radiusX(width)
                 .radiusY(height)
                 .build();
         Group save = new Group();
         save.getChildren().add(ellipse);
         elements.add(save);
         group.getChildren().add(ellipse);
     }
    
}
