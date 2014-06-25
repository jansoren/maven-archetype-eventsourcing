#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config.eventstore;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import no.ks.eventstore2.command.CommandDispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

@Configuration
public class CommandDispatcherInitializer {

	@Resource(name="eventStore")
	private ActorRef eventStore;
	@Autowired
	private ActorSystem actorSystem;

	@Bean(name="commandDispatcher")
	public ActorRef getCommandDispatcherRef(){
		List<Props> props = new ArrayList<Props>();

		//props.add(SendAvviksmeldingCommandHandler.mkProps(eventStore, tittelregisterProjection, javaMailSender, konfigurasjonHandler));
		
        return actorSystem.actorOf(CommandDispatcher.mkProps(props), "commandDispatcher");
	}
}
