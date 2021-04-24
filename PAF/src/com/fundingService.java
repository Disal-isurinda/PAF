package com;
import model.funding;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Items")
public class fundingService
{
	funding itemObj = new funding();
@GET
@Path("/")

@Produces(MediaType.TEXT_HTML)


public String readfunding()
 {
	return itemObj.readfunding();
 }

@POST
@Path("/")

@Produces(MediaType.TEXT_PLAIN)

@Consumes(MediaType.APPLICATION_FORM_URLENCODED)

public String insertfunding(@FormParam("researchName") String researchName,
		 @FormParam("value") String value,
		 @FormParam("researchId") String researchId
		)
		{
	String output = itemObj.insertfunding(researchName, value, researchId);
	return output;
		}

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updatefunding(String itemData)
{
//Convert the input string to a JSON object
 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
//Read the values from the JSON object
 String fId = itemObject.get("fId").getAsString();
 String researchName = itemObject.get("researchName").getAsString();
 String value = itemObject.get("value").getAsString();
 String researchId = itemObject.get("researchId").getAsString();
 
 String output = itemObj.updatefunding(fId, researchName, value, researchId);
return output;
}

@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deletefunding(String itemData)
{
//Convert the input string to an XML document
 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

//Read the value from the element <itemID>
 String fId = doc.select("fId").text();
 String output = itemObj.deletefunding(fId);
return output;
}


}



