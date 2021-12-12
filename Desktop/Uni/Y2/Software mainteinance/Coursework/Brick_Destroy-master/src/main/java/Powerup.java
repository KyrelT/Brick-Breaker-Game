package main.java;

import java.awt.*;
import java.awt.geom.Point2D;

public class Powerup {

    private double x;
    private double y;
    private Rectangle powerup;


    /**
     * @param position the position for the power up
     */
    public Powerup(Point2D position){
        Rectangle powerup = new Rectangle(20,20);
        this.setPowerup(powerup);
        x = position.getX();
        y = position.getY();
        powerup.setLocation((int) x,(int) y);

    }

    /**
     * power up will drop downwards
     */
    public void drop(){
        y += 8;
        getPowerup().setLocation((int) x,(int) y);
    }

    /**
     * @return get power up shape
     */
    public Rectangle getPowerup() {
        return powerup;
    }

    /**
     * @param powerup shape for power up
     */
    public void setPowerup(Rectangle powerup) {
        this.powerup = powerup;
    }
}
