package model;

import java.sql.*;

public class BuyerAdmin 

{
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
	
	public String insertBuyerAdmin(String buyername, String nic, String email, String phonenumber) 
	
	 { 
		
		String output = ""; 
		try
		
		{ 
			
				Connection con = connect(); 
				if (con == null) 
				{return "Error while connecting to the database for inserting."; } 
	 
				// create a prepared statement
				String query = " insert into buyeradmin (`BuyerID`,`BuyerName`,`NIC`,`Email`,`PhoneNumber`)"
							+ " values (?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, buyername); 
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
		 
		 output = "Error while inserting the buyer details."; 
		 System.err.println(e.getMessage()); 
		 
	 } 
		
	 return output; 
	 
	 } 
	
	public String readBuyerAdmin() 
	
	 { 
		
		String output = ""; 
	 try
	 
	 { 
		 
		 Connection con = connect(); 
	 if (con == null) 
	 		{return "Error while connecting to the database for reading."; } 
	 		// Prepare the html table to be displayed
	 		output = "<table border='1'><tr><th>BuyerID</th><th>BuyerName</th>" +
	 				"<th>NIC</th>" + 
	 				"<th>Email</th>" +
	 				"<th>PhoneNumber</th>" +
	 				"<th>Update</th><th>Remove</th></tr>"; 
	 
	 		String query = "select * from buyeradmin"; 
	 		Statement stmt = con.createStatement(); 
	 		ResultSet rs = stmt.executeQuery(query); 
	 		// iterate through the rows in the result set
	 while (rs.next())
		 
	 { 
		 String BuyerID = Integer.toString(rs.getInt("BuyerID")); 
		 String BuyerName = rs.getString("BuyerName"); 
		 String NIC = rs.getString("NIC"); 
		 String Email = rs.getString("Email"); 
			String PhoneNumber = rs.getString("PhoneNumber"); 
	 // Add into the HTML table
		 output += "<tr><td>" + BuyerID + "</td>"; 
		 output += "<td>" + BuyerName + "</td>";
		 output += "<td>" + NIC + "</td>";
		 output += "<td>" + Email + "</td>"; 
		 output += "<td>" + PhoneNumber + "</td>"; 
	 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						 + "<td><form method='post' action='buyeradmin.jsp'>"
						 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='BuyerID' type='hidden' value='" + BuyerID 
						+ "'>" + "</form></td></tr>"; 
	 } 	
	 		con.close(); 
	 		// Complete the html table
	 		output += "</table>"; 
	 } 
	 
	 catch (Exception e) 
	 
	 { 
		 
		 	output = "Error while reading the buyer details."; 
		 	System.err.println(e.getMessage()); 
		 	
	 } 
	 
	 return output; 
	 
	 } 
	
	public String updateBuyerAdmin(String ID, String buyername, String nic, String email, String phonenumber)

	{ 
		 String output = ""; 
		 try
		 { 
			 		Connection con = connect(); 
			 		if (con == null) 
			 		{return "Error while connecting to the database for updating."; } 
			 		// create a prepared statement
			 		String query = "UPDATE buyeradmin SET BuyerName=?,NIC=?,Email=?,PhoneNumber=? WHERE BuyerID=?"; 
			 		PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
			 		// binding values
			 		preparedStmt.setString(1, buyername); 
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
			 
			 	output = "Error while updating the buyer details."; 
			 	System.err.println(e.getMessage()); 
			 	
		 } 
		 
		 return output;
		 
		 } 
	
		public String deleteBuyerAdmin(String BuyerID) 
		 { 
				String output = ""; 
				
				try
				{ 
					Connection con = connect(); 
					if (con == null) 
					{return "Error while connecting to the database for deleting."; } 
		 
					// create a prepared statement
					String query = "delete from buyeradmin where BuyerID=?"; 
					PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(BuyerID)); 
		 
					// execute the statement
					preparedStmt.execute(); 
					con.close(); 
					output = "Deleted successfully"; 
		 
				} 
				catch (Exception e) 
				
				{ 
					output = "Error while deleting the buyer details."; 
					System.err.println(e.getMessage()); 
				} 
		 return output; 
		 
		 }
		
}
