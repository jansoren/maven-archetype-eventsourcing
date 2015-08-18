package com.domain.myapp.config.eventstore;

import akka.actor.ActorSystem;
import akka.actor.Props;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import scala.concurrent.duration.Duration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ActorShutdownListener implements ServletContextListener {
    private static final Logger LOG = LoggerFactory.getLogger(ActorShutdownListener.class);

    private ActorSystem actorSystem;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContextEvent.getServletContext());
        actorSystem = context.getBean(ActorSystem.class);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOG.info("Shutting down actor...");
        actorSystem.actorOf(Props.create(ShutdownActor.class), "shutdownactor");
        actorSystem.awaitTermination(Duration.apply("5 seconds"));
    }
}

