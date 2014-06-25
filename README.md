maven-archetype-eventsourcing
=============================

This archetype creates a java-web-application using technologies like:
 - Angularjs
 - Spring 4
 - Eventstore2

*** Get started

1. Check out code with `git clone https://github.com/bouvet-openlab/maven-archetype-eventsourcing.git`
2. Enter folder `/maven-archetype-eventsourcing` and run `mvn clean install`
3. Archetype is now created and to create projects with it run `mvn archetype:generate -DarchetypeGroupId=no.bouvet -DarchetypeArtifactId=maven-archetype-eventsourcing -DarchetypeVersion=1.0-SNAPSHOT  -DgroupId=com.domain.myapp -DartifactId=myapp`

You can also test the result of the archetype:
1. Enter folder `/myapp`
2. Run `mvn clean install`
3. Run `mvn jetty:run` to start application
4. Open url `http://localhost:8080`