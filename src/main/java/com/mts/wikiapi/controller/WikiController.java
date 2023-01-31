package com.mts.wikiapi.controller;

import com.mts.wikiapi.events.DomainEvent;
import com.mts.wikiapi.events.WikiEntity;
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
        DomainEvent domainEvent = new DomainEvent();

        WikiEntity wikiEntity = new WikiEntity();
        wikiEntity.setId(21);
        wikiEntity.setName("abhishek");
        wikiEntity.setEmployeeCode("F002184");

        domainEvent.setWikiEntity(wikiEntity);
        domainEvent.setError(false);
        domainEvent.setMessage("wiki event occurred");

        this.wikiKafkaProducer.sendMessage("key=" + domainEvent.getWikiEntity().getId(), domainEvent);
        return "Published 1 message, value=" + domainEvent.toString();
    }
}
