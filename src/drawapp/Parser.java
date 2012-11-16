package drawapp;

/**
 *
 * @author Mocanu Emil George
 */

import javafx.scene.paint.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcBuilder;
import javafx.scene.shape.EllipseBuilder;
import javafx.scene.shape.LineBuilder;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.TextBuilder;

public class Parser
{
  private ArrayList<String> commands;
  private Scene scene;
  private Group group;
  private Paint paint;
  private TextA textArea;
  private Turtle turtle;


  public Parser(ArrayList<String> commands,Scene scene,Group group,TextA textArea,Turtle turtle)
  {
    this.commands = commands;
    this.scene = scene;
    this.group = group;
    this.textArea = textArea;
    this.paint=Color.BLACK;
    this.turtle=turtle;
    
  }
  
  public Parser(ArrayList<String> commands,Scene scene,Group group,TextA textArea,Turtle turtle,Paint paint){
    this.commands = commands;
    this.scene = scene;
    this.group = group;
    this.textArea = textArea;
    this.paint=paint;
    this.turtle=turtle;
  }

  public void parse()
  {
      int i=0;
    try
    {
      String line=" ";
      while ((line != null) && (i<commands.size()))
      {
        line=commands.get(i);
        if(line!=null)parseLine(line);
        i++;
      }
    }
    
    catch (ParseException e)
    {
      textArea.Text("Parse Exception: " + e.getMessage());
      return;
    }
      textArea.Text("Drawing completed");
      
  }
  
  public Paint getPaint(){
      return paint;
  }
  
  public Group getGroup(){
      return group;
  }
  
  public void parseLineStep(int i){
        try {
            String line=commands.get(i);
            System.out.println(line);
            if((line != null) && i<commands.size()) {
                parseLine(line);
            }
        } 
        catch(ParseException e){
            textArea.Text("Parse Exception: " + e.getMessage());
            return;
        }
      
  }

  private void parseLine(String line) throws ParseException
  {
    if (line.length() < 2) {
          return;
      }
    String command = line.substring(0, 2);
    if (command.equals("DL")) { drawLine(line.substring(2,line.length())); return; }
    if (command.equals("DR")) { drawRect(line.substring(2, line.length())); return; }
    if (command.equals("FR")) { fillRect(line.substring(2, line.length())); return; }
    if (command.equals("SC")) { setColour(line.substring(3, line.length())); return; }
    if (command.equals("DS")) { drawString(line.substring(3, line.length())); return; }
    if (command.equals("DA")) { drawArc(line.substring(2, line.length())); return; }
    if (command.equals("DO")) { drawOval(line.substring(2, line.length())); return; }
    if (command.equals("SB")) { setBackgroundColour(line.substring(3, line.length())); return; }
    if (command.equals("DI")) { drawImage(line.substring(2,line.length())); return; }
    if (command.equals("SI")) { setImagePattern(line.substring(3,line.length())); return; }
    if (command.equals("ST")) { setTurtle(line.substring(2,line.length())); return; }
    if (command.equals("TL")) { turtleLeft(line.substring(2,line.length())); return; }
    if (command.equals("TR")) { turtleRight(line.substring(2,line.length())); return; }
    if (command.equals("TF")) { turtleForward(line.substring(2,line.length())); return; }


    throw new ParseException("Unknown drawing command in :"+line);
  }
  
  private void setBackgroundColour(String colourName) throws ParseException{
    if (colourName.equals("black")) { setBackgroundColour(Color.BLACK); return;}
    if (colourName.equals("blue")) { setBackgroundColour(Color.BLUE); return;}
    if (colourName.equals("cyan")) { setBackgroundColour(Color.CYAN); return;}
    if (colourName.equals("darkgray")) { setBackgroundColour(Color.DARKGRAY); return;}
    if (colourName.equals("gray")) { setBackgroundColour(Color.GRAY); return;}
    if (colourName.equals("green")) { setBackgroundColour(Color.GREEN); return;}
    if (colourName.equals("lightgray")) { setBackgroundColour(Color.LIGHTGRAY); return;}
    if (colourName.equals("magenta")) { setBackgroundColour(Color.MAGENTA); return;}
    if (colourName.equals("orange")) { setBackgroundColour(Color.ORANGE); return;}
    if (colourName.equals("pink")) { setBackgroundColour(Color.PINK); return;}
    if (colourName.equals("red")) { setBackgroundColour(Color.RED); return;}
    if (colourName.equals("white")) { setBackgroundColour(Color.WHITE); return;}
    if (colourName.equals("yellow")) { setBackgroundColour(Color.YELLOW); return;}
    throw new ParseException("Invalid colour name " + colourName);
  }
  
