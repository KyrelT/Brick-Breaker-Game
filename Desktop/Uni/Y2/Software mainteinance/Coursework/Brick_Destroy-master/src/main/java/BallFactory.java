package main.java;

import main.Controller.BallControl;

import java.awt.geom.Point2D;

public class BallFactory {
    /**
     * @param name name for the type of ball
     * @param ballPos position of the ball
     * @return a type of ball
     */
    public BallControl getBallType(String name, Point2D ballPos){
        if (name == null)
            return null;
        if (name.equalsIgnoreCase("RUBBER"))
            return new RubberBall(ballPos);
        return null;
    }
}
