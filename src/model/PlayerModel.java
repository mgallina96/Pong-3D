package model;

import application.Settings;
import controller.Ball;
import controller.Field;
import controller.Player;
import controller.PlayerTag;
import controller.gameelement.GameElement;
import controller.gameelement.GameElementModel;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.input.MouseEvent;
import model.listener.BallListener;
import model.listener.GameElementListener;
import model.listener.PlayerListener;
import utility.sound.SoundPlayer;

import java.util.List;
import java.util.logging.Logger;

import static controller.Player.SIZE;

/**
 * The Player model component.
 * <p>
 * This class defines how the player and the AI move. It also updates their position.
 * It works with the {@link model.BallModel ball model} to check if the ball bounces on the player's bat.
 *
 * @author Manuel Gallina
 * @author Giosuè Filippini
 */
public class PlayerModel extends GameElementModel implements BallListener {
    private static final Logger LOGGER = Logger.getLogger(PlayerModel.class.getName());

    private static final String POINT_SOUND_URL = "out/production/Pong3D/music/Pong 3D - Vittoria.wav";

    private Field field;

    private PlayerTag tag;
    private int score;

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
        super(controller);

        this.tag = tag;
        position = tag.getPosition();
        target = ORIGIN;
        instantTarget = ORIGIN;
        speed = Settings.aiDifficulty.getSpeed();
        score = 0;

        if (tag == PlayerTag.P1 || tag == PlayerTag.P2) // Player moves faster than AI.
            speed = 15; //15 - 20

        List<GameElement> objectsList = controller.getGame().getObjectsList();
        field = (Field) objectsList.get(0);

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
            EventHandler<MouseEvent> mouseEventHandler = e -> {
                double cursorX = e.getSceneX() - controller.getGame().getScene().getWidth() / 2;
                double cursorY = e.getSceneY() - controller.getGame().getScene().getHeight() / 2;

                instantTarget = new Point3D((int) (fieldWidth * cursorX / sceneWidth), (int) (fieldHeight * cursorY / sceneHeight), 0);
            };
            controller.getGame().getScene().setOnMouseMoved(mouseEventHandler);
            controller.getGame().getScene().setOnMouseDragged(mouseEventHandler);
        }

        for (GameElement e : objectsList) { // Listening to the ball(s).
            if (e instanceof Ball)
                e.addListener(this);
        }
    }

    /** @return The player position. */
    public Point3D getPosition() {
        return position;
    }

    /** Updates the position of the player, following the target position. */
    public void updatePosition() {
        target = instantTarget;

        double l = target.getX() - position.getX();
        double m = target.getY() - position.getY();

        double k;

        double x;
        double y;

        if (Math.abs(m) > speed || Math.abs(l) > speed) {   // If the player is far from the target.
            k = speed / Math.sqrt(m * m + l * l);

            x = l * k + position.getX();
            y = m * k + position.getY();

        } else {    // If the player is near to the target.
            x = target.getX();
            y = target.getY();
        }

        // Bound check.
        if (x > right) {
            x = right;
        } else if (x < left) {
            x = left;
        }

        if (y > top) {
            y = top;
        } else if (y < bottom) {
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

        double zP = this.position.getZ();
        double fDepth = field.getSize().getDepth();

        // AI logic. It updates its position when the ball bounces and it's moving towards the player.
        if (this.tag == PlayerTag.AI1 || this.tag == PlayerTag.AI2
                && n / zP > 0 && Math.abs(z0) < fDepth / 2
                && Math.abs(fDepth / 2 * Math.signum(n) - z0) > 30) {
            double iX = (l / n) * (tag.getPosition().getZ() - z0) + x0;
            double iY = (m / l) * (iX - x0) + y0;

            double r = Settings.aiDifficulty.getPrecision();     // This determines the AI precision. The lower the better. A value of 160 is good.

            instantTarget = new Point3D(iX + Math.random() * r - r / 2, iY + Math.random() * r - r / 2, 0);
        }

        if (n / this.position.getZ() < 0 && Math.abs(z0) > field.getSize().getDepth()) {
            this.score++;
            new SoundPlayer().playSound(POINT_SOUND_URL);
            LOGGER.info(String.format("%s: %d \n", tag, score));
        }

        if (Math.abs(fDepth / 2 * Math.signum(n) - z0) < 30 && n / zP > 0)
            fireEvent();
    }

    /* Fires an event. */
    private void fireEvent() {
        for (GameElementListener listener : getListenersList()) {
            ((PlayerListener) listener).onPlayerEvent(position, SIZE);
        }
    }

    public int getScore() {
        return score;
    }
}
