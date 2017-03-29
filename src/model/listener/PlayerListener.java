package model.listener;

import javafx.geometry.Point3D;
import utility.geometry3d.Dimension3D;

/**
 * Player events listener.
 *
 * @author Manuel Gallina
 * @author Giosuè Filippini
 */
@FunctionalInterface
public interface PlayerListener extends GameElementListener {
    /**
     * Executes this action when triggered.
     *
     * @param position The player position.
     * @param size     The player size.
     */
    void onPlayerEvent(Point3D position, Dimension3D size);
}

