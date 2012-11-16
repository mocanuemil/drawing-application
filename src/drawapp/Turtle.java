package drawapp;

/**
 *
 * @author Mocanu Emil George
 */
public class Turtle {
    private double x;
    private double y;
    private int angle;
    
    public Turtle(double x,double y, int angle){
        this.x=x;
        this.y=y;
        this.angle=angle;
    }
    
    public void setX(double x){
        this.x=x;
    }
    
    public void setY(double y){
        this.y=y;
    }
    
    public void setAngle(int angle){
        this.angle=angle;
    }  
    
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    public int getAngle(){
        return angle;
    }
    
    public void turnLeft(int angle){
        this.angle-=angle;
    }
    
    public void turnRight(int angle){
        this.angle+=angle;
    }
    
    
    public void forward(int dist){
        x+=dist*Math.cos(angle*Math.PI/180);
        y+=dist*Math.sin(angle*Math.PI/180);
    }
    
}
