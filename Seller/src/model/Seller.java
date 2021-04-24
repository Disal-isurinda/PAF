package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Seller {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/paf", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertResearch(String researchName, String researcherName, String researchDescription,
			String researchPrice) {
		String output = null;
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " insert into research(`researchId`,`researchName`,`researcherName`,`researchDescription`,`researchPrice`)"
					+ " values (?, ?, ?, ?,?)";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			// binding values
			preparedStatement.setInt(1, 0);
			preparedStatement.setString(2, researchName);
			preparedStatement.setString(3, researcherName);
			preparedStatement.setString(4, researchDescription);
			preparedStatement.setString(5, researchPrice);
// execute the statement

			preparedStatement.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the research record.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateResearch(int ResearchId, String ResearchName, String ResearcherName, String ResearchDescription,
			String ResearchPrice) {
		String output = null;
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE research SET researchName=?,researcherName=?,researchDescription=?,researchPrice=? WHERE researchId=?";
			PreparedStatement preparedStatement = con.prepareStatement(query);

			// binding values
			preparedStatement.setString(1, ResearchName);
			preparedStatement.setString(2, ResearcherName);
			preparedStatement.setString(3, ResearchDescription);
			preparedStatement.setString(4, ResearchPrice);
			preparedStatement.setInt(5, ResearchId);

			// execute the statement
			preparedStatement.execute();
			con.close();
			output = "Updated successfully";

		} catch (Exception e) {
			output = "Error while updating the research record.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteResearch(String researchId) {
		String output = null;
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from research where researchId=?";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			// binding values
			preparedStatement.setString(1, researchId);
			// execute the statement
			preparedStatement.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the recearch record.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String retriveResearch() {
		String output = null;
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the HTML table to be displayed
			output = "<table border='1'><tr><th>Recearch ID</th>" + "<th>Research Name</th>"
					+ "<th>Researcher Name</th>" + "<th>Researcher Discription</th>" + "<th>Research Price</th></tr>";

			String query = "select * from research";
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			// iterate through the rows in the result set
			while (resultSet.next()) {
				int researchId = resultSet.getInt("researchId");
				String researchName = resultSet.getString("researchName");
				String researcherName = resultSet.getString("researcherName");
				String researchDescription = resultSet.getString("researchDescription");
				String researchPrice = resultSet.getString("researchPrice");

				// Add into the HTML table
				output += "<tr><td>" + researchId + "</td>";
				output += "<td>" + researchName + "</td>";
				output += "<td>" + researcherName + "</td>";
				output += "<td>" + researchDescription + "</td>";
				output += "<td>" + researchPrice + "</td>";

			}
			con.close();

			// Complete the HTML table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the recearch table.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
