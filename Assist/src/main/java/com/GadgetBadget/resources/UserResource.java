package com.GadgetBadget.resources;




import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.GadgetBadget.service.user.UserService;
import com.GadgetBadget.util.User;

import com.google.gson.*;


@Path("/Tips")
public class UserResource {
	User tip = new User();

	
	@GET
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String readTips() {
		UserService tip = new UserService();

		String output = tip.readTips();
		return output;

	}
	

	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertTips(String tipData) {
		
		// Convert the input string to a JSON object
		JsonObject TipObj = new JsonParser().parse(tipData).getAsJsonObject();
		
		// Read the values from the JSON object
		String tipId = TipObj.get("tipId").getAsString();
		String relatedArea = TipObj.get("relatedArea").getAsString();
		String tipDetail = TipObj.get("tipDetail").getAsString();
		String date = TipObj.get("date").getAsString();	
		
		UserService tipobject1 = new UserService();
		
		tip.setTipId(tipId);
		tip.setRelatedArea(relatedArea);
		tip.setTipDetail(tipDetail);
		tip.setDate(date);
		

		String output = tipobject1.insertTips(tip);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateTips(String tipData) throws SQLException {
		
		// Convert the input string to a JSON object
		JsonObject TipObj = new JsonParser().parse(tipData).getAsJsonObject();
		
		// Read the values from the JSON object
		String tipId = TipObj.get("tipId").getAsString();
		String relatedArea = TipObj.get("relatedArea").getAsString();
		String tipDetail = TipObj.get("tipDetail").getAsString();
		String date = TipObj.get("date").getAsString();
		

		UserService tipobject2 = new UserService();
		
		
		tip.setTipId(tipId);
		tip.setRelatedArea(relatedArea);
		tip.setTipDetail(tipDetail);
		tip.setDate(date);
		
		String output = tipobject2.updateTips(tip);
		return output;
	}
	
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteTips(String tipData) {
		
		// Convert the input string to a JSON object
		JsonObject tipObject = new JsonParser().parse(tipData).getAsJsonObject();

		// Read the value from the element tipID
		String tipId = tipObject.get("tipId").getAsString();

		UserService tipobject3 = new UserService();
		tip.setTipId(tipId);
		String output = tipobject3.deleteTips(tip);
		return output;
	}
	


}