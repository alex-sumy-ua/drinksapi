package com.northcoders.drinksapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
public class TeaControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testTeaControllerOutput() throws Exception {

        String expectedValue = "yorkshire";
        int expectedId = 1;

        // Use the .param() method after the .get() to pass in a request param into the test.

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/tea")
                                .param(expectedValue, String.valueOf(expectedId)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(expectedValue))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(expectedId));

    }

}
