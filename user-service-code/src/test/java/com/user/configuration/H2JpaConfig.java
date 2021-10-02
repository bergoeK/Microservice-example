package com.user.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager", basePackages = {
		"com.user.repository" })
@ComponentScan(basePackageClasses = com.user.mapper.EmployeeMapper.class, basePackages = { 
		"com.user.repository", "com.user.service" })
public class H2JpaConfig {

	@Autowired
	Environment environment;

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean EntityMgrFactory(final EntityManagerFactoryBuilder builder,
			final DataSource dataSource) {
		final Map<String, String> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", environment.getProperty("spring.jpa.properties.hibernate.ddl-auto"));
		// in springboot2 the dialect can be automatically detected.
		// we are setting up here just to avoid any incident.
		properties.put("hibernate.dialect", environment.getProperty("spring.jpa.database-platform"));
		return builder.dataSource(dataSource).properties(properties).packages("com.user.model")
				.persistenceUnit("persistence_unit").build();
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager db1TransactionMgr(
			@Qualifier("entityManagerFactory") final EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}
