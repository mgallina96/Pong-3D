package model.listener;

import javafx.geometry.Point3D;

/**
 * Ball events listener.
 *
 * @author Manuel Gallina
 * @author Giosuè Filippini
 */
@FunctionalInterface
public interface BallListener extends GameElementListener {
    /**
     * Executes this action when triggered.
     * It's triggered whenever the ball changes its direction or its state, for example when bouncing on
     * the walls or touches the players.
     *
     * @param position  The ball position.
     * @param direction The ball direction.
     */
    void onBallEvent(Point3D position, Point3D direction);
}
