package application;

import javafx.geometry.Dimension2D;

/**
 * Settings.
 * <p>
 * This class should contain all those constants that are useful all trough the application.
 *
 * @author Manuel Gallina
 */
public final class Settings {
    /**
     * The window resolution.
     */
    public static final Dimension2D RESOLUTION = new Dimension2D(1600, 900);

    public static final int FPS = 60;

    /* Constructor. */
    private Settings() {
    }
}
