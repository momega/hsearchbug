package com.momega.hsearchbug;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.momega.hsearchbug.service")
public class SpringConfig {

	@Bean
	public DataSource runtimeDataSource() {
		return new EmbeddedDatabaseBuilder()
				.setName("runtime_db")
				.setType(EmbeddedDatabaseType.H2).build();
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(runtimeDataSource());
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan("com.momega.hsearchbug.model");
		entityManagerFactoryBean.setJpaProperties(jpaProperties());
		entityManagerFactoryBean.setPersistenceUnitName("hsearchbug");
		entityManagerFactoryBean.afterPropertiesSet();
		return entityManagerFactoryBean.getObject();
	}

	private Properties jpaProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.format_sql", "true");
		
		properties.setProperty("hibernate.search.default.indexmanager", "elasticsearch");
		properties.setProperty("hibernate.search.default.elasticsearch.host", "http://localhost:9200");
		properties.setProperty("hibernate.search.default.elasticsearch.index_schema_management_strategy", "MERGE");
		properties.setProperty("elasticsearch.refresh_after_write", "true");
		return properties;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory());
		return transactionManager;
	}
	
}
