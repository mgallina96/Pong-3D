package controller;

import controller.gameelement.GameElement;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import model.PlayerModel;
import utility.geometry3d.Dimension3D;
import view.PlayerView;

import java.io.IOException;

/**
 * The player controller.
 *
 * @author Manuel Gallina
 */
public class Player extends GameElement {
    public static final Dimension3D SIZE = new Dimension3D(80, 80, 2);
    private Scoreboard scoreboard;

    /**
     * Constructor.
     *
     * @param game The current game.
     * @param tag  The player tag. It's used to define the player position.
     * @param root The rendering group of the current scene.
     *
     * @throws IOException If the player 3D model can't be found.
     */
    public Player(Game game, PlayerTag tag, Group root) throws IOException {
        super(game);

        scoreboard = new Scoreboard(tag, root);
        setModel(new PlayerModel(this, tag));
        setView(new PlayerView(SIZE, tag, root));
    }

    @Override
    public void updateView(Point3D position) {
        getView().moveTo(position);
        scoreboard.updateScore(((PlayerModel) getModel()).getScore());
    }
}

