package springbootTest.mutiDataSource.mongodb;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "spring.data.mongodb")
@Configuration
public class MultipleMongoProperties {
	private MongoProperties primary = new MongoProperties();
	private MongoProperties secondary = new MongoProperties();

	public MongoProperties getPrimary() {
		// TODO Auto-generated method stub
		return primary;
	}

	public MongoProperties getSecondary() {
		// TODO Auto-generated method stub
		return secondary;
	}
}
