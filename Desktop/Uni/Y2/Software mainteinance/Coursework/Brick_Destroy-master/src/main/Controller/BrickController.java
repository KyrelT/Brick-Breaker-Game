package main.Controller;

import main.Model.Ball.BallM;
import main.Model.Brick.BrickM;
import main.View.BallView;
import main.View.BrickView;
import main.java.Ball;

import java.awt.*;

import static main.java.Brick.*;

public class BrickController {
    BallM b;
    BrickM a;
    Shape brickFace = a.getBrickFace();

    public BrickController(String name, Point pos, Dimension size, Color border, Color inner, int strength){
        BrickM mbrick = new BrickM(name,pos,size,border,inner,strength);
        BrickView vbrick = new BrickView();
    }

    /**
     * @param b ball
     * @return the side of impact on brick
     */
    public final int findImpact(BallM b){
        if(a.isBroken())
            return 0;
        int out  = 0;
        if(brickFace.contains(b.getRight()))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.getLeft()))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.getUp()))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.getDown()))
            out = UP_IMPACT;

        return out;
    }

    /**
     * reset the strength of bricks
     */
    public void repair() {
        a.setBroken(false);
        a.isBroken();
        a.setStrength(a.getFullStrength());
    }

    /**
     * decrement strength value of brick
     */
    public void impact(){
        a.setStrength(a.getStrength()-1);
        if (a.getStrength()==0){
            a.setBroken(true);
        }
        //a.isBroken() = (a.getStrength()==0);
    }
}