   private void drawLine(String args) throws ParseException
  {
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    x1 = getInteger(tokenizer,args);
    y1 = getInteger(tokenizer,args);
    x2 = getInteger(tokenizer,args);
    y2 = getInteger(tokenizer,args);
    drawLine(x1,y1,x2,y2);
  }

  private void drawImage(String args) throws ParseException
  {
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    x1 = getInteger(tokenizer,args);
    y1 = getInteger(tokenizer,args);
    x2 = getInteger(tokenizer,args);
    y2 = getInteger(tokenizer,args);
    String s="";
    int position = args.indexOf("@");
    if (position == -1) {
          throw new ParseException("Image path string is missing");
      }
    s = args.substring(position+1,args.length());
    drawImage(x1,y1,x2,y2,s);
  }

  private void drawRect(String args) throws ParseException
  {
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    x1 = getInteger(tokenizer,args);
    y1 = getInteger(tokenizer,args);
    x2 = getInteger(tokenizer,args);
    y2 = getInteger(tokenizer,args);
    drawRect(x1, y1, x2, y2);
  }

  private void fillRect(String args) throws ParseException
  {
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    x1 = getInteger(tokenizer,args);
    y1 = getInteger(tokenizer,args);
    x2 = getInteger(tokenizer,args);
    y2 = getInteger(tokenizer,args);
    fillRect(x1, y1, x2, y2);
  }

  private void drawArc(String args) throws ParseException
  {
    int x = 0;
    int y = 0;
    int width = 0;
    int height = 0;
    int startAngle = 0;
    int arcAngle = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    x = getInteger(tokenizer,args);
    y = getInteger(tokenizer,args);
    width = getInteger(tokenizer,args);
    height = getInteger(tokenizer,args);
    startAngle = getInteger(tokenizer,args);
    arcAngle = getInteger(tokenizer,args);
    drawArc(x, y, width, height, startAngle, arcAngle);
  }

  private void drawOval(String args) throws ParseException
  {
    int x1 = 0;
    int y1 = 0;
    int width = 0;
    int height = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    x1 = getInteger(tokenizer,args);
    y1 = getInteger(tokenizer,args);
    width = getInteger(tokenizer,args);
    height = getInteger(tokenizer,args);
    drawOval(x1, y1, width, height);
  }
  
  private void setTurtle(String args) throws ParseException{
      double x = 0;
      double y = 0;
      int angle;
      
      StringTokenizer tokenizer = new StringTokenizer(args);
      x=getInteger(tokenizer,args);
      y=getInteger(tokenizer,args);
      angle=getInteger(tokenizer,args);
      setTurtle(x,y,angle);
    }
  
  private void turtleLeft(String args) throws ParseException{
      int angle;
      
      StringTokenizer tokenizer = new StringTokenizer(args);
      angle=getInteger(tokenizer,args);
      turtleLeft(angle);
    }
  
  private void turtleRight(String args) throws ParseException{
      int angle;
      
      StringTokenizer tokenizer = new StringTokenizer(args);
      angle=getInteger(tokenizer,args);
      turtleRight(angle);
    }
  
   private void turtleForward(String args) throws ParseException{
      int distance;
      
      StringTokenizer tokenizer = new StringTokenizer(args);
      distance=getInteger(tokenizer,args);
      turtleForward(distance);
    }

  private void drawString(String args) throws ParseException
  {
    int x = 0;
    int y = 0 ;
    String s = "";
    StringTokenizer tokenizer = new StringTokenizer(args);
    x = getInteger(tokenizer,args);
    y = getInteger(tokenizer,args);
    int position = args.indexOf("@");
    if (position == -1) {
          throw new ParseException("DrawString string is missing");
      }
    s = args.substring(position+1,args.length());
    drawString(x,y,s);
  }

