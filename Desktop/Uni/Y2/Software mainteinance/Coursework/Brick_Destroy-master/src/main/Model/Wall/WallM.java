/*
package main.Model.Wall;

import main.Controller.BallController;
import main.Controller.BrickController;
import main.Controller.PlayerController;
import main.Controller.WallController;
import main.Model.Ball.BallM;
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

    WallController wallController;
    private BallController ball;
    public BrickController brickController;

    public BrickController[][] levels;
    private BrickController[] bricks;

    public BrickController[] getBricks() {
        return bricks;
    }

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
    private static int ballCount;

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

    public BallController getBall() {
        return ball;
    }

    public void setBall(BallController ball){this.ball = ball;}

    public void setBrickCount(int brickCount) {
        this.brickCount = brickCount;
    }

    */
/**
     * @param drawArea the area of the wall
     * @param ballPos the position of the ball
     *//*

    public WallM(Rectangle drawArea, Point ballPos){

        this.startPoint = new Point(ballPos);

       // levels = wallController.makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;

        ballCount = 3;
        ballLost = false;

        */
/*rnd = new Random();*//*


        //wallController.makeBall(ballPos);

       */
/* int speedX,speedY;
        speedX = 8; // changing speed of the ball , when speed = 0, speedX keeps random-ing number , <=0 then left
        speedY = -3;// negative = upwards
        BallM.setSpeed(speedX,speedY);

        //player = new ((Point) ballPos.clone(),150,10, drawArea);

        PlayerController.getInstance((Point) ballPos.clone(),150,10,drawArea);*//*


        area = drawArea;


    }

    public int getCurrentHighScore() {
        return CurrentHighScore;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    */
/**
     * @return the amount of bricks
     *//*

    public int getBrickCount(){
        return brickCount;
    }

    public void setBallCount(int ballCount) {
        this.ballCount = ballCount;
    }

    */
/**
     * @return the amount of ball
     *//*

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

    public static void setBallXSpeed(int s){
        BallController.setXSpeed(s);
    }

    public static void setBallYSpeed(int s){
        BallController.setYSpeed(s);
    }

    public static void resetBallCount(){
        ballCount = 3;
    }

    public void setBricks(BrickController[] bricks){
        this.bricks = bricks;
    }

}
*/
