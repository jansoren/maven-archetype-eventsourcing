#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config.eventstore;

import ${package}.monitoring.ApplicationHasStartedEvent;
import com.esotericsoftware.kryo.Kryo;
import no.ks.eventstore2.eventstore.KryoClassRegistration;


public class EventStoreClassRegistration implements KryoClassRegistration {
    @Override
    public void registerClasses(Kryo kryo) {
        kryo.register(ApplicationHasStartedEvent.class, 200);

    }
}
