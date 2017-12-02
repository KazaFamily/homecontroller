package com.kazafamily.homecontroller;


import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@ApplicationPath("${spring.jersey.application-path:/home}")
public class HomeControlConfiguration extends ResourceConfig {

	@Value("${spring.jersey.application-path:/home}")
	private String apiPath;

	public HomeControlConfiguration() {
        registerEndpoints();
	}

	private void registerEndpoints() {

		packages("com.kazafamily.homecontroller.resources");
		register(WadlResource.class);
	}

}
