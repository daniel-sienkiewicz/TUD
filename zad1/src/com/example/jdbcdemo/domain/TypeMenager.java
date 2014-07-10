package com.example.jdbcdemo.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.jdbc.domain.Kawa;
import com.example.jdbc.domain.Type;

public class TypeMenager {
	private Connection connection;
	private Statement statement;

	// dane do polaczenia z baza danych
	String url = "jdbc:postgresql://62.212.65.84/midaspro_daniel";
	String user = "midaspro_daniel";
	String password = "JavaTest";
	String createType = "CREATE TABLE type(id_type SERIAL PRIMARY KEY not null, name varchar(100) not null);";

	// wzorce do zapytan sql
	private PreparedStatement addType;
	private PreparedStatement delAllType;
	private PreparedStatement writeAllType;
	private PreparedStatement searchType;
	private PreparedStatement updateType;

	public TypeMenager() {
		try {
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();

			// sprawdzanie czy baza instnieje
			ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
			boolean tableKawaExists = false;
			boolean tableTypeExists = false;

			while (rs.next()) {
				if ("Type".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableTypeExists = true;
					break;
				}
			}
			
			if (!tableTypeExists)
				statement.executeUpdate(createType);

			// Przygotowanie wzorcow do zapytan sql
			addType = connection.prepareStatement("INSERT into type (name) VALUES (?);");
			delAllType = connection.prepareStatement("DELETE FROM type where id_type = (?);");
			writeAllType = connection.prepareStatement("SELECT * from type;");
			searchType = connection.prepareStatement("SELECT * from type where id_type = (?);");
			updateType = connection.prepareStatement("UPDATE type SET name = (?) WHERE id_type = (?);");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// polaczenie z baza
	public Connection getConnection() {
		return connection;
	}

	public void delType(Type type) {
		try {

			delAllType.setInt(1, type.getId());
			delAllType.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// dodanie nowego typu do bazy
	public void AddType(Type type) {

		try {
			addType.setString(1, type.getNazwa());
			addType.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// wyspisanie wszystki z bazy danych
	public ArrayList<Type> WriteAllType() {
		ArrayList<Type> Lista = new ArrayList<Type>();

		try {
			ResultSet rs = writeAllType.executeQuery();
			while (rs.next()) {
				Type type = new Type();
				type.setId(rs.getInt(1));
				type.setNazwa(rs.getString(2));
				Lista.add(type);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Lista;
	}

	public Type searchType(int id) {
		Type type = new Type();

		try {
			searchType.setInt(1, id);
			ResultSet rs = searchType.executeQuery();

			while (rs.next()) {
				type.setId(rs.getInt(1));
				type.setNazwa(rs.getString(2));
				return type;
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void UpdateType(Type type){
		try {
			updateType.setString(1, type.getNazwa());
			updateType.setInt(2, type.getId());
			updateType.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}