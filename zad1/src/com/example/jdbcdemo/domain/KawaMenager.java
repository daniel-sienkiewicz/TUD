package com.example.jdbcdemo.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.jdbc.domain.Kawa;

public class KawaMenager {
	private Connection connection;
	private Statement statement;

	// dane do polaczenia z baza danych
	String url = "jdbc:postgresql://62.212.65.84/midaspro_daniel";
	String user = "midaspro_daniel";
	String password = "JavaTest";
	String createKawa = "Create Table kawa (id SERIAL PRIMARY KEY not null, name varchar(20) not null, type int REFERENCES type(id_type) null, cena int not null);";
	
	// wzorce do zapytan sql
	private PreparedStatement addKawa;
	private PreparedStatement delAllKawa;
	private PreparedStatement writeAllKawa;
	private PreparedStatement searchKawa;
	private PreparedStatement updateKawa;
	private PreparedStatement selectAllKawa;

	public KawaMenager() {
		try {
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();

			// sprawdzanie czy baza instnieje
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			boolean tableKawaExists = false;
			
			while (rs.next()) {
				if ("Kawa".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableKawaExists = true;
					break;
				}
			}
			
			if (!tableKawaExists)
				statement.executeUpdate(createKawa);

			// Przygotowanie wzorcow do zapytan sql
			addKawa = connection.prepareStatement("INSERT into kawa (name, type, cena) VALUES (?, ?, ?);");
			delAllKawa = connection.prepareStatement("DELETE FROM kawa where id = (?);");
			writeAllKawa = connection.prepareStatement("SELECT * from kawa;");
			selectAllKawa = connection.prepareStatement("SELECT * from kawa WHERE type = (?);");
			searchKawa = connection.prepareStatement("SELECT * from kawa where id = (?);");
			updateKawa = connection.prepareStatement("UPDATE kawa SET name = (?), type = (?), cena = (?) WHERE id = (?);");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// polaczenie z baza
	public Connection getConnection() {
		return connection;
	}

	public void delKawa(Kawa kawa) {
		try {

			delAllKawa.setInt(1, kawa.getId());
			delAllKawa.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// dodanie nowej osoby do bazy
	public void AddKawa(Kawa kawa) {

		try {
			addKawa.setString(1, kawa.getName());
			addKawa.setInt(2, kawa.getType());
			addKawa.setInt(3, kawa.getCena());
			addKawa.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// wyspisanie wszystki z bazy danych
	public ArrayList<Kawa> WriteAll() {
		ArrayList<Kawa> Lista = new ArrayList<Kawa>();

		try {
			ResultSet rs = writeAllKawa.executeQuery();
			while (rs.next()) {
				Kawa kawa = new Kawa();
				kawa.setId(rs.getInt(1));
				kawa.setName(rs.getString(2));
				kawa.setType(rs.getInt(3));
				kawa.setCena(rs.getInt(4));
				Lista.add(kawa);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Lista;
	}

	public Kawa searchKawa(int id) {
		Kawa kawa = new Kawa();

		try {
			searchKawa.setInt(1, id);
			ResultSet rs = searchKawa.executeQuery();

			while (rs.next()) {
				kawa.setId(rs.getInt(1));
				kawa.setName(rs.getString(2));
				kawa.setType(rs.getInt(3));
				kawa.setCena(rs.getInt(4));
				return kawa;
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void UpdateKawa(Kawa kawa){
		try {
			updateKawa.setString(1, kawa.getName());
			updateKawa.setInt(2, kawa.getType());
			updateKawa.setInt(3, kawa.getCena());
			updateKawa.setInt(4, kawa.getId());
			updateKawa.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void delAll(){
		try {
			statement.execute("Drop Table kawa;");
			statement.execute("Drop Table type;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Kawa> selectAll(int id) {
		ArrayList<Kawa> Lista = new ArrayList<Kawa>();

		try {
			selectAllKawa.setInt(1, id);
			ResultSet rs = selectAllKawa.executeQuery();
			while (rs.next()) {
				Kawa kawa = new Kawa();
				kawa.setId(rs.getInt(1));
				kawa.setName(rs.getString(2));
				kawa.setType(rs.getInt(3));
				kawa.setCena(rs.getInt(4));
				Lista.add(kawa);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Lista;
	}
	
}