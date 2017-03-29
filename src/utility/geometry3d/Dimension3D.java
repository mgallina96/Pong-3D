package utility.geometry3d;

/**
 * This class represents the dimensions of a 3D object.
 *
 * @author Manuel Gallina
 */
public class Dimension3D {
    private double width;
    private double height;
    private double depth;

    /**
     * Default constructor.
     */
    public Dimension3D() {
        this.setWidth(0.0);
        this.setHeight(0.0);
        this.setDepth(0.0);
    }

    /**
     * Constructor.
     *
     * @param height The height.
     * @param width  The width.
     * @param depth  The depth.
     */
    public Dimension3D(double width, double height, double depth) {
        this.setWidth(width);
        this.setHeight(height);
        this.setDepth(depth);
    }

    /**
     * @return The height.
     */
    public double getHeight() {
        return height;
    }

    /**
     * @param height The height to set.
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * @return The width.
     */
    public double getWidth() {
        return width;
    }

    /**
     * @param width The width to set.
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * @return The depth.
     */
    public double getDepth() {
        return depth;
    }

    /**
     * @param depth The depth to set.
     */
    public void setDepth(double depth) {
        this.depth = depth;
    }
}
