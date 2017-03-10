package application;

import controller.Game;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main menu of the application.
 * Singleton class.
 *
 * @author Manuel Gallina
 */
class MainMenu {
    private static MainMenu INSTANCE;
    private Scene scene;

    /* Constructor. */
    private MainMenu(Stage window) {
        AnchorPane root = new AnchorPane();

        scene = new Scene(root, Settings.RESOLUTION.getWidth(), Settings.RESOLUTION.getHeight());
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        Button btn = new Button("Start");
        btn.setOnAction(e -> new Game(window));

        root.getChildren().add(btn);

        window.setScene(scene);
        window.show();
    }

    /**
     * @return The instance of the main menu.
     */
    private static MainMenu getInstance(Stage window) {
        if (INSTANCE == null)
            INSTANCE = new MainMenu(window);
        return INSTANCE;
    }

    /**
     * Changes the scene to show the main menu.
     *
     * @param window The window.
     */
    static void show(Stage window) {
        window.setScene(getInstance(window).scene);
    }
}
