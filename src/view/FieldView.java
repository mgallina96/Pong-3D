package view;

import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import utility.geometry.geometry3d.Dimension3D;
import utility.graphics.graphicobject.Object3D;

import java.net.URL;

/**
 * The field view.
 *
 * @author Manuel Gallina
 */
public class FieldView {
    private static final String MESH_PATH = "/obj-models/FieldWire.obj";
    private static final double SCALE_FACTOR = 30;

    /**
     * Constructor.
     *
     * @param size The dimensions of the field.
     * @param root The root node of the scene.
     */
    public FieldView(Dimension3D size, Point3D position, Group root) {
        Box shape = new Box(20000, 20000, 10);
        Box shape2 = new Box(3900, 3900, 10);
        Object3D parent = new Object3D();

        PhongMaterial shapeMaterial = new PhongMaterial(Color.DARKRED);
        PhongMaterial shape2Material = new PhongMaterial(Color.BLACK);
        PhongMaterial innerMaterial = new PhongMaterial(Color.ORANGERED);

        AmbientLight ambient = new AmbientLight();

        URL objFileUrl = this.getClass().getResource(MESH_PATH);
        MeshView innerShape = parent.importObjMesh(objFileUrl);

        innerShape.setScaleX(SCALE_FACTOR);
        innerShape.setScaleY(SCALE_FACTOR);
        innerShape.setScaleZ(SCALE_FACTOR);
        innerShape.setTranslateX(0);
        innerShape.setTranslateY(10);
        innerShape.setTranslateZ(300);
        innerShape.setMaterial(innerMaterial);

        shape.setTranslateX(0);
        shape.setTranslateY(0);
        shape.setTranslateZ(9000);
        shape.setDrawMode(DrawMode.FILL);
        shape.setCullFace(CullFace.FRONT);
        shape.setMaterial(shapeMaterial);
        shape.setVisible(true);

        shape2.setTranslateX(0);
        shape2.setTranslateY(0);
        shape2.setTranslateZ(8900);
        shape2.setDrawMode(DrawMode.FILL);
        shape2.setCullFace(CullFace.FRONT);
        shape2.setMaterial(shape2Material);
        shape2.setVisible(true);

        parent.setTranslateX(position.getX());
        parent.setTranslateY(position.getY());
        parent.setTranslateZ(position.getZ());

        parent.getChildren().addAll(shape, shape2, innerShape, ambient);
        root.getChildren().add(parent);
    }
}
