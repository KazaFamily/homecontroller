package com.kazafamily.homecontroller;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ApplicationPath("${spring.jersey.application-path:/home}")
public class HomeControlConfiguration extends ResourceConfig {

	@Value("${spring.jersey.application-path:/home}")
	private String apiPath;

	public HomeControlConfiguration() {
        registerEndpoints();
	}
	
	@PostConstruct
    public void init() {
		log.info("Initializing swagger");
        configureSwagger();
        log.info("Swagger initialized");
    }

	private void registerEndpoints() {

		packages("com.kazafamily.homecontroller.resources");
		register(WadlResource.class);
	}
	
	private void configureSwagger() {
	     this.register(ApiListingResource.class);
	     this.register(SwaggerSerializers.class);

	     BeanConfig config = new BeanConfig();
	     config.setConfigId("com.kazafamily.homecontroller");
	     config.setTitle("Home Integration APIs");
	     config.setVersion("v1");
	     config.setContact("sridharkaza@gmail.com");
	     config.setSchemes(new String[] { "http", "https" });
	     config.setBasePath(apiPath);
	     config.setResourcePackage("com.kazafamily.homecontroller.resources");
	     config.setPrettyPrint(true);
	     config.setScan(true);
	   }
}
