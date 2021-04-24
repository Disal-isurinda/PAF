package com;

import model.Seller;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Research")
public class SellerService {
	Seller seller = new Seller();

	@GET
	@Path("/")

	@Produces(MediaType.TEXT_HTML)

	public String retriveResearch() {
		return seller.retriveResearch();
	}

	@POST
	@Path("/")

	@Produces(MediaType.TEXT_PLAIN)

	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)

	public String insertResearch(@FormParam("researchName") String researchName,
			@FormParam("researcherName") String researcherName,
			@FormParam("researchDescription") String researchDescription,
			@FormParam("researchPrice") String researchPrice) {
		String output = seller.insertResearch(researchName, researcherName, researchDescription, researchPrice);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateResearch(String itemData) {

		// Convert the input string to a JSON object
		JsonObject jsonObject = new JsonParser().parse(itemData).getAsJsonObject();

		// Read the values from the JSON object
		int researchId = jsonObject.get("researchId").getAsInt();
		String researchName = jsonObject.get("researchName").getAsString();
		String researcherName = jsonObject.get("researcherName").getAsString();
		String researchDescription = jsonObject.get("researchDescription").getAsString();
		String researchPrice = jsonObject.get("researchPrice").getAsString();

		String output = seller.updateResearch(researchId, researchName, researcherName, researchDescription,
				researchPrice);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteResearch(String researchId) {

		// Convert the input string to an XML document
		Document document = Jsoup.parse(researchId, "", Parser.xmlParser());

		// Read the value from the element <researchId>
		String researchId1 = document.select("researchId").text();
		String output = seller.deleteResearch(researchId1);

		return output;
	}

}
