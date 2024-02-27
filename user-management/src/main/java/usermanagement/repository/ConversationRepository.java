package usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import usermanagement.entity.Conversation;

public interface ConversationRepository extends JpaRepository<Conversation,Long> {
}
