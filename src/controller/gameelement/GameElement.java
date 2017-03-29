package controller.gameelement;

import controller.Game;
import javafx.geometry.Point3D;
import model.listener.GameElementListener;

/**
 * A generic game element.
 *
 * @author Manuel Gallina
 */
public abstract class GameElement {
    private GameElementModel model;
    private GameElementView view;
    private Game game;

    /**
     * Constructor.
     *
     * @param game The current game.
     */
    public GameElement(Game game) {
        this.game = game;
    }

    /** Constructor. */
    public GameElement() {
        // Empty constructor.
    }

    /** @return The game element model. */
    public GameElementModel getModel() {
        return model;
    }

    /** @param model The game element model to set. */
    public void setModel(GameElementModel model) {
        this.model = model;
    }

    /** @return The game element view. */
    public GameElementView getView() {
        return view;
    }

    /** @param view The game element view to set. */
    public void setView(GameElementView view) {
        this.view = view;
    }

    /** @return The current game. */
    public Game getGame() {
        return game;
    }

    /** @return The current position of the element. */
    public Point3D getPosition() {
        return model.getPosition();
    }

    /** Updates the position of the game element. */
    public void updateModel() {
        if (model != null)
            model.updatePosition();
    }

    /**
     * Moves the view component of the game element to a position.
     * The position should be determined by the model component.
     *
     * @param position The position to move to.
     */
    public void updateView(Point3D position) {
        if (view != null)
            view.moveTo(position);
    }

    /**
     * Adds a listener to the model's listeners list.
     *
     * @param listener The listener to add.
     */
    public void addListener(GameElementListener listener) {
        model.addListener(listener);
    }
}
