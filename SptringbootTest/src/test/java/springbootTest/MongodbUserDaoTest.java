package springbootTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import springbootTest.entity.User;
import springbootTest.repository.UserDaoImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbUserDaoTest {

	@Autowired
	private UserDaoImpl userDao;

	@Test
	public void testSaveUser() throws Exception {
		User user = new User();
		user.setid(25);
		user.setuserName("kenneth4");
		user.setpassWord("kenenth123");
		userDao.saveUser(user);
	}

	@Test
	public void findUserByUserName() {
		User user = userDao.findUserByUserName("kenneth4");
		System.out.println("user is " + user);
	}
	
	@Test
	public void updateUser() {
		User user = new User();
		user.setid(24);
		user.setuserName("天空");
		user.setpassWord("fffxxxx");
		userDao.updateUser(user);
	}
	
	/*
	@Test
	public void deleteUserById() {
		userDao.deleteUserById(21);
	}
	*/
}