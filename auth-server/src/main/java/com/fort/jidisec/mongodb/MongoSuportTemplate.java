package com.fort.jidisec.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoSuportTemplate {
	
	@Autowired
	protected MongoTemplate mongoTemplate;
}
