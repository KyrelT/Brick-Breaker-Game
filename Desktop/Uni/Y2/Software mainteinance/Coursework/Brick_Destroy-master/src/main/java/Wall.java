package main.java;

import main.Controller.BallControl;
import main.Controller.BrickControl;
import main.Controller.PlayerControl;
import main.Model.Brick.BrickModel;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


public class Wall {

    private static final int LEVELS_COUNT = 8;

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    private static final int DIAMOND = 4;
    private static final int BOMB = 5;

    private final Rectangle area;

    BrickControl[] bricks;
    BallControl ball;
    BrickFactory brickfactory;
    BallFactory ballfactory;
    BombBrick bb;

    private final BrickControl[][] levels;
    private int level;

    public Point startPoint;
    private int brickCount;
    private int CurrentHighScore;
    private int ballCount;
    private int FinalHighScore;
    private boolean collected;
    private boolean isBomb;
    public boolean showEndscreen;

    PlayerControl player;


    private boolean ballLost;
    public boolean isPowerup;
    public Powerup p;


/**
     * @param drawArea the area of the wall
     * @param brickCount the amount of brick
     * @param lineCount the amount of lines
     * @param brickDimensionRatio the ratio of the brick shape
     * @param ballPos the position of the ball
    */

    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){

        this.startPoint = new Point(ballPos);

        levels = makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;

        makeBall(ballPos);
        this.ball = getBall();
        ballCount = 3;
        ballLost = false;


        int speedX,speedY;
            speedX = 8; // changing speed of the ball , when speed = 0, speedX keeps random-ing number , <=0 then left
            speedY = -3;// negative = upwards
        ball.setSpeed(speedX,speedY);

        player = PlayerControl.getInstance((Point) ballPos.clone(),150,10,drawArea);

        area = drawArea;


    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    /**
     * @param drawArea the area of the wall
     * @param brickCnt the amount of bricks
     * @param lineCnt the amount of lines
     * @param brickSizeRatio the ratio of the brick size
     * @param type the type of the brick
     * @return an array of brick objects*/


    private BrickControl[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type){


        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        BrickControl[] tmp  = new BrickControl[brickCnt];

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

    private BrickControl[] makeFunLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type){


        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        BrickControl[] tmp  = new BrickControl[brickCnt];

        setBomb(true);

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
            double x = (brickOnLine * brickLen) - (brickLen / 0.5);
            p.setLocation(x,y);
            tmp[i] = new BombBrick(p,brickSize);
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
     * @return an array of the brick objects*/


    private BrickControl[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB){


        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        BrickControl[] tmp  = new BrickControl[brickCnt];

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
     * @param ballPos coordinates of the position of the ball*/

    public void makeBall(Point2D ballPos){
        ballfactory = new BallFactory();
        ball = ballfactory.getBallType("RUBBER",ballPos);
    }


/**
     * method to create multiple levels
     * @param drawArea the area of the wall
     * @param brickCount the amount of brick
     * @param lineCount the amount of line
     * @param brickDimensionRatio the ratio of the brick size
     * @return an 2D array of the brick objects
     */
    private BrickControl[][] makeLevels(Rectangle drawArea,int brickCount,int lineCount,double brickDimensionRatio){
        BrickControl[][] tmp = new BrickControl[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY);
        tmp[1] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,CEMENT);
        tmp[2] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,STEEL);
        tmp[3] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,CEMENT);
        tmp[4] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,DIAMOND);
        tmp[5] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,DIAMOND,CEMENT);
        tmp[6] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,DIAMOND,STEEL);
        tmp[7] = makeFunLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CEMENT);
        return tmp;
    }


/**
     * a method to move the player and the ball
     */
    public void move(){
        player.getInstance().move();
        ball.move();
    }

    /**
     * @return get the current level's highscore
     */
    public int getCurrentHighScore() {
        return CurrentHighScore;
    }

    /**
     * @param collected boolean value if the power up is collected
     */
    public void setCollected(boolean collected) {
        this.collected = collected;
    }


