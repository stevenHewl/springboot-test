package springbootTest.repository.primary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import springbootTest.entity.User;

//public interface PrimaryRepositoryMongodb extends MongoRepository<User, String> {}

@Component
public class PrimaryRepositoryMongodb {

	@Autowired
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