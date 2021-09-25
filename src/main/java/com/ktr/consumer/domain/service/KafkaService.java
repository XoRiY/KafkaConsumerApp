package com.ktr.consumer.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktr.consumer.domain.exception.TechnicalException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaService<T> {

    @KafkaListener(topics = "${topic-name}", groupId = "group_id")
    public String  consume(String message) {
        System.out.println(message);
        return message;
    }

    private T getObjectFromJsonMessage(String message, Class<T> t) {

        ObjectMapper mapper = new ObjectMapper();
        try {

            return mapper.readValue(message, t);

        } catch (JsonProcessingException e) {

            throw new TechnicalException(e.getMessage());

        }

    }

}
