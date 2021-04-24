package model;

import java.sql.*;

public class funding
{ //A common method to connect to the DB
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
public String insertfunding(String rName, String value, String rId)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for inserting."; }
  
 // create a prepared statement
 String query = " insert into funding(`fId`,`researchName`,`value`,`researchId`)" + " values (?, ?, ?, ?)";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, 0);
 preparedStmt.setString(2, rName);
 preparedStmt.setString(3, value);
 
 preparedStmt.setString(4, rId);
// execute the statement
 
 preparedStmt.execute();
 con.close();
 output = "Inserted successfully";
 }
 catch (Exception e)
 {
 output = "Error while inserting the funding.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String readfunding()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for reading."; }
 // Prepare the HTML table to be displayed
 output = "<table border='1'><tr><th>Funding ID</th>" +
 "<th>Research Name</th>" +
 "<th>Value</th>" +
 "<th>Research ID</th><th>Remove</th><th>Update</th></tr>";

 String query = "select * from funding";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
 String fId = Integer.toString(rs.getInt("fId"));
 String researchName = rs.getString("researchName");
 String value = rs.getString("value");

 String researchId = rs.getString("researchId");
 // Add into the HTML table
 
 output += "<tr><td>" + fId + "</td>";
 output += "<td>" + researchName + "</td>";

 output += "<td>" + value + "</td>";
 output += "<td>" + researchId + "</td>";
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"+ "<td><form method='post' action='fundings.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
 + "<input name='fId' type='hidden' value='" + fId
 + "'>" + "</form></td></tr>";
 }
 con.close();
 // Complete the HTML table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the funding.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String updatefunding(String ID, String rName, String value, String rId){
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE funding SET researchName=?,value=?,researchId=? WHERE fId=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, rName);
	 preparedStmt.setString(2, value);
	 
	 preparedStmt.setString(3, rId);
	 preparedStmt.setInt(4, Integer.parseInt(ID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the funding.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String deletefunding(String fId)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from funding where fId=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(fId));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the funding.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	} 