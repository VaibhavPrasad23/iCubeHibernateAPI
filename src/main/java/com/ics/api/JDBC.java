package com.ics.api;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBC {

	private static final Logger LOGGER = Logger.getLogger(JDBC.class.getName());
	private static final String DB_URL = "jdbc:mysql://localhost:3306/mydp";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "Welcome@123";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			LOGGER.info("MySQL driver loaded successfully.");
		} catch (ClassNotFoundException e) {
			LOGGER.log(Level.SEVERE, "Failed to load MySQL driver", e);
		}
	}

	public List<Info> jdbc_serve() {
		List<Info> list2 = new ArrayList<>();
		String query = "SELECT * FROM login";

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query)) {

			LOGGER.info("Executing query to fetch all users.");
			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				Info user = new Info(id, username, password);
				list2.add(user);
				LOGGER.info("Fetched user: " + user);
				System.out.println("Username: " + username + "\nPassword: " + password);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error retrieving users", e);
		}

		LOGGER.info("Completed fetching users. Total users fetched: " + list2.size());
		return list2;
	}

	public String jdbc_add() {
		String query = "INSERT INTO login (username, password, ID) VALUES (?, ?, NULL)";
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				Scanner input = new Scanner(System.in)) {

			System.out.println("Enter username:");
			String varuser = input.nextLine().trim();
			System.out.println("Enter password:");
			String passphrase = input.nextLine().trim();

			if (varuser.isEmpty() || passphrase.isEmpty()) {
				System.out.println("Field(s) may be empty");
				LOGGER.warning("Empty fields detected. Prompting user again.");
				return jdbc_add();
			} else {
				preparedStatement.setString(1, varuser);
				preparedStatement.setString(2, passphrase);
				int rowsAffected = preparedStatement.executeUpdate();
				LOGGER.info("User added successfully: " + varuser);
				System.out.println(rowsAffected + " row(s) inserted.");
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error adding user", e);
		}
		return null;
	}

	public void jdbc_into(Info user) {
		String query = "INSERT INTO login (username, password, ID) VALUES (?, ?, NULL)";
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement statement = connection.prepareStatement(query)) {

			LOGGER.info("Adding user: " + user.getUsername());
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.executeUpdate();
			LOGGER.info("User added successfully.");
			System.out.println("User Entered.");
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error adding user", e);
		}
	}

	public List<Info> jdbc_specuser(int id) {
		List<Info> list2 = new ArrayList<>();
		String query = "SELECT * FROM login WHERE ID = ?";

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setInt(1, id);
			LOGGER.info("Executing query to fetch user by ID: " + id);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					String username = resultSet.getString("username");
					String password = resultSet.getString("password");
					Info user = new Info(id, username, password);
					list2.add(user);
					LOGGER.info("Fetched user: " + user);
				}
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error retrieving user by ID", e);
		}

		LOGGER.info("Completed fetching user by ID. Total users fetched: " + list2.size());
		return list2;
	}

	public List<Info> jdbc_specusername(String usr) {
		List<Info> list2 = new ArrayList<>();
		String query = "SELECT * FROM login WHERE username = ?";

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setString(1, usr);
			LOGGER.info("Executing query to fetch user by username: " + usr);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					int id = resultSet.getInt("ID");
					String username = resultSet.getString("username");
					String password = resultSet.getString("password");
					Info user = new Info(id, username, password);
					list2.add(user);
					LOGGER.info("Fetched user: " + user);
				}
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error retrieving user by username", e);
		}

		LOGGER.info("Completed fetching user by username. Total users fetched: " + list2.size());
		return list2;
	}

	public String jdbc_forgot() {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				Scanner input = new Scanner(System.in)) {

			LOGGER.info("Prompting user for credential update choice.");
			System.out.println("Press 1 to change Password.\nPress 2 to change ID.");
			int choice = input.nextInt();
			input.nextLine(); // Consume newline

			String query;
			if (choice == 1) {
				System.out.println("Enter username:");
				String username = input.nextLine();
				System.out.println("Enter new password:");
				String password = input.nextLine();
				query = "UPDATE login SET password = ? WHERE username = ?";
				try (PreparedStatement statement = connection.prepareStatement(query)) {
					statement.setString(1, password);
					statement.setString(2, username);
					statement.executeUpdate();
					LOGGER.info("Password updated for user: " + username);
				}
			} else if (choice == 2) {
				System.out.println("Enter username:");
				String username = input.nextLine();
				System.out.println("Enter new ID:");
				int id = input.nextInt();
				query = "UPDATE login SET ID = ? WHERE username = ?";
				try (PreparedStatement statement = connection.prepareStatement(query)) {
					statement.setInt(1, id);
					statement.setString(2, username);
					statement.executeUpdate();
					LOGGER.info("ID updated for user: " + username);
				}
			} else {
				System.out.println("Invalid choice.");
				LOGGER.warning("Invalid choice made by user.");
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Error updating credentials", e);
		}
		return null;
	}

	/////////////////////////////////////////////////

	public void create(Info user) throws SQLException, IOException {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				Scanner input = new Scanner(System.in)) {

			String sql = "INSERT INTO login (username, password, ID) VALUES (?, ?, NULL)";
			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, user.getId());
				statement.setString(2, user.getUsername());

				statement.setString(3, user.getPassword());

				System.out.println("inserted into Database");
				statement.executeUpdate();
			} catch (IllegalStateException e) {
				System.out.println(e);

				e.printStackTrace();
			}
		}
	}

	public void updateuser(Info user) throws SQLException {

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				Scanner input = new Scanner(System.in)) {
//        String sql = "UPDATE login SET username = ?, password = ? WHERE id = ? OR username = ?";
			String sql = "UPDATE login SET username = ?, password = ? WHERE id = ? ";

			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setString(1, user.getUsername());
				statement.setString(2, user.getPassword());

				statement.setInt(3, user.getId());

				statement.executeUpdate();
				System.out.println("Update Request Complete");
			} catch (SQLException e) {
				System.out.println(e);

				e.printStackTrace();
			}
		}
	}

	public void deleteuserID(int id) throws SQLException {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				Scanner input = new Scanner(System.in)) {
			String sql = "DELETE FROM login WHERE id = ?";

			try (PreparedStatement statement = connection.prepareStatement(sql)) {
				statement.setInt(1, id);
				statement.executeUpdate();
				System.out.println("User Deleted Successfully");
			} catch (SQLException | IllegalStateException e) {
				System.out.println(e);

				e.printStackTrace();
			}
		}
	}

	public Info getoneuserID(int id) throws SQLException {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				Scanner input = new Scanner(System.in)) {
			String sql = "select * from login where id= ?";
			Info z = new Info();
			try {
				Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery(sql);
				if (rs.next()) {

					z.setId(rs.getInt(1));
					z.setUsername(rs.getString(2));
					z.setPassword(rs.getString(3));

				}
			} catch (SQLException e) {
				System.out.println(e);
				e.printStackTrace();
			}
			return z;
		}
	}

}
