package view;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;
import utility.graphics.graphicobject.Object3D;

/**
 * The ball view.
 *
 * @author Manuel Gallina
 */
public class BallView {
    private Object3D parent;

    /**
     * Constructor.
     *
     * @param radius The ball radius.
     * @param root   The root node of the game scene.
     */
    public BallView(double radius, Group root) {
        PhongMaterial material = new PhongMaterial(Color.AQUA);

        Sphere shape = new Sphere(radius);
        parent = new Object3D();

        material.setSpecularColor(Color.LIGHTBLUE);
        shape.setMaterial(material);
        shape.setDrawMode(DrawMode.FILL);

        parent.getChildren().add(shape);
        root.getChildren().add(parent);
    }

    /**
     * Moves the ball to the new position.
     *
     * @param position The new position.
     */
    public void moveTo(Point3D position) {
        parent.setPosition(position);
    }
}
