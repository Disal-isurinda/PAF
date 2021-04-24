package com;

import model.BuyerAdmin; 

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 

//For JSON
import com.google.gson.*; 

//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/BuyerAdmin")

public class BuyerAdminService 
{
	BuyerAdmin buyeradminObj = new BuyerAdmin(); 
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertBuyerAdmin(@FormParam("BuyerName") String BuyerName, 
			 @FormParam("NIC") String NIC, 
			 @FormParam("Email") String Email, 
			 @FormParam("PhoneNumber") String PhoneNumber) 
			{ 
				String output = buyeradminObj.insertBuyerAdmin(BuyerName, NIC, Email, PhoneNumber);
				return output;

			}
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readBuyerAdmin() 
	 { 
	 return buyeradminObj.readBuyerAdmin(); 
	 }

	
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateBuyerAdmin(String adminbuyerData) 
	{ 
		//Convert the input string to a JSON object 
		JsonObject adminbuyerObject = new JsonParser().parse(adminbuyerData).getAsJsonObject(); 
		//Read the values from the JSON object
			String BuyerID = adminbuyerObject.get("BuyerID").getAsString(); 
			String BuyerName = adminbuyerObject.get("BuyerName").getAsString(); 
			String NIC = adminbuyerObject.get("NIC").getAsString(); 
			String Email = adminbuyerObject.get("Email").getAsString(); 
			String PhoneNumber = adminbuyerObject.get("PhoneNumber").getAsString(); 
			String output = buyeradminObj.updateBuyerAdmin(BuyerID, BuyerName, NIC, Email, PhoneNumber); 
	return output; 
	}
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteBuyerAdmin(String buyeradminData) 
	{ 
	//Convert the input string to an XML document 
	 Document doc = Jsoup.parse(buyeradminData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <buyerID>
	 String BuyerID = doc.select("BuyerID").text(); 
	 String output = buyeradminObj.deleteBuyerAdmin(BuyerID); 
	return output; 
	}


}
