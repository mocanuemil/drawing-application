package drawapp;

import javafx.scene.control.TextArea;

/**
 *
 * @author Mocanu Emil George
 */
public class TextA {
    private TextArea textArea;
    
    public TextA(){
        textArea=new TextArea();
        textArea.setPrefRowCount(10);
        textArea.setPrefColumnCount(500);
        textArea.setWrapText(true);
        textArea.setPrefWidth(500);
    }
    
    public void Text(String text){
        String eol = System.getProperty("line.separator");
        textArea.appendText(text + eol);
    }
    
    public TextArea getTextArea(){
        return textArea;
    }
    
}
