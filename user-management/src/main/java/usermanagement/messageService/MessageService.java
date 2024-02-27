package usermanagement.messageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import usermanagement.entity.Conversation;
import usermanagement.entity.User;
import usermanagement.repository.ConversationRepository;
import usermanagement.repository.UserRepository;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class MessageService {
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private UserRepository userRepository;
    public  void sendMessagesToClients(Conversation conversation){
        for (SseEmitter emitter : emitters){
            try {
                emitter.send(conversation);
            }catch (IOException e){
                emitter.complete();
                emitters.remove(emitter);
            }
        }
    }
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public Conversation saveConversation(User sender, User receiver, String message) {
        Conversation conversation = new Conversation();
        conversation.setSender(sender);
        conversation.setReceiver(receiver);
        conversation.setMessage(message);
        return conversationRepository.save(conversation);
    }

    public void saveAndSendConversation(String senderUsername, String receiverUsername, String message) {
        User sender = getUserByUsername(senderUsername);
        User receiver = getUserByUsername(receiverUsername);

        if (sender != null && receiver != null) {
            Conversation conversation = saveConversation(sender, receiver, message);
            sendMessagesToClients(conversation);
        } else {
            throw new IllegalArgumentException("Users do not exist");
        }
    }

}
