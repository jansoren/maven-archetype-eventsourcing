package com.domain.myapp.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class WebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", createDispatcherServlet());
		servlet.addMapping("/services/*");
		servlet.setLoadOnStartup(1);
	}

	private DispatcherServlet createDispatcherServlet() {
		AnnotationConfigWebApplicationContext dispatcherContext = getDispatcherContext();
		return new DispatcherServlet(dispatcherContext);
	}

	private AnnotationConfigWebApplicationContext getDispatcherContext() {
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(DispatcherConfig.class);
		return dispatcherContext;
	}
}
