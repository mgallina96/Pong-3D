package utility.graphics.graphicobject;

import javafx.scene.transform.Rotate;

/**
 * This class represents a rotation in the 3D space.
 * <p>
 * More precisely the 3 different rotations around the X, Y, Z axes, expressed in degrees.
 *
 * @author Michele Franceschetti
 */
public class Rotation {
    private Rotate x = new Rotate();
    private Rotate y = new Rotate();
    private Rotate z = new Rotate();

    /**
     * Default Constructor.
     * <p>
     * Sets the rotation to (0,0,0).
     */
    public Rotation() {
        x.setAxis(Rotate.X_AXIS);
        y.setAxis(Rotate.Y_AXIS);
        z.setAxis(Rotate.Z_AXIS);

        setRotation(0, 0, 0);
    }

    /**
     * @return The Rotate object for the X axis.
     */
    public Rotate getRotateX() {
        return this.x;
    }

    /**
     * @return The Rotate object for the Y axis.
     */
    public Rotate getRotateY() {
        return this.y;
    }

    /**
     * @return The Rotate object for the Z axis.
     */
    public Rotate getRotateZ() {
        return this.z;
    }

    /**
     * Sets the rotation of the transform around the three axis.
     *
     * @param x The rotation around the x axis.
     * @param y The rotation around the y axis.
     * @param z The rotation around the z axis.
     */
    public void setRotation(double x, double y, double z) {
        this.x.setAngle(x);
        this.y.setAngle(y);
        this.z.setAngle(z);
    }

    /**
     * @return The rotation around the x axis.
     */
    public double getX() {
        return this.x.getAngle();
    }

    /**
     * @param x The rotation around the x axis.
     */
    public void setX(double x) {
        this.x.setAngle(x);
    }

    /**
     * @return The rotation around the y axis.
     */
    public double getY() {
        return this.y.getAngle();
    }

    /**
     * @param y The rotation around the y axis.
     */
    public void setY(double y) {
        this.y.setAngle(y);
    }

    /**
     * @return The rotation around the z axis.
     */
    public double getZ() {
        return this.z.getAngle();
    }

    /**
     * @param z The rotation around the z axis.
     */
    public void setZ(double z) {
        this.z.setAngle(z);
    }

    /**
     * Returns a string representation of the rotation.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "r = (" +
                x.getAngle() + ", " +
                y.getAngle() + ", " +
                z.getAngle() + ")  ";
    }
}
