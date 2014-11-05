package com.domain.myapp.config.eventstore;

import com.domain.myapp.monitoring.ApplicationHasStartedEvent;
import com.esotericsoftware.kryo.Kryo;
import no.ks.eventstore2.eventstore.KryoClassRegistration;


public class EventStoreClassRegistration implements KryoClassRegistration {
    @Override
    public void registerClasses(Kryo kryo) {
        kryo.register(ApplicationHasStartedEvent.class, 200);

    }
}
