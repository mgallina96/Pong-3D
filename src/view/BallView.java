package view;

import controller.gameelement.GameElementView;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;

/**
 * The ball view.
 *
 * @author Manuel Gallina
 */
public class BallView extends GameElementView {
    /**
     * Constructor.
     *
     * @param radius The ball radius.
     * @param root   The root node of the game scene.
     */
    public BallView(double radius, Group root) {
        super(root);

        PhongMaterial material = new PhongMaterial(Color.LIGHTGRAY);

        Sphere shape = new Sphere(radius);

        material.setSpecularColor(Color.LIGHTGRAY);
        shape.setMaterial(material);
        shape.setDrawMode(DrawMode.FILL);

        getParent().getChildren().add(shape);
        root.getChildren().addAll(getParent());
    }
}
