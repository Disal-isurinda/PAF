
package com;

import model.FunderHandle;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/funders")
public class FunderHandleService {
	FunderHandle itemObj = new FunderHandle();

	@GET
	@Path("/")

	@Produces(MediaType.TEXT_HTML)

	public String readfunder() {
		return itemObj.readfunder();
	}

	@POST
	@Path("/")

	@Produces(MediaType.TEXT_PLAIN)

	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)

	public String insertfunder(@FormParam("fname") String fname, @FormParam("NIC") String NIC,
			@FormParam("address") String address, @FormParam("tel") String tel, @FormParam("userName") String userName,
			@FormParam("password") String password) {
		String output = itemObj.insertfunder(fname, NIC, address, tel, userName, password);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatefunder(String itemData) {
//Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
//Read the values from the JSON object
		String id = itemObject.get("id").getAsString();
		String fname = itemObject.get("fname").getAsString();
		String NIC = itemObject.get("NIC").getAsString();
		String address = itemObject.get("address").getAsString();
		String tel = itemObject.get("tel").getAsString();
		String userName = itemObject.get("userName").getAsString();
		String password = itemObject.get("password").getAsString();

		String output = itemObj.updatefunder(id, fname, NIC, address, tel, userName, password);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletefunder(String itemData) {
//Convert the input string to an XML document
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

//Read the value from the element <itemID>
		String fId = doc.select("id").text();
		String output = itemObj.deletefunder(fId);
		return output;
	}

}
