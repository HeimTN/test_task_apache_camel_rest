package com.digitalfuture.vacancy.controllers;

import com.digitalfuture.vacancy.dto.VacancyDTO;
import com.digitalfuture.vacancy.services.VacancyService;
import com.digitalfuture.vacancy.services.impl.VacancyServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
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
    public ResponseEntity<Collection<VacancyDTO>> getCollectionVacancy(@PathVariable @Pattern(regexp = "name|position|city") String filter,
                                                                       @PathVariable String key){
        Collection<VacancyDTO> result = filter != null ?
                                            vacancyService.getFilteredCollectionVacancy(filter, key):
                                            vacancyService.getCollectionVacancy();
        return result.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).build():
                ResponseEntity.status(200).body(result);
    }
}
