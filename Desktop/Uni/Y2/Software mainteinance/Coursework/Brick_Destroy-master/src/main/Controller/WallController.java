package main.Controller;

import main.Model.Ball.BallM;
import main.Model.Wall.WallM;
import main.View.BallView;
import main.java.*;

import java.awt.*;
import java.awt.geom.Point2D;

public class WallController {

    BallController bc;
    BallM bm;
    WallM wm;
    Brick[] bricks;
    BrickFactory brickf;
    BallFactory ballf;
    BallView ballv;


    /**
     * @param drawArea the area of the wall
     * @param brickCnt the amount of bricks
     * @param lineCnt the amount of lines
     * @param brickSizeRatio the ratio of the brick size
     * @param type the type of the brick
     * @return an array of brick objects
     */
    private Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,type);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = new ClayBrick(p,brickSize);
        }
        return tmp;

    }

    /**
     * method to create an array of a two type of bricks in a chessboard pattern
     * @param drawArea the area of the wall
     * @param brickCnt the amount of brick
     * @param lineCnt the amount of lines
     * @param brickSizeRatio the ratio of the brick size
     * @param typeA the type of the brick
     * @param typeB the type of the brick
     * @return an array of the brick objects
     */
    public Brick[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ?  makeBrick(p,brickSize,typeA) : makeBrick(p,brickSize,typeB);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,typeA);
        }
        return tmp;
    }

    /**
     * @param ballPos coordinates of the position of the ball
     */
    public void makeBall(Point2D ballPos){
        ballf = new BallFactory();
        bc = ballf.getBallType("RUBBER",ballPos);
    }

    /**
     * method to create multiple levels
     * @param drawArea the area of the wall
     * @param brickCount the amount of brick
     * @param lineCount the amount of line
     * @param brickDimensionRatio the ratio of the brick size
     * @return an 2D array of the brick objects
     */
    public Brick[][] makeLevels(Rectangle drawArea,int brickCount,int lineCount,double brickDimensionRatio){
        Brick[][] tmp = new Brick[WallM.LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio, WallM.CLAY);
        tmp[1] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio, WallM.CLAY, WallM.CEMENT);
        tmp[2] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio, WallM.CLAY, WallM.STEEL);
        tmp[3] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio, WallM.STEEL, WallM.CEMENT);
        tmp[4] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio, WallM.CLAY, WallM.DIAMOND);
        tmp[5] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio, WallM.DIAMOND, WallM.CEMENT);
        tmp[6] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio, WallM.DIAMOND, WallM.STEEL);
        return tmp;
    }

    /**
     * a method to move the player and the ball
     */
    public void move(){
        Player.getInstance().move();
        bc.move();
    }

    /**
     * a method to checks if there is any impact on the wall or border
     */
    public void findImpacts(){
        if(Player.getInstance().impact(ball)){
            bc.reverseY();
        }
        else if(impactWall()){
            wm.setPowerup(true);
            wm.p = new Powerup(bm.getPosition());
            wm.setBrickCount(wm.getBrickCount()-1);
            wm.setCurrentHighScore(wm.getCurrentHighScore()+1);
        }
        else if(impactBorder()) {
            bc.reverseX();
        }
        else if(bm.getPosition().getY() < wm.area.getY()){
            bc.reverseY();
        }
        else if(bm.getPosition().getY() > wm.area.getY() + wm.area.getHeight()+ 100){
            wm.setBallCount(wm.getBallCount()-1);
            wm.setBallLost(true);
        }
        if (wm.isCollected()){
            wm.setCurrentHighScore(wm.getCurrentHighScore()+2);
            wm.setCollected(false);
        }
    }

    /**
     * @return true if there's impact on the wall, false if there's not
     */
    private boolean impactWall(){
        for(Brick b : bricks){
            switch(b.findImpact(ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    bc.reverseY();
                    return b.setImpact(bm.getDown(), Crack.UP);
                case Brick.DOWN_IMPACT:
                    bc.reverseY();
                    return b.setImpact(bm.getUp(),Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    bc.reverseX();
                    return b.setImpact(bm.getRight(),Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    bc.reverseX();
                    return b.setImpact(bm.getLeft(),Crack.LEFT);
            }
        }
        return false;
    }

    /**
     * @return true if there's impact on the border
     */
    private boolean impactBorder(){
        Point2D p = bm.getPosition();
        return ((p.getX() < wm.area.getX()) ||(p.getX() > (wm.area.getX() + wm.area.getWidth())));
    }

    /**
     * reset the ball onto the player's rectangle if the player lost the ball or the next level
     */
    public void ballReset(){
        Player.getInstance().moveTo(wm.getStartPoint());
        bc.moveTo(wm.getStartPoint());


        int speedX,speedY;
        speedX = 8;
        speedY = -3;

        bm.setSpeed(speedX,speedY);
        wm.setBallLost(false);
    }

    public void wallReset(){
        for(Brick b : bricks)
            b.repair();
        wm.setBrickCount(bricks.length);
        wm.setBallCount(3);
        wm.setFinalHighScore(wm.getCurrentHighScore());
        wm.setCurrentHighScore(0);
    }

    public void nextLevel(){
        bricks = wm.levels[wm.getLevel()+1];
        wm.setBrickCount(bricks.length);
    }

    /**
     * @param point the position of the brick
     * @param size the size  of the brick
     * @param type the type of the brick
     * @return brick object
     */
    private Brick makeBrick(Point point, Dimension size, int type){
        brickf = new BrickFactory();
        Brick out;
        switch(type){
            case WallM.CLAY:
                out = new ClayBrick(point,size);
                out = brickf.getBrickType("CLAY",point,size);
                break;
            case WallM.STEEL:
                out = new SteelBrick(point,size);
                out = brickf.getBrickType("STEEL",point,size);
                break;
            case WallM.CEMENT:
                out = new CementBrick(point, size);
                out = brickf.getBrickType("CEMENT",point,size);
                break;
            case WallM.DIAMOND:
                out = new DiamondBrick(point,size);
                out = brickf.getBrickType("DIAMOND",point,size);
                break;
            default:
                throw  new IllegalArgumentException(String.format("Unknown Type:%d\n",type));
        }
        return  out;
    }
}
