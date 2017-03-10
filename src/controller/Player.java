package controller;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import model.PlayerModel;
import view.PlayerView;

/**
 * The player controller.
 *
 * @author Manuel Gallina
 */
public class Player implements GameElement {
    private PlayerModel model;
    private PlayerView view;
    private Game game;

    /**
     * Constructor.
     *
     * @param game The current game.
     * @param tag  The player tag. It's used to define the player position.
     * @param root The rendering group of the current scene.
     */
    Player(Game game, PlayerTag tag, Group root) {
        this.game = game;

        model = new PlayerModel(this, tag);
        view = new PlayerView(model.getSize(), model.getPosition(), root);
    }

    /**
     * @return The current game.
     */
    public Game getGame() {
        return game;
    }

    @Override
    public void updateModel() {
        model.updatePosition();
    }

    @Override
    public void updateView(Point3D position) {
        view.moveTo(position);
    }

    @Override
    public Point3D getPosition() {
        return model.getPosition();
    }
}
