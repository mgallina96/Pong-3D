package view;

import controller.PlayerTag;
import controller.gameelement.GameElementView;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Rectangle;
import utility.geometry3d.Dimension3D;

import java.io.IOException;
import java.net.URL;

/**
 * The player view component.
 *
 * @author Manuel Gallina
 */
public class PlayerView extends GameElementView {
    private static final String MESH_PATH = "/obj-models/BatFrame.obj";
    private static final double SCALE_FACTOR = 1.75;

    /**
     * Constructor.
     *
     * @param size The player size.
     * @param tag  The player tag.
     * @param root The root node of the game scene.
     *
     * @throws IOException If the player 3D model can't be found.
     */
    public PlayerView(Dimension3D size, PlayerTag tag, Group root) throws IOException {
        super(root);

        PhongMaterial material = new PhongMaterial(tag.getColor());

        Box shape = new Box(size.getWidth(), size.getHeight(), size.getDepth());
        URL objFileUrl = this.getClass().getResource(MESH_PATH);
        MeshView innerShape = getParent().importObjMesh(objFileUrl);

        Rectangle back = new Rectangle(size.getWidth(), size.getHeight());
        if (tag == PlayerTag.P1 || tag == PlayerTag.AI1) {
            back.setFill(Color.color(0, 0.6627, 0.7765));
            back.setTranslateZ(-15);
            innerShape.setTranslateZ(-17);
        }

        if (tag == PlayerTag.P2 || tag == PlayerTag.AI2) {
            back.setFill(Color.color(0.8274, 0.505, 0));
            back.setTranslateZ(15);
            innerShape.setTranslateZ(17);
        }
        back.setOpacity(0.2);
        back.setTranslateX(-size.getWidth() / 2);
        back.setTranslateY(-size.getHeight() / 2 - 1);

        material.setSpecularColor(tag.getColor());

        shape.setMaterial(material);
        shape.setDrawMode(DrawMode.LINE);

        innerShape.setScaleX(SCALE_FACTOR);
        innerShape.setScaleY(SCALE_FACTOR);
        innerShape.setScaleZ(SCALE_FACTOR);
        innerShape.setRotationAxis(new Point3D(1, 0, 0));
        innerShape.setRotate(90);
        innerShape.setTranslateX(0);
        innerShape.setTranslateY(0);
        innerShape.setMaterial(material);

        getParent().setPosition(tag.getPosition());
        getParent().getChildren().addAll(innerShape, back);
        root.getChildren().add(getParent());
    }
}
