package com.northcoders.drinksapi.controller;

import com.northcoders.drinksapi.model.Coffee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CoffeeController {

    AtomicLong counter = new AtomicLong();

    @GetMapping("/coffee")
    public Coffee getCoffee(@RequestParam(value = "name", defaultValue = "latte") String name) {
        return new Coffee(counter.incrementAndGet(), name);
    }

}
