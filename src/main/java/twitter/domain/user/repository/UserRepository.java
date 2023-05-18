package twitter.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import twitter.domain.user.domain.User;

public interface UserRepository extends JpaRepository<User, String> {

}
