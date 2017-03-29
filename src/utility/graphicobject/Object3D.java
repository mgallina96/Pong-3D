package utility.graphicobject;

import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

import java.io.IOException;
import java.net.URL;

/**
 * This class represents a mesh in the 3D space and its {@link Transform transform}.
 *
 * @author Michele Franceschetti
 * @author Manuel Gallina
 */
public class Object3D extends Group implements Transform, ObjImporter {
    /**
     * Conversion factor: radiant into degrees.
     */
    public static final double RAD_TO_DEG = 180.0 / Math.PI;
    /**
     * The obj mesh importer.
     */
    private static final ObjModelImporter OBJ_IMPORTER = new ObjModelImporter();
    private Translate position = new Translate(0, 0, 0);
    private Rotation rotation = new Rotation();
    private Scale scale = new Scale();

    /**
     * Default constructor.
     */
    public Object3D() {
        super();
        getTransforms().addAll(position, rotation.getRotateX(), rotation.getRotateY(), rotation.getRotateZ(), scale);
        reset();
    }

    /**
     * Returns the distance between two positions.
     *
     * @param a First position.
     * @param b Second position.
     *
     * @return The distance between a and b.
     */
    public static double distance(Translate a, Translate b) {
        return Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) +
                (a.getY() - b.getY()) * (a.getY() - b.getY()) +
                (a.getZ() - b.getZ()) * (a.getZ() - b.getZ()));
    }

    /**
     * @return The Translate object for the position.
     */
    public Translate getPosition() {
        return this.position;
    }

    /**
     * Sets the position to a 3D point.
     *
     * @param point The 3D point.
     */
    public void setPosition(Point3D point) {
        position.setX(point.getX());
        position.setY(point.getY());
        position.setZ(point.getZ());
    }

    /**
     * @return The Rotation object for the rotation.
     */
    public Rotation getRotation() {
        return this.rotation;
    }

    /**
     * @return The Scale object for the scale.
     */
    public Scale getScale() {
        return this.scale;
    }

    /**
     * Sets the scale factor for the three axes.
     *
     * @param scaleFactor The scale factor.
     */
    public void setScale(double scaleFactor) {
        scale.setX(scaleFactor);
        scale.setY(scaleFactor);
        scale.setZ(scaleFactor);
    }

    /**
     * Sets the translation from the three axes.
     *
     * @param x The translation from the x axis.
     * @param y The translation from the y axis.
     * @param z The translation from the z axis.
     */
    public void setPosition(double x, double y, double z) {
        position.setX(x);
        position.setY(y);
        position.setZ(z);
    }

    /**
     * Sets the translation from the x and y axes.
     *
     * @param x The translation from the x axis.
     * @param y The translation from the y axis.
     */
    public void setPosition(double x, double y) {
        position.setX(x);
        position.setY(y);
    }

    /**
     * Sets the rotation around the three axes.
     *
     * @param x The rotation around the x axis.
     * @param y The rotation around the y axis.
     * @param z The rotation around the z axis.
     */
    public void setRotation(double x, double y, double z) {
        rotation.setRotation(x, y, z);
    }

    /**
     * Sets the scale for the three axes.
     *
     * @param x The scale for the x axis.
     * @param y The scale for the y axis.
     * @param z The scale for the z axis.
     */
    public void setScale(double x, double y, double z) {
        scale.setX(x);
        scale.setY(y);
        scale.setZ(z);
    }

    /**
     * Resets the position to the origin of the axes, the rotation parallel to the axes
     * and the scale to the unitary values.
     */
    public void reset() {
        position.setX(0.0);
        position.setY(0.0);
        position.setZ(0.0);

        rotation.setX(0.0);
        rotation.setY(0.0);
        rotation.setZ(0.0);

        scale.setX(1.0);
        scale.setY(1.0);
        scale.setZ(1.0);
    }

    /**
     * Imports the mesh from a .obj file.
     *
     * @param path The file path.
     *
     * @throws IOException If it can't find the obj file.
     */
    public MeshView importObjMesh(URL path) throws IOException {
        OBJ_IMPORTER.read(path);
        return OBJ_IMPORTER.getImport()[0];
    }

    /**
     * Returns the string representation of this Object3D.
     */
    @Override
    public String toString() {
        return "Transform [t = (" +
                position.getX() + ", " +
                position.getY() + ", " +
                position.getZ() + ")  " +
                rotation.toString() +
                "s = (" +
                scale.getX() + ", " +
                scale.getY() + ", " +
                scale.getZ() + ") ]";
    }
}
