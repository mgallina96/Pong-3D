package model;

import controller.*;
import javafx.geometry.Point3D;
import utility.geometry.geometry3d.Dimension3D;

import java.util.List;

/**
 * The Player model.
 * This class defines how the player moves.
 *
 * @author Manuel Gallina
 */
public class PlayerModel implements BallListener {
    private static final Dimension3D SIZE = new Dimension3D(80, 80, 2);

    private PlayerTag tag;

    private Point3D position;
    private Point3D target;
    private Point3D instantTarget; //This field is used to avoid changes in the target position during calculations.
    private double speed;

    //Coordinates bounds.
    private double left;
    private double right;

    private double top;
    private double bottom;

    /**
     * Constructor.
     *
     * @param controller The player controller class.
     * @param tag        The player tag.
     */
    public PlayerModel(Player controller, PlayerTag tag) {
        this.tag = tag;
        position = tag.getPosition();
        target = new Point3D(0, 0, 0);
        instantTarget = new Point3D(0, 0, 0);
        speed = 25;

        List<GameElement> objectsList = controller.getGame().getObjectsList();
        Field field = (Field) objectsList.get(0);

        left = -field.getSize().getWidth() / 2 + field.getPosition().getX() + SIZE.getWidth() / 2;
        right = field.getSize().getWidth() / 2 + field.getPosition().getX() - SIZE.getWidth() / 2;

        top = field.getSize().getHeight() / 2 + field.getPosition().getY() - SIZE.getHeight() / 2;
        bottom = -field.getSize().getHeight() / 2 + field.getPosition().getY() + SIZE.getHeight() / 2;

        // Target update to cursor position.
        double fieldWidth = field.getSize().getWidth();
        double fieldHeight = field.getSize().getHeight();

        double sceneWidth = controller.getGame().getScene().getWidth();
        double sceneHeight = controller.getGame().getScene().getHeight();

        if (this.tag == PlayerTag.P1 || this.tag == PlayerTag.P2) {
            controller.getGame().getScene().setOnMouseMoved(e -> {
                double cursorX = e.getSceneX() - controller.getGame().getScene().getWidth() / 2;
                double cursorY = e.getSceneY() - controller.getGame().getScene().getHeight() / 2;

                instantTarget = new Point3D((int) (fieldWidth * cursorX / sceneWidth), (int) (fieldHeight * cursorY / sceneHeight), 0);
            });
            controller.getGame().getScene().setOnMouseDragged(e -> {
                double cursorX = e.getSceneX() - controller.getGame().getScene().getWidth() / 2;
                double cursorY = e.getSceneY() - controller.getGame().getScene().getHeight() / 2;

                instantTarget = new Point3D((int) (fieldWidth * cursorX / sceneWidth), (int) (fieldHeight * cursorY / sceneHeight), 0);
            });
        } else if (this.tag == PlayerTag.AI1 || this.tag == PlayerTag.AI2) {
            for (GameElement e : objectsList) {
                if (e instanceof Ball)
                    ((Ball) e).addListener(this);
            }
        }
    }

    /**
     * @return The player size.
     */
    public Dimension3D getSize() {
        return SIZE;
    }

    /**
     * @return The player position.
     */
    public Point3D getPosition() {
        return position;
    }

    /**
     * Updates the position of the player, based on the target position.
     */
    public void updatePosition() {
        target = instantTarget;

        double l = target.getX() - position.getX();
        double m = target.getY() - position.getY();
        double k;

        double x, y;

        if (Math.abs(m) > speed || Math.abs(l) > speed) {
            k = speed / Math.sqrt(m * m + l * l);

            x = l * k + position.getX();
            y = m * k + position.getY();

        } else {
            x = target.getX();
            y = target.getY();
        }

        if (x > right) {
            x = right;
        }
        if (x < left) {
            x = left;
        }

        if (y > top) {
            y = top;
        }
        if (y < bottom) {
            y = bottom;
        }

        position = new Point3D(x, y, tag.getPosition().getZ());
    }

    @Override
    public void onBallEvent(Point3D position, Point3D direction) {
        double l = direction.getX();
        double m = direction.getY();
        double n = direction.getZ();

        double x0 = position.getX();
        double y0 = position.getY();
        double z0 = position.getZ();

        if ((tag == PlayerTag.AI2 && direction.getZ() > 0) || (tag == PlayerTag.AI1 && direction.getZ() < 0)) {
            double iX = (l / n) * (tag.getPosition().getZ() - z0) + x0;
            double iY = (m / l) * (iX - x0) + y0;

            double r = 160;     // This determines the AI precision. The lower the better. A value of 160 is good.

            instantTarget = new Point3D(iX + Math.random() * r - r / 2, iY + Math.random() * r - r / 2, 0);
        }
    }
}
