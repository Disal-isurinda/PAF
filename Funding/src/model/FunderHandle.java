package model;

import java.sql.*;

public class FunderHandle { // A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertfunder(String FName, String NIC, String Address, String TEL, String Uname, String pwd) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " insert into funders(`id`,`fname`,`NIC`,`address`,`tel`,`userName`,`password`)"
					+ " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, FName);
			preparedStmt.setString(3, NIC);

			preparedStmt.setString(4, Address);
			preparedStmt.setString(5, TEL);
			preparedStmt.setString(6, Uname);
			preparedStmt.setString(7, pwd);
// execute the statement

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the funder";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readfunder() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the HTML table to be displayed
			output = "<table border='1'><tr><th>Funder ID</th>" + "<th>Funder Name</th>" + "<th>NIC</th>"
					+ "<th>Address ID</th><th>TEL</th><th>Username</th><th>Password</th></tr>";

			String query = "select * from funders";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("id"));
				String fname = rs.getString("fname");
				String NIC = rs.getString("NIC");

				String address = rs.getString("address");
				String tel = rs.getString("tel");
				String userName = rs.getString("userName");
				String password = rs.getString("password");
				// Add into the HTML table

				output += "<tr><td>" + id + "</td>";
				output += "<td>" + fname + "</td>";

				output += "<td>" + NIC + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + tel + "</td>";
				output += "<td>" + userName + "</td>";
				output += "<td>" + password + "</td>";
				// buttons
//				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
//						+ "<td><form method='post' action='funders.jsp'>"
//						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
//						+ "<input name='fId' type='hidden' value='" + id + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the HTML table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the funders.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatefunder(String Id, String Fname, String NIC, String Address, String TEL, String uName,
			String pwd) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE funders SET fname=?,NIC=?,address=?,tel=?,userName=?,password=? WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, Fname);
			preparedStmt.setString(2, NIC);

			preparedStmt.setString(3, Address);
			preparedStmt.setString(4, TEL);
			preparedStmt.setString(5, uName);
			preparedStmt.setString(6, pwd);
			preparedStmt.setInt(7, Integer.parseInt(Id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the funders.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletefunder(String id) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from funders where id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the funder.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
