package drawapp;

import javafx.scene.paint.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.control.TextArea;

public class Parser
{
  private BufferedReader reader;
  private ImageScene image;
 

  public Parser(Reader reader, ImageScene image)
  {
    this.reader = new BufferedReader(reader);
    this.image = image;
    
  }

  public void parse()
  {
     TextArea textArea = new TextArea();
        textArea.setPrefRowCount(10);
        textArea.setPrefColumnCount(500);
        textArea.setWrapText(true);
        textArea.setPrefWidth(500);
    try
    {
      String line = reader.readLine();
      while (line != null)
      {
        parseLine(line);
        line = reader.readLine();
      }
    }
    catch (IOException e)
    {
      textArea.setText("IOException");
      return;
    }
    catch (ParseException e)
    {
      textArea.setText("Parse Exception: " + e.getMessage());
      return;
    }
      textArea.setText("Drawing completed");
      textArea.setTranslateX(0);
      textArea.setTranslateY(400);
      image.getElements().add(textArea);
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


    throw new ParseException("Unknown drawing command in :"+line);
  }
  
  private void setBackgroundColour(String colourName) throws ParseException{
    if (colourName.equals("black")) { image.setBackgroundColour(Color.BLACK); return;}
    if (colourName.equals("blue")) { image.setBackgroundColour(Color.BLUE); return;}
    if (colourName.equals("cyan")) { image.setBackgroundColour(Color.CYAN); return;}
    if (colourName.equals("darkgray")) { image.setBackgroundColour(Color.DARKGRAY); return;}
    if (colourName.equals("gray")) { image.setBackgroundColour(Color.GRAY); return;}
    if (colourName.equals("green")) { image.setBackgroundColour(Color.GREEN); return;}
    if (colourName.equals("lightgray")) { image.setBackgroundColour(Color.LIGHTGRAY); return;}
    if (colourName.equals("magenta")) { image.setBackgroundColour(Color.MAGENTA); return;}
    if (colourName.equals("orange")) { image.setBackgroundColour(Color.ORANGE); return;}
    if (colourName.equals("pink")) { image.setBackgroundColour(Color.PINK); return;}
    if (colourName.equals("red")) { image.setBackgroundColour(Color.RED); return;}
    if (colourName.equals("white")) { image.setBackgroundColour(Color.WHITE); return;}
    if (colourName.equals("yellow")) { image.setBackgroundColour(Color.YELLOW); return;}
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
    image.drawLine(x1,y1,x2,y2);
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
    image.drawImage(x1,y1,x2,y2,s);
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
    image.drawRect(x1, y1, x2, y2);
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
    image.fillRect(x1, y1, x2, y2);
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
    image.drawArc(x, y, width, height, startAngle, arcAngle);
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
    image.drawOval(x1, y1, width, height);
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
    image.drawString(x,y,s);
  }

  private void setColour(String colourName) throws ParseException
  {
    if (colourName.equals("black")) { image.setColour(Color.BLACK); return;}
    if (colourName.equals("blue")) { image.setColour(Color.BLUE); return;}
    if (colourName.equals("cyan")) { image.setColour(Color.CYAN); return;}
    if (colourName.equals("darkgray")) { image.setColour(Color.DARKGRAY); return;}
    if (colourName.equals("gray")) { image.setColour(Color.GRAY); return;}
    if (colourName.equals("green")) { image.setColour(Color.GREEN); return;}
    if (colourName.equals("lightgray")) { image.setColour(Color.LIGHTGRAY); return;}
    if (colourName.equals("magenta")) { image.setColour(Color.MAGENTA); return;}
    if (colourName.equals("orange")) { image.setColour(Color.ORANGE); return;}
    if (colourName.equals("pink")) { image.setColour(Color.PINK); return;}
    if (colourName.equals("red")) { image.setColour(Color.RED); return;}
    if (colourName.equals("white")) { image.setColour(Color.WHITE); return;}
    if (colourName.equals("yellow")) { image.setColour(Color.YELLOW); return;}
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
}
