package application.menu;

import application.Main;
import controller.Game;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * @author Manuel Gallina
 */
public class PauseMenu {
    public static AnchorPane buildPauseMenu(Game game) {
        AnchorPane pauseMenu = new AnchorPane();
        pauseMenu.setPrefSize(439, 467);
        pauseMenu.setStyle("-fx-background-color: black");

        VBox vbox = new VBox();
        vbox.setLayoutX(8);
        vbox.setLayoutY(66);
        vbox.setPrefSize(423, 326);

        Button resume = gameButton("RESUME");
        resume.setOnAction(e -> game.restartGame());

        Button mainMenu = gameButton("MAIN MENU");
        mainMenu.setOnAction(e -> game.toMainMenu());

        Button quit = gameButton("QUIT");
        quit.setOnAction(e -> Main.getStage().hide());

        vbox.getChildren().addAll(resume, mainMenu, quit);
        pauseMenu.getChildren().add(vbox);

        pauseMenu.setTranslateX(-40);
        pauseMenu.setTranslateY(-230);
        pauseMenu.setTranslateZ(-470);
        pauseMenu.setScaleX(0.22);
        pauseMenu.setScaleY(0.22);
        pauseMenu.setScaleZ(0.22);
        pauseMenu.setVisible(false);

        return pauseMenu;
    }

    private static Button gameButton(String text) {
        Button resume = new Button(text);
        resume.setAlignment(Pos.CENTER);
        resume.setContentDisplay(ContentDisplay.CENTER);
        resume.setPrefSize(453, 79);
        resume.getStylesheets().add("/application/menu/settings/settings.css");
        resume.setTextFill(Color.WHITE);
        resume.setFont(Font.font("Tw Cen MT Condensed Extra Bold", 61));
        return resume;
    }
}
