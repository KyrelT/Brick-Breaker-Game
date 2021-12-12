package main.Model.Brick;

import java.awt.*;
import java.util.Random;

public class BrickModel {


    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    private static Random rnd;

    private String name;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    public boolean broken;


/**
 * @param name type of the brick
 * @param pos position of the brick
 * @param size size of the brick
 * @param border border color of the brick
 * @param inner inner color of the brick
 * @param strength strength of the brick
 */

    public BrickModel(String name, Point pos,Dimension size,Color border,Color inner,int strength){
        rnd = new Random();
        broken = false;
        this.name = name;
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;
    }


/**
 * @return return a random value
 */

    public static Random getRnd() {
        return rnd;
    }



/**
 * @return return a border color
 */

    public Color getBorderColor(){
        return  border;
    }


/**
 * @return return a inner color
 */

    public Color getInnerColor(){
        return inner;
    }


/**
 * @return true if its broken vice versa
 */

    public final boolean isBroken(){
        return broken;
    }

    public int getStrength() {
        return strength;
    }

    public int getFullStrength() {
        return fullStrength;
    }


/**
 * decrement strength value of brick
 */

    public void impact(){
        setStrength(getStrength()-1);
        setBroken(getStrength()==0);

    }

    /**
     * @param strength int variable for strength
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * @param broken boolean value to check if the brick is broken
     */
    public void setBroken(boolean broken) {
        this.broken = broken;
    }


}
