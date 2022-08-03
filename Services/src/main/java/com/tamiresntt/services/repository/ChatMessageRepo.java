package com.tamiresntt.services.repository;

import com.tamiresntt.services.models.Message;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ChatMessageRepo extends ReactiveMongoRepository<Message, String> {
    @Tailable
    public Flux<Message> findWithTailableCursorByChannelId(String channelId);
}
