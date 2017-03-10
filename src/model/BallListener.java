package model;

import javafx.geometry.Point3D;

/**
 * Ball events listener.
 * It's triggered whenever the ball changes its direction.
 *
 * @author Manuel Gallina
 */
public interface BallListener {
    /**
     * Executes this action when triggered.
     *
     * @param position  The ball position.
     * @param direction The ball direction.
     */
    void onBallEvent(Point3D position, Point3D direction);
}
