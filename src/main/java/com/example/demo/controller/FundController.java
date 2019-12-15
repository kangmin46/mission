package com.example.demo.controller;

import com.example.demo.Entity.Institute;
import com.example.demo.response.FundResponse;
import com.example.demo.response.RecommendResponse;
import com.example.demo.service.FundService;
import com.example.demo.service.InstituteService;
import com.example.demo.utils.CSVDataReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.demo.controller.FundController.FUND_URL;

@RestController
@RequestMapping(FUND_URL)
public class FundController {
    private static final String DATA_RESOURCE_NAME = "사전과제3.csv";
    public static final String FUND_URL = "/funds";

    private final FundService fundService;
    private final InstituteService instituteService;

    public FundController(FundService fundService, InstituteService instituteService) {
        this.fundService = fundService;
        this.instituteService = instituteService;
    }

    @PostMapping
    public ResponseEntity save() {
        CSVDataReader csvDataReader = new CSVDataReader(DATA_RESOURCE_NAME);
        List<Institute> institutes = instituteService.save(csvDataReader.getHeader());
        fundService.save(csvDataReader.getBody(), institutes);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity find() {
        FundResponse fundResponse = fundService.find();
        return ResponseEntity.ok(fundResponse);
    }

    @GetMapping("/predict/{month}/{instituteName}")
    public ResponseEntity predict(@PathVariable Integer month, @PathVariable String instituteName) {
        RecommendResponse recommendResponse = fundService.recommend(month, instituteName);
        return ResponseEntity.ok(recommendResponse);
    }
}
