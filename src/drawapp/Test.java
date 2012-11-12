package drawapp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author geo
 */
public class Test extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        /*Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });*/
        
        StackPane root = new StackPane();
        
        Scene scene = new Scene(root, 500, 500);
        
        ImageScene image = new ImageScene(scene);
        String eol = System.getProperty("line.separator");
        Reader reader = new StringReader("SB green"+eol+"DA 150 200 70 50 30 50" + eol +"SC blue" + eol +"FR 50 50 30 30"+eol+"DS 300 300 @Pana Mea");
        Parser parser = new Parser(reader,image);
        parser.parse();
        
        root.getChildren().add(image.getGroup());
        
        primaryStage.setTitle("Draw App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

   
    public static void main(String[] args) {
        launch(args);
    }
}
