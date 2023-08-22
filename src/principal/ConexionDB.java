package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConexionDB {
	private final String Usuario;
	private final String Contrasena;
	private final String URLBaseDatos;
	private  Connection conexion=null;
	private Statement consulta;
	private ResultSet rs;
	
	public ConexionDB() {
		Usuario= "root";
		Contrasena= "";
		URLBaseDatos="jdbc:mysql://localhost:3306/registro_animales";
		try {
			conexion= conectar();
			consulta =  conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = consulta.executeQuery("SELECT * FROM animales");
		}catch (Exception e) {
			e.printStackTrace();
		}
		/*
		try {
			conectar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	

	private Connection conectar() throws SQLException {
		
		Properties conPro= new Properties();
		conPro.put("user", this.Usuario);
		conPro.put("password", this.Contrasena);
		Connection con= DriverManager.getConnection(URLBaseDatos, conPro);
		return con;
		
	}
	
	
	public Connection getConexion() {
		return conexion;
	}
	
	public ResultSet getResultSet() {
		return rs;
	}
	
	public void setResultSet(String query) {
		try {
			rs = consulta.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
