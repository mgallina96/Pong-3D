package application.menu.settings;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static application.Settings.*;

/**
 * The settings menu.
 *
 * @author Manuel Gallina
 * @author Giosuè Filippini
 */
public class SettingsMenu implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(SettingsMenu.class.getName());

    @FXML
    private Button diffPrev;
    @FXML
    private Button diffNext;
    @FXML
    private Label diffLabel;

    @FXML
    private Button aiPrev;
    @FXML
    private Button aiNext;
    @FXML
    private Label aiLabel;

    @FXML
    private Button musicPrev;
    @FXML
    private Button musicNext;
    @FXML
    private Label musicLabel;

    @FXML
    private Button soundPrev;
    @FXML
    private Button soundNext;
    @FXML
    private Label soundLabel;

    @FXML
    private Button back;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        diffLabel.setText(difficulty.getText());
        aiLabel.setText(aiDifficulty.getText());
        musicLabel.setText(musicSetting.getText());
        soundLabel.setText(soundSetting.getText());

        back.setOnAction(e -> {
            Parent menuRoot = null;
            try {
                menuRoot = FXMLLoader.load(getClass().getResource("/application/menu/main/MainMenu.fxml"));
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, "Error loading MainMenu.fxml", ex);
            }
            Main.getScene().setRoot(menuRoot);
        });

        diffNext.setOnAction(e -> {
            difficulty = difficulty.next();
            diffLabel.setText(difficulty.getText());
        });
        diffPrev.setOnAction(e -> {
            difficulty = difficulty.previous();
            diffLabel.setText(difficulty.getText());
        });

        aiNext.setOnAction(e -> {
            aiDifficulty = aiDifficulty.next();
            aiLabel.setText(aiDifficulty.getText());
        });
        aiPrev.setOnAction(e -> {
            aiDifficulty = aiDifficulty.previous();
            aiLabel.setText(aiDifficulty.getText());
        });

        EventHandler<ActionEvent> musicSwitch = e -> {
            musicSetting = musicSetting.switchValue();
            Main.getMusicPlayer().switchMusic();
            musicLabel.setText(musicSetting.getText());
        };

        musicNext.setOnAction(musicSwitch);
        musicPrev.setOnAction(musicSwitch);

        EventHandler<ActionEvent> soundSwitch = e -> {
            soundSetting = soundSetting.switchValue();
            soundLabel.setText(soundSetting.getText());
        };

        soundNext.setOnAction(soundSwitch);
        soundPrev.setOnAction(soundSwitch);
    }
}
