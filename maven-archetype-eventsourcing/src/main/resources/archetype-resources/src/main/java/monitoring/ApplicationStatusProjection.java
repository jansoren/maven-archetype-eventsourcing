#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.monitoring;

import static akka.pattern.Patterns.ask;
import static no.ks.eventstore2.projection.CallProjection.call;

import org.joda.time.DateTime;

import no.ks.eventstore2.Handler;
import no.ks.eventstore2.projection.Projection;
import no.ks.eventstore2.projection.Subscriber;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.Props;

import ${package}.config.eventstore.Aggregate;
import ${package}.util.Timeout;

@Subscriber(Aggregate.SYSTEM)
public class ApplicationStatusProjection extends Projection {

	private ApplicationStatus applicationStatus = new ApplicationStatus();

	public ApplicationStatusProjection(ActorRef eventStore) {
		super(eventStore);
	}

	public static Props mkProps(ActorRef eventstore) {
		return Props.create(ApplicationStatusProjection.class, eventstore);
	}

	@Handler
	public void handleEvent(ApplicationHasStartedEvent event) {
		updateFirstStart(event);
		updateLastStart(event);
		updateStartCounter();
	}

	public ApplicationStatus getApplicationStatus() {
		return applicationStatus;
	}

	public static ApplicationStatus askApplicationStatus(ActorRef applicationStatusProjection) {
		Future<Object> askApplicationStatus = ask(applicationStatusProjection, call("getApplicationStatus"), Timeout.THREE_SECONDS);
		try {
			return (ApplicationStatus)Await.result(askApplicationStatus, Duration.create(Timeout.THREE_SECONDS_STR));
		} catch (Exception e) {
			throw new RuntimeException("Could not retrieve application status", e);
		}
	}

	private void updateStartCounter() {
		int startCounter = applicationStatus.getStartCounter() + 1;
		applicationStatus.setStartCounter(startCounter);
	}

	private void updateLastStart(ApplicationHasStartedEvent event) {
		applicationStatus.setLastStart(event.getCreated());
	}

	private void updateFirstStart(ApplicationHasStartedEvent event) {
		DateTime firstStart = applicationStatus.getFirstStart();
		if(firstStart == null) {
			applicationStatus.setFirstStart(event.getCreated());
		}
	}
}
