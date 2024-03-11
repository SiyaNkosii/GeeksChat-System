package usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import usermanagement.entity.Conversation;
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
    public SseEmitter stream() {
        SseEmitter emitter = new SseEmitter();
        messageService.addEmitter(emitter);
        emitter.onCompletion(() -> messageService.removeEmitter(emitter));
        emitter.onTimeout(() -> messageService.removeEmitter(emitter));
        return emitter;
    }

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.OK)
    public void sendMessage(@RequestBody MessageDto messageDto) {
        String senderUsername = messageDto.getSenderUsername();
        String receiverUsername = messageDto.getReceiverUsername();
        String message = messageDto.getMessage();
        messageService.saveConversation(senderUsername, receiverUsername, message);
    }

    @GetMapping("/messages/{username}")
    public List<Conversation> getMessagesForUser(@PathVariable String username) {
        return messageService.getMessagesForUser(username);
    }
}
