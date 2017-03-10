package model;

import controller.Ball;
import controller.Field;
import javafx.geometry.Point3D;

import java.util.ArrayList;
import java.util.List;

/**
 * The ball model.
 * This class defines how the ball moves.
 *
 * @author Manuel Gallina
 */
public class BallModel {
    /**
     * The ball radius.
     */
    public static final double RADIUS = 30.0;

    private List<BallListener> listenersList = new ArrayList<>();

    private Point3D position;
    private Point3D direction;
    private double speed;

    // Coordinates bounds.
    private double left;
    private double right;

    private double top;
    private double bottom;

    private double front;
    private double back;

    /**
     * Constructor.
     *
     * @param controller The ball controller class.
     */
    public BallModel(Ball controller) {
        position = new Point3D(0, 0, 0);
        direction = new Point3D(Math.random() * 30 - 15, Math.random() * 30 - 15, Math.random() * 100 - 50);
        speed = 10;

        Field field = (Field) controller.getGame().getObjectsList().get(0);

        left = -field.getSize().getWidth() / 2 + field.getPosition().getX() + RADIUS / 2;
        right = field.getSize().getWidth() / 2 + field.getPosition().getX() - RADIUS / 2;

        top = field.getSize().getHeight() / 2 + field.getPosition().getY() - RADIUS / 2;
        bottom = -field.getSize().getHeight() / 2 + field.getPosition().getY() + RADIUS / 2;

        front = field.getSize().getDepth() / 2 + field.getPosition().getZ() - RADIUS / 2;
        back = -field.getSize().getDepth() / 2 + field.getPosition().getZ() + RADIUS / 2;
    }

    /**
     * Calculates the position of the ball in the next frame.
     */
    public void updatePosition() {
        double l = direction.getX();
        double m = direction.getY();
        double n = direction.getZ();

        double k = speed / Math.sqrt(n * n + m * m + l * l);

        double x = l * k + position.getX();
        double y = m * k + position.getY();
        double z = n * k + position.getZ();

        boolean aiUpdate = false;

        if (x > right) {
            x = right;
            l = -l;
            direction = new Point3D(l, m, n);
            aiUpdate = true;
        }
        if (x < left) {
            x = left;
            l = -l;
            direction = new Point3D(l, m, n);
            aiUpdate = true;
        }

        if (y > top) {
            y = top;
            m = -m;
            direction = new Point3D(l, m, n);
            aiUpdate = true;
        }
        if (y < bottom) {
            y = bottom;
            m = -m;
            direction = new Point3D(l, m, n);
            aiUpdate = true;
        }

        if (z > front) {
            z = front;
            n = -n;
            direction = new Point3D(l, m, n);
            aiUpdate = true;
        }
        if (z < back) {
            z = back;
            n = -n;
            direction = new Point3D(l, m, n);
            aiUpdate = true;
        }

        position = new Point3D(x, y, z);

        if (aiUpdate)
            fireEvent();
    }

    /**
     * @return The ball position.
     */
    public Point3D getPosition() {
        return position;
    }

    /**
     * Adds a listener to the listeners list.
     *
     * @param listener The listener to add.
     */
    public void addListener(BallListener listener) {
        listenersList.add(listener);
    }

    private void fireEvent() {
        for (BallListener listener : listenersList) {
            listener.onBallEvent(position, direction);
        }
    }
}
