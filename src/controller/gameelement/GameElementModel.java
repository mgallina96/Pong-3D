package controller.gameelement;

import javafx.geometry.Point3D;
import model.listener.GameElementListener;

import java.util.ArrayList;
import java.util.List;

/**
 * The generic game element model component.
 *
 * @author Manuel Gallina
 */
public abstract class GameElementModel {
    /** */
    public static final Point3D ORIGIN = new Point3D(0, 0, 0);
    private List<GameElementListener> listenersList = new ArrayList<>();

    private GameElement controller;

    /**
     * Constructor.
     *
     * @param controller The game element controller.
     */
    public GameElementModel(GameElement controller) {
        this.controller = controller;
    }

    /** @return The game element cotroller. */
    public GameElement getController() {
        return controller;
    }

    /** Updates the game element position. */
    public void updatePosition() {
    }

    /** @return The game element position. */
    public Point3D getPosition() {
        return null;
    }

    /**
     * Adds a listener to the listeners list.
     *
     * @param listener The listener to add.
     */
    public void addListener(GameElementListener listener) {
        listenersList.add(listener);
    }

    /** @return The listeners list. */
    public List<GameElementListener> getListenersList() {
        return listenersList;
    }
}
