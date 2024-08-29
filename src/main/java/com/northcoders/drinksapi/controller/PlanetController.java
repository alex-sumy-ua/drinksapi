package com.northcoders.drinksapi.controller;

import com.northcoders.drinksapi.model.Planet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanetController {

    @Autowired
    Planet planet;


}
