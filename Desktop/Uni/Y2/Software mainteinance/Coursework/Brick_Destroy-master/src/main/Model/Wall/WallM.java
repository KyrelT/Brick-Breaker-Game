package main.Model.Wall;

import main.Controller.WallController;
import main.java.*;

import java.awt.*;
import java.util.Random;

public class WallM {
    public static final int LEVELS_COUNT = 7;

    public static final int CLAY = 1;
    public static final int STEEL = 2;
    public static final int CEMENT = 3;
    public static final int DIAMOND = 4;

    private Random rnd;
    public Rectangle area;

    Brick[] bricks;
    Ball ball;
    WallController wc;

    public Brick[][] levels;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    private int level;

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    private Point startPoint;
    private int brickCount;

    public void setCurrentHighScore(int currentHighScore) {
        CurrentHighScore = currentHighScore;
    }

    private int CurrentHighScore;
    private int ballCount;

    public void setFinalHighScore(int finalHighScore) {
        FinalHighScore = finalHighScore;
    }

    private int FinalHighScore;

    public boolean isCollected() {
        return collected;
    }

    private boolean collected;


    private boolean ballLost;

    public boolean isPowerup() {
        return isPowerup;
    }

    public void setPowerup(boolean powerup) {
        isPowerup = powerup;
    }

    public boolean isPowerup;
    public Powerup p;

    public Ball getBall() {
        return ball;
    }

    public void setBrickCount(int brickCount) {
        this.brickCount = brickCount;
    }

    /**
     * @param drawArea the area of the wall
     * @param brickCount the amount of brick
     * @param lineCount the amount of lines
     * @param brickDimensionRatio the ratio of the brick shape
     * @param ballPos the position of the ball
     */
    public WallM(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){

        this.startPoint = new Point(ballPos);

        levels = wc.makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;

        ballCount = 3;
        ballLost = false;

        rnd = new Random();

        wc.makeBall(ballPos);

        int speedX,speedY;
        speedX = 8; // changing speed of the ball , when speed = 0, speedX keeps random-ing number , <=0 then left
        speedY = -3;// negative = upwards
        ball.setSpeed(speedX,speedY);

        //player = new ((Point) ballPos.clone(),150,10, drawArea);

        Player.getInstance((Point) ballPos.clone(),150,10,drawArea);

        area = drawArea;


    }

    public int getCurrentHighScore() {
        return CurrentHighScore;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    /**
     * @return the amount of bricks
     */
    public int getBrickCount(){
        return brickCount;
    }

    public void setBallCount(int ballCount) {
        this.ballCount = ballCount;
    }

    /**
     * @return the amount of ball
     */
    public int getBallCount(){
        return ballCount;
    }

    public void setBallLost(boolean ballLost) {
        this.ballLost = ballLost;
    }

    public boolean isBallLost(){
        return ballLost;
    }

    public int getFinalHighScore() {
        return FinalHighScore;
    }

    public boolean ballEnd(){
        return ballCount == 0;
    }

    public boolean isDone(){
        return brickCount == 0;
    }

    public boolean hasLevel(){
        return level < levels.length;
    }

    public void setBallXSpeed(int s){
        ball.setXSpeed(s);
    }

    public void setBallYSpeed(int s){
        ball.setYSpeed(s);
    }

    public void resetBallCount(){
        ballCount = 3;
    }
}
