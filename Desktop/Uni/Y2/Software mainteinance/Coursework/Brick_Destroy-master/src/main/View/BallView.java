package main.View;

import main.Controller.BallControl;
import main.java.RubberBall;

import java.awt.*;

public class BallView {
    public void drawBall(BallControl ball, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(RubberBall.DEF_INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(RubberBall.DEF_BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(tmp);
    }
}
