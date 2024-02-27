package usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import usermanagement.entity.User;
import usermanagement.exceptions.IllegalArgumentException;
import usermanagement.messageService.MessageService;
import usermanagement.requestPayloads.MessageDto;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class MessageController {
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @Autowired
    private MessageService messageService;
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter stream() throws IOException {
        SseEmitter emitter = new SseEmitter();
        emitters.add(emitter);

        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));

        return emitter;
    }

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.OK)
    public void sendMessage(@RequestBody MessageDto messageDto) {
        String senderUsername = messageDto.getSenderUsername();
        String receiverUsername = messageDto.getReceiverUsername();
        String message = messageDto.getMessage();

        User sender = messageService.getUserByUsername(senderUsername);
        User receiver = messageService.getUserByUsername(receiverUsername);
        if (sender != null && receiver != null) {
            messageService.saveAndSendConversation(senderUsername, receiverUsername, message);
        } else {
            throw new IllegalArgumentException("Users do not exist");
        }
    }
}
