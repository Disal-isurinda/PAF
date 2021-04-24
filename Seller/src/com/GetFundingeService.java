package com;
import model.GetFunding;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/getFunding")
public class GetFundingeService {
	GetFunding funder = new GetFunding();

	@GET
	@Path("/")

	@Produces(MediaType.TEXT_HTML)

	public String retriveResearch() {
		return funder.retriveResearchFunding();
	}

	@POST
	@Path("/")

	@Produces(MediaType.TEXT_PLAIN)

	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)

	public String insertResearchFunfing(@FormParam("ResearchName") String ResearchName,
			@FormParam("ResearcherName") String ResearcherName,
			@FormParam("ResearchDescription") String ResearchDescription) {
		String output = funder.insertResearchFunding(ResearchName, ResearcherName, ResearchDescription);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateResearchFunding(String itemData) {

		// Convert the input string to a JSON object
		JsonObject jsonObject = new JsonParser().parse(itemData).getAsJsonObject();

		// Read the values from the JSON object
		int ResearchId = jsonObject.get("ResearchId").getAsInt();
		String ResearchName = jsonObject.get("ResearchName").getAsString();
		String ResearcherName = jsonObject.get("ResearcherName").getAsString();
		String ResearchDescription = jsonObject.get("ResearchDescription").getAsString();
		

		String output = funder.updateResearchFunding(ResearchId, ResearchName, ResearcherName, ResearchDescription);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteResearchFunding(String ResearchId) {

		// Convert the input string to an XML document
		Document document = Jsoup.parse(ResearchId, "", Parser.xmlParser());

		// Read the value from the element <researchId>
		String ResearchId1 = document.select("ResearchId").text();
		String output = funder.deleteResearchFunding(ResearchId1);

		return output;
	}

}

