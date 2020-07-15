package springbootTest.mybatis.mapperDao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import springbootTest.common.UserSexEnum;
import springbootTest.mybatisEntity.UserEntity;

public interface UserMapper {
	@Select("SELECT * FROM user2")
	@Results({ @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
			@Result(property = "nickName", column = "nickName") })
	List<UserEntity> getAll();

	@Select("SELECT * FROM user2 WHERE id = #{id}")
	@Results({ @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
			@Result(property = "nickName", column = "nickName") })
	UserEntity getOne(Long id);

	@Insert("INSERT INTO user2(userName,passWord,user_sex,nickName) VALUES(#{userName}, #{passWord}, #{userSex},#{nickName})")
	void insert(UserEntity user);

	@Update("UPDATE user2 SET userName=#{userName},nickName=#{nickName} WHERE id =#{id}")
	void update(UserEntity user);

	@Delete("DELETE FROM user2 WHERE id =#{id}")
	void delete(Long id);
}
