package controller;

import javafx.geometry.Point3D;

/**
 * Player type and behavior.
 */
public enum PlayerTag {
    P1(new Point3D(0, 0, -400)), P2(new Point3D(0, 0, 400)),
    AI1(new Point3D(0, 0, -400)), AI2(new Point3D(0, 0, 400));

    private Point3D position;

    PlayerTag(Point3D position) {
        this.position = position;
    }

    /**
     * @return The position.
     */
    public Point3D getPosition() {
        return position;
    }
}
