package test;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;


/**
 * a class so set up a new type of brick
 */
public class ClayBrick extends Brick {

    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
cd

        /**
         * @param point Getting the position of the brick.
         * @param size  Getting the size of the Clay brick.
         */
        public ClayBrick(Point point, Dimension size) {
            super(NAME, point, size, DEF_BORDER, DEF_INNER, CLAY_STRENGTH);
        }

    /**
     * @param pos position of the Clay Brick
     * @param size size of the Clay Brick
     * @return new rectangle position and size
     */
        @Override
        protected Shape makeBrickFace(Point pos, Dimension size) {
            return new Rectangle(pos, size);
        }

    /**
     * @return get shape of brick
     */
        @Override
        public Shape getBrick() {
            return super.brickFace;
        }


}
