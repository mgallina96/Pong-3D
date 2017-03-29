package controller;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import utility.graphicobject.Object3D;

import java.util.logging.Logger;

/**
 * @author Manuel Gallina
 */
public class Scoreboard {
    private static final Logger LOGGER = Logger.getLogger(Scoreboard.class.getName());

    private PlayerTag tag;
    private int score;
    private Label playerScore;

    public Scoreboard(PlayerTag tag, Group root) {
        this.tag = tag;

        Object3D parent = new Object3D();

        AnchorPane scoreboard = buildScoreboard();

        parent.setTranslateY(-550);
        parent.setTranslateZ(10);

        if (tag == PlayerTag.P1 || tag == PlayerTag.AI1)
            parent.setTranslateX(650);
        else
            parent.setTranslateX(-1100);

        parent.getChildren().add(scoreboard);
        root.getChildren().add(parent);
    }

    private AnchorPane buildScoreboard() {
        AnchorPane scoreboard = new AnchorPane();
        scoreboard.setPrefSize(400, 350);
        scoreboard.setStyle("-fx-background-color: black");

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPrefSize(400, 350);
        vbox.setStyle("-fx-background-color: black");

        Label playerName = new Label(tag.getName());
        playerName.setTextFill(tag.getColor());
        playerName.setFont(Font.font("Tw Cen MT Condensed Extra Bold", 96));

        playerScore = new Label(String.format("%d", score));
        playerScore.setTextFill(Color.WHITE);
        playerScore.setFont(Font.font("Tw Cen MT Condensed Extra Bold", 96));

        vbox.getChildren().addAll(playerName, playerScore);
        scoreboard.getChildren().add(vbox);

        return scoreboard;
    }

    public void updateScore(int score) {
        this.playerScore.setText(String.format("%d", score));
    }

}
