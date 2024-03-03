package com.digitalfuture.vacancy.controllers;

import com.digitalfuture.vacancy.dto.VacancyDTO;
import com.digitalfuture.vacancy.services.VacancyService;
import com.digitalfuture.vacancy.services.impl.VacancyServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/vacancy")
public class VacancyController {
    private final VacancyService vacancyService;

    public VacancyController(VacancyServiceImpl vacancyService){
        this.vacancyService = vacancyService;
    }
    @PutMapping
    public ResponseEntity<?> createVacancy(@RequestBody VacancyDTO vacancy){
        return vacancyService.addVacancy(vacancy) ?
                ResponseEntity.status(HttpStatus.CREATED).build():
                ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    @GetMapping
    public ResponseEntity<Collection<VacancyDTO>> getCollectionVacancy(@RequestParam(name = "name", required = false) String name,
                                                                       @RequestParam(name = "position", required = false) String position,
                                                                       @RequestParam(name = "city", required = false) String city){
        Collection<VacancyDTO> result;
        if(name != null || position != null || city != null){
            result = vacancyService.getFilteredCollectionVacancy(name, position, city);
        } else  result = vacancyService.getCollectionVacancy();
        return result.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).build():
                ResponseEntity.status(200).body(result);
    }
}
