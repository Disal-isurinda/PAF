package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetFunding {
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

	public String insertResearchFunding(String ResearchName, String ResearcherName, String ResearchDescription) {
		String output = null;
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " insert into researchfunding(`ResearchID`,`ResearchName`,`ResearcherName`,`ResearchDescription`)"
					+ " values (?, ?, ?, ?)";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			// binding values
			preparedStatement.setInt(1, 0);
			preparedStatement.setString(2, ResearchName);
			preparedStatement.setString(3, ResearcherName);
			preparedStatement.setString(4, ResearchDescription);

// execute the statement

			preparedStatement.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the Fuding research record.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateResearchFunding(int UpResearchId, String UpResearchName, String UpResearcherName,
			String UpResearchDescription) {
		String output = null;
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE researchfunding SET ResearchName=?,ResearcherName=?,ResearchDescription=? WHERE ResearchID=?";
			PreparedStatement preparedStatement = con.prepareStatement(query);

			// binding values
			preparedStatement.setString(1, UpResearchName);
			preparedStatement.setString(2, UpResearcherName);
			preparedStatement.setString(3, UpResearchDescription);
			preparedStatement.setInt(4, UpResearchId);

			// execute the statement
			preparedStatement.execute();
			con.close();
			output = "Updated successfully";

		} catch (Exception e) {
			output = "Error while updating the Fuding research record.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	

	public String deleteResearchFunding(String ResearchId) {
		String output = null;
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from researchfunding where researchId=?";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			// binding values
			preparedStatement.setString(1, ResearchId);
			// execute the statement
			preparedStatement.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Fuding recearch record.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String retriveResearchFunding() {
		String output = null;
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the HTML table to be displayed
			output = "<table border='1'><tr><th>Recearch ID</th>" + "<th>Research Name</th>"
					+ "<th>Researcher Name</th>" + "<th>Researcher Discription</th>";

			String query = "select * from researchfunding";
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			// iterate through the rows in the result set
			while (resultSet.next()) {
				int ResearchId = resultSet.getInt("ResearchId");
				String ResearchName = resultSet.getString("ResearchName");
				String ResearcherName = resultSet.getString("ResearcherName");
				String ResearchDescription = resultSet.getString("ResearchDescription");

				// Add into the HTML table
				output += "<tr><td>" + ResearchId + "</td>";
				output += "<td>" + ResearchName + "</td>";
				output += "<td>" + ResearcherName + "</td>";
				output += "<td>" + ResearchDescription + "</td>";

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
