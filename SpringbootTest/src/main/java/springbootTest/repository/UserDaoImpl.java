package springbootTest.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import springbootTest.entity.User;

/*
 * Mogodb test
 * */
@Component
public class UserDaoImpl {

	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * 创建对象
	 * 
	 * @param user
	 */
	public void saveUser(User user) {
		mongoTemplate.save(user);
	}

	/**
	 * 根据用户名查询对象
	 * 
	 * @param userName
	 * @return
	 */
	public User findUserByUserName(String userName) {
		Query query = new Query(Criteria.where("username").is(userName));
		User user = mongoTemplate.findOne(query, User.class);
		return user;
	}

	/**
	 * 更新对象
	 * 
	 * @param user
	 */
	public void updateUser(User user) {
		Query query = new Query(Criteria.where("id").is(user.getid()));
		Update update = new Update().set("username", user.getuserName()).set("pass_word", user.getpassWord());

		// 更新查询返回结果集的第一条
		mongoTemplate.updateFirst(query, update, User.class);
		// 更新查询返回结果集的所有
		// mongoTemplate.updateMulti(query,update,UserEntity.class);
	}

	/**
	 * 删除对象
	 * 
	 * @param id
	 */
	public void deleteUserById(long id) {
		Query query = new Query(Criteria.where("id").is(id));
		mongoTemplate.remove(query, User.class);
	}
}