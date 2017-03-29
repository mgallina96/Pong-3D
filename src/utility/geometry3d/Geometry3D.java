package utility.geometry3d;

import javafx.geometry.Point3D;

/**
 * This class contains methods to manage 3D spaces.
 *
 * @author Manuel Gallina
 */
public class Geometry3D {
    private Geometry3D() {
    }

    /**
     * Distance between two points in the space.
     *
     * @param a Point A.
     * @param b Point B.
     *
     * @return The distance between A and B.
     */
    public static double distance3D(Point3D a, Point3D b) {
        return Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) +
                (a.getY() - b.getY()) * (a.getY() - b.getY()) +
                (a.getZ() - b.getZ()) * (a.getZ() - b.getZ()));
    }
}
