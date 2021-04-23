package com.order.resources;


import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.google.gson.*;
import com.order.model.Order;
import com.order.service.OrderService;


@Path("/Orders")
public class OrderResource {
	Order order = new Order();

		//retrieve
		@GET
		@Path("/get")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_HTML)
		public String readOrders() {
			OrderService order = new OrderService();
	
			String output = order.readOrders();
			return output;
	
		}
		
	
		@GET
		@Path("/getnames")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_HTML)
		public String readSoftwareNames() {
			OrderService order = new OrderService();

			String output = order.readSoftwareNames();
			return output;

		}
		
		@GET
		@Path("/getdetails")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String readSoftwareDetails(String orderData) {
			JsonObject orderObject = new JsonParser().parse(orderData).getAsJsonObject();

			// Read the value from the element <doctorID>
			String orderId = orderObject.get("orderId").getAsString();

			OrderService orderobject3 = new OrderService();
			order.setOrderId(orderId);
			String output = orderobject3.readSoftwareDetails(order);
			return output;
		}
	

	//insert
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertOrders(String orderData) {
		// Convert the input string to a JSON object
		JsonObject OrderObj = new JsonParser().parse(orderData).getAsJsonObject();
		// Read the values from the JSON object

		String orderId = OrderObj.get("orderId").getAsString();
		String buyerName = OrderObj.get("buyerName").getAsString();
		String address = OrderObj.get("address").getAsString();
		String NIC = OrderObj.get("NIC").getAsString();
		String sofwareName = OrderObj.get("sofwareName").getAsString();
		Float cost = OrderObj.get("cost").getAsFloat();
		String date = OrderObj.get("date").getAsString();
		String status = OrderObj.get("status").getAsString();
		
		
		OrderService orderobject1 = new OrderService();
		order.setOrderId(orderId);
		order.setBuyerName(buyerName);
		order.setAddress(address);
		order.setNIC(NIC);
		order.setSofwareName(sofwareName);
		order.setCost(cost);
		order.setDate(date);
		order.setStatus(status);

		String output = orderobject1.insertOrders(order);
		return output;
	}
	
	//update
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateOrders(String orderData) throws SQLException {
		// Convert the input string to a JSON object
		JsonObject OrderObj = new JsonParser().parse(orderData).getAsJsonObject();
		// Read the values from the JSON object

		String orderId = OrderObj.get("orderId").getAsString();
		String buyerName = OrderObj.get("buyerName").getAsString();
		String address = OrderObj.get("address").getAsString();
		String NIC = OrderObj.get("NIC").getAsString();
		String sofwareName = OrderObj.get("sofwareName").getAsString();
		Float cost = OrderObj.get("cost").getAsFloat();
		String date = OrderObj.get("date").getAsString();
		String status = OrderObj.get("status").getAsString();
		
		

		OrderService orderobject2 = new OrderService();
		
		order.setOrderId(orderId);
		order.setBuyerName(buyerName);
		order.setAddress(address);
		order.setNIC(NIC);
		order.setSofwareName(sofwareName);
		order.setCost(cost);
		order.setDate(date);
		order.setStatus(status);

		String output = orderobject2.updateOrders(order);
		return output;
	}
	
	//delete
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteOrders(String orderData) {
		JsonObject orderObject = new JsonParser().parse(orderData).getAsJsonObject();

		// Read the value from the element <doctorID>
		String orderId = orderObject.get("orderId").getAsString();

		OrderService orderobject3 = new OrderService();
		order.setOrderId(orderId);
		String output = orderobject3.deleteOrders(order);
		return output;
	}
	
//		//updte status
//		@PUT
//		@Path("/")
//		@Consumes(MediaType.APPLICATION_JSON)
//		@Produces(MediaType.TEXT_PLAIN)
//		public String updateStatus(String orderData) throws SQLException {
//			// Convert the input string to a JSON object
//			JsonObject OrderObj = new JsonParser().parse(orderData).getAsJsonObject();
//			// Read the values from the JSON object
//
//			String orderId = OrderObj.get("orderId").getAsString();
//			String buyerName = OrderObj.get("buyerName").getAsString();
//			String address = OrderObj.get("address").getAsString();
//			String NIC = OrderObj.get("NIC").getAsString();
//			String sofwareName = OrderObj.get("sofwareName").getAsString();
//			Float cost = OrderObj.get("cost").getAsFloat();
//			String date = OrderObj.get("date").getAsString();
//			String status = OrderObj.get("status").getAsString();
//			
//
//			OrderService orderobject4 = new OrderService();
//			
//			order.setOrderId(orderId);
//			order.setBuyerName(buyerName);
//			order.setAddress(address);
//			order.setNIC(NIC);
//			order.setSofwareName(sofwareName);
//			order.setCost(cost);
//			order.setDate(date);
//			order.setStatus(status);
//
//			String output = orderobject4.updateStatus(order);
//			return output;
//		}


}