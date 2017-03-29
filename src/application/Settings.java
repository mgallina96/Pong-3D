package application;

import application.settings.AiDifficulty;
import application.settings.BinarySetting;
import application.settings.Difficulty;
import javafx.geometry.Dimension2D;

/**
 * Settings.
 * This class should contain all those constants that are useful all trough the application.
 *
 * @author Manuel Gallina
 */
public final class Settings {
    /** The window resolution. */
    public static final Dimension2D RESOLUTION = new Dimension2D(1600, 900);

    /** The target frame rate of the application. */
    public static final int FPS = 60;

    public static Difficulty difficulty = Difficulty.NORMAL;
    public static AiDifficulty aiDifficulty = AiDifficulty.NORMAL;
    public static BinarySetting musicSetting = BinarySetting.ON;
    public static BinarySetting soundSetting = BinarySetting.ON;

    /* Constructor. */
    private Settings() {
    }
}
