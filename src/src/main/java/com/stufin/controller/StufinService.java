package com.stufin.controller;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.stufin.vo.Course;


@Component
@Path("/stufin")
public class StufinService {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	//@ResponseBody
	public Response getRecommendation(String str) {
		ObjectMapper om  = new ObjectMapper();
		om.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		List<Course> lstOfCSs = getCoures(str);
		String res = null;
		try {
			res = om.writeValueAsString(lstOfCSs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(res).build();
		//return wrapper;
	}
	
	private List<Course> getCoures(String str) {
		List<Course> lstOfCrs = new ArrayList<Course>();
		MongoClient mongo = new MongoClient();
		MongoDatabase db =  mongo.getDatabase("stufin");
		MongoCollection<Document> collection = db.getCollection("courses");
		String[] arr = str.split(",");
		FindIterable<Document> iterable = collection.find(eq("name", arr[0].trim()));
		for (Document doc : iterable) {
			Course crs = new Course();
			crs.setCostPrCrdt(doc.getString("costPrCrdt"));
			System.out.println(crs.getCostPrCrdt());
			crs.setName(doc.getString("name"));
			crs.setNumber(doc.getString("number"));
			crs.setNumOfCrdt(doc.getString("numCrdt"));
			crs.setVenue(doc.getString("venue"));
			lstOfCrs.add(crs);
			
		}
		mongo.close();
		return lstOfCrs;
	}

}
