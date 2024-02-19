package usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import usermanagement.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    List<User> findByUsernameContaining(String username);
    User findByUsername(String username);
}
