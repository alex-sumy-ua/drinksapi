package com.northcoders.drinksapi.controller;

import com.northcoders.drinksapi.model.HealthLevel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
public class HealthLevelControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHealthLevelControllerOutput() throws Exception {

        String expectedValue1 = "Simon";
        String expectedValue2 = HealthLevel.GREAT;
        int expectedId = 1;

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/health")
                                .param(expectedValue1, expectedValue2, String.valueOf(expectedId)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expectedValue1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.healthLevel").value(expectedValue2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedId));

    }

}
