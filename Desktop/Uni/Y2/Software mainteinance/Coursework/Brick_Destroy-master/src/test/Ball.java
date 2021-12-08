package test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * this abstract class allows other class to implement its methods
 *
 */
abstract public class Ball{

    private Shape ballFace;

    private Point2D center;

    Point2D up;
    Point2D down;
    Point2D left;
    Point2D right;

    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;


    /**
     * @param center to center the ball at the start of the game
     * @param radiusA the Y-axis radius of the ball
     * @param radiusB the X-axis radius of the ball
     * @param inner the inner color of the ball
     * @param border the outer border color of the ball
     */
    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){
        this.center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(),center.getY()-(radiusB/2));
        down.setLocation(center.getX(),center.getY()+(radiusB/2));

        left.setLocation(center.getX()-(radiusA /2),center.getY());
        right.setLocation(center.getX()+(radiusA /2),center.getY());


        ballFace = makeBall(center,radiusA,radiusB);
        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;
    }

    /**
     * @param center to position the ball center at the start of the game
     * @param radiusA the Y-axis radius of the ball
     * @param radiusB the X-axis radius of the ball
     * @return returns the shape of the ball
     */
    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

    /**
     * this class allows the ball to move in a motion
     */
    public void move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);


        ballFace = tmp;
    }

    /**
     * @param x the X-axis speed of the ball
     * @param y the Y-axis speed of the ball
     */
    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    /**
     * a setter for X-axis speed of the ball
     * @param s a value for the X-axis speed of the ball
     */
    public void setXSpeed(int s){
        speedX = s;
    }

    /**
     * a setter for Y-axis speed of the ball
     * @param s a value for the Y-axis speed of the ball
     */
    public void setYSpeed(int s){  speedY = s;}

    /**
     * ball repels to opposite direction after impact
     */
    public void reverseX(){
        speedX *= -1;
    }

    /**
     * ball repels to opposite direction after impact
     */
    public void reverseY(){
        speedY *= -1;
    }

    /**
     * a getter for the border color of the ball
     * @return return the border color of the ball
     */
    public Color getBorderColor(){
        return border;
    }

    /**
     * a getter for the inner color of the ball
     * @return return the inner color of the ball
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * a getter for getting the position of the ball to the center position
     * @return return the center position coordinate value
     */
    public Point2D getPosition(){
        return center;
    }

    /**
     * a getter for getting the shape of the ball
     * @return return the shape of the ball
     */
    public Shape getBallFace(){
        return ballFace;
    }

    /**
     * @param p the starting position of the ball
     */
    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    public void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }

    /**
     * a getter for a value for the X-axis speed of the ball
     * @return a value for the X-axis speed of the ball
     */
    public int getSpeedX(){
        return speedX;
    }

    /**
     * a getter for a value for the Y-axis speed of the ball
     * @return a value for the Y-axis speed of the ball
     */
    public int getSpeedY(){
        return speedY;
    }


}
