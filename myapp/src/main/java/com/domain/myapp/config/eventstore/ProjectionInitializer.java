package com.domain.myapp.config.eventstore;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;

import akka.actor.ActorRef;

@Configuration
public class ProjectionInitializer {

	@Resource(name="projectionManager")
	private ActorRef projectionManager;

/*
	@Bean(name="materialeProjection")
	public ActorRef getMaterialeProjectionRef() {
		return askProjectionRef(MaterialeProjection.class);
	}


	private ActorRef askProjectionRef(Class<? extends Projection> projectionClass) {
		Future<Object> getProjection = ask(projectionManager, call("getProjectionRef", projectionClass), Timeout.THREE_SECONDS);
		try {
			return (ActorRef) Await.result(getProjection, Duration.create(Timeout.THREE_SECONDS_STR));
		} catch (Exception e) {
			throw new RuntimeException("Error retrieving projection reference", e);
		}
	}*/
}
