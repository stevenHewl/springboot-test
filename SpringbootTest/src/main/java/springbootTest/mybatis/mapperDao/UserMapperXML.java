package springbootTest.mybatis.mapperDao;

import java.util.List;

import springbootTest.mybatisEntity.UserEntity;

public interface UserMapperXML {
	
	List<UserEntity> getAll();
	
	UserEntity getOne(Long id);

	void insert(UserEntity user);

	void update(UserEntity user);

	void delete(Long id);

}