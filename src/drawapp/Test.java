package drawapp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;
import javafx.stage.Stage;

/**
 *
 * @author geo
 */
public class Test extends Application {
    
    private Group group = new Group();
    private Group group1=new Group();
    private Group group2=new Group();
    private ImageScene image;
    private Scene scene;
    private Reader reader;
    
    @Override
    public void start(Stage primaryStage) {
        Button btn1 = new Button();
        btn1.setText("Draw All");
        btn1.setTranslateX(225);
        btn1.setTranslateY(250);
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               group.setVisible(false);
               group1.setVisible(true);
                
                
            }
        });
        
        Button btn2 = new Button();
        btn2.setText("Draw Step by Step");
        btn2.setTranslateX(200);
        btn2.setTranslateY(300);
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void  handle(ActionEvent event) {
                group.setVisible(false);
                ArrayList<Node> elements = image.getElements();
                for (Node g :elements) {
                if(g == null){
                System.out.println("gol");
                }
                group2.getChildren().add(g);
                }
               
                
            }
        });
        
        Group root = new Group();
        
        scene = new Scene(root, 500, 500);
        String eol = System.getProperty("line.separator");
        reader = new StringReader("SB yellow"+eol+"DA 150 200 70 50 30 50" + eol +"SC blue" + eol +"FR 50 50 30 30"+eol+"DS 300 300 @Pana Mea"+eol+"DI 200 200 50 50 @http://www.robots.ox.ac.uk/~vgg/research/flowers_demo/images/flower_4.jpg");
        
        image = new ImageScene(scene);
        Parser parser = new Parser(reader,image);
        parser.parse();
        group1.setVisible(false);
        
        //System.out.println(image.getElements().size());
        ArrayList<Node> elements = image.getElements();
        for (Node g :elements) {
            if(g == null){
                System.out.println("gol");
            }
            group1.getChildren().add(g);
        }
        
        
        group.getChildren().add(btn1);
        group.getChildren().add(btn2);
        
        root.getChildren().add(group);
        root.getChildren().add(group1);
        root.getChildren().add(group2);
        
        primaryStage.setTitle("Draw App");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

   
    public static void main(String[] args) {
        launch(args);
    }
}
