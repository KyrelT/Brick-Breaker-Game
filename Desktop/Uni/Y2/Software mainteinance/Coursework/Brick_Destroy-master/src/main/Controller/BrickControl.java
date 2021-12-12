package main.Controller;

import main.Model.Brick.BrickModel;
import main.View.BrickView;

import java.awt.*;
import java.awt.geom.Point2D;

abstract public class BrickControl {
    public static final int MIN_CRACK = 1;
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;

    public BrickView brickView ;
    public BrickModel brickModel ;
    public Shape brickFace ;


    /**
     * @param name name of the brick type
     * @param pos position of the brick
     * @param size size of the brick
     * @param border border color of the brick
     * @param inner inner color of the brick
     * @param strength strength of the brick
     */
    public BrickControl(String name, Point pos, Dimension size, Color border, Color inner, int strength)
    {
        brickView = new BrickView();
        brickModel = new BrickModel(name, pos, size, border, inner, strength );
        brickFace = makeBrickFace(pos,size);

    }

    /**
     * @param pos position of the brick
     * @param size size of the brick
     * @return Brick face
     */
    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    /**
     * @return get brick
     */
    public abstract Shape getBrick();

    /**
     * @param strength the strength/ toughness of the brick
     */
    public void setStrength(int strength) {
        brickModel.setStrength(strength);
    }

    /**
     * impact of the brick
     */
    public void impact(){
        setStrength(getStrength()-1);
        setBroken(getStrength()== 0);// broken = true (when broken)
    }

    /**
     * brick's strength will reset if this method is called
     */
    public void repair() {

        setBroken(false);
        setStrength(getFullStrength());
    }


    /**
     * @param broken return a boolean if the brick is broken
     */
    public void setBroken(boolean broken)
    {
        brickModel.setBroken(broken);
    }

    /**
     * @return a strength value which is originally initialize for the brick type
     */
    public int getFullStrength()
    {
        return brickModel.getFullStrength();
    }

    /**
     * @param b ball object
     * @return which direction of the brick is impacted
     */
    public final int findImpact(BallControl b){
        if(isBroken())
            return 0;
        int out  = 0;
        if(brickFace.contains(b.getRight())){
            System.out.println("findimpact");
            out = BrickModel.LEFT_IMPACT;}
        else if(brickFace.contains(b.getLeft())){
            System.out.println("findimpact");
            out = BrickModel.RIGHT_IMPACT;}
        else if(brickFace.contains(b.getUp())){
            System.out.println("findimpact");
            out = BrickModel.DOWN_IMPACT;}
        else if(brickFace.contains(b.getDown())){
            System.out.println("findimpact");
            out = BrickModel.UP_IMPACT ;}
        return out;

    }

    /**
     * @return a boolean from brick model class if the brick is broken or not
     */
    public final boolean isBroken()
    {
        return brickModel.isBroken();
    }


    /**
     * @return an int value of strength from the brick
     */
    public int getStrength(){
        return brickModel.getStrength();
    }


    /**
     * @return get the border color from brick model class
     */
    public Color getBorderColor(){
        return brickModel.getBorderColor();
    }

    /**
     * @return get the inner color from brick model class
     */
    public Color getInnerColor(){
        return brickModel.getInnerColor();
    }

    /**
     * @param point position of the brick
     * @param dir direction of which the brick is impacted
     * @return a boolean value if there is any impact
     */
    public  boolean setImpact(Point2D point , int dir){
        System.out.println("setImpact");
        if(isBroken()){
            System.out.println("setImpact");
            return false;}
        impact();
        System.out.println("setImpact");
        return isBroken();
    }


}
