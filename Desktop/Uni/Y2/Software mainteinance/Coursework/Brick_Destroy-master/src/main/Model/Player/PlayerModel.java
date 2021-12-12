package main.Model.Player;

import java.awt.*;

public class PlayerModel {

        public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
        public static final Color INNER_COLOR = Color.GREEN;

        public static final int DEF_MOVE_AMOUNT = 5;

        private Rectangle playerFace;

        private Point ballPoint;
        private int moveAmount;
        private int min;
        private int max;
        private static PlayerModel playerInstance  ;


        public PlayerModel(Point ballPoint,int width,int height,Rectangle container) {
            this.ballPoint = ballPoint;
            moveAmount = 0;
            playerFace = makeRectangle(width, height);
            min = container.x + (width / 2);
            max = min + container.width - width;

        }

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

        public void setMoveAmount(int moveAmount) {
            this.moveAmount = moveAmount;
        }

        public Rectangle getPlayerFace(){
            return  playerFace;
        }


        public int getMoveAmount() {
            return moveAmount;
        }

        public Point getBallPoint() {
            return ballPoint;
        }
}
