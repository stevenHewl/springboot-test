package springbootTest.mutiDataSource.mongodb;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author neo
 */
@Configuration
@EnableMongoRepositories(basePackages = "springbootTest.repository.secondary",
		mongoTemplateRef = SecondaryMongoConfig.MONGO_TEMPLATE)
public class SecondaryMongoConfig {

	public static final String MONGO_TEMPLATE = "secondaryMongoTemplate";
}
