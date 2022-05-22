import java.util.Set;

public interface IProductoDAO {

	public int añadirProductoBBDD(String nombre, String referencia, int existencias, String letraLineal);
	public boolean eliminarProductoBBDD(String referencia);
	public Set<Producto> getProductos();
}
