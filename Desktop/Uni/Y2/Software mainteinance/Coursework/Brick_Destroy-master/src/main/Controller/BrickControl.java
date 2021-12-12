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


    public BrickControl(String name, Point pos, Dimension size, Color border, Color inner, int strength)
    {
        brickView = new BrickView();
        brickModel = new BrickModel(name, pos, size, border, inner, strength );
        brickFace = makeBrickFace(pos,size);

    }

    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    public abstract Shape getBrick();

/*    public void setFullStrength(int fullStrength) {
        brickModel.setStrength(fullStrength);
    }*/


    public void setStrength(int strength) {
        brickModel.setStrength(strength);
    }

    public void impact(){

        System.out.println("print yi dian dong xi******************");
        setStrength(getStrength()-1);
        setBroken(getStrength()== 0);// broken = true (when broken)
    }

    public void repair() {

        setBroken(false);
        setStrength(getFullStrength());
    }


    public void setBroken(boolean broken)
    {
        brickModel.setBroken(broken);
    }

    public int getFullStrength()
    {
        return brickModel.getFullStrength();
    }

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

    public final boolean isBroken()
    {
        return brickModel.isBroken();
    }


    public int getStrength(){
        return brickModel.getStrength();
    }


    public Color getBorderColor(){
        return brickModel.getBorderColor();
    }

    public Color getInnerColor(){
        return brickModel.getInnerColor();
    }

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
