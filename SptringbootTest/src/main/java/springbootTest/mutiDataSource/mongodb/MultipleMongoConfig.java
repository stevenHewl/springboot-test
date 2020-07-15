package springbootTest.mutiDataSource.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClientURI;

/**
 * @author neo
 */
@Configuration
public class MultipleMongoConfig {

	@Autowired
	private MultipleMongoProperties mongoProperties;

	@Primary
	@Bean
	public MongoTemplate primaryMongoTemplate() throws Exception {
		return new MongoTemplate(primaryFactory(this.mongoProperties.getPrimary()));
	}

	@Bean(name = SecondaryMongoConfig.MONGO_TEMPLATE)
	public MongoTemplate secondaryMongoTemplate() throws Exception {
        return new MongoTemplate(secondaryFactory(this.mongoProperties.getSecondary()));
	}

	@Bean
	public MongoDbFactory primaryFactory(MongoProperties mongo) throws Exception {
		return new SimpleMongoDbFactory(new MongoClientURI(mongo.getUri()));
	}

	@Bean
	public MongoDbFactory secondaryFactory(MongoProperties mongo) throws Exception {
		return new SimpleMongoDbFactory(new MongoClientURI(mongo.getUri()));
		/*
		 * return new SimpleMongoDbFactory(new MongoClient(mongo.getHost(),
		 * mongo.getPort()), mongo.getDatabase());
		 */
	}
}
