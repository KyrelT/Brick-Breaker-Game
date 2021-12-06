package test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

public class Powerup {

    private static boolean isPowerCollected;
    private double x;
    private double y;
    private Rectangle powerup;


    public Powerup(Point2D position){
        Rectangle powerup = new Rectangle(20,20);
        this.setPowerup(powerup);
        x = position.getX();
        y = position.getY();
        powerup.setLocation((int) x,(int) y);

    }

    public void drop(){
        y += 5;
        getPowerup().setLocation((int) x,(int) y);
    }

    public boolean powercollected() {
        return isPowerCollected;
    }

    public Rectangle getPowerup() {
        return powerup;
    }

    public void setPowerup(Rectangle powerup) {
        this.powerup = powerup;
    }
}
