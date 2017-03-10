package controller;

import javafx.geometry.Point3D;

/**
 * A generic game element.
 *
 * @author Manuel Gallina
 */
public interface GameElement {
    /**
     * Updates the position of the game element.
     */
    void updateModel();

    /**
     * Moves the view component of the game element to a position.
     * The position should be determined by the model component.
     *
     * @param position The position to move to.
     */
    void updateView(Point3D position);

    /**
     * @return The current position of the element.
     */
    Point3D getPosition();
}
