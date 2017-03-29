package utility.graphicobject;

import javafx.scene.shape.MeshView;

import java.io.IOException;
import java.net.URL;

/**
 * This interface provides a method to import a mesh.
 *
 * @author Manuel Gallina
 */
@FunctionalInterface
public interface ObjImporter {
    /**
     * Imports the mesh from an .obj file.
     *
     * @param path The file path.
     *
     * @return The mesh.
     */
    MeshView importObjMesh(URL path) throws IOException;
}
