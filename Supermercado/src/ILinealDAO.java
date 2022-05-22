import java.util.List;

public interface ILinealDAO {

	public Lineal crearLineal(String letra);
	public Lineal existeLineal(String letra);
	public List<Lineal> getLineales();
}
