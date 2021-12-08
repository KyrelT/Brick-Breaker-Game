package main.java;

import java.awt.*;

public class BrickFactory {

    public Brick getBrickType(String name, Point point, Dimension size){
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

        return null;
    }
}
