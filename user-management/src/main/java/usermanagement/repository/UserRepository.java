package usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import usermanagement.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

}
