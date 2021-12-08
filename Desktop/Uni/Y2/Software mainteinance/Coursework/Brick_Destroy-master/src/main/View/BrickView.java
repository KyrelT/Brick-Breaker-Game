package main.View;

import main.java.Brick;

import java.awt.*;

public class BrickView {
    private void drawBrick(Brick brick, Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());


        g2d.setColor(tmp);
    }
}
