package usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import usermanagement.entity.Conversation;

import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation,Long> {

    List<Conversation> findBySenderUsernameOrderByTimestamp(String senderUsername);
    List<Conversation> findByReceiverUsernameOrderByTimestamp(String receiverUsername);
    List<Conversation> findBySenderUsernameOrReceiverUsernameOrderByTimestamp(String senderUsername, String receiverUsername);
}
