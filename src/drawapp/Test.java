package drawapp;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author geo
 */
public class Test extends Application {
    
    private BorderPane root = new BorderPane();
    private TextA textArea = new TextA();
    private HBox hbox = new HBox();
    private Group group1=new Group();
    private Color stepColour=Color.BLACK;
    private ImageScene image;
    private Scene scene;
    private Reader reader;
    private int commandNr=0;
    private ArrayList<String> commands=new ArrayList<>();
    
    
    private java.awt.image.BufferedImage convertToAwtImage(javafx.scene.image.Image fxImage) {
                if (Image.impl_isExternalFormatSupported(BufferedImage.class)) {
                        java.awt.image.BufferedImage awtImage = new BufferedImage((int)fxImage.getWidth(), (int)fxImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
                        return (BufferedImage)fxImage.impl_toExternalImage(awtImage);
                } else {
                        return null;
                }
        }
    
    @Override
    public void start(Stage primaryStage) {
        Button btn1 = new Button();
        btn1.setText("Draw All");
        btn1.setPrefSize(100, 20);
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               Parser1 parser = new Parser1(commands,scene,group1,textArea);
               parser.parse();
             
               root.setCenter(group1);
                
                
            }
        });
        
        Button btn2 = new Button();
        btn2.setText("Step by Step");
        btn2.setPrefSize(100, 20);
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void  handle(ActionEvent event) {
                
                System.out.println(commandNr);  
                
                Parser1 parser = new Parser1(commands,scene,group1,textArea,stepColour);
                
                if(commandNr<commands.size()){
                parser.parseLineStep(commandNr);
                stepColour=parser.getColor();
                commandNr++;
                
                root.setCenter(group1);
                }
                if (commandNr== commands.size()){
                    textArea.Text("Drawing Completed!");
                }
                
            }
        });
        
         Button btn3 = new Button();
        btn3.setText("Save as image");
        btn3.setPrefSize(100, 20);
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void  handle(ActionEvent event) {
                
               WritableImage snapshot = scene.snapshot(null);
               BufferedImage img = convertToAwtImage(snapshot);
               File outputfile = new File("saved.png");
                try {
                    ImageIO.write(img, "png", outputfile);
                } catch (IOException ex) {
                   System.out.println("Image Exception");
                }
                
            }
        });
        
       
        root.setPrefHeight(600);
        root.setPrefWidth(600);
        hbox.setStyle("-fx-background-color: #336699;");
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(30);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(btn1,btn2,btn3);
        root.setTop(hbox);
        root.setBottom(textArea.getTextArea());
        
        scene = new Scene(root, 600, 600);
        String eol = System.getProperty("line.separator");
        reader = new StringReader("SB yellow"+eol+"DA 150 200 70 50 30 50" + eol +"SC blue" + eol +"FR 50 50 30 30"+eol+"DS 300 300 @Pana Mea"+eol+"DI 200 200 50 50 @http://www.robots.ox.ac.uk/~vgg/research/flowers_demo/images/flower_4.jpg");
        BufferedReader read = new BufferedReader(reader);
        try {
            String line =read.readLine();
            while(line!=null){
                if(line!=null)commands.add(line);  
                line=read.readLine();
            }
        } catch (IOException ex) {
            System.out.println("{IOException");
        }
 
        primaryStage.setTitle("Draw App");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

   
    public static void main(String[] args) {
        launch(args);
    }
}
