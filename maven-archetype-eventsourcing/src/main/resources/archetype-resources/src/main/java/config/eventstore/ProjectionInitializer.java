#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config.eventstore;

import static akka.pattern.Patterns.ask;
import static no.ks.eventstore2.projection.CallProjection.call;

import javax.annotation.Resource;

import no.ks.eventstore2.projection.Projection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;

import ${package}.monitoring.ApplicationStatusProjection;
import ${package}.util.Timeout;

@Configuration
public class ProjectionInitializer {

	@Resource(name="projectionManager")
	private ActorRef projectionManager;


	@Bean(name="applicationStatusProjection")
	public ActorRef getApplicationStatusProjectionRef() {
		return askProjectionRef(ApplicationStatusProjection.class);
	}

	private ActorRef askProjectionRef(Class<? extends Projection> projectionClass) {
		Future<Object> getProjection = ask(projectionManager, call("getProjectionRef", projectionClass), Timeout.THREE_SECONDS);
		try {
			return (ActorRef) Await.result(getProjection, Duration.create(Timeout.THREE_SECONDS_STR));
		} catch (Exception e) {
			throw new RuntimeException("Error retrieving projection reference", e);
		}
	}
}
