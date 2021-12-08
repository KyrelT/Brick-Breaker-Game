package main.Controller;

import main.Model.Ball.BallM;
import main.Model.Player.PlayerM;
import main.View.BallView;
import main.View.PlayerView;
import main.java.Ball;

import java.awt.*;

public class PlayerController {

    PlayerM a;
    BallM b;
    Shape playerFace = a.getPlayerFace();

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
        double x = ballPoint.getX() + moveAmount;
        if(x < min || x > max)
            return;
        ballPoint.setLocation(x,ballPoint.getY());
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }

    /**
     * allow player to move left
     */
    public void moveLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    /**
     * allow player to move right
     */
    public void moveRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }

    /**
     * stop the player's movement
     */
    public void stop(){
        moveAmount = 0;
    }
    /**
     * @param p position of player
     */
    public void moveTo(Point p){
        ballPoint.setLocation(p);
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }
}
