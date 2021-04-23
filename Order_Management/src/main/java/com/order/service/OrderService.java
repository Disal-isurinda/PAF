package com.order.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.order.model.Order;
import com.order.util.DBconnection;



public class OrderService {
	

	Connection con = null;

	public OrderService() {

		con = DBconnection.connecter();
	}
	
	//function to insert
	public String insertOrders(Order order) {

		// make variable to get the output text
		String output;
		try {
			Connection con = DBconnection.connecter();
			
			//check the db connection
			if (con == null) {
				return "Error...(Insert-db)";
			}

			
		//insert query	
		String query = "INSERT INTO `orderdetails`(`orderId`, `buyerName`, `address`, `NIC`, `sofwareName`, `cost`, `date`) VALUES (?,?,?,?,?,?,?)";

			//assign input details
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, order.getOrderId());
			preparedStmt.setString(2, order.getBuyerName());
			preparedStmt.setString(3, order.getAddress());
			if (order.getNIC()=="") {
				output = "enter nic";
			}
			preparedStmt.setString(4, order.getNIC());
			preparedStmt.setString(5, order.getSofwareName());
			preparedStmt.setFloat(6, order.getCost());
			preparedStmt.setString(7, order.getDate());
			//preparedStmt.setString(8, order.getStatus());

			 //excecute the query with input details 
			preparedStmt.execute();

			output = "Order added Successfully!";

		} catch (SQLException e) {
			output = "Error..!(Insert)";
			System.err.println(e.getMessage());
		}

