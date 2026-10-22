package com.eventplatform;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class KafkaTestController {
    private final KafkaProducerService kafkaProducerService;

    public  KafkaTestController(KafkaProducerService kafkaProducerService){
        this.kafkaProducerService = kafkaProducerService;
    }

    @GetMapping("/test-kafka")
    public String testKafka(){
        kafkaProducerService.sendTestMessage();
        return "Kafka message send requested";
    }

}
