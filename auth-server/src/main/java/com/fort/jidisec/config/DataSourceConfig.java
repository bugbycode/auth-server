package com.fort.jidisec.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * 数据源配置
 * @author zhigongzhang
 *
 */
@Configuration
public class DataSourceConfig {
	
	@Value("${spring.mongodb.uri}")
	private String mongoUri;
	
	@Value("${spring.mongodb.database}")
	private String mongoDatabase;
	
	@Bean("mongoDbFactory")
	public MongoDbFactory getMongoDbFactory() {
		return new SimpleMongoDbFactory(new MongoClient(new MongoClientURI(mongoUri)), mongoDatabase);
	}
	
	@Bean("mongoTemplate")
	@Resource(name="mongoDbFactory")
	public MongoTemplate getMongoTemplate(MongoDbFactory mongoDbFactory) {
		return new MongoTemplate(mongoDbFactory);
	}
}
