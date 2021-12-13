package main.java;

import main.Controller.BrickControl;

import java.awt.*;

public class BrickFactory {

    /**
     * @param name name for the type of brick
     * @param point position of the brick
     * @param size size of the brick
     * @return type of brick
     */
    public BrickControl getBrickType(String name, Point point, Dimension size){
        if (name==null)
            return null;
        if (name.equalsIgnoreCase("CLAY"))
            return new ClayBrick(point,size);
        else if(name.equalsIgnoreCase("CEMENT"))
            return new CementBrick(point,size);
        else if(name.equalsIgnoreCase("STEEL"))
            return new SteelBrick(point,size);
        else if(name.equalsIgnoreCase("DIAMOND"))
            return new DiamondBrick(point,size);
        else if(name.equalsIgnoreCase("BOMB"))
            return new BombBrick(point,size);

        return null;
    }
}