  private void setColour(String colourName) throws ParseException
  {
    if (colourName.equals("black")) { this.paint=Color.BLACK; return;}
    if (colourName.equals("blue")) { this.paint=Color.BLUE; return;}
    if (colourName.equals("cyan")) { this.paint=Color.CYAN; return;}
    if (colourName.equals("darkgray")) { this.paint=Color.DARKGRAY; return;}
    if (colourName.equals("gray")) { this.paint=Color.GRAY; return;}
    if (colourName.equals("green")) { this.paint=Color.GREEN; return;}
    if (colourName.equals("lightgray")) { this.paint=Color.LIGHTGRAY; return;}
    if (colourName.equals("magenta")) { this.paint=Color.MAGENTA; return;}
    if (colourName.equals("orange")) { this.paint=Color.ORANGE; return;}
    if (colourName.equals("pink")) { this.paint=Color.PINK; return;}
    if (colourName.equals("red")) { this.paint=Color.RED; return;}
    if (colourName.equals("white")) { this.paint=Color.WHITE; return;}
    if (colourName.equals("yellow")) { this.paint=Color.YELLOW; return;}
    throw new ParseException("Invalid colour name");
  }

  private int getInteger(StringTokenizer tokenizer,String line) throws ParseException
  {
    if (tokenizer.hasMoreTokens()) {
          return Integer.parseInt(tokenizer.nextToken());
      }
    else {
          throw new ParseException("Missing Integer value in " + line);
      }
  }
  
   public void setBackgroundColour(Color colour){
        scene.setFill(colour);
    }
    
    public void setColour(Color colour){
        this.paint=colour;
    }
    
    public void drawImage(int x1, int y1, int x2, int y2,String path){
        Image image = new Image(path);
        ImageView img= new ImageView();
        img.setImage(image);
        img.setFitWidth(x2);
        img.setFitHeight(y2);
        img.setTranslateX(x1);
        img.setTranslateY(y1);
        group.getChildren().add(img);
    }
    
     public void setImagePattern(String path){
        Image image = new Image(path);
        paint=new ImagePattern(image);
    }
    
    public void drawLine(int x1, int y1, int x2, int y2){
        Node line= LineBuilder.create()
                .stroke(paint)
                .fill(Color.TRANSPARENT)
                .startX(x1)
                .startY(y1)
                .endX(x2)
                .endY(y2)
                .build();
         group.getChildren().add(line);
    }
    
     public void drawRect(int x1, int y1, int x2, int y2){
         Node rectangle= RectangleBuilder.create()
                 .stroke(paint)
                 .fill(Color.TRANSPARENT)
                 .x(x1)
                 .y(y1)
                 .width(x2)
                 .height(y2)
                 .build();
         group.getChildren().add(rectangle);
     }
     
     public void fillRect(int x1, int y1, int x2, int y2){
         Node rectangle= RectangleBuilder.create()
                 .stroke(paint)
                 .fill(paint)
                 .x(x1)
                 .y(y1)
                 .width(x2)
                 .height(y2)
                 .build();
         group.getChildren().add(rectangle);
     }
     
     public void drawString(int x,int y, String s){
         Node text= TextBuilder.create()
                 .focusTraversable(true)
                 .font(new Font(16))
                 .x(x)
                 .y(y)
                 .stroke(paint)
                 .text(s)
                 .build();
         group.getChildren().add(text);
     }
     
     public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle){
         Node arc= ArcBuilder.create()
                 .stroke(paint)
                 .fill(Color.TRANSPARENT)
                 .centerX(x)
                 .centerY(y)
                 .startAngle(startAngle)
                 .radiusX(width)
                 .radiusY(height)
                 .length(arcAngle)
                 .build();
         group.getChildren().add(arc);
     }
     
     public void drawOval(int x, int y, int width, int height){
         Node ellipse= EllipseBuilder.create()
                 .stroke(paint)
                 .centerX(x)
                 .centerY(y)
                 .radiusX(width)
                 .radiusY(height)
                 .build();
         group.getChildren().add(ellipse);
     }
     
     public void setTurtle(double x, double y, int angle){
         turtle=new Turtle(x,y,angle);
     }
     
     public void turtleLeft(int angle){
         turtle.turnLeft(angle);
     }
     
     public void turtleRight(int angle){
         turtle.turnRight(angle);
     }
      
      public void turtleForward(int dist){
         int x1=(int) turtle.getX();
         int y1=(int) turtle.getY();
         turtle.forward(dist);
         int x2=(int) turtle.getX();
         int y2=(int) turtle.getY();
         drawLine(x1,y1,x2,y2);
     }
    
}
