package main.java;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;


/**
 * this abstract class allows other class to implement its methods
 */
abstract public class Brick  {



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
    public Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength){
        rnd = new Random();
        broken = false;
        this.name = name;
        brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;
        this.wall = wall;
    }

    /**
     * @return return a random value
     */
    public static Random getRnd() {
        return rnd;
    }

    protected abstract Shape makeBrickFace(Point pos,Dimension size);

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


    public abstract Shape getBrick();

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
     * @param b ball
     * @return the side of impact on brick
     */
    public final int findImpact(Ball b){
        if(broken)
            return 0;
        int out  = 0;
        if(brickFace.contains(b.right))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.left))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.up))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.down))
            out = UP_IMPACT;

        return out;
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
     * reset the strength of bricks
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * decrement strength value of brick
     */
    public void impact(){
        strength--;
        broken = (strength==0);

    }

}





