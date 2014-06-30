maven-archetype-eventsourcing
=============================

This archetype creates a java-web-application using technologies like:
 - Angularjs
 - Spring 4
 - Eventstore2

### Usage 

1. Create project using archetype
```
mvn archetype:generate -DarchetypeGroupId=no.bouvet -DarchetypeArtifactId=maven-archetype-eventsourcing -DarchetypeVersion=1.0.0  -DgroupId=com.domain.myapp -DartifactId=myapp
``` 
2. Enter folder e.g `/myapp`
3. Run `mvn clean install`
4. Run `mvn jetty:run` to start application
5. Enter url `http://localhost:8080`
 
### Get started developing the archetype

1. Check out code with `git clone https://github.com/bouvet-openlab/maven-archetype-eventsourcing.git`
2. Enter folder `/maven-archetype-eventsourcing` and run `mvn clean install`
3. Archetype is now created and to create projects with it run:
```
mvn archetype:generate -DarchetypeGroupId=no.bouvet -DarchetypeArtifactId=maven-archetype-eventsourcing -DarchetypeVersion=1.0.1-SNAPSHOT  -DgroupId=com.domain.myapp -DartifactId=myapp
```

### Create a Maven Archetype from an existing project

1. Enter folder `/myapp`
1. Run `mvn archetype:create-from-project`
2. You will find your generated archetype code in `target/generated-sources/archetype` folder