package springbootTest;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import springbootTest.entity.User;
import springbootTest.repository.Secondary.SecondaryRepositoryMongodb;
import springbootTest.repository.primary.PrimaryRepositoryMongodb;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MuliMongodbTest {

    @Autowired
	private PrimaryRepositoryMongodb primaryRepository;

    @Autowired
	private SecondaryRepositoryMongodb secondaryRepository;

    @Test
    public void TestSave() {

        System.out.println("************************************************************");
        System.out.println("测试开始");
        System.out.println("************************************************************");

		Date date = new Date();
		String formattedDate = date.toString();
		User user = new User("mongodb1", "mongodb1@126.com", "mongodb1", "aa123456", formattedDate);
		User user2 = new User("mongodb2", "mongodb2@126.com", "mongodb2", "aa123456", formattedDate);

		this.primaryRepository.save(user);
		this.secondaryRepository.save(user2);

		List<User> primaries = this.primaryRepository.findAll();
		for (User primary : primaries) {
            System.out.println(primary.toString());
        }

		List<User> secondaries = this.secondaryRepository.findAll();
		for (User secondary : secondaries) {
            System.out.println(secondary.toString());
        }

        System.out.println("************************************************************");
        System.out.println("测试完成");
        System.out.println("************************************************************");
    }

}
