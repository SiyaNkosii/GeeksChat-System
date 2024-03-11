package usermanagement.messageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import usermanagement.entity.Conversation;
import usermanagement.entity.User;
import usermanagement.repository.ConversationRepository;
import usermanagement.repository.UserRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class MessageService {
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @Autowired
    private ConversationRepository conversationRepository;
    public void addEmitter(SseEmitter emitter) {
        emitters.add(emitter);
    }
    public void removeEmitter(SseEmitter emitter) {
        emitters.remove(emitter);
    }
    public void sendMessageToClients(Conversation conversation) {
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(conversation);
            } catch (Exception e) {
                // Handle exception
            }
        }
    }

    public Conversation saveConversation(String senderUsername, String receiverUsername, String message) {
        Conversation conversation = new Conversation();
        conversation.setSenderUsername(senderUsername);
        conversation.setReceiverUsername(receiverUsername);
        conversation.setMessage(message);
        conversation.setTimestamp(LocalDateTime.now()); // Set current timestamp
        return conversationRepository.save(conversation);
    }

    public List<Conversation> getMessagesForUser(String username) {
        return conversationRepository.findBySenderUsernameOrReceiverUsernameOrderByTimestamp(username, username);
    }


}
