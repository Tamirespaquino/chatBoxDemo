package com.tamiresntt.services.repository;

import com.tamiresntt.services.models.Channel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ChatChannelRepo extends ReactiveMongoRepository<Channel, String> {
}
