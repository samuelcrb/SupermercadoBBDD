import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;

public class ProductoDAO implements IProductoDAO{
	
	private Connection conexion;
	private String url = "jdbc:mysql://localhost/supermercado";
	
	public ProductoDAO() {
		try {
			this.conexion = DriverManager.getConnection(url, "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Connection conectar() {
		
		return this.conexion;
	}
	
	public int añadirProductoBBDD(String nombre, String referencia, int existencias, String letraLineal) {
		
		LinealDAO linealDAO = null;
		
		try {
			linealDAO = new LinealDAO();
			
			Lineal lineal = linealDAO.existeLineal(letraLineal);
			
			if(lineal == null) {
				lineal = linealDAO.crearLineal(letraLineal);
			}
			
			String sql = "insert into producto (nombre,referencia,existencias,idLineal) values (?,?,?,?)";
			
			PreparedStatement sentencia = conectar().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			sentencia.setString(1, nombre);
			sentencia.setString(2, referencia);
			sentencia.setInt(3, existencias);
			sentencia.setInt(4, lineal.getId());
			sentencia.executeUpdate();
			
			ResultSet rs = sentencia.getGeneratedKeys();
			int id = 0;
			
			if(rs.next()) {
				id = rs.getInt(1);
			}
			
			return id;
					
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				linealDAO.cerrarConexion();
			} catch (Exception e2) {
				System.err.println(e2.getMessage());
			}
		}
		
		
		return -1;
	}
	
	public boolean eliminarProductoBBDD(String referencia) {
		boolean retorno = false;
			try {
				
				String ref = referencia.substring(1,referencia.length());
				String sql = "delete from Producto where referencia = ?";
				
				PreparedStatement sentencia = conectar().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				
				sentencia.setString(1, ref);
				sentencia.execute();
				
				retorno = true;
						
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return retorno;
	}
	
	public int getIdLineal(int id) {
		try {
			
			String sql = "select idLineal from producto where id = ?";
			
			PreparedStatement sentencia = conectar().prepareStatement(sql);
			
			sentencia.setInt(1, id);
						
			ResultSet rs = sentencia.executeQuery();
			
			int idLineal = 0;
			if(rs.next()) {
				idLineal = rs.getInt(1);
			}
			return idLineal;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
		
	}
	
	public void cerrarConexion() throws SQLException {
		conectar().close();
	}
	
	public Set<Producto> getProductos(){
		Set<Producto> set = new TreeSet<Producto>();
		try {
			String sql = "select * from producto";
			Statement sentencia = conectar().createStatement();
			ResultSet rs = sentencia.executeQuery(sql);
			
			while(rs.next()) {
				Producto p = new Producto(rs.getInt(1),rs.getString(3),rs.getString(2),rs.getInt(4),rs.getInt(5));
				set.add(p);
			}
			
			return set;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