/**
     * a method to checks if there is any impact on the wall or border
     */
    public void findImpacts(){
        if(player.getInstance().impact(ball)){
            ball.reverseY();
        }
        else if(impactWall()){
            isPowerup = true;
            this.p = new Powerup(ball.getPosition());
            brickCount--;
            CurrentHighScore++;
        }
        else if(impactBorder()) {
            ball.reverseX();
        }
        else if(ball.getPosition().getY() < area.getY()){
            ball.reverseY();
        }
        else if(ball.getPosition().getY() > area.getY() + area.getHeight()+ 100){
            ballCount--;
            ballLost = true;
        }
        if (collected){
            CurrentHighScore += 2;
            collected = false;
        }
    }


/**
     * @return true if there's impact on the wall, false if there's not
     */
    private boolean impactWall(){
        for(BrickControl b : bricks){
            switch(b.findImpact(ball)) {
                //Vertical Impact
                case BrickModel.UP_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.getDown(), Crack.UP);
                case BrickModel.DOWN_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.getUp(),Crack.DOWN);

                //Horizontal Impact
                case BrickModel.LEFT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.getRight(),Crack.RIGHT);
                case BrickModel.RIGHT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.getLeft(),Crack.LEFT);
            }
        }
        return false;
    }

/**
     * @return true if there's impact on the border*/

    private boolean impactBorder(){
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

/**
     * @return the amount of bricks
     */

    public int getBrickCount(){
        return brickCount;
    }

/**
     * @return the amount of ball
     */

    public int getBallCount(){
        return ballCount;
    }

    /**
     * @return a boolean to check if the ball is lost
     */
    public boolean isBallLost(){
        return ballLost;
    }

/**
     * reset the ball onto the player's rectangle if the player lost the ball or the next level
     */

    public void ballReset(){
        player.getInstance().moveTo(startPoint);
        ball.moveTo(startPoint);

        int speedX,speedY;
            speedX = -8;
            speedY = -3;

        ball.setSpeed(speedX,speedY);
        ballLost = false;
    }

    /**
     * @return get the level's final highscore
     */
    public int getFinalHighScore() {
        return FinalHighScore;
    }

    /**
     * reset the bricks , ball , ballcount, highscore
     */
    public void wallReset(){
        for(BrickControl b : bricks)
            b.repair();
        brickCount = bricks.length;
        ballCount = 3;
        FinalHighScore = CurrentHighScore;
        CurrentHighScore = 0;
    }

    public BallControl getBall() {
        return ball;
    }

    /**
     * @return a boolean value to check if there's any ball left
     */
    public boolean ballEnd(){
        return ballCount == 0;
    }

    /**
     * @return a boolean value to check if there's any brick left
     */
    public boolean isDone(){
        return brickCount == 0;
    }

    /**
     * get to the next level after all the bricks is finished
     */
    public void nextLevel(){
        bricks = levels[level++];
        this.brickCount = bricks.length;
    }

    public boolean hasLevel(){
        return level < levels.length;
    }

    /**
     * @param s int value for ball x speed
     */
    public void setBallXSpeed(int s){
        ball.setXSpeed(s);
    }

    /**
     * @param s int value for ball y speed
     */
    public void setBallYSpeed(int s){
        ball.setYSpeed(s);
    }

    /**
     * reset the ball count back to 3
     */
    public void resetBallCount(){
        ballCount = 3;
    }

/**
     * @param point the position of the brick
     * @param size the size  of the brick
     * @param type the type of the brick
     * @return brick object
     */

    private BrickControl makeBrick(Point point, Dimension size, int type){
        brickfactory = new BrickFactory();
        BrickControl out;
        switch(type){
            case CLAY:
                out = brickfactory.getBrickType("CLAY",point,size);
                break;
            case STEEL:
                out = brickfactory.getBrickType("STEEL",point,size);
                break;
            case CEMENT:
                out = brickfactory.getBrickType("CEMENT",point,size);
                break;
            case DIAMOND:
                out = brickfactory.getBrickType("DIAMOND",point,size);
                break;
            case BOMB:
                out = brickfactory.getBrickType("BOMB",point,size);
                break;
            default:
                throw  new IllegalArgumentException(String.format("Unknown Type:%d\n",type));
        }
        return  out;
    }

    public BrickControl[] getBricks() {return bricks;}


}
