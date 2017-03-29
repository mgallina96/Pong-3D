package utility.graphicobject;

/**
 * This interface provides a list of methods that let a subclass to be positioned in the 3D space.
 *
 * @author Michele Franceschetti
 */
public interface Transform {
    /**
     * Sets the translation from the three axis.
     *
     * @param x The translation from the x axis.
     * @param y The translation from the y axis.
     * @param z The translation from the z axis.
     */
    void setPosition(double x, double y, double z);

    /**
     * Sets the translation from the x and y axis.
     *
     * @param x The translation from the x axis.
     * @param y The translation from the y axis.
     */
    void setPosition(double x, double y);

    /**
     * Sets the rotation around the three axis.
     *
     * @param x The rotation around the x axis.
     * @param y The rotation around the y axis.
     * @param z The rotation around the z axis.
     */
    void setRotation(double x, double y, double z);

    /**
     * Sets the scale factor for the three axis.
     *
     * @param scaleFactor The scale factor.
     */
    void setScale(double scaleFactor);

    /**
     * Sets the scale for the three axis.
     *
     * @param x The scale for the x axis.
     * @param y The scale for the y axis.
     * @param z The scale for the z axis.
     */
    void setScale(double x, double y, double z);

    /**
     * Resets the position to the origin of the axis, the rotation parallel to the axis
     * and the scale to the unitary values.
     */
    void reset();

    /**
     * Returns the string representation of this transform.
     */
    @Override
    String toString();
}
