package com.northcoders.drinksapi.model;

public record Health(long id,
                     String name,
                     String/*HealthLevel*/ healthLevel) {
}
