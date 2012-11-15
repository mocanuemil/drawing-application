package drawapp;

/**
 *
 * @author geo
 */
public class Turtle {
    private int x;
    private int y;
    private int angle;
    
    public Turtle(int x,int y, int angle){
        this.x=x;
        this.y=y;
        this.angle=angle;
    }
    
    public void turnLeft(int angle){
        this.angle+=angle;
    }
    
    public void turnRight(int angle){
        this.angle-=angle;
    }
    
}
