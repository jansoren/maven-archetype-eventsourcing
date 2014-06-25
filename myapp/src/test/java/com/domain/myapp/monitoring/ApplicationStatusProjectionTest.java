package com.domain.myapp.monitoring;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import akka.testkit.TestActorRef;

import com.domain.myapp.SystemTestKit;

public class ApplicationStatusProjectionTest extends SystemTestKit {

    private TestActorRef<ApplicationStatusProjection> projection;

    @Before
    public void setup() {
        projection = createApplicationStatusProjection();
    }

    @Test
    public void testThatProjectionHandlesEmptyData() {
        ApplicationStatus status = ApplicationStatusProjection.askApplicationStatus(projection);
        assertEquals(0, status.getStartCounter());
    }

    @Test
    public void testThatProjectionReceivesOneApplicationHasStartedEvent() {
        projection.tell(new ApplicationHasStartedEvent(), testActor());
        ApplicationStatus status = ApplicationStatusProjection.askApplicationStatus(projection);
        assertEquals(1, status.getStartCounter());
    }

    @Test
    public void testThatProjectionReceivesMultipleApplicationHasStartedEvents() {
        projection.tell(new ApplicationHasStartedEvent(), testActor());
        projection.tell(new ApplicationHasStartedEvent(), testActor());
        projection.tell(new ApplicationHasStartedEvent(), testActor());
        ApplicationStatus status = ApplicationStatusProjection.askApplicationStatus(projection);
        assertEquals(3, status.getStartCounter());
    }
}
