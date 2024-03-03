package com.digitalfuture.vacancy.controllers;

import com.digitalfuture.vacancy.services.SubscribeService;
import com.digitalfuture.vacancy.services.impl.SubscribeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;

@RestController
@RequestMapping("/subscriber")
public class SubscriptionController {

    private final SubscribeService subscribeService;

    public SubscriptionController(SubscribeServiceImpl subscribeService){
        this.subscribeService = subscribeService;
    }

    @PutMapping
    public ResponseEntity<?> subscribeCandidate(@PathVariable @Email String email){
        return subscribeService.subscribeCandidate(email) ?
                ResponseEntity.status(200).build():
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
