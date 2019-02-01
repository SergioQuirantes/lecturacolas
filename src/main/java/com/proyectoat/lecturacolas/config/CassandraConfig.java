package com.proyectoat.lecturacolas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {

	@Override
	protected String getKeyspaceName() {
		return "cassandra_keyspace";
	}
	
	@Override
	public SchemaAction getSchemaAction() {
		return SchemaAction.RECREATE_DROP_UNUSED;
	}
	
	@Bean
	public CassandraClusterFactoryBean cluster() {
		CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
		cluster.setContactPoints("127.0.0.1");
		cluster.setPort(9042);
		cluster.setJmxReportingEnabled(false);
		return cluster;
	}
	
	@Bean
	public CassandraMappingContext cassandraMapping() throws ClassNotFoundException{
		return new CassandraMappingContext();
	}
	
	
}
