package main.Controller;

import main.Model.Ball.BallModel;
import main.View.BallView;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

abstract public class BallControl {

    public BallModel ballModel;
    public BallView ballView;


    private Shape ballFace;

    private Point2D center;

    /**
     * @param center the center position of the ball
     * @param radius radius of the ball
     * @param inner inner color of the ball
     * @param border border color of the ball
     */
    public BallControl(Point2D center,int radius,Color inner,Color border){
        ballModel = new BallModel(center,radius,inner,border);
        ballView = new BallView();
        ballFace = makeBall(center,radius);
    }

    /**
     * @param center center position of the ball
     * @param radius radius of the ball
     * @return
     */
    protected  abstract Shape makeBall(Point2D center,int radius);

    /**
     * @param p position of the ball
     */
    public void moveTo(Point p){
        center = ballModel.getCenter();
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    /**
     * allow ball to move
     */
    public void move(){
        center = ballModel.getCenter();
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + ballModel.getSpeedX()),(center.getY() + ballModel.getSpeedY()));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);


        ballFace = tmp;
    }

    /**
     * @param width width of the ball
     * @param height height of the ball
     */
    public void setPoints(double width,double height){
        ballModel.getUp().setLocation(center.getX(),center.getY()-(height / 2));
        ballModel.getDown().setLocation(center.getX(),center.getY()+(height / 2));

        ballModel.getLeft().setLocation(center.getX()-(width / 2),center.getY());
        ballModel.getRight().setLocation(center.getX()+(width / 2),center.getY());
    }

    /**
     * @return the center position of the ball from the BallModel class
     */
    public Point2D getPosition(){
        return ballModel.getCenter();
    }

    /**
    * a getter for a value for the X-axis speed of the ball
    * @return a value for the X-axis speed of the ball
    */

    public int getSpeedX(){
        return ballModel.getSpeedX();
    }


    /**
     * a getter for a value for the Y-axis speed of the ball
     * @return a value for the Y-axis speed of the ball
     */

    public int getSpeedY(){
        return ballModel.getSpeedY();
    }

    /**
    * a getter for getting the shape of the ball
    * @return return the shape of the ball
     */

    public Shape getBallFace(){
        return ballFace;
    }

    /**
     * @param x the horizontal speed
     * @param y the vertical speed
     */
    public void setSpeed(int x,int y){
        ballModel.setSpeed(x,y);
    }

    /**
     * @param s int variable for x speed
     */
    public void setXSpeed(int s){
        ballModel.setXSpeed(s);
    }

    /**
     * @param s int variable for y speed
     */
    public void setYSpeed(int s){  ballModel.setYSpeed(s);}

    /**
     * reverse the vertical movement of the ball
     */
    public void reverseY(){
        setYSpeed(-getSpeedY());
    }

    /**
     * reverse the horizontal movement of the ball
     */
    public void reverseX(){
        setXSpeed(-getSpeedX());
    }

    /**
     * @return get the Right point of the ball
     */
    public Point2D getRight() {
        return ballModel.getRight();
    }

    /**
     * @return get the left point of the ball
     */
    public Point2D getLeft(){
        return ballModel.getLeft();
    }

    /**
     * @return get the down point of the ball
     */
    public Point2D getDown(){
        return ballModel.getDown();
    }

    /**
     * @return get the up point of the ball
     */
    public Point2D getUp(){
        return ballModel.getUp();
    }
}
