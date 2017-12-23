package com.kazafamily.homecontroller.resources;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.kazafamily.homecontroller.objects.RokuApp;
import com.kazafamily.homecontroller.objects.RokuApps;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Path("/roku")
@Api( value="/roku")
public class RokuResource {
	@Context
	SecurityContext sc;

	public final String MAIN_ROKU_API = "http://192.168.0.3:8060/";
	public final String BEDROOM_ROKU_API = "http://192.168.0.18:8060/";
	public final String ROKU_API = MAIN_ROKU_API;
	
	@POST
	@ApiOperation(value = "Launches Roku")	
	public Response home() throws Exception {
		Client client = ClientBuilder.newClient();
		String link = ROKU_API + "keypress/home";
		Response response = client.target(link).request().post(null);
		return response;
	}
	
	@POST
	@Path("/play")
	@ApiOperation(value = "Launches Roku")	
	public Response play() throws Exception {
		Client client = ClientBuilder.newClient();
		String link = ROKU_API + "keypress/play";
		Response response = client.target(link).request().post(null);
		return response;
	}
	
	@POST
	@Path("/launch/{text}")
	@Produces(MediaType.APPLICATION_XML_VALUE)
	@ApiOperation(value = "Launches Roku Channel by ID")	
	public Response launch(@ApiParam("text") @PathParam("text") String text) throws Exception {
			
		log.info("launch channel: {}", text);
		Client client = ClientBuilder.newClient();
		
		String query =  ROKU_API + "query/apps";
		Response result = client.target(query).request(MediaType.APPLICATION_XML_VALUE).get();	
		RokuApps apps = result.readEntity(RokuApps.class);
		for (RokuApp app : apps.getApps()) {
			if (text.trim().equalsIgnoreCase(app.getName())) {
				String launch = ROKU_API + "launch/"+app.getId();
				Response response = client.target(launch).request().post(null);
				log.info("Matched {} to {}",text,app);
				return response;
			}
		}

		return Response.ok(apps).build();
	}
	
	@POST
	@Path("/search/{text}")
	@ApiOperation(value = "Searches Roku for Content")	
	public Response search(@ApiParam("search text") @PathParam("text") String text) throws Exception {
		log.info("search for me: {}", text);
		Client client = ClientBuilder.newClient();
		String link = ROKU_API + "search/browse";
		
		Response response = client.target(link)
		.queryParam("keyword", text)
		.request().post(null);
		return response;
		
	}
	
	@POST
	@Path("/play/{text}/on/{provider}")
	@ApiOperation(value = "Searches Roku for Content")	
	public Response playOn(
			@ApiParam("search text") @PathParam("text") String text,
			@ApiParam("provider") @PathParam("provider") String provider
			) throws Exception {
		
		log.info("Trying to play: {} on {}", text, provider);
		
		Client client = ClientBuilder.newClient();
		
		String link = ROKU_API + "search/browse";
		
		Response response = client.target(link)
		.queryParam("keyword", text)
		.queryParam("launch", "true")
		.queryParam("match-any", "true")
		.queryParam("provider", provider)
		.request().post(null);
		return response;
	}
	
}
