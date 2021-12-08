/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package main.java;

import java.awt.*;


public class Player {



    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static final Color INNER_COLOR = Color.GREEN;

    private static final int DEF_MOVE_AMOUNT = 8;

    private Rectangle playerFace;
    private Point ballPoint;
    private int moveAmount;
    private int min;
    private int max;
    private static Player PlayerInstance;

    private Player(){
    }

    /**
     * @param ballPoint the position of the ball on the player's rectangle
     * @param width the width of the player
     * @param height the height of the player
     * @param container limits the movement range of the player
     */
    private Player(Point ballPoint,int width,int height,Rectangle container) {
        this.ballPoint = ballPoint;
        moveAmount = 0;
        playerFace = makeRectangle(width, height);
        min = container.x + (width / 2);
        max = min + container.width - width;
    }

    public static Player getInstance(){
        if (PlayerInstance == null){
            PlayerInstance = new Player();
        }
        return PlayerInstance;
    }

    public static Player getInstance(Point ballPos,int width,int height, Rectangle container){
        if (PlayerInstance == null){
            PlayerInstance = new Player(ballPos,width,height,container);
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

    public boolean impact(Ball b){
        return playerFace.contains(b.getPosition()) && playerFace.contains(b.down) ;
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

    public int getMoveAmount() {
        return moveAmount;
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
     * a getter to get the shape of the player
     * @return the shape for the player
     */
    public Shape getPlayerFace(){
        return  playerFace;
    }

    /**
     * @param p position of player
     */
    public void moveTo(Point p){
        ballPoint.setLocation(p);
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }
}
