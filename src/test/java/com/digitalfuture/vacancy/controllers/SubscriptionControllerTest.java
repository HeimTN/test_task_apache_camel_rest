package com.digitalfuture.vacancy.controllers;

import com.digitalfuture.vacancy.services.impl.SubscribeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SubscriptionController.class)
public class SubscriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubscribeServiceImpl subscribeService;

    @Test
    public void testSubscribeCandidate_Positive() throws Exception {
        String email = "test@example.com";
        when(subscribeService.subscribeCandidate(email)).thenReturn(true);
        mockMvc.perform(put("/subscriber").param("email", "test@example.com"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSubscribeCandidate_Negative() throws Exception {
        String email = "test@example.com";
        when(subscribeService.subscribeCandidate(email)).thenReturn(false);
        mockMvc.perform(put("/subscriber").param("email", "test@example.com"))
                .andExpect(status().isNotFound());
    }
}
