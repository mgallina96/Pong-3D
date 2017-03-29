package view;

import javafx.geometry.Point3D;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Rectangle;
import utility.graphicobject.Object3D;

import java.io.IOException;
import java.net.URL;

/**
 * The field view.
 *
 * @author Manuel Gallina
 */
public class FieldView {
    private static final String MESH_PATH = "/obj-models/FieldWire.obj";
    private static final double SCALE_FACTOR = 30;

    private static int sideNear = 2900;
    private static int sideFar = 1200;
    private static int sideBack1 = 1300;
    private static int sideBack2 = 600;

    /* Private constructor to hide the default public one. */
    private FieldView() {
    }

    /**
     * Builds the field model and adds it to the scene.
     *
     * @param position The field position.
     * @param root     The root node of the scene.
     *
     * @throws IOException If the field 3D model can't be found.
     */
    public static void createField(Point3D position, Group root) throws IOException {
        Object3D parent = new Object3D();
        AmbientLight ambientLight = new AmbientLight();
        URL objFileUrl = FieldView.class.getResource(MESH_PATH);
        MeshView innerShape = parent.importObjMesh(objFileUrl);

        innerShape.setScaleX(SCALE_FACTOR);
        innerShape.setScaleY(SCALE_FACTOR);
        innerShape.setScaleZ(SCALE_FACTOR);
        innerShape.setTranslateX(0);
        innerShape.setTranslateY(10);
        innerShape.setTranslateZ(300);
        innerShape.setMaterial(new PhongMaterial(Color.BLACK));

        Rectangle nearSide = getNearSide(Color.color(0, 0.6627, 0.7765));
        Rectangle farSide = getFarSide(Color.color(0.8274, 0.505, 0), sideFar, 801);
        Rectangle background1 = getBackground(Color.BLACK, sideBack1, 802);
        Rectangle background2 = getBackground(Color.BLACK, sideBack2, 800);

        nearSide.setTranslateX(-sideNear / 2);
        nearSide.setTranslateY(-sideNear / 2);

        farSide.setTranslateX(-sideFar / 2);
        farSide.setTranslateY(-sideFar / 2);

        background1.setTranslateX(-sideBack1 / 2);
        background1.setTranslateY(-sideBack1 / 2);

        background2.setTranslateX(-sideBack2 / 2);
        background2.setTranslateY(-sideBack2 / 2);

        nearSide.setOpacity(0.9);
        farSide.setOpacity(0.9);

        parent.setTranslateX(position.getX());
        parent.setTranslateY(position.getY());
        parent.setTranslateZ(position.getZ());

        parent.getChildren().addAll(background1, farSide, background2, nearSide, innerShape, ambientLight);
        root.getChildren().add(parent);
    }

    private static Rectangle getBackground(Paint material, int side, int distance) {
        Rectangle back = new Rectangle(side, side);
        back.setTranslateZ(distance);
        back.setFill(material);
        back.setVisible(true);
        return back;
    }

    private static Rectangle getFarSide(Paint farSideMaterial, int side, int distance) {
        return getBackground(farSideMaterial, side, distance);
    }

    private static Rectangle getNearSide(Paint nearSideMaterial) {
        return getFarSide(nearSideMaterial, sideNear, 803);
    }
}
