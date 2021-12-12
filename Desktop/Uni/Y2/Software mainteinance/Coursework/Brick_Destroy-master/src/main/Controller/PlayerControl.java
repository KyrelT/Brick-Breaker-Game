package main.Controller;

import main.Model.Player.PlayerModel;
import main.View.PlayerView;

import java.awt.*;

public class PlayerControl {
    public PlayerView playerView;
    public PlayerModel playerModel;
    public static PlayerControl playerInstance;
    public Point ballPoint ;
    public Rectangle playerFace ;


    private PlayerControl(){}

    private PlayerControl(Point ballPoint,int width,int height,Rectangle container)
    {
        playerView = new PlayerView();
        playerModel = new PlayerModel(ballPoint, width, height, container);
        ballPoint = playerModel.getBallPoint();
        playerFace = playerModel.getPlayerFace();
        //ballControl = new RubberBall(ballPoint);
    }

    public static PlayerControl getInstance() {
        if (playerInstance == null) {
            playerInstance = new PlayerControl();
        }
        return playerInstance;

    }
    public static PlayerControl getInstance(Point ballpos,int width, int height, Rectangle container)
    {
        if (playerInstance == null)
        {
            playerInstance = new PlayerControl(ballpos,width,height,container);
        }
        return playerInstance;
    }

    /**
     * controls the movement of the player's rectangle
     */
    public void move(){
        double x = playerModel.getBallPoint().getX() + playerModel.getMoveAmount();

        if(x < playerModel.getMin() || x > playerModel.getMax())
            return;
        playerModel.getBallPoint().setLocation(x,playerModel.getBallPoint().getY());
        playerFace.setLocation(playerModel.getBallPoint().x - (int)playerFace.getWidth()/2,playerModel.getBallPoint().y);
    }


    /**
     * the player rectangle moves left by 5 units
     */
    public void moveLeft(){
        playerModel.setMoveAmount(-PlayerModel.DEF_MOVE_AMOUNT) ;

    }

    public void moveRight(){
        playerModel.setMoveAmount(PlayerModel.DEF_MOVE_AMOUNT) ;
    }

    public void stop(){
        playerModel.setMoveAmount(0) ;
    }

    /**
     * @param p the middle position (starting position)
     * resets the position of the player's rectangle during start of each round / lose the ball
     */
    public void moveTo(Point p){
        ballPoint = playerModel.getBallPoint();
        ballPoint.setLocation(p);
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }

    /**
     * @param b ball
     * @return the position of the ball when it impacts with the player's rectangle
     */
    public boolean impact(BallControl b){
        return playerFace.contains(b.getPosition()) && playerFace.contains(b.getDown()) ;
    }

    public Color getInnerColor()
    {
        return PlayerModel.INNER_COLOR;
    }

    public Color getBorderColor()
    {
        return PlayerModel.BORDER_COLOR;
    }

    public Rectangle getPlayerFace()
    {
        return playerModel.getPlayerFace();
    }

}
