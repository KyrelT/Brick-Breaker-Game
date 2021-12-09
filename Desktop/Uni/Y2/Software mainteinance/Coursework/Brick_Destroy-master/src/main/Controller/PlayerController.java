package main.Controller;

import main.Model.Ball.BallM;
import main.Model.Brick.BrickM;
import main.Model.Player.PlayerM;
import main.View.BallView;
import main.View.PlayerView;
import main.java.Ball;

import java.awt.*;

public class PlayerController {

    BrickController brickc;
    BallController ballc;
    BrickM brickm;
    BallM ballm;
    PlayerM pm;
    Shape playerFace = pm.getPlayerFace();

    public PlayerController(Point ballPoint,int width,int height,Rectangle container){
        PlayerM mplayer = new PlayerM(ballPoint,width,height,container);
        PlayerView vplayer = new PlayerView();
    }


    public boolean impact(BallM b){
        return playerFace.contains(b.getPosition()) && playerFace.contains(b.getDown()) ;
    }

    /**
     *
     */
    public void move(){
        double x = pm.getBallPoint().getX() + pm.getMoveAmount();
        if(x < pm.getMin() || x > pm.getMax())
            return;
        pm.getBallPoint().setLocation(x,pm.getBallPoint().getY());
        pm.playerFace.setLocation(pm.getBallPoint().x - (int)pm.playerFace.getWidth()/2,pm.getBallPoint().y);
    }

    /**
     * allow player to move left
     */
    public void moveLeft(){
        pm.setMoveAmount(-PlayerM.DEF_MOVE_AMOUNT);
    }

    /**
     * allow player to move right
     */
    public void moveRight(){
        pm.setMoveAmount(PlayerM.DEF_MOVE_AMOUNT);
    }

    /**
     * stop the player's movement
     */
    public void stop(){
        pm.setMoveAmount(0);
    }
    /**
     * @param p position of player
     */
    public void moveTo(Point p){
        pm.getBallPoint().setLocation(p);
        pm.playerFace.setLocation(pm.getBallPoint().x - (int)pm.playerFace.getWidth()/2,pm.getBallPoint().y);
    }
}
