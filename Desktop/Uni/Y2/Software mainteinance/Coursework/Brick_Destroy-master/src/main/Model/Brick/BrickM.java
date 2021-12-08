package main.Model.Brick;

import main.java.Wall;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class BrickM {

    public static final int MIN_CRACK = 1;
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    private static Random rnd;

    private String name;
    Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;
    private Wall wall;

    /**
     * @param name type of the brick
     * @param pos position of the brick
     * @param size size of the brick
     * @param border border color of the brick
     * @param inner inner color of the brick
     * @param strength strength of the brick
     */
    public BrickM(String name, Point pos, Dimension size, Color border, Color inner, int strength){
        rnd = new Random();
        broken = false;
        this.name = name;
        brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;
/*        this.wall = wall;*/
    }

    /**
     * @return return a random value
     */
    public static Random getRnd() {
        return rnd;
    }

    public boolean isBroken() {
        return broken;
    }

    /**
     * @param point position of the brick
     * @param dir direction of the impact
     * @return true if it's not broken, false if it's broken
     */
    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }


    public Shape getBrickFace(){
        return brickFace;
    }

    public Shape getBrick() {
        return null;
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

    public int getStrength() {
        return strength;
    }

    public int getFullStrength() {
        return fullStrength;
    }
}
