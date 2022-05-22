
public class Lineal {
	private int id;
	private String letra;
	private String descripcion;
	
	public Lineal(String letra) {
		setLetra(letra);
	}
	
	public Lineal(int id, String letra) {
		setId(id);
		setLetra(letra);
	}


	public int getId() {
		return id;
	}
	
	private void setId(int id) {
		this.id = id;
	}
	
	public String getLetra() {
		return letra;
	}
	
	private void setLetra(String letra) {
		this.letra = letra;
	}

	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public String toString() {
		return "Lineal [id=" + id + ", letra=" + letra + ", descripcion=" + descripcion + "]";
	}

	

	
	
	
}

