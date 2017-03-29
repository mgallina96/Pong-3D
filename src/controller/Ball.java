package controller;

import controller.gameelement.GameElement;
import javafx.scene.Group;
import model.BallModel;
import view.BallView;

/**
 * The ball controller.
 *
 * @author Manuel Gallina
 */
public class Ball extends GameElement {
    /**
     * Constructor.
     *
     * @param game The current game.
     * @param root The rendering group of the current scene.
     */
    public Ball(Game game, Group root) {
        super(game);

        setModel(new BallModel(this));
        setView(new BallView(BallModel.RADIUS, root));
    }

    /** Starts listening to all the players events. */
    void addPlayers() {
        ((BallModel) getModel()).addPlayers();
    }
}
