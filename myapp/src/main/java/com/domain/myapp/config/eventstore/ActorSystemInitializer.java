package com.domain.myapp.config.eventstore;

import no.ks.eventstore2.DeadLetterLogger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

@Configuration
public class ActorSystemInitializer {

    private ActorSystem actorSystem;

    public ActorSystemInitializer() {
        initializeActorSystem();
    }

    private void initializeActorSystem() {
        actorSystem = ActorSystem.create("example");
        actorSystem.actorOf(Props.create(DeadLetterLogger.class));
    }

    @Bean
    public ActorSystem getActorSystem() {
        return actorSystem;
    }

    @Bean(name = "projectionErrorListener")
    public ActorRef getProjectionErrorListener() {
        return actorSystem.actorOf(Props.create(ProjectionErrorListener.class), "projectionErrorListener");
    }
}
