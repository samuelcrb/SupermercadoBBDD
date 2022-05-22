
import java.util.TreeMap;
import java.util.TreeSet;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Supermercado {
	
	private ProductoDAO producto;
	private TreeMap<String, TreeSet<Producto>> supermercado;
	
	public Supermercado() {
		this.producto = new ProductoDAO();
		this.supermercado = (TreeMap<String, TreeSet<Producto>>) 
		cargarDatos();
		
	}
	
	public void añadir(String nombre, String referencia, int existencias, String letraLineal) {
		
		int id = this.producto.añadirProductoBBDD(nombre, referencia, existencias, letraLineal);
		int idLineal = this.producto.getIdLineal(id);
		Producto producto = new Producto(id,nombre,referencia,existencias,idLineal);
		supermercado.get(letraLineal).add(producto);
		
	}
	
	public void eliminar(String referencia) {
		
		this.producto.eliminarProductoBBDD(referencia);
		 String lineal = "" + referencia.charAt(0);
		 TreeSet<Producto> array = supermercado.get(lineal);
		 
		 String ref = referencia.substring(1,referencia.length()); 
		 Iterator<Producto> it = array.iterator();
		 
		 while (it.hasNext()) {
			Producto producto = (Producto) it.next();
			if(producto.getCodigo().equals(ref))
				it.remove();
		}
	 }
	
	public int existenciasLineal(String lineal) {
		int existencias = 0;
		TreeSet<Producto> arrayLineal = supermercado.get(lineal);

		for (Producto producto : arrayLineal) {
			existencias += producto.getNum_existencias();
		}
		return existencias;
	}
	
	public int existenciasTipo(String tipo) {
		int existencias = 0;
		for (Map.Entry<String, TreeSet<Producto>> entry : supermercado.entrySet()) {
			TreeSet<Producto> arrayLineal = entry.getValue();
			for (Producto producto : arrayLineal) {
				if(producto.getTipo().equals(tipo)) {
					existencias += producto.getNum_existencias();
				}
			}
		}
		return existencias;
	}
	
	public int existenciasTotales() {
		int existencias = 0;
		for (Map.Entry<String, TreeSet<Producto>> entry : supermercado.entrySet()) {
			TreeSet<Producto> arrayLineal = entry.getValue();
			for (Producto producto : arrayLineal) {
				existencias += producto.getNum_existencias();
			}
		}
		return existencias;
	}
	
	public String inventarioLineal(String lineal) {
		String cadena = "";
		TreeSet<Producto> arrayLineal = supermercado.get(lineal);
		for (Producto producto : arrayLineal) {
			cadena += producto.toString() + "\n";
		}
		return cadena;
	}
	
	public String inventarioTotal() {
		String cadena = "";
		for (Map.Entry<String, TreeSet<Producto>> entry : supermercado.entrySet()) {
			TreeSet<Producto> arrayLineal = entry.getValue();
			cadena += "Lineal " + entry.getKey()+"\n";
			for (Producto producto : arrayLineal) {
				cadena += producto.toString()+"\n";
			}
		}
		return cadena;
	}
	
	public Map<String, TreeSet<Producto>> cargarDatos() {
		ProductoDAO p = new ProductoDAO();
		LinealDAO lineal = new LinealDAO();
		
		Set<Producto> set = p.getProductos();
		List<Lineal> list = lineal.getLineales();
		
		Map<String,TreeSet<Producto>> mapa = new TreeMap<String,TreeSet<Producto>>();

		for (Lineal lineales : list) {
			Set<Producto> listaProductos = new TreeSet<Producto>();
			mapa.put(lineales.getLetra(),(TreeSet<Producto>) listaProductos);
			for (Producto productos : set) {
				if(productos.getIdLineal() == lineales.getId()) {
					listaProductos.add(productos);
				}
			}
		}
		return mapa;
	}
	
	public ProductoDAO getProductoDAO() {
		return this.producto;
	}

	@Override
	public String toString() {
		return "Supermercado [supermercado=" + supermercado + "]";
	}	
	
}
