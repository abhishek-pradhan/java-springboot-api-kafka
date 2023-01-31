package com.mts.wikiapi.controller;

import com.mts.wikiapi.service.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WikiController {
    private static final Logger logger = LoggerFactory.getLogger(WikiController.class);

    @Autowired
    private KafkaProducer wikiKafkaProducer;

    @GetMapping("/pub")
    public String publish(@RequestParam("value") String value) {
        for (int i = 1; i <= 3; i++) {
            this.wikiKafkaProducer.sendMessage("key" + i, value);
        }
        return "Published 3 messages, keys: 1-3, value: " + value;
    }
}
