package com.domain.myapp.monitoring;

import akka.actor.ActorRef;
import akka.actor.Props;
import com.domain.myapp.config.eventstore.Aggregate;
import no.ks.eventstore2.Handler;
import no.ks.eventstore2.ask.Asker;
import no.ks.eventstore2.projection.Projection;
import no.ks.eventstore2.projection.Subscriber;
import org.joda.time.DateTime;

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
        try {
            return Asker.askProjection(applicationStatusProjection, "getApplicationStatus").single(ApplicationStatus.class);
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
        if (firstStart == null) {
            applicationStatus.setFirstStart(event.getCreated());
        }
    }
}
