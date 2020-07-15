package springbootTest.service;

import java.util.List;

import springbootTest.entity.User;

public interface UserService {

    public List<User> getUserList();

	public User findByusername(String userName);

    public User findUserById(long id);

    public void save(User user);

    public void edit(User user);

    public void delete(long id);


}
