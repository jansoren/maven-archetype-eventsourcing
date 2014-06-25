#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config.eventstore;

import javax.sql.DataSource;

import no.ks.eventstore2.eventstore.EventStore;
import no.ks.eventstore2.eventstore.H2JournalStorage;
import no.ks.eventstore2.eventstore.JournalStorage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ${package}.monitoring.ApplicationHasStartedEvent;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

@Configuration
public class EventStoreInitializer {

	private static Logger log = LoggerFactory.getLogger(EventStoreInitializer.class);

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private ActorSystem actorSystem;

	@Bean(name="eventStore")
	public ActorRef initializeEventStore() {
		JournalStorage journalStorage = new H2JournalStorage(dataSource);
		ActorRef eventStore = actorSystem.actorOf(EventStore.mkProps(journalStorage), "eventStore");
		log.info("Started EventStore {}", eventStore);
		eventStore.tell(new ApplicationHasStartedEvent(), null);
		return eventStore;
	}
}
