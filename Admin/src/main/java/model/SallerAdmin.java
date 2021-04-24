package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SallerAdmin {
	
	//A common method to connect to the DB
		private Connection connect() 
		
		 { 
			
			Connection con = null; 
			try
			
		 { 
				Class.forName("com.mysql.jdbc.Driver"); 
		 
		 //Provide the correct details: DBServer/DBName, username, password 
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", "root"); 
		 
		 } 
			
			catch (Exception e) 
			{e.printStackTrace();} 
			return con; 
			
		 } 
		
		public String insertSallerAdmin(String sallername, String nic, String email, String phonenumber) 
		
		 { 
			
			String output = ""; 
			try
			
			{ 
				
					Connection con = connect(); 
					if (con == null) 
					{return "Error while connecting to the database for inserting."; } 
		 
					// create a prepared statement
					String query = " insert into salleradmin (`SallerID`,`SallerName`,`NIC`,`Email`,`PhoneNumber`)"
								+ " values (?, ?, ?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, sallername); 
		 preparedStmt.setString(3, nic); 
		 preparedStmt.setString(4, email); 
		 preparedStmt.setString(5, phonenumber);
		 
		 
		// execute the statement
		 	preparedStmt.execute(); 
		 	con.close(); 
		 	output = "Inserted successfully"; 
		 	
		 } 
			
		 catch (Exception e) 
			
		 { 
			 
			 output = "Error while inserting the saller details."; 
			 System.err.println(e.getMessage()); 
			 
		 } 
			
		 return output; 
		 
		 } 
		
		public String readSallerAdmin() 
		
		 { 
			
			String output = ""; 
		 try
		 
		 { 
			 
			 Connection con = connect(); 
		 if (con == null) 
		 		{return "Error while connecting to the database for reading."; } 
		 		// Prepare the HTML table to be displayed
		 		output = "<table border='1'><tr><th>SallerID</th><th>SallerName</th>" +
		 				"<th>NIC</th>" + 
		 				"<th>Email</th>" +
		 				"<th>PhoneNumber</th>" +
		 				"<th>Update</th><th>Remove</th></tr>"; 
		 
		 		String query = "select * from salleradmin"; 
		 		Statement stmt = con.createStatement(); 
		 		ResultSet rs = stmt.executeQuery(query); 
		 		// iterate through the rows in the result set
		 while (rs.next())
			 
		 { 
			 String SallerID = Integer.toString(rs.getInt("SallerID")); 
			 String SallerName = rs.getString("SallerName"); 
			 String NIC = rs.getString("NIC"); 
			 String Email = rs.getString("Email"); 
			 String PhoneNumber = rs.getString("PhoneNumber"); 
		 // Add into the HTML table
			 output += "<tr><td>" + SallerID + "</td>"; 
			 output += "<td>" + SallerName + "</td>";
			 output += "<td>" + NIC + "</td>";
			 output += "<td>" + Email + "</td>"; 
			 output += "<td>" + PhoneNumber + "</td>"; 
		 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
							 + "<td><form method='post' action='salleradmin.jsp'>"
							 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
							+ "<input name='SallerID' type='hidden' value='" + SallerID 
							+ "'>" + "</form></td></tr>"; 
		 } 	
		 		con.close(); 
		 		// Complete the HTML table
		 		output += "</table>"; 
		 } 
		 
		 catch (Exception e) 
		 
		 { 
			 
			 	output = "Error while reading the saller details."; 
			 	System.err.println(e.getMessage()); 
			 	
		 } 
		 
		 return output; 
		 
		 } 
		
		public String updateSallerAdmin(String ID, String sallername, String nic, String email, String phonenumber)

		{ 
			 String output = ""; 
			 try
			 { 
				 		Connection con = connect(); 
				 		if (con == null) 
				 		{return "Error while connecting to the database for updating."; } 
				 		// create a prepared statement
				 		String query = "UPDATE salleradmin SET SallerName=?,NIC=?,Email=?,PhoneNumber=? WHERE SallerID=?"; 
				 		PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
				 		// binding values
				 		preparedStmt.setString(1, sallername); 
				 		preparedStmt.setString(2, nic); 
				 		preparedStmt.setString(3, email); 
				 		preparedStmt.setString(4, phonenumber); 
				 		preparedStmt.setInt(5, Integer.parseInt(ID)); 
			
				 		// execute the statement
				 		preparedStmt.execute(); 
				 		con.close(); 
				 		output = "Updated successfully"; 
			 } 
			 
			 catch (Exception e) 
			 
			 { 
				 
				 	output = "Error while updating the saller details."; 
				 	System.err.println(e.getMessage()); 
				 	
			 } 
			 
			 return output;
			 
			 } 
		
			public String deleteSallerAdmin(String SallerID) 
			 { 
					String output = ""; 
					
					try
					{ 
						Connection con = connect(); 
						if (con == null) 
						{return "Error while connecting to the database for deleting."; } 
			 
						// create a prepared statement
						String query = "delete from salleradmin where SallerID=?"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(SallerID)); 
			 
						// execute the statement
						preparedStmt.execute(); 
						con.close(); 
						output = "Deleted successfully"; 
			 
					} 
					catch (Exception e) 
					
					{ 
						output = "Error while deleting the saller details."; 
						System.err.println(e.getMessage()); 
					} 
			 return output; 
			 
			 }

}
