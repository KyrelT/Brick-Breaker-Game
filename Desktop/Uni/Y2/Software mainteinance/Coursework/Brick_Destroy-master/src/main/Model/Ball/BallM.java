package main.Model.Ball;

import main.View.BallView;

import java.awt.*;
import java.awt.geom.Point2D;

public class BallM {

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
     * @param radius the radius of the ball
     * @param inner the inner color of the ball
     * @param border the outer border color of the ball
     */
    public BallM(Point2D center,int radius,Color inner,Color border){
        this.center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(),center.getY()-(radius/2));
        down.setLocation(center.getX(),center.getY()+(radius/2));

        left.setLocation(center.getX()-(radius /2),center.getY());
        right.setLocation(center.getX()+(radius /2),center.getY());


        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;
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

 /*   public void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }
*/
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

    public Point2D getCenter() {
        return center;
    }

    public Point2D getUp() {
        return up;
    }

    public Point2D getDown() {
        return down;
    }

    public Point2D getLeft() {
        return left;
    }

    public Point2D getRight() {
        return right;
    }

}
