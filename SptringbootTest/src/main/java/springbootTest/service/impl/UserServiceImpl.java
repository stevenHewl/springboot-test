package springbootTest.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import springbootTest.entity.User;
import springbootTest.repository.UserRepository;
import springbootTest.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	Date date = new Date();
	String formattedDate = date.toString();
	
    @Autowired
    private UserRepository userRepository;

	@Autowired
	private RedisTemplate redisTemplate;

    @Override
    public List<User> getUserList() {
		List<User> list = userRepository.findAll(new Sort("id").by("username").descending());
        return list;
    }

	@Override
	public User findByusername(String userName) {
		return userRepository.findByusername(userName);
	}

	/*
	 * value属性是必须指定的，其表示当前方法的返回值是会被缓存在哪个Cache上的，对应Cache的名称; 缓存的 key，可以为空，如果指定要按照
	 * SpEL表达式编写，如果不指定，则缺省按照方法的所有参数进行组合,
	 * 自定义策略是指我们可以通过Spring的EL表达式来指定我们的key。这里的EL表达式可以使用方法参数及它们对应的属性,
	 * 使用方法参数时我们可以直接使用“#参数名”或者“#p参数index”
	 */
    @Override
	// @Cacheable(value = "user-key", key = "#id")
	public User findUserById(long id) {
		/*
		 * ValueOperations<String, User> operations = redisTemplate.opsForValue();
		 * String key = "user-" + id; if (redisTemplate.hasKey(key)) { return
		 * operations.get(key); }
		 */
		// System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
		User user = userRepository.findById(id);
		// operations.set(key, user);
		return user;
    }

    @Override
    public void save(User user) {
    	user.setregTime(formattedDate);
        userRepository.save(user);
    }

    @Override
    public void edit(User user) {
		user.setregTime(formattedDate);
		userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}


