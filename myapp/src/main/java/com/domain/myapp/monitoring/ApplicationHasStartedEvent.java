package com.domain.myapp.monitoring;

import com.domain.myapp.config.eventstore.Aggregate;

import no.ks.eventstore2.Event;

public class ApplicationHasStartedEvent extends Event {

    @Override
    public String getAggregateType() {
        return Aggregate.SYSTEM;
    }

    @Override
    public String getLogMessage() {
        return "Application has started";
    }

    @Override
    public String getAggregateRootId() {
        return "ApplicationHasStartedEvent";
    }

}
