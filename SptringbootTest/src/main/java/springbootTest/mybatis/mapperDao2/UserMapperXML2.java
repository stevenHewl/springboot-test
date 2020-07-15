package springbootTest.mybatis.mapperDao2;

import java.util.List;

import springbootTest.mybatisEntity.UserEntity;

public interface UserMapperXML2 {
	
	List<UserEntity> getAll();
	
	UserEntity getOne(Long id);

	void insert(UserEntity user);

	void update(UserEntity user);

	void delete(Long id);

}