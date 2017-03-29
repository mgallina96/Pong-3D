package model;

import application.Settings;
import controller.Ball;
import controller.Field;
import controller.Player;
import controller.gameelement.GameElement;
import controller.gameelement.GameElementModel;
import javafx.geometry.Point3D;
import model.listener.BallListener;
import model.listener.GameElementListener;
import model.listener.PlayerListener;
import utility.geometry3d.Dimension3D;
import utility.sound.SoundPlayer;

import java.util.List;

/**
 * The ball model.
 * This class defines how the ball moves.
 *
 * @author Manuel Gallina
 * @author Giosuè Filippini
 */
public class BallModel extends GameElementModel implements PlayerListener {
    /**
     * The ball radius.
     */
    public static final double RADIUS = 30.0;
    private static final String BOUNCE_SOUND_URL = "out/production/Pong3D/music/Pong 3D - Contatto.wav";

    private final List<GameElement> objectsList;
    private final Field field;

    // Coordinates bounds.
    private final double left;
    private final double right;
    private final double top;
    private final double bottom;
    private final double front;
    private final double back;

    private Point3D position;
    private Point3D direction;
    private double speed;

    /**
     * Constructor.
     *
     * @param controller The ball controller class.
     */
    public BallModel(Ball controller) {
        super(controller);
        position = ORIGIN;
        //TODO Random starting direction generator has to be improved.
        direction = new Point3D(Math.random() * 30 - 15, Math.random() * 30 - 15, Math.random() * 100 - 50);
        speed = Settings.difficulty.getSpeed();

        objectsList = controller.getGame().getObjectsList();
        field = (Field) controller.getGame().getObjectsList().get(0);

        // Coordinates bounds.
        left = -field.getSize().getWidth() / 2 + field.getPosition().getX() + RADIUS / 2;
        right = field.getSize().getWidth() / 2 + field.getPosition().getX() - RADIUS / 2;

        top = field.getSize().getHeight() / 2 + field.getPosition().getY() - RADIUS / 2;
        bottom = -field.getSize().getHeight() / 2 + field.getPosition().getY() + RADIUS / 2;

        front = field.getSize().getDepth() / 2 + field.getPosition().getZ() - RADIUS / 2;
        back = -field.getSize().getDepth() / 2 + field.getPosition().getZ() + RADIUS / 2;
    }

    /** Starts listening to all the players events. */
    public void addPlayers() {
        for (GameElement e : objectsList) {
            if (e instanceof Player)
                e.addListener(this);
        }
    }

    /** Calculates the position of the ball in the next frame. */
    @Override
    public void updatePosition() {
        double l = direction.getX();
        double m = direction.getY();
        double n = direction.getZ();

        // Work in progress
        if (Math.abs(l) > 30)
            l = 30 * Math.signum(l);
        if (Math.abs(m) > 30)
            m = 30 * Math.signum(m);
        //////////////////////////////////

        double k = speed / Math.sqrt(n * n + m * m + l * l);

        if (Math.abs(this.position.getZ()) > field.getSize().getDepth()) {
            this.position = ORIGIN; //set a default position (origin).
            speed = 0;
            getController().getGame().getScene().setOnMouseClicked(event -> speed = Settings.difficulty.getSpeed());
        }

        boolean aiUpdate = false;
        if (new Point3D(position.getX(), position.getY(), position.getZ()).equals(ORIGIN))
            aiUpdate = true;

        double x = l * k + position.getX();
        double y = m * k + position.getY();
        double z = n * k + position.getZ();

        // Bound check.
        if (x > right) {
            x = right;
            l = -l;
            aiUpdate = true;
            new SoundPlayer().playSound(BOUNCE_SOUND_URL);
        } else if (x < left) {
            x = left;
            l = -l;
            aiUpdate = true;
            new SoundPlayer().playSound(BOUNCE_SOUND_URL);
        }

        if (y > top) {
            y = top;
            m = -m;
            aiUpdate = true;
            new SoundPlayer().playSound(BOUNCE_SOUND_URL);
        } else if (y < bottom) {
            y = bottom;
            m = -m;
            aiUpdate = true;
            new SoundPlayer().playSound(BOUNCE_SOUND_URL);
        }

        if (z > front || z < back)
            aiUpdate = true;

        position = new Point3D(x, y, z);
        direction = new Point3D(l, m, n);

        if (aiUpdate)
            fireEvent();
    }

    /** @return The ball position. */
    public Point3D getPosition() {
        return position;
    }

    /* Fires an event. */
    private void fireEvent() {
        for (GameElementListener listener : getListenersList()) {
            ((BallListener) listener).onBallEvent(position, direction);
        }
    }

    @Override
    public void onPlayerEvent(Point3D position, Dimension3D size) {
        double l = direction.getX();
        double m = direction.getY();
        double n = direction.getZ();

        double xB = this.position.getX();
        double yB = this.position.getY();

        double xP = position.getX();
        double yP = position.getY();

        double pWidth = size.getWidth();
        double pHeight = size.getHeight();

        if ((Math.abs(xB - xP) - RADIUS < pWidth / 2) && (Math.abs(yB - yP) - RADIUS < pHeight / 2)) {
            n = -n;
            new SoundPlayer().playSound(BOUNCE_SOUND_URL);

            if (Math.abs(xB - xP) > pWidth / 3) {
                l = 3 * l;
            }

            if (Math.abs(yB - yP) > pHeight / 3) {
                m = 3 * m;
            }

            if (Math.abs(xB - xP) < pWidth / 3 && Math.abs(yB - yP) < pHeight / 3) {
                l = l / 3;
                m = m / 3;
            }

            direction = new Point3D(l, m, n);
            fireEvent();
        }
    }
}
