package controller;

import application.Settings;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.geometry.Point3D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import utility.geometry.geometry3d.Dimension3D;
import view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * The game panel.
 *
 * @author Manuel Gallina
 */
public class Game {
    /* The first element must be the field. */
    private List<GameElement> objectsList = new ArrayList<>();
    private Scene scene;

    /**
     * Constructor.
     *
     * @param window The window.
     */
    public Game(Stage window) {
        Group root = new Group();
        scene = new Scene(root, Settings.RESOLUTION.getWidth(), Settings.RESOLUTION.getHeight(), true, SceneAntialiasing.BALANCED);

        Field field = new Field(new Dimension3D(400, 400, 800), new Point3D(0, 0, 0), root);
        objectsList.add(field);

        Ball ball = new Ball(this, root);
        objectsList.add(ball);

        Player player1 = new Player(this, PlayerTag.AI1, root);
        objectsList.add(player1);

        Player player2 = new Player(this, PlayerTag.AI2, root);
        objectsList.add(player2);

        scene.setFill(Color.BLACK);
        scene.setCamera(new View(root).getCamera());
        scene.setCursor(Cursor.NONE);
        window.setScene(scene);
        window.setFullScreen(true);

        ScheduledService<Object> gameService = new ScheduledService<Object>() {
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
        gameService.start();
    }

    /**
     * @return The scene.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * @return The list of game objects.
     */
    public List<GameElement> getObjectsList() {
        return objectsList;
    }

    /*
     * Model update task. It updates all the moving game objects.
     */
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
