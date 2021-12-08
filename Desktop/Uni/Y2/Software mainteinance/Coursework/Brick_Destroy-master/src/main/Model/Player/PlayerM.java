package main.Model.Player;

import main.java.Player;

import java.awt.*;

public class PlayerM {
    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static final Color INNER_COLOR = Color.GREEN;

    private static final int DEF_MOVE_AMOUNT = 8;

    private Rectangle playerFace;
    private Point ballPoint;
    private int moveAmount;
    private int min;
    private int max;
    private static PlayerM PlayerInstance;

    private PlayerM(){
    }

    /**
     * @param ballPoint the position of the ball on the player's rectangle
     * @param width the width of the player
     * @param height the height of the player
     * @param container limits the movement range of the player
     */
    public PlayerM(Point ballPoint,int width,int height,Rectangle container) {
        this.ballPoint = ballPoint;
        moveAmount = 0;
        playerFace = makeRectangle(width, height);
        min = container.x + (width / 2);
        max = min + container.width - width;
    }

    public static PlayerM getInstance(){
        if (PlayerInstance == null){
            PlayerInstance = new PlayerM();
        }
        return PlayerInstance;
    }

    public static PlayerM getInstance(Point ballPos,int width,int height, Rectangle container){
        if (PlayerInstance == null){
            PlayerInstance = new PlayerM(ballPos,width,height,container);
        }
        return PlayerInstance;
    }

    /**
     * @param width value for the width of the rectangle
     * @param height value for the height of the rectangle
     * @return return a rectangle shape
     */
    private Rectangle makeRectangle(int width,int height){
        Point p = new Point((int)(ballPoint.getX() - (width / 2)),(int)ballPoint.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }

    public int getMoveAmount() {
        return moveAmount;
    }

    /**
     * a getter to get the shape of the player
     * @return the shape for the player
     */
    public Shape getPlayerFace(){
        return  playerFace;
    }
}
