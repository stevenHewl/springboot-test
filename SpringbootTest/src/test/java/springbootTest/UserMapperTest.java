package springbootTest;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import springbootTest.common.UserSexEnum;
import springbootTest.mybatis.mapperDao.UserMapperXML;
import springbootTest.mybatis.mapperDao2.UserMapperXML2;
import springbootTest.mybatisEntity.UserEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

	/*
	 * 方式一，直接在代码中写sql，map
	 */
	// @Autowired
	// private UserMapper UserMapper;

	/*
	 * 方式二，在xml中写sql映射，mapper dao 中直接调用
	 */
	@Autowired
	private UserMapperXML UserMapper;

	@Autowired
	private UserMapperXML2 UserMapper2;

	@Test
	public void testInsert() throws Exception {
		UserMapper.insert(new UserEntity(1, "aa7", "a123456", UserSexEnum.MAN, "aa7"));
		Assert.assertEquals(17, UserMapper.getAll().size());
	}

	@Test
	public void testQuery() throws Exception {
		List<UserEntity> users = UserMapper.getAll();
		System.out.println("Database Test:" + users.toString());
	}
	
	@Test
	public void testUpdate() throws Exception {
		UserEntity user = UserMapper.getOne(5l);
		user.setNickName("spring6");
		UserMapper.update(user);
		Assert.assertTrue(("spring6".equals(UserMapper.getOne(5l).getNickName())));
	}
	
	@Test
	public void testInsert2() throws Exception {
		UserMapper2.insert(new UserEntity(2, "aa2", "a123456", UserSexEnum.MAN, "aa2"));
		Assert.assertEquals(3, UserMapper2.getAll().size());
	}

	@Test
	public void testQuery2() throws Exception {
		List<UserEntity> users2 = UserMapper2.getAll();
		System.out.println("Database Test2:" + users2.toString());
	}
	
	@Test
	public void testUpdate2() throws Exception {
		UserEntity user2 = UserMapper2.getOne(19l);
		user2.setNickName("spring1");
		UserMapper2.update(user2);
		Assert.assertTrue(("spring1".equals(UserMapper2.getOne(19l).getNickName())));
	}
}