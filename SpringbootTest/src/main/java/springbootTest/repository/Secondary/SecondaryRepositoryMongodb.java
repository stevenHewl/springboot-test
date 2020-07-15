package springbootTest.repository.Secondary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import springbootTest.entity.User;
import springbootTest.mutiDataSource.mongodb.SecondaryMongoConfig;

@Component
public class SecondaryRepositoryMongodb {
	@Autowired
	@Qualifier(SecondaryMongoConfig.MONGO_TEMPLATE)
	private MongoTemplate mongoTemplate;

	/**
	 * 创建对象
	 * 
	 * @param user
	 */
	public void save(User user) {
		mongoTemplate.save(user);
	}

	public List<User> findAll() {
		return mongoTemplate.findAll(User.class);
	}
}

