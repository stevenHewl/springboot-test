package springbootTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import springbootTest.entity.User;

//hibnete
public interface UserRepository extends JpaRepository<User, Long> {
	User findByusername(String userName);
    User findById(long id);
    void deleteById(long id);    
}

