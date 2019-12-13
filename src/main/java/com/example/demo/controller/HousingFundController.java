package com.example.demo.controller;

import com.example.demo.service.HousingFundService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/housingfunds")
public class HousingFundController {

    private final HousingFundService housingFundService;

    public HousingFundController(final HousingFundService housingFundService) {
        this.housingFundService = housingFundService;
    }

    @PostMapping
    public ResponseEntity<Object> save() {
        housingFundService.save();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Object> find() {
       // housingFundService.find();
        return ResponseEntity.ok().build();
    }
}
