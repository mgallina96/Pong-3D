package view;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import utility.geometry.geometry3d.Dimension3D;
import utility.graphics.graphicobject.Object3D;

/**
 * The player view component.
 *
 * @author Manuel Gallina
 */
public class PlayerView {
    private Object3D parent;

    /**
     * Constructor.
     *
     * @param size     The player size.
     * @param position The player position.
     * @param root     The root node of the game scene.
     */
    public PlayerView(Dimension3D size, Point3D position, Group root) {
        PhongMaterial material = new PhongMaterial(Color.SPRINGGREEN);

        Box shape = new Box(size.getWidth(), size.getHeight(), size.getDepth());
        parent = new Object3D();

        material.setSpecularColor(Color.GREEN);

        shape.setMaterial(material);
        shape.setDrawMode(DrawMode.LINE);

        parent.setPosition(position);
        parent.getChildren().add(shape);
        root.getChildren().add(parent);
    }

    /**
     * Moves the player to the new position.
     *
     * @param position The new position.
     */
    public void moveTo(Point3D position) {
        parent.setPosition(position);
    }
}
