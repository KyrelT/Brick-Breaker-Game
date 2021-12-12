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

    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;

    public BallControl(Point2D center,int radius,Color inner,Color border){
        ballModel = new BallModel(center,radius,inner,border);
        ballView = new BallView();
        ballFace = makeBall(center,radius);
    }

    protected  abstract Shape makeBall(Point2D center,int radius);

    public void moveTo(Point p){
        center = ballModel.getCenter();
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

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

    public void setPoints(double width,double height){
        ballModel.getUp().setLocation(center.getX(),center.getY()-(height / 2));
        ballModel.getDown().setLocation(center.getX(),center.getY()+(height / 2));

        ballModel.getLeft().setLocation(center.getX()-(width / 2),center.getY());
        ballModel.getRight().setLocation(center.getX()+(width / 2),center.getY());
    }

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

    public void setSpeed(int x,int y){
        ballModel.setSpeed(x,y);
    }

    public void setXSpeed(int s){
        ballModel.setXSpeed(s);
    }

    public void setYSpeed(int s){  ballModel.setYSpeed(s);}

    public void reverseY(){
        setYSpeed(-getSpeedY());
    }

    public void reverseX(){
        setXSpeed(-getSpeedX());
    }

    public Color getInnerColor(){
        return inner;
    }

    public Color getBorderColor(){
        return border;
    }

    public Point2D getRight() {
        return ballModel.getRight();
    }

    public Point2D getLeft(){
        return ballModel.getLeft();
    }

    public Point2D getDown(){
        return ballModel.getDown();
    }

    public Point2D getUp(){
        return ballModel.getUp();
    }
}
