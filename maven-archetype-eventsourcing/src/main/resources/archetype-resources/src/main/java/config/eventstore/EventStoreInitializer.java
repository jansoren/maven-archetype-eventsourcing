#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config.eventstore;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import ${package}.monitoring.ApplicationHasStartedEvent;
import no.ks.eventstore2.eventstore.EventStore;
import no.ks.eventstore2.eventstore.JournalStorage;
import no.ks.eventstore2.eventstore.MysqlJournalStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class EventStoreInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(EventStoreInitializer.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ActorSystem actorSystem;

    @Bean(name = "eventStore")
    public ActorRef initializeEventStore() {
        JournalStorage journalStorage = new MysqlJournalStorage(dataSource, new EventStoreClassRegistration());
        ActorRef eventStore = actorSystem.actorOf(EventStore.mkProps(journalStorage), "eventStore");
        LOG.info("Started EventStore {}", eventStore);
        eventStore.tell(new ApplicationHasStartedEvent(), null);
        return eventStore;
    }
}
