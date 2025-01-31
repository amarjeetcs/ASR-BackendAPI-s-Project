package com.asr.website.controller;

import com.asr.website.model.Subscriber;
import com.asr.website.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/newsletter")
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;

    //@PostMapping("/subscribe")
   /* public ResponseEntity<String> subscribe(@RequestBody Map<String, String> requestBody) {
        // Extract the email from the JSON body
        String email = requestBody.get("email");
        
        // Call the service to add the subscriber
        String responseMessage = subscriberService.addSubscriber(email);
        
        // Return success response
        return ResponseEntity.ok(responseMessage);
    }*/
    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestBody Subscriber subscriber) {
        String email = subscriber.getEmail();
        String responseMessage = subscriberService.addSubscriber(email);
        return ResponseEntity.ok(responseMessage);
    }
}
