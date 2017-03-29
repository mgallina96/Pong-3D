package application.menu.main;

import application.Main;
import controller.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML controller for the main menu.
 *
 * @author Manuel Gallina
 */
public class MainMenu implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(MainMenu.class.getName());

    @FXML
    private Button singlePlayer;
    @FXML
    private Button multiplayer;
    @FXML
    private Button settings;
    @FXML
    private Button quit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        singlePlayer.setOnAction(e -> new Game());
        settings.setOnAction(e -> {
            Parent menuRoot = null;
            try {
                menuRoot = FXMLLoader.load(getClass().getResource("/application/menu/settings/SettingsMenu.fxml"));
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, "Error loading SettingsMenu.fxml", ex);
            }
            Main.getScene().setRoot(menuRoot);
        });
        quit.setOnAction(e -> Main.getStage().hide());
    }
}
