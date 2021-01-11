import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor sensor;
    private Rectangle rec;
    private double edgeBlockWidth;
    private int paddleSpeed;

    /**
     * The constructor for Paddle.
     *
     * @param gui       is the gui the animation runs there.
     * @param rectangle is the rectangle who represent the shape of the paddle.
     * @param eBW       is the width of the edge block
     * @param speed     is the speed of the paddle.
     */
    public Paddle(GUI gui, Rectangle rectangle, double eBW, int speed) {
        this.sensor = gui.getKeyboardSensor();
        this.rec = rectangle;
        this.edgeBlockWidth = eBW;
        this.paddleSpeed = speed;
    }

    /**
     * The function move the paddle to the left.
     */
    public void moveLeft() {
        //each move is 'paddleSpeed' pixels, 800 is the given width of the GUI(the game GUI)
        if (this.rec.getUpperLeft().getX() - this.paddleSpeed - this.edgeBlockWidth <= 0) {
            this.rec.getUpperLeft().setX(this.edgeBlockWidth);
            return;
        }
        this.rec.getUpperLeft().setX(this.rec.getUpperLeft().getX() - this.paddleSpeed);
    }

    /**
     * The function move the paddle to the right.
     */
    public void moveRight() {
        //each move is 'paddleSpeed' pixels, 800 is the given width of the GUI(the game GUI)
        if (this.rec.getUpperLeft().getX() + this.rec.getWidth() + this.paddleSpeed + this.edgeBlockWidth >= 800) {
            //put the paddle next to the edge
            this.rec.getUpperLeft().setX(800 - this.rec.getWidth() - this.edgeBlockWidth);
            return;
        }
        this.rec.getUpperLeft().setX(this.rec.getUpperLeft().getX() + this.paddleSpeed);
    }

    /**
     * The function checks if the paddle need to move right or left.
     */
    public void timePassed() {
        if (sensor.isPressed(sensor.RIGHT_KEY)) {
            moveRight();
        }
        if (sensor.isPressed(sensor.LEFT_KEY)) {
            moveLeft();
        }
    }

    /**
     * The function draw the paddle.
     *
     * @param d is the surface we want to draw the paddle on.
     */
    public void drawOn(DrawSurface d) {
        this.rec.drawOn(d);
    }

    /**
     * The function returns the collision rectangle.
     *
     * @return fhe function returns the collision rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    /**
     * The function calculate and returns the new velocity of the ball after the hit.
     *
     * @param hitter          is the ball that hit the collidable object.
     * @param collisionPoint  is the collision point of the ball and the collidable object
     * @param currentVelocity is the current velocity of the ball.
     * @return fhe function returns the new velocity of the ball.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint == null || currentVelocity == null) {
            return null;
        }
        //get the width of every region of the paddle
        double regionLength = this.rec.getWidth() / 5;
        double paddleColl = collisionPoint.getX() - this.rec.getUpperLeft().getX();
        //get the speed of the ball according to the dx and the dy values
        double speed = Math.sqrt((Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2)));
        //check which region of the paddle the ball hit and change the position,velocity of the ball accordingly
        if (paddleColl >= 0 && paddleColl < regionLength) {
            return Velocity.fromAngleAndSpeed(210, speed);
        } else if (paddleColl >= regionLength && paddleColl < 2 * regionLength) {
            return Velocity.fromAngleAndSpeed(240, speed);
        } else if (paddleColl >= regionLength * 2 && paddleColl < 3 * regionLength) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (paddleColl >= regionLength * 3 && paddleColl < 4 * regionLength) {
            return Velocity.fromAngleAndSpeed(300, speed);
        }
        return Velocity.fromAngleAndSpeed(330, speed);
    }

    /**
     * The function add the paddle to the game.
     *
     * @param g is the game variable we ant to add the paddle in.
     */
    public void addToGame(GameLevel g) {
        if (g == null) {
            return;
        }
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Getter for paddle speed.
     *
     * @return the function returns the value of the paddle speed.
     */
    public int getPaddleSpeed() {
        return paddleSpeed;
    }

    /**
     * Setter for paddle GUI.
     *
     * @param keyboard is the new keyboard of the paddle.
     */
    public void setKeyboard(KeyboardSensor keyboard) {
        this.sensor = keyboard;
    }
}
