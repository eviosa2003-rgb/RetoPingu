package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import Modelo.Partida;

public class GestorBBDD {
	
	private String ur1BBDD;
	private String DM1_2526_GRUP03;
	private String AGRUP03;
	
	public String getUr1BBDD() {
		return ur1BBDD;
		
	}
	
	public void setUr1BBDD(String ur1BBDD) {
		this.ur1BBDD = ur1BBDD;
		
	}
	
	public String getUsername() { 
		return DM1_2526_GRUP03;
	}
	
	public void setUsername(String username) {
		this.DM1_2526_GRUP03 = username;
	}
	
	public String getPasword() {
		return AGRUP03;
	}
	
	public void setPassword(String password) {
		this.AGRUP03 = password;
	}
	
	public void guardarPartida(Partida p) {
	System.out.println("Partida guardada ");
	}
	
	public Partida cargarPartida (int id) {
		System.out.println("Cargando partida con id= " + id );
		return null;
	}
	
	public void guardarTurnoActual(Partida p) {
		System.out.println(" Turno actual guardado");
	}
	
}
