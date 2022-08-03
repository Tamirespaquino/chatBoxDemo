package com.tamiresntt.services.controllers;

import com.tamiresntt.services.models.Channel;
import com.tamiresntt.services.models.Message;
import com.tamiresntt.services.repository.ChatChannelRepo;
import com.tamiresntt.services.repository.ChatMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ChatMessageController {

    @Autowired
    ChatChannelRepo chatChannelRepo;
    @Autowired
    ChatMessageRepo chatMessageRepo;

    @PostMapping("/chats")
    @ResponseStatus(HttpStatus.CREATED)
    public void postChat(@RequestBody Message chatMessage) {
        chatMessageRepo.insert(chatMessage).subscribe();
    }

    @PostMapping("/channels")
    public Mono<Channel> createChannel(@RequestBody Channel channel) {
        return chatChannelRepo.save(channel);
    }

    @GetMapping(value = "/chats/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Message> streamMessages(@RequestParam String channelId) {
        return chatMessageRepo.findWithTailableCursorByChannelId(channelId);
    }
}
