
package pe.edu.pucp.pazCitas.DAO;

import java.util.ArrayList;

public interface ICrud<T> {
    int insertar(T modelo);
    int eliminar(int idModelo);
    int modificar(T modelo);
    ArrayList<T> listarTodos();
}
