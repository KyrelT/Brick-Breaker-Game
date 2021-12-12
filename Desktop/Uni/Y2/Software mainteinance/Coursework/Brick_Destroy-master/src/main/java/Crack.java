package main.java;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

import static main.Model.Brick.BrickModel.getRnd;

//import static main.java.Brick.getRnd;

public class Crack {

        private static final int CRACK_SECTIONS = 3;
        private static final double JUMP_PROBABILITY = 0.7;

        public static final int LEFT = 10;
        public static final int RIGHT = 20;
        public static final int UP = 30;
        public static final int DOWN = 40;
        public static final int VERTICAL = 100;
        public static final int HORIZONTAL = 200;



        private GeneralPath crack;

        private int crackDepth;
        private int steps;


        /**
         * @param crackDepth the depth of crack
         * @param steps
         */
        public Crack(int crackDepth, int steps){

            crack = new GeneralPath();
            this.crackDepth = crackDepth;
            this.steps = steps;

        }


        /**
         * @return the crack of the brick
         */
        public GeneralPath draw(){

            return crack;
        }

    /**
     * reset/erase the crack visuals
     */
        public void reset(){
            crack.reset();
        }

        /**
         * create the visuals of the crack on the brick
         * @param point position of the brick
         * @param direction which direction of the brick had impacted
         */
        protected void makeCrack(Shape brickFace,Point2D point, int direction){
            Rectangle bounds = brickFace.getBounds();

            Point impact = new Point((int)point.getX(),(int)point.getY());
            Point start = new Point();
            Point end = new Point();


            switch(direction){
                case LEFT:
                    start.setLocation(bounds.x + bounds.width, bounds.y);
                    end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                    Point tmp = makeRandomPoint(start,end,VERTICAL);
                    makeCrack(impact,tmp);

                    break;
                case RIGHT:
                    start.setLocation(bounds.getLocation());
                    end.setLocation(bounds.x, bounds.y + bounds.height);
                    tmp = makeRandomPoint(start,end,VERTICAL);
                    makeCrack(impact,tmp);

                    break;
                case UP:
                    start.setLocation(bounds.x, bounds.y + bounds.height);
                    end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                    tmp = makeRandomPoint(start,end,HORIZONTAL);
                    makeCrack(impact,tmp);
                    break;
                case DOWN:
                    start.setLocation(bounds.getLocation());
                    end.setLocation(bounds.x + bounds.width, bounds.y);
                    tmp = makeRandomPoint(start,end,HORIZONTAL);
                    makeCrack(impact,tmp);

                    break;

            }
        }

    /**
     * @param start start point of a line in the brick
     * @param end end point of a line in the brick
     */
        protected void makeCrack(Point start, Point end){

            GeneralPath path = new GeneralPath();


            path.moveTo(start.x,start.y);

            double w = (end.x - start.x) / (double)steps;
            double h = (end.y - start.y) / (double)steps;

            int bound = crackDepth;
            int jump  = bound * 5;

            double x,y;

            for(int i = 1; i < steps;i++){

                x = (i * w) + start.x;
                y = (i * h) + start.y + randomInBounds(bound);

                if(inMiddle(i,CRACK_SECTIONS,steps))
                    y += jumps(jump,JUMP_PROBABILITY);

                path.lineTo(x,y);

            }

            path.lineTo(end.x,end.y);
            crack.append(path,true);
        }

    /**
     * @param bound random value
     * @return integer of random value
     */
        private int randomInBounds(int bound){
            int n = (bound * 2) + 1;
            return getRnd().nextInt(n) - bound;
        }

    /**
     * @param i
     * @param steps
     * @param divisions
     * @return
     */
        private boolean inMiddle(int i,int steps,int divisions){
            int low = (steps / divisions);
            int up = low * (divisions - 1);

            return  (i > low) && (i < up);
        }

    /**
     * @param bound random value
     * @param probability a chance
     * @return
     */
        private int jumps(int bound,double probability){

            if(getRnd().nextDouble() > probability)
                return randomInBounds(bound);
            return  0;

        }

    /**
     * @param from start point
     * @param to end point
     * @param direction direction to a point
     * @return position
     */
        private Point makeRandomPoint(Point from,Point to, int direction){

            Point out = new Point();
            int pos;

            switch(direction){
                case HORIZONTAL:
                    pos = getRnd().nextInt(to.x - from.x) + from.x;
                    out.setLocation(pos,to.y);
                    break;
                case VERTICAL:
                    pos = getRnd().nextInt(to.y - from.y) + from.y;
                    out.setLocation(to.x,pos);
                    break;
            }
            return out;
        }

    }

