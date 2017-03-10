package application;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Entry point class of the application.
 *
 * @author Manuel Gallina
 */
public class Main extends Application {
    /**
     * Main method.
     *
     * @param args The console arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MainMenu.show(primaryStage);
    }
}
