package controller;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import model.BallListener;
import model.BallModel;
import view.BallView;

/**
 * The ball controller.
 *
 * @author Manuel Gallina
 */
public class Ball implements GameElement {
    private BallModel model;
    private BallView view;
    private Game game;

    /**
     * Constructor.
     *
     * @param game The current game.
     * @param root The rendering group of the current scene.
     */
    Ball(Game game, Group root) {
        this.game = game;

        model = new BallModel(this);
        view = new BallView(BallModel.RADIUS, root);
    }

    /**
     * @return The current game.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Adds a listener to the model's listeners list.
     *
     * @param listener The listener to add.
     */
    public void addListener(BallListener listener) {
        model.addListener(listener);
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
