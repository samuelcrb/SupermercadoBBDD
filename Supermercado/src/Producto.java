
public class Producto implements Comparable<Producto> {
	private int id;
	private String tipo;
	private String codigo;
	private int num_existencias;
	private int idLineal;
	
	public Producto(String tipo, String codigo, int num_existencias) {
		setTipo(tipo);
		setCodigo(codigo);
		setNum_existencias(num_existencias);
	}
	
	public Producto(int id, String tipo, String codigo, int num_existencias, int idLineal) {
		setId(id);
		setTipo(tipo);
		setCodigo(codigo);
		setNum_existencias(num_existencias);
		setIdLineal(idLineal);
	}

	public String getTipo() {
		return tipo;
	}

	private void setTipo(String nombre) {
		this.tipo = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	private void setCodigo(String referencia) {
		this.codigo = referencia;
	}

	public int getNum_existencias() {
		return num_existencias;
	}

	private void setNum_existencias(int num_existencias) {
		this.num_existencias = num_existencias;
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public int getIdLineal() {
		return idLineal;
	}

	private void setIdLineal(int idLineal) {
		this.idLineal = idLineal;
	}

	@Override
	public int compareTo(Producto o) {
		int codigo = Integer.parseInt(o.getCodigo());
		if(Integer.parseInt(this.codigo) > codigo) {
			return 1;
		}else if(Integer.parseInt(this.codigo) < codigo) {
			return -1;
		}else {
			return 0;
		}
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", tipo=" + tipo + ", codigo=" + codigo + ", num_existencias=" + num_existencias
				+ ", idLineal=" + idLineal + "]";
	}

	
}
