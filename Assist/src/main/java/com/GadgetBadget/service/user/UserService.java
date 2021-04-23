package com.GadgetBadget.service.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.GadgetBadget.model.DBconnection;
import com.GadgetBadget.util.User;



public class UserService {
	Connection con = null;

	public UserService() {

		con = DBconnection.connecter();
	}
	

	//Insert the tips
	public String insertTips(User user) {


		String output;
		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error! There are something wrong when connecting to the database for inserting.";
			}

			
			
		String query = "INSERT INTO `tipstable`(`tipId`, `relatedArea`, `tipDetail`, `date`) VALUES (?,?,?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, user.getTipId());
			preparedStmt.setString(2, user.getRelatedArea());
			preparedStmt.setString(3, user.getTipDetail());
			preparedStmt.setString(4, user.getDate());
			
			
			preparedStmt.execute();

			output = "Data Inserted Successfully";

		} catch (SQLException e) {
			output = "Error! There are something wrong when inserting the tip.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	//Read the tips
	public String readTips() {
		String output1 = " ";
		
		// Prepare the html table to be displayed 
		output1 = "<table border=\"1\"><tr><th>Tip ID</th>" + "<th>Related Area</th><th>Tip Detail</th><th>Date</th></tr>";
		
		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error! There are something wrong when connecting to the database.";
			}
			
			String query = "select * from `tipstable`";
			
			Statement st = con.createStatement();
			ResultSet results = st.executeQuery(query);
			

			//Show the results through the rows in the table
			while (results.next()) {
				String tipId = results.getString("tipId");
				String relatedArea = results.getString("relatedArea");
				String tipDetail = results.getString("tipDetail");
				String date = results.getString("date");
				
				// Add values into the html table
				output1 += "<tr><td>" + tipId + "</td>";
				output1 += "<td>" + relatedArea + "</td>";
				output1 += "<td>" + tipDetail + "</td>";
				output1 += "<td>" + date + "</td>";
				   
			}

			// Complete the html table
			output1 += "</table>";
			
		} 
		catch (Exception e) {
			output1 = "Error! There are something wrong.";
			System.err.println(e.getMessage());
		}
		return output1;
	}
	
	//Update the tips
		public String updateTips(User user) {
		
		
		String output = "";
		
		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error! There are something wrong when connecting to the database for updating.";
			}
			
			//checking the id correct or incorrect
			String query = "UPDATE `tipstable` SET relatedArea=?,tipDetail=?,date=? WHERE tipId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding the values
			preparedStmt.setString(1, user.getRelatedArea());
			preparedStmt.setString(2, user.getTipDetail());
			preparedStmt.setString(3, user.getDate());
			preparedStmt.setString(4, user.getTipId());


			// execute the statement
			preparedStmt.executeUpdate();
			
			
			
			if(preparedStmt.executeUpdate() == 1) {
				output = "Data Updated Successfully";			
			}
			else{
				output = "Error! There are something wrong when updating the tip.";			}
			
				con.close();
			
		} catch (SQLException e) {
			output = "Error! There are something wrong when updating the tip.";
			System.err.println(e.getMessage());
		}
		return output;
	}

		//Delete the tips
	public String deleteTips(User user) {
		
		
		String output="";
		

		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error! There are something wrong when connecting to the database for deleting.";
			}
			
			String query ="DELETE FROM `tipstable` WHERE tipId=?";

			
			// create a prepared statement
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, user.getTipId());
			
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Data Deleted Successfully";
			
			
		} catch (Exception e) {
			output = "Error! There are something wrong when deleting the tip..";
			System.err.println(e.getMessage());
		}
		return output;
	}


}
	