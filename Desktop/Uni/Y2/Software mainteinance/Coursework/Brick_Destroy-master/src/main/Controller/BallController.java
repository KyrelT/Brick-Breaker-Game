package main.Controller;

import main.Model.Ball.BallM;
import main.View.BallView;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

public class BallController {
    BallM ball;
    Point2D center = ball.getCenter();
    Shape ballFace = ball.getBallFace();

    public BallController(Point2D center,int radius,Color Inner,Color Border){
        BallM mball = new BallM(center,radius,Inner,Border);
        BallView vball = new BallView();
    }
    /**
     * this class allows the ball to move in a motion
     */
    public void move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        ball.getCenter().setLocation((ball.getCenter().getX() + ball.getSpeedX()), ball.getCenter().getY() + ball.getSpeedX());
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((ball.getCenter().getX() -(w / 2)),(ball.getCenter().getY() - (h / 2)),w,h);
        setPoints(w,h);


        ballFace = tmp;
    }

    /**
     * @param p the starting position of the ball
     */
    public void moveTo(Point p){
        ball.getCenter().setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((ball.getCenter().getX() -(w / 2)),(ball.getCenter().getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    /**
     * ball repels to opposite direction after impact
     */
    public void reverseX(){
        int speedX = ball.getSpeedX();
        speedX *= -1;
    }

    /**
     * ball repels to opposite direction after impact
     */
    public void reverseY(){
        int  speedY = ball.getSpeedY();
        speedY *= -1;
    }

    /**
     * a getter for the border color of the ball
     * @return return the border color of the ball
     */
    public Color getBorderColor(){
        return ball.getBorderColor();
    }

    /**
     * a getter for the inner color of the ball
     * @return return the inner color of the ball
     */
    public Color getInnerColor(){
        return ball.getInnerColor();
    }

    public void setPoints(double width,double height){
        ball.getUp().setLocation(center.getX(),center.getY()-(height / 2));
        ball.getDown().setLocation(center.getX(),center.getY()+(height / 2));

        ball.getLeft().setLocation(center.getX()-(width / 2),center.getY());
        ball.getRight().setLocation(center.getX()+(width / 2),center.getY());
    }

}
