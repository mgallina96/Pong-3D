package controller.gameelement;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import utility.graphicobject.Object3D;

/**
 * The generic game element view component
 *
 * @author Manuel Gallina
 */
public abstract class GameElementView {
    private Object3D parent;
    private Group root;

    /** Constructor. */
    public GameElementView(Group root) {
        parent = new Object3D();
        this.root = root;
    }

    /**
     * Moves the game element to the new position.
     *
     * @param position The new position.
     */
    public void moveTo(Point3D position) {
        parent.setPosition(position);
    }

    /** @return The parent node. */
    public Object3D getParent() {
        return parent;
    }

    /** @return The root node. */
    public Group getRoot() {
        return root;
    }
}
