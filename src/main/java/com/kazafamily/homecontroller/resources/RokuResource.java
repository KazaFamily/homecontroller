package com.kazafamily.homecontroller.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Path("/roku")
public class RokuResource {
	@Context
	SecurityContext sc;

	@POST
	@Path("/")
	@ApiOperation(value = "Launches Roku")	
	public Response home() throws Exception {
		Client client = ClientBuilder.newClient();
		String link = "http://192.168.0.3:8060/keypress/home";
		Response response = client.target(link).request().post(null);
		return response;
	}
	
	@POST
	@Path("/play")
	@ApiOperation(value = "Launches Roku")	
	public Response play() throws Exception {
		Client client = ClientBuilder.newClient();
		String link = "http://192.168.0.3:8060/keypress/play";
		Response response = client.target(link).request().post(null);
		return response;
	}
	
	@POST
	@Path("/launch/{text}")
	@ApiOperation(value = "Launches Roku Channel by ID")	
	public Response launch(@ApiParam("text") @PathParam("text") String text) throws Exception {
		log.info("launch channel: {}", text);
		Client client = ClientBuilder.newClient();
		String link = "http://192.168.0.3:8060/launch/"+text;
		Response response = client.target(link).request().post(null);
		return response;
	}
	
	@POST
	@Path("/search/{text}")
	@ApiOperation(value = "Searches Roku for Content")	
	public Response search(@ApiParam("search text") @PathParam("text") String text) throws Exception {
		log.info("search for me: {}", text);
		Client client = ClientBuilder.newClient();
		String link = "http://192.168.0.3:8060/search/browse";
		
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
		String link = "http://192.168.0.3:8060/search/browse";
		Response response = client.target(link)
		.queryParam("keyword", text)
		.queryParam("launch", "true")
		.queryParam("match-any", "true")
		.queryParam("provider", provider)
		.request().post(null);
		return response;
	}
	
}
