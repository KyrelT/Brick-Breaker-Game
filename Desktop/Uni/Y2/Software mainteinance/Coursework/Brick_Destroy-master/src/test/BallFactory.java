package test;

import java.awt.*;
import java.awt.geom.Point2D;

public class BallFactory {
    public Ball getBallType(String name, Point2D ballPos){
        if (name == null)
            return null;
        if (name.equalsIgnoreCase("RUBBER"))
            return new RubberBall(ballPos);

        return null;
    }
}
