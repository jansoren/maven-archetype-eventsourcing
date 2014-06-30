#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config.eventstore;

import javax.annotation.Resource;
import javax.sql.DataSource;

import no.ks.eventstore2.saga.SagaDatasourceRepository;
import no.ks.eventstore2.saga.SagaManager;
import no.ks.eventstore2.saga.SagaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;

@Configuration
public class SagaManagerInitializer {

    private static final String PACKAGE_SCAN_PATH = "${package}";

    @Resource(name = "commandDispatcher")
    private ActorRef commandDispatcher;

    @Resource(name = "eventStore")
    private ActorRef eventStore;

    @Autowired
    private ActorSystem actorSystem;

    @Bean(name = "sagaManager")
    public ActorRef getSagaManager(SagaRepository repository) {
        return actorSystem.actorOf(SagaManager.mkProps(commandDispatcher, repository, eventStore, PACKAGE_SCAN_PATH), "sagaManager");
    }

    @Bean
    public SagaRepository getSagaRepository(DataSource datasource) {
        return new SagaDatasourceRepository(datasource);
    }
}
