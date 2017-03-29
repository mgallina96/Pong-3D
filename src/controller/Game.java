package controller;

import application.Main;
import application.Settings;
import application.menu.Countdown;
import application.menu.PauseMenu;
import controller.gameelement.GameElement;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.geometry.Point3D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import utility.sound.SoundPlayer;
import view.camera.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The game panel.
 *
 * @author Manuel Gallina
 * @author Giosuè Filippini
 */
public class Game {
    private static final Logger LOGGER = Logger.getLogger(Game.class.getName());
    private static final String START_SOUND_URL = "out/production/Pong3D/music/Pong 3D - Inizio.wav";

    /* The first element must be the field. */
    private List<GameElement> objectsList = new ArrayList<>();
    private Scene scene;
    private Group root;
    private ScheduledService<Object> gameService;
    private AnchorPane pauseMenu;
    private Countdown countdown;

    /** Constructor. */
    public Game() {
        root = new Group();
        scene = Main.getScene();

        pauseMenu = PauseMenu.buildPauseMenu(this);
        root.getChildren().add(pauseMenu);

        countdown = new Countdown();
        AnchorPane countdownPanel = countdown.getCountdown();
        root.getChildren().add(countdownPanel);

        initializeScene();
        Main.getMusicPlayer().stopLoop();

        Field field;
        Ball ball;
        Player player1;
        Player player2;

        try {
            field = new Field(new Point3D(0, 0, 0), root);
            objectsList.add(field);

            ball = new Ball(this, root);
            objectsList.add(ball);

            player2 = new Player(this, PlayerTag.AI2, root);
            objectsList.add(player2);
            player1 = new Player(this, PlayerTag.P1, root);
            objectsList.add(player1);

            ball.addPlayers();

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error importing 3d models", e);
        }

        // Start the background thread.
        gameService = new ScheduledService<Object>() {
            @Override
            protected Task<Object> createTask() {
                return new Updater();
            }
        };
        gameService.setPeriod(Duration.millis(1000 / Settings.FPS));
        gameService.setOnSucceeded(e -> {
            for (GameElement element : objectsList) {
                element.updateView(element.getPosition());
            }
        });
        gameService.setRestartOnFailure(true);
        gameService.setDelay(Duration.millis(3000));

        new SoundPlayer().playSound(START_SOUND_URL);
    }

    /** @return The scene. */
    public Scene getScene() {
        return scene;
    }

    /** @return The list of game objects. */
    public List<GameElement> getObjectsList() {
        return objectsList;
    }

    /** Restarts the game service. */
    public void restartGame() {
        pauseMenu.setVisible(false);
        scene.setCursor(Cursor.NONE);
        gameService.restart();
    }

    /** Returns to the main menu. */
    public void toMainMenu() {
        scene.setOnKeyPressed(null);
        scene.setOnMouseClicked(null);
        scene.setCursor(Cursor.DEFAULT);
        scene.setCamera(null);
        Main.mainMenu();
    }

    // Sets the scene parameters.
    private void initializeScene() {
        scene.setFill(Color.BLACK);
        scene.setCamera(new View(root).getCamera());
        scene.setCursor(Cursor.NONE);
        scene.setRoot(root);
        scene.setOnMouseClicked(e -> {
            countdown.startTimer();
            gameService.start();
        });
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                gameService.cancel();
                pauseMenu.setVisible(true);
                scene.setCursor(Cursor.DEFAULT);
            }
        });
    }

    /* Model update task. It updates all the moving game objects. */
    private class Updater extends Task {
        @Override
        protected Object call() throws Exception {
            for (GameElement element : objectsList) {
                element.updateModel();
            }
            return null;
        }
    }
}