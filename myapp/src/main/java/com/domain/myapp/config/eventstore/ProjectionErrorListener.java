package com.domain.myapp.config.eventstore;

import static akka.pattern.Patterns.ask;

import java.util.ArrayList;
import java.util.List;

import no.ks.eventstore2.projection.ProjectionFailedError;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;

import com.domain.myapp.util.Timeout;

public class ProjectionErrorListener extends UntypedActor{

    private static final int ERRORS_SIZE = 100;
	private List<ProjectionError> errors = new ArrayList<>();

    public ProjectionErrorListener() {
    }

    @Override
    public void onReceive(Object o) {
        if(o instanceof ProjectionFailedError){
            if(errors.size() < ERRORS_SIZE){
            	errors.add(new ProjectionError((ProjectionFailedError) o));
            }
        } else if("getErrors".equals(o)){
            sender().tell(errors, self());
        }
    }

    @SuppressWarnings("unchecked")
	public static List<ProjectionError> askErrors(ActorRef projectionErrorListener) {
    	Future<Object> errors = ask(projectionErrorListener, "getErrors", Timeout.THREE_SECONDS);
        try {
            return (List<ProjectionError>) Await.result(errors, Duration.create(Timeout.THREE_SECONDS_STR));
        } catch (Exception e) {
            throw new RuntimeException("Kunne ikke hente feil fra projectionErrorListener", e);
        }
    }

}
