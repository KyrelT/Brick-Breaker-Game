package main.Model.Ball;

import java.awt.*;
import java.awt.geom.Point2D;

public class BallModel {

    private Point2D center;

    Point2D up;
    Point2D down;
    Point2D left;
    Point2D right;



    private int speedX;
    private int speedY;


    /**
     * @param center center position for the ball
     * @param radius radius of the ball
     * @param inner inner color of the ball
     * @param border border color of the ball
     */
    public BallModel(Point2D center, int radius,Color inner, Color border)
    {
        this.center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(),center.getY()-(radius / 2));
        down.setLocation(center.getX(),center.getY()+(radius / 2));

        left.setLocation(center.getX()-(radius /2),center.getY());
        right.setLocation(center.getX()+(radius /2),center.getY());

        speedX = 0;
        speedY = 0;
    }


    /**
     * @return up point of the ball
     */
    public Point2D getUp() {
        return up;
    }

    /**
     * @return down point of the ball
     */
    public Point2D getDown() {
        return down;
    }

    /**
     * @return left point of the ball
     */
    public Point2D getLeft() {
        return left;
    }

    /**
     * @return right point of the ball
     */
    public Point2D getRight() {
        return right;
    }

    /**
     * @param x int variable for x speed
     * @param y int variable for y speed
     */
    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    /**
     * @param s int variable for x speed
     */
    public void setXSpeed(int s){
        speedX = s;
    }

    /**
     * @param s int variable for y speed
     */
    public void setYSpeed(int s){
        speedY = s;
    }


    /**
     * @return center point position
     */
    public Point2D getPosition(){
        return center;
    }


    public int getSpeedX(){
        return speedX;
    }

    public int getSpeedY(){
        return speedY;
    }


    public Point2D getCenter() {
        return center;
    }
}
