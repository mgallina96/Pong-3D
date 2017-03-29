package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import utility.sound.SoundPlayer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Entry point class of the application.
 *
 * @author Manuel Gallina
 */
public class Main extends Application {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private static final int SPLASH_TIME = 5000;
    private static final boolean FULLSCREEN = true;
    private static final String SPLASH_URL = "/images/Logo Pong3D bis.png";
    private static final String MUSIC_URL = "out/production/Pong3D/music/Pong 3D - Soundtrack.wav";

    private static Scene scene;
    private static Stage stage;

    private static SoundPlayer musicPlayer;

    /**
     * Main method.
     *
     * @param args The console arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /** @return The main scene. */
    public static Scene getScene() {
        return scene;
    }

    /** @param sceneNew The scene to set. */
    public static void setScene(Scene sceneNew) {
        scene = sceneNew;
    }

    /** @return The music player. */
    public static SoundPlayer getMusicPlayer() {
        return musicPlayer;
    }

    /** @return The main stage. */
    public static Stage getStage() {
        return stage;
    }

    /** Creates the main menu screen. */
    public static void mainMenu() {
        Parent menuRoot = null;
        try {
            menuRoot = FXMLLoader.load(Main.class.getResource("/application/menu/main/MainMenu.fxml"));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading MainMenu.fxml", e);
        }
        assert menuRoot != null;
        scene = new Scene(menuRoot, Settings.RESOLUTION.getWidth(), Settings.RESOLUTION.getHeight(), true, SceneAntialiasing.BALANCED);
        stage.setScene(scene);
        stage.setFullScreen(FULLSCREEN);
        if (!musicPlayer.isPlaying())
            musicPlayer.playLoop(MUSIC_URL);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        AnchorPane splashPane = new AnchorPane();
        ImageView splash = new ImageView();

        scene = new Scene(root, Settings.RESOLUTION.getWidth(), Settings.RESOLUTION.getHeight(), true, SceneAntialiasing.BALANCED);
        scene.setFill(Paint.valueOf("Black"));
        stage = primaryStage;

        splash.setImage(new Image(SPLASH_URL));
        splash.setFitHeight(1200);
        splash.setFitWidth(1200);
        splash.setX(350);
        splash.setY(-50);

        splashPane.getChildren().add(splash);
        root.getChildren().add(splashPane);

        scene.setRoot(root);

        primaryStage.setScene(scene);
        primaryStage.setFullScreen(FULLSCREEN);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.show();

        musicPlayer = new SoundPlayer();
        musicPlayer.playLoop(MUSIC_URL);

        // Waiting before the main menu.
        try {
            Thread.sleep(SPLASH_TIME);
        } catch (InterruptedException e) {
            LOGGER.log(Level.SEVERE, "Error in thread sleep", e);
            Thread.currentThread().interrupt();
        }

        scene.getCamera();

        // Load FXML form.
        mainMenu();
    }
}
