package com.domain.myapp.config.eventstore;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class DataSourceInitializer {

	private SimpleDriverDataSource dataSource;
	
	public DataSourceInitializer() {
		initializeDataSource();
	}
	
	private void initializeDataSource() {
		dataSource = createDataSource();
		DatabasePopulatorUtils.execute(createDatabasePopulator(), dataSource);		
	}

	@Bean(name = "dataSource")
	public DataSource getDataSource(){
		return dataSource;
	}
	
	private DatabasePopulator createDatabasePopulator() {
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.addScript(new ClassPathResource("schema.sql"));
		return databasePopulator;
	}
	
	private SimpleDriverDataSource createDataSource() {
		SimpleDriverDataSource simpleDriverDataSource = new SimpleDriverDataSource();
		simpleDriverDataSource.setDriverClass(org.h2.Driver.class);
		simpleDriverDataSource.setUrl("jdbc:h2:target/database/example;AUTO_RECONNECT=TRUE");
		simpleDriverDataSource.setUsername("");
		simpleDriverDataSource.setPassword("");
		return simpleDriverDataSource;		
	}
}
