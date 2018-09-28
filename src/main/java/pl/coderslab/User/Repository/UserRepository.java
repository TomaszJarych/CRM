package pl.coderslab.User.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.coderslab.User.domain.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>{
	
	User findUserByLogin(String login);

}
