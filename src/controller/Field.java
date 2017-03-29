package controller;

import controller.gameelement.GameElement;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import utility.geometry3d.Dimension3D;
import view.FieldView;

import java.io.IOException;

/**
 * The game field.
 *
 * @author Manuel Gallina
 */
public class Field extends GameElement {
    private Dimension3D size = new Dimension3D(400, 400, 800);
    private Point3D position = new Point3D(0, 0, 0);

    /**
     * Constructor.
     *
     * @param position The position of the field.
     * @param root     The root node of the scene.
     *
     * @throws IOException If the field 3D model can't be found.
     */
    public Field(Point3D position, Group root) throws IOException {
        super();
        FieldView.createField(position, root);
    }

    /** @return The field size. */
    public Dimension3D getSize() {
        return size;
    }

    /** @return The field position. */
    @Override
    public Point3D getPosition() {
        return position;
    }
}

