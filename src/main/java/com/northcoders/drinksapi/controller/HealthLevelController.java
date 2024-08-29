package com.northcoders.drinksapi.controller;

import com.northcoders.drinksapi.model.Health;
import com.northcoders.drinksapi.model.HealthLevel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HealthLevelController {

    AtomicLong counter = new AtomicLong();

    @GetMapping("/health")
    public Health getHealth(@RequestParam(value = "name", defaultValue = "Simon") String name,
                            @RequestParam(value = "healthLevel", defaultValue = HealthLevel.GREAT) String healthLevel) {
        return new Health(counter.incrementAndGet(), name, healthLevel);
    }

}
