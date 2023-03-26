package com.example.demo.controller;

import com.example.demo.domain.Chat;
import com.example.demo.domain.Message;
import com.example.demo.domain.User;
import com.example.demo.dto.ChatDto;
import com.example.demo.dto.DriverDto;
import com.example.demo.repository.ChatRepository;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ModelMapper modelMapper;

    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, Message message) {
        System.out.println("handling send message: " + message + " to: " + to);
        message.setChat_id(createAndOrGetChat(to));
        message.setTimeStamp(generateTimeStamp());
        message = messageRepository.save(message);
        simpMessagingTemplate.convertAndSend("/topic/messages/" + to, message);
    }

    @GetMapping("/getChats/{username}")
    public ResponseEntity<Collection<ChatDto>> getChats(@PathVariable String username){
        return new ResponseEntity<Collection<ChatDto>>(chatRepository.findByPartecipant(username)
                .stream()
                .map(chat -> modelMapper.map(chat, ChatDto.class))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    //returns an empty list if the chat doesn't exist
    @PostMapping("/getMessages")
    public List<Message> getMessages(@RequestBody String name) {
        Chat chat = chatRepository.findByName(name);

        if(chat != null) {
            return messageRepository.findAllByChat(chat.getId());
        }
        else{
            return new ArrayList<Message>();
        }
    }

    private Integer createAndOrGetChat(String name) {
        Chat chat = chatRepository.findByName(name);

        if (chat != null) {
            return chat.getId();
        }
        else {
            Chat newChat = new Chat(name);
            return chatRepository.save(newChat).getId();
        }
    }

    private String generateTimeStamp() {
        Instant i = Instant.now();
        String date = i.toString();
        System.out.println("Source: " + i.toString());
        int endRange = date.indexOf('T');
        date = date.substring(0, endRange);
        date = date.replace('-', '/');
        System.out.println("Date extracted: " + date);
        String time = Integer.toString(i.atZone(ZoneOffset.UTC).getHour() + 1);
        time += ":";

        int minutes = i.atZone(ZoneOffset.UTC).getMinute();
        if (minutes > 9) {
            time += Integer.toString(minutes);
        } else {
            time += "0" + Integer.toString(minutes);
        }

        System.out.println("Time extracted: " + time);
        String timeStamp = date + "-" + time;
        return timeStamp;
    }
}
