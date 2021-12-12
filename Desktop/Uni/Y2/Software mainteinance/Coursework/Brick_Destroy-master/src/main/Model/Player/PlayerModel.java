package main.Model.Player;

import java.awt.*;

public class PlayerModel {

        public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
        public static final Color INNER_COLOR = Color.GREEN;

        public static final int DEF_MOVE_AMOUNT = 6;

        private Rectangle playerFace;

        private Point ballPoint;
        private int moveAmount;
        private int min;
        private int max;


    /**
     * @param ballPoint position of the ball
     * @param width width of the player
     * @param height height of the player
     * @param container container of the player
     */
        public PlayerModel(Point ballPoint,int width,int height,Rectangle container) {
            this.ballPoint = ballPoint;
            moveAmount = 0;
            playerFace = makeRectangle(width, height);
            min = container.x + (width / 2);
            max = min + container.width - width;

        }

    /**
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @return a rectangle shape
     */
        private Rectangle makeRectangle(int width,int height){
            Point p = new Point((int)(ballPoint.getX() - (width / 2)),(int)ballPoint.getY());
            return  new Rectangle(p,new Dimension(width,height));
        }




        public int getMin() {
            return min;
        }

        public int getMax() {
            return max;
        }

    /**
     * @param moveAmount moving distance if key 'A' or key 'D' is pressed
     */
        public void setMoveAmount(int moveAmount) {
            this.moveAmount = moveAmount;
        }

        public Rectangle getPlayerFace(){
            return  playerFace;
        }


    /**
     * @return get the moving amount
     */
        public int getMoveAmount() {
            return moveAmount;
        }

    /**
     * @return get ball position
     */
        public Point getBallPoint() {
            return ballPoint;
        }
}
