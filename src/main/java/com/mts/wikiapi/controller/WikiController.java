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

    // for simplicity's sake, this method is get, else would be post :)
    @GetMapping("/pub")
    public String publish(@RequestParam("value") String value) {
        //todo: populate domain event instead of below hardcoding
        WikiEntity wikiEntity = new WikiEntity();
        wikiEntity.setId(21);
        wikiEntity.setName(value);
        wikiEntity.setEmployeeCode("F002100");

        DomainEvent domainEvent = new DomainEvent();
        domainEvent.setWikiEntity(wikiEntity);
        domainEvent.setError(false);
        domainEvent.setMessage("Wiki Event occurred");

        this.wikiKafkaProducer.sendMessage("key" + domainEvent.getWikiEntity().getId(), domainEvent);

        return "Published 1 message, key" + domainEvent.getWikiEntity().getId() +", value=" + domainEvent.toString();
    }
}
