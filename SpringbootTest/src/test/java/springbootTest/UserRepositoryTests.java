package springbootTest;

import java.text.DateFormat;
import java.util.Date;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import springbootTest.entity.User;
import springbootTest.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void test() throws Exception {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);        
		String formattedDate = dateFormat.format(date);
		
		String hashAlgorithmName = "MD5";
		String credentials = "123456";
		int hashIterations = 2;
		ByteSource credentialsSalt = ByteSource.Util.bytes("admin");
		Object passObj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);

		// userRepository.save(new User("admin", "admin@126.com", "管理员",
		// passObj.toString(), formattedDate));

		userRepository.save(new User("test414", "test414@126.com", "test414", passObj.toString(), formattedDate));

		//userRepository.save(new User("cc1", "cc1@126.com", "cc1", "cc123456",formattedDate));

		int size = userRepository.findAll().size();
		System.out.println("记录数是：" + size);
		Assert.assertEquals(2, size);
		// Assert.assertEquals("test",
		// userRepository.findByusername("test").getnickName());
		// userRepository.delete(userRepository.findByUserName("aa1"));
	}

}