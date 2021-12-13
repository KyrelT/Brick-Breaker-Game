package main.java;

import main.Controller.BrickControl;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

public class BombBrick extends BrickControl {

    private static final String NAME = "Bomb Brick";
    private static final Color DEF_INNER = new Color(250, 0, 0);
    private static final Color DEF_BORDER = new Color(0, 0, 0);
    private static final int BOMB_STRENGTH = 1;

    private Crack crack;
    private Shape brickFace;


    /**
     * @param point position of the cement brick
     * @param size size of the cement brick
     */
    public BombBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,BOMB_STRENGTH);
        crack = new Crack(DEF_CRACK_DEPTH,DEF_STEPS);
        brickFace = super.brickFace;
    }

    /**
     * @param pos get the position of the brick
     * @param size get the size of the brick
     * @return returns a shape of the brick
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * @param point get the position of the ball
     * @param dir get the direction of the impact
     * @return return false if the brick is broken, true if it's not broken
     */
    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(super.isBroken()){
            return false;}
        super.impact();
        if(!super.isBroken()){
            crack.makeCrack(brickFace,point,dir);
            updateBrick();
            return false;
        }
        return true;
    }


    /**
     * @return returns the shape of the brick
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /**
     * update the visuals(crack) of the brick if there's impact
     */
    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath gp = crack.draw(); // GP = geometrical lines
            gp.append(super.brickFace,false); // so that crack lines don't connect each other
            brickFace = gp;
        }
    }

    /**
     * reset the visuals of the brick
     * erase crack
     */
    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.brickFace;
    }
}
