package springbootTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import springbootTest.common.UserSexEnum;
import springbootTest.mybatis.mapperDao.UserMapperTkMybatis;
import springbootTest.mybatisEntity.UserEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class userMapperTkMybatisTest {

	@Autowired
	private UserMapperTkMybatis UserMapperDao;

	@Test
	public void testInsert() throws Exception {
		UserMapperDao.insert(new UserEntity(12, "aa12", "a123456", UserSexEnum.MAN, "aa12", 53));
		Assert.assertEquals(4, UserMapperDao.selectAll().size());
	}
}
