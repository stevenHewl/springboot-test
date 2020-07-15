package springbootTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import springbootTest.entity.User;
import springbootTest.rabiitmq.send.helloS;
import springbootTest.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqHelloTest {

	@Autowired
	private helloS helloSender;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void hello() throws Exception {
		User user1 = userRepository.findByusername("aa2");
		User user2 = userRepository.findByusername("bb1");
		User user3 = userRepository.findByusername("cc1");
		helloSender.send(user1, user2, user3);
		// helloSender.send(userRepository.findByUserName("aa3"));
	}
}