package Controlador;

import Modelo.Partida;

/**
 * GestorBBDD - Gestión de la base de datos.
 * Los métodos de persistencia son stubs funcionales que no requieren
 * librerías externas (Gson) ni conexión de base de datos activa.
 */
public class GestorBBDD {
	
	private String urlBBDD;
	private String username;
	private String password;
	
	public String getUrlBBDD() {
		return urlBBDD;
	}
	
	public void setUrlBBDD(String urlBBDD) {
		this.urlBBDD = urlBBDD;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
<<<<<<< Updated upstream
	
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
=======
	/** Guarda el estado de la partida */
	public void guardarPartida(Partida p) {
		System.out.println("Partida guardada (stub - sin conexión BD).");
	}
	
	/** Carga una partida por ID */
	public Partida cargarPartida(int id) {
		System.out.println("Cargando partida con id=" + id + " (stub - sin conexión BD).");
		return null;
>>>>>>> Stashed changes
	}
	
	/** Guarda el turno actual de la partida */
	public void guardarTurnoActual(Partida p) {
		System.out.println("Turno actual guardado (stub - sin conexión BD).");
	}
}
