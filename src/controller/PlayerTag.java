package controller;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;

/**
 * Player type and behavior.
 */
public enum PlayerTag {
    P1(new Point3D(0, 0, -400), Color.color(0.035, 0.87, 1.0), "PLAYER 1"), P2(new Point3D(0, 0, 400), Color.color(1.0, 0.843, 0.003), "PLAYER 2"),
    AI1(new Point3D(0, 0, -400), Color.color(0.035, 0.87, 1.0), "CPU 1"), AI2(new Point3D(0, 0, 400), Color.color(1.0, 0.843, 0.003), "CPU 2");

    private Point3D position;
    private Color color;
    private String name;

    /**
     * Constructor.
     *
     * @param position The player position.
     * @param color    The player color.
     * @param name     The player name.
     */
    PlayerTag(Point3D position, Color color, String name) {
        this.position = position;
        this.color = color;
        this.name = name;
    }

    /** @return The position. */
    public Point3D getPosition() {
        return position;
    }

    /** @return The color. */
    public Color getColor() {
        return color;
    }

    /** @return The name. */
    public String getName() {
        return name;
    }

    /** @param name The name to set. */
    public void setName(String name) {
        this.name = name;
    }
}
