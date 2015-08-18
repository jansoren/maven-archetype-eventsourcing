#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config.eventstore;

import com.typesafe.config.ConfigFactory;
import no.ks.eventstore2.DeadLetterLogger;

import no.ks.eventstore2.projection.ProjectionErrorListener;
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
        actorSystem = ActorSystem.create("example-actorsystem", ConfigFactory.parseResources("classpath:/application.conf"));
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
