package messageservice.service;

import messageservice.exceptions.IllegalArgumentException;
import messageservice.requestPayloads.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageService {
    @Autowired
    private RestTemplate restTemplate;
    public void sendMessage(MessageDto messageDto){
        boolean senderExists = restTemplate.getForObject("http://localhost:8001/users/exists/{username}", Boolean.class, messageDto.getSenderUsername());
        boolean receiverExists = restTemplate.getForObject("http://localhost:8080/users/exists/{username}", Boolean.class, messageDto.getReceiverUsername());
        if(senderExists && receiverExists )
        {
            restTemplate.postForObject("http://localhost:8001/messages/send",messageDto, Void.class);
        }else {
            throw new IllegalArgumentException("Sender and Receiver don't exist");
        }

    }
}
