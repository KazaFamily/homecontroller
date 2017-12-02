package com.kazafamily.homecontroller.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jersey.repackaged.com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Path("/roku")
public class RokuResource {
	@Context
	SecurityContext sc;
	
	@POST
	@GET
	@Path("/{rokucommand}")
	public void getStripeAccount(@PathParam("rokucommand") String command) throws Exception {

		log.info("Received external command: {}", command);
		
	}

}
