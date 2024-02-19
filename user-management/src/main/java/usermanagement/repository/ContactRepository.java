package usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import usermanagement.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
