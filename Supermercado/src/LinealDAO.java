import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LinealDAO implements ILinealDAO{

	
	private Connection conexion;
	String url = "jdbc:mysql://localhost/supermercado";
	
	public LinealDAO() {
		
		try {
			this.conexion = DriverManager.getConnection(url, "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Connection conectar() {
		return this.conexion;
	}
	
	public Lineal crearLineal(String letra) {
		
		Lineal lineal = null;
		try {
			
			String sql = "insert into Lineal (letraLineal) values (?)";
			PreparedStatement sentencia = conectar().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			sentencia.setString(1, letra);
			sentencia.executeUpdate();
			
			ResultSet rs = sentencia.getGeneratedKeys();
			
			int id = 0;
			
			if(rs.next()) {
				id = rs.getInt(1);
				lineal = new Lineal(id, letra);
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lineal;
	}
	
	public Lineal existeLineal(String letra) {
		
		Lineal lineal = null;
		try {
			
			String sql = "select * from Lineal where letraLineal = ?";
			PreparedStatement sentencia = conectar().prepareStatement(sql);
			
			sentencia.setString(1, letra);
			
			ResultSet rs = sentencia.executeQuery();
			
			if(rs.next()) {
				lineal = new Lineal(rs.getInt(1), rs.getString(2));
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lineal;
	}
	
	public void cerrarConexion() throws SQLException {
		conectar().close();
	}
	
	public List<Lineal> getLineales(){
		List<Lineal> lista = new ArrayList<Lineal>();
		try {
			String sql = "select * from lineal";
			Statement sentencia = conectar().createStatement();
			ResultSet rs = sentencia.executeQuery(sql);
			
			while(rs.next()) {
				Lineal a = new Lineal(rs.getInt(1),rs.getString(2));
				lista.add(a);
			}
			
			return lista;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			try {
				conectar().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
