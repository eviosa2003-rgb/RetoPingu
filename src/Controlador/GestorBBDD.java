package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Modelo.Foca;
import Modelo.Inventario;
import Modelo.Jugador;
import Modelo.Partida;
import Modelo.Pinguino;

public class GestorBBDD {

	// Datos introducidos directamente para evitar errores de lectura
	private final String URL = "jdbc:oracle:thin:@//192.168.3.26:1521/XEPDB2";
	private final String USER = "DM1_2526_GRUP03";
	private final String PASS = "AGRUP03";

	// carga el driver Oracle 
	
	public GestorBBDD() {
		try {
			// ESTA LÍNEA ES FUNDAMENTAL: Carga el driver en memoria
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("✓ Driver Oracle cargado correctamente.");
		} catch (ClassNotFoundException e) {
			System.out.println("✗ Error: No se encontró el JAR del Driver en el Build Path.");
		}
	}
	
	// metodo devolucion de la conexion de la BD
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASS);
	}
	
	// guarda la partida en base de datos
	
	public void guardarBBDD(Partida p) {
		try (Connection conn = getConnection()) {
			conn.setAutoCommit(false);

			String sqlPartida = "INSERT INTO PARTIDA (NOM, TAULELL, TORN_ACTUAL) VALUES (?, ?, ?)";
			// Usamos Statement.RETURN_GENERATED_KEYS para obtener el ID creado por el
			// trigger/secuencia
			try (PreparedStatement ps = conn.prepareStatement(sqlPartida, Statement.RETURN_GENERATED_KEYS)) {
				ps.setString(1, "Partida_" + System.currentTimeMillis());
				ps.setString(2, p.getTablero().serializar());
				ps.setInt(3, p.getJugadorActualIndice());
				System.out.println("INSERT INTO PARTIDA (NOM, TAULELL, TORN_ACTUAL) VALUES ("+"Partida_" + System.currentTimeMillis() + " "+ p.getTablero().serializar() + " "+ p.getJugadorActualIndice()+")");
				ps.executeUpdate();
				
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					int partidaId = rs.getInt(1);

					String sqlJugador = "INSERT INTO JUGADOR (PARTIDA_ID, NOM, POSICIO, PEIXOS, BOLES) VALUES (?, ?, ?, ?, ?)";
					for (Jugador j : p.getJugadores()) {
						try (PreparedStatement pj = conn.prepareStatement(sqlJugador)) {
							pj.setInt(1, partidaId);
							pj.setString(2, j.getNombre());
							pj.setInt(3, j.getPosicion());
							pj.setInt(4, (j instanceof Pinguino) ? j.getPeces() : 0);
							pj.setInt(5, (j instanceof Pinguino) ? j.getBolasNieve() : 0);
							pj.executeUpdate();
						}
					}
				}
				conn.commit();
				System.out.println("✓ Partida guardada con éxito en la IP 192.168.3.26");
			}
		} catch (SQLException e) {
			System.out.println("✗ Error ORA al guardar: " + e.getMessage());
		}
	}
	
	// carga la ultima base de datos
	
	public Partida cargarBBDD(int idInutil) {
		Partida partida = new Partida();
		try (Connection conn = getConnection()) {
			Statement st = conn.createStatement();
			// Pillamos la última partida guardada
			ResultSet rs = st.executeQuery("SELECT * FROM PARTIDA ORDER BY ID DESC FETCH FIRST 1 ROWS ONLY");

			if (rs.next()) {
				int partidaId = rs.getInt("ID");
				partida.setJugadorActualIndice(rs.getInt("TORN_ACTUAL"));
				partida.getTablero().deserializar(rs.getString("TAULELL"));

				PreparedStatement pj = conn.prepareStatement("SELECT * FROM JUGADOR WHERE PARTIDA_ID = ?");
				pj.setInt(1, partidaId);
				ResultSet rj = pj.executeQuery();

				partida.getJugadores().clear();
				while (rj.next()) {
					String nom = rj.getString("NOM");
					int pos = rj.getInt("POSICIO");
					Jugador j;
					if (nom.toLowerCase().contains("foca")) {
						j = new Foca(nom, "Gris", pos);
					} else {
						j = new Pinguino(nom, "Azul", pos, new Inventario());
						j.setPeces(rj.getInt("PEIXOS"));
						j.setBolasNieve(rj.getInt("BOLES"));
					}
					partida.getJugadores().add(j);
				}
				System.out.println("✓ Partida cargada correctamente.");
				return partida;
			}
		} catch (SQLException e) {
			System.out.println("✗ Error ORA al cargar: " + e.getMessage());
		}
		return null;
	}
}