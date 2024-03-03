package com.digitalfuture.vacancy.controllers;


import com.digitalfuture.vacancy.dto.VacancyDTO;
import com.digitalfuture.vacancy.services.impl.VacancyServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VacancyController.class)
public class VacancyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VacancyServiceImpl vacancyService;

    @Test
    public void testCreateVacancy_Positive() throws Exception {
        when(vacancyService.addVacancy(any())).thenReturn(true);
        String requestBody = "{\"name\": \"test\"}";
        mockMvc.perform(put("/vacancy").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isCreated());

    }

    @Test
    public void testCreateVacancy_Negative() throws Exception {
        when(vacancyService.addVacancy(any())).thenReturn(false);
        String requestBody = "{\"name\": \"test\"}";
        mockMvc.perform(put("/vacancy").contentType(MediaType.APPLICATION_JSON).content(requestBody))
                .andExpect(status().isConflict());
    }

    @Test
    public void testGetCollectionVacancy_FilteredPositive(){
        Collection<VacancyDTO> vacancyDTOS = Arrays.asList(new VacancyDTO(), new VacancyDTO());
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            String jsonExpected = objectMapper.writeValueAsString(vacancyDTOS);
            when(vacancyService.getFilteredCollectionVacancy(any(), any(), any())).thenReturn(vacancyDTOS);
            mockMvc.perform(get("/vacancy").param("name", "test"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(jsonExpected));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testGetCollectionVacancy_FilteredNegative() throws Exception {
        Collection<VacancyDTO> vacancyDTOS = new ArrayList<>();
        when(vacancyService.getFilteredCollectionVacancy(any(), any(), any())).thenReturn(vacancyDTOS);
        mockMvc.perform(get("/vacancy").param("name", "test"))
                    .andExpect(status().isNotFound());
    }

    @Test
    public void testGetCollectionVacancy_AllPositive(){
        Collection<VacancyDTO> vacancyDTOS = Arrays.asList(new VacancyDTO(), new VacancyDTO());
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            String jsonExpected = objectMapper.writeValueAsString(vacancyDTOS);
            when(vacancyService.getCollectionVacancy()).thenReturn(vacancyDTOS);
            mockMvc.perform(get("/vacancy"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(jsonExpected));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testGetCollectionVacancy_AllNegative() throws Exception {
        Collection<VacancyDTO> vacancyDTOS = new ArrayList<>();
        when(vacancyService.getCollectionVacancy()).thenReturn(vacancyDTOS);
        mockMvc.perform(get("/vacancy"))
                .andExpect(status().isNotFound());
    }
}
