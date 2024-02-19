package usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import usermanagement.entity.Contact;
import usermanagement.entity.User;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query("SELECT c FROM Contact c " +
            "JOIN c.user u " +
            "WHERE u.username = :username")
    Optional<Contact> findByUserUsername(String username);
    List<Contact> findByUser(User user);
}
