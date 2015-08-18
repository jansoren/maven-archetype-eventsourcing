#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config.eventstore;

import akka.actor.UntypedActor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShutdownActor extends UntypedActor {
    private static final Logger LOG = LoggerFactory.getLogger(ShutdownActor.class);

    @Override
    public void onReceive(Object o) throws Exception {
        LOG.info("Shutting down akka system...");
        getContext().system().shutdown();
    }
}

