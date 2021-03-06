#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.monitoring;

import akka.actor.ActorRef;
import ${package}.util.Timeout;
import no.ks.eventstore2.eventstore.AcknowledgePreviousEventsProcessed;
import no.ks.eventstore2.response.NoResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static akka.pattern.Patterns.ask;

@Controller
@RequestMapping("/monitoring")
public class MonitoringController {
    private static final Logger LOG = LoggerFactory.getLogger(MonitoringController.class);

    private static final int SERVICE_UNAVAILABLE = 503;

    @Resource(name = "eventStore")
    private ActorRef eventStore;

    @Resource(name = "applicationStatusProjection")
    private ActorRef applicationStatusProjection;

    @RequestMapping(value = "/selfTest", method = RequestMethod.GET)
    @ResponseBody
    public String doSelfTest(HttpServletResponse response) {
        LOG.info("Do self test");

        List<String> errors = new ArrayList<String>();
        askEventStore(errors);
        if (errors.size() == 0) {
            return "OK";
        } else {
            LOG.info("Self test failed with errors: {}", errors);
            try {
                response.sendError(SERVICE_UNAVAILABLE, "Self test failed with errors: {} " + errors);
            } catch (IOException e) {
                LOG.warn("IOException", e);
            }
            return null;
        }
    }

    @RequestMapping(value = "/applicationStatus", method = RequestMethod.GET)
    @ResponseBody
    public ApplicationStatus getApplicationStatus() {
        LOG.info("Get application status");
        return ApplicationStatusProjection.askApplicationStatus(applicationStatusProjection);
    }

    private void askEventStore(List<String> errors) {
        Future<Object> askEventStore = ask(eventStore, new AcknowledgePreviousEventsProcessed(), Timeout.THREE_SECONDS);
        try {
            Object result = Await.result(askEventStore, Duration.create(Timeout.THREE_SECONDS_STR));
            if (result instanceof NoResult) {
                errors.add("EventStore did not respond in 3 seconds");
            }
        } catch (Exception e) {
            errors.add("Querying EventStore failed");
        }
    }
}
