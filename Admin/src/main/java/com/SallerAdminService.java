package com;

import model.SallerAdmin; 

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/SalleAdmin")

public class SallerAdminService 
{
	SallerAdmin salleradminObj = new SallerAdmin(); 
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertSallerAdmin(@FormParam("SallerName") String SallerName, 
			 @FormParam("NIC") String NIC, 
			 @FormParam("Email") String Email, 
			 @FormParam("PhoneNumber") String PhoneNumber) 
			{ 
				String output = salleradminObj.insertSallerAdmin(SallerName, NIC, Email, PhoneNumber);
				return output;

			}
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readSallerAdmin() 
	 { 
	 return salleradminObj.readSallerAdmin(); 
	 }

	
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateSallerAdmin(String adminSallerData) 
	{ 
		//Convert the input string to a JSON object 
		JsonObject adminsallerObject = new JsonParser().parse(adminSallerData).getAsJsonObject(); 
		//Read the values from the JSON object
			String SallerID = adminsallerObject.get("SallerID").getAsString(); 
			String SallerName = adminsallerObject.get("SallerName").getAsString(); 
			String NIC = adminsallerObject.get("NIC").getAsString(); 
			String Email = adminsallerObject.get("Email").getAsString(); 
			String PhoneNumber = adminsallerObject.get("PhoneNumber").getAsString(); 
			String output = salleradminObj.updateSallerAdmin(SallerID, SallerName, NIC, Email, PhoneNumber); 
	return output; 
	}
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteSallerAdmin(String salleradminData) 
	{ 
	//Convert the input string to an XML document 
	 Document doc = Jsoup.parse(salleradminData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <buyerID>
	 String SallerID = doc.select("SallerID").text(); 
	 String output = salleradminObj.deleteSallerAdmin(SallerID); 
	return output; 
	}


}
