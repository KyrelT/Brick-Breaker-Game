package main.View;

import main.Controller.BrickControl;

import java.awt.*;

public class BrickView {
    public void drawBrick(BrickControl brick, Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());


        g2d.setColor(tmp);
    }
}