		return output;
	}

	//retrieve function
	public String readOrders() {
		String output1 = " ";
		
		//make a table to show details
		output1 = "<table border=\"1\"><tr><th>Order ID</th>" + "<th>Buyer Name</th><th>Address</th>"
				+ "<th>NIC</th>" + "<th>Software Name</th>" + "<th>Cost</th> "+ "<th>Date</th> " + "<th>Status</th></tr>";
		
		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error...(retreive-db)";
			}
			String query = "select * from `orderdetails`";
			
			Statement st = con.createStatement();
			ResultSet results = st.executeQuery(query);
			

			//loop to get all raws
			while (results.next()) {
				String orderId = results.getString("orderId");
				String buyerName = results.getString("buyerName");
				String address = results.getString("address");
				String NIC = results.getString("NIC");
				String sofwareName = results.getString("sofwareName");
				float cost = results.getFloat("cost");
				String date = results.getString("date");
				String status = results.getString("status");
				
				// Add details to the table
				output1 += "<tr><td>" + orderId + "</td>";
				output1 += "<td>" + buyerName + "</td>";
				output1 += "<td>" + address + "</td>";
				output1 += "<td>" + NIC + "</td>";
				output1 += "<td>" + sofwareName + "</td>";
				output1 += "<td>" + cost +"</td>";
				output1 += "<td>" + date +"</td>"; 
				output1 += "<td>" + status +"</td>"; 
				
			}

			// Complete the html table
			output1 += "</table>";
		} catch (Exception e) {
			output1 = "Error...(Retrive)";
			System.err.println(e.getMessage());
		}
		return output1;
	}
	
		//update function
		public String updateOrders(Order order) {
		
		
		String output = "";
		
		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error...(update-db)";
			}
			
			//id check
			//update query
			String query = "UPDATE `orderdetails` SET `buyerName`=?,`address`=?,`NIC`=?,`sofwareName`=?,`cost`=?,`date`=?,`status`=? WHERE `orderId`=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			//get input details
			preparedStmt.setString(1, order.getBuyerName());
			preparedStmt.setString(2, order.getAddress());
			preparedStmt.setString(3, order.getNIC());
			preparedStmt.setString(4, order.getSofwareName());
			preparedStmt.setFloat(5, order.getCost());
			preparedStmt.setString(6, order.getDate());
			preparedStmt.setString(7, order.getStatus());
			preparedStmt.setString(8, order.getOrderId());
			


			// execute query
			preparedStmt.executeUpdate();
			
			
			
			if(preparedStmt.executeUpdate() == 1) {
				output = "Order updated successfully";
			}else{
				output = "Error...(update)";			
				}
			
				con.close();
			
		} catch (SQLException e) {
			output = "Error...(update)";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//delete query
	public String deleteOrders(Order order) {
		
		
		String output="";
		

		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error...(delete-db).";
			}
			
			String query ="DELETE FROM `orderdetails` WHERE `orderId`=?";
 
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, order.getOrderId());
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Order deleted successfully!";
			
			
		} catch (Exception e) {
			output = "Error...(delete)";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String readSoftwareNames() {
		String output1 = " ";
		// Prepare the html table to be displayed
		output1 = "<table border=\"1\"><tr><th>Order ID</th>" + "<th>Software Name</th></tr>";
		
		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error...(retriveNames-db)";
			}
			String query = "select `orderId`,`sofwareName` from `orderdetails`";
			
			Statement st = con.createStatement();
			ResultSet results = st.executeQuery(query);
			

			// iterate through the rows in the result set
			while (results.next()) {
				String orderId = results.getString("orderId");
				String sofwareName = results.getString("sofwareName");

				// Add into the html table
				output1 += "<tr><td>" + orderId + "</td>";
				output1 += "<td>" + sofwareName + "</td>";

				
			}

			// Complete the html table
			output1 += "</table>";
		} catch (Exception e) {
			output1 = "Error...(Retrieve names)";
			System.err.println(e.getMessage());
		}
		return output1;
		
	}
	
	public String readSoftwareDetails(Order order) {
		String output1 = " ";
		// Prepare the html table to be displayed
		output1 = "<table border=\"1\"><tr><th>Order ID</th>" + "<th>Buyer Name</th><th>Address</th>"
				+ "<th>NIC</th>" + "<th>Software Name</th>" + "<th>Cost</th> "+ "<th>Date</th> " + "<th>Status</th></tr>";
		
		try {
			Connection con = DBconnection.connecter();
			
			if (con == null) {
				return "Error(retrieveDetails-db)";
			}
			String query = "SELECT `orderId`, `buyerName`, `address`, `NIC`, `sofwareName`, `cost`, `date`, `status` FROM `orderdetails` WHERE `orderId`='?'";
			
			Statement st = con.createStatement();
			ResultSet results = st.executeQuery(query);
			

			// iterate through the rows in the result set
			while (results.next()) {
				String orderId = results.getString("orderId");
				String buyerName = results.getString("buyerName");
				String address = results.getString("address");
				String NIC = results.getString("NIC");
				String sofwareName = results.getString("sofwareName");
				float cost = results.getFloat("cost");
				String date = results.getString("date");
				String status = results.getString("status");
				
				// Add into the html table
				output1 += "<tr><td>" + orderId + "</td>";
				output1 += "<td>" + buyerName + "</td>";
				output1 += "<td>" + address + "</td>";
				output1 += "<td>" + NIC + "</td>";
				output1 += "<td>" + sofwareName + "</td>";
				output1 += "<td>" + cost +"</td>";
				output1 += "<td>" + date +"</td>"; 
				output1 += "<td>" + status +"</td>"; 
				
			}

			// Complete the html table
			output1 += "</table>";
		} catch (Exception e) {
			output1 = "Error...(Retrieve details)";
			System.err.println(e.getMessage());
		}
		return output1;
	}
	
//	public String updateStatus(Order order) {
//		
//		
//		String output = "";
//		
//		try {
//			Connection con = DBconnection.connecter();
//			
//			if (con == null) {
//				return "Error...(update-db)";
//			}
//			
//			//id check
//			String query = "UPDATE `orderdetails` SET status=? WHERE orderId=?";
//			PreparedStatement preparedStmt = con.prepareStatement(query);
//			// binding values
//			preparedStmt.setString(8, order.getStatus());
//			preparedStmt.setString(7, order.getOrderId());
//
//
//			// execute the statement
//			preparedStmt.executeUpdate();
//			
//			
//			
//			if(preparedStmt.executeUpdate() == 1) {
//				output = "Updated status successfully";
//			}else{
//				output = "Error while updating the orders.";			
//				}
//			
//				con.close();
//			
//		} catch (SQLException e) {
//			output = "Error...(update)";
//			System.err.println(e.getMessage());
//		}
//		return output;
//	}

}