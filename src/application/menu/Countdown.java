package application.menu;

import application.Main;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * @author Manuel Gallina
 */
public class Countdown {
    private AnchorPane countdown;
    private Label timerLabel;
    private int time;

    public Countdown() {
        countdown = new AnchorPane();
        countdown.setPrefSize(500, 200);
        countdown.setStyle("-fx-background-color: transparent");

        VBox vbox = new VBox();
        vbox.setLayoutX(8);
        vbox.setLayoutY(66);
        vbox.setPrefSize(500, 200);

        Label resume = buildLabel();

        vbox.getChildren().addAll(resume);
        countdown.getChildren().add(vbox);

        countdown.setTranslateX(-250);
        countdown.setTranslateY(-195); //Center = -138
        countdown.setTranslateZ(-470);
        countdown.setScaleX(0.25);
        countdown.setScaleY(0.25);
        countdown.setScaleZ(0.25);
        countdown.setVisible(true);
    }

    public AnchorPane getCountdown() {
        return countdown;
    }

    private Label buildLabel() {
        timerLabel = new Label("CLICK TO START");
        timerLabel.setAlignment(Pos.CENTER);
        timerLabel.setContentDisplay(ContentDisplay.CENTER);
        timerLabel.setPrefSize(453, 79);
        timerLabel.getStylesheets().add("/application/menu/settings/settings.css");
        timerLabel.setTextFill(Color.WHITE);
        timerLabel.setFont(Font.font("Tw Cen MT Condensed Extra Bold", 75));
        return timerLabel;
    }

    public void startTimer() {
        time = 3;
        timerLabel.setFont(Font.font("Tw Cen MT Condensed Extra Bold", 150));
        timerLabel.setText(String.format("%d", time));
        ScheduledService timer = new ScheduledService() {
            @Override
            protected Task createTask() {
                return new Task() {
                    @Override
                    protected Object call() throws Exception {
                        time--;
                        return null;
                    }
                };
            }
        };
        timer.setDelay(Duration.millis(1000));
        timer.setPeriod(Duration.millis(1000));
        timer.setOnSucceeded(e -> {
            if (time == 0) {
                timer.cancel();
                countdown.setVisible(false);
                Main.getScene().setOnMouseClicked(null);
            }
            timerLabel.setText(String.format("%d", time));
        });
        timer.start();
    }
}
