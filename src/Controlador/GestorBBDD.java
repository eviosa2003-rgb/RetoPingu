package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestorBBDD {
	
	private String ur1BBDD;
	private String username;
	private String password;
	
	public String getUr1BBDD() {
		return ur1BBDD;
		
	}
	
	public void setUr1BBDD(String ur1BBDD) {
		this.ur1BBDD = ur1BBDD;
		
	}
	
	public String getUsername() { 
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPasword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	// guardar partida
	public void guardarBBDD(Partida p) {
		String sql = "INSERT INTO partidas (estado) VALUES (?)";
		
		try (Connection conn = DriverManager.getConnection(ur1BBDD), username,
				PreparedStatment stmt = conn.prepareStatement(sql )){
			
			Gson gson = new Gson();
			String json = gson.toJson(p);
			
			stmt.setString(1,jason);
			stmt.executeUpdate();
			
			System.out.println("Partida guardada correctamente");
		}
		catch (SQL Exception e) {
			e.printStrackTrace();
			
		}
	}
	
		// cargar partida
	
	public Partida cargarBBDD(int id) {
		
		String sql = "SELECT estado FROM partidas WHERE id = ?";
		Partida partida = null;
		
		try (Connection conn = DriverManager.getConnection(sql)){
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String json = rs.getString("estado");
				
				Gson gson = new Gson();
				partida = gson.fromJson(json, Partida.class);
			}
		} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		
		return partida;
	}
	
	
}
