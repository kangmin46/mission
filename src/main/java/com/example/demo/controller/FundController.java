package com.example.demo.controller;

import com.example.demo.Entity.Institute;
import com.example.demo.response.FundResponse;
import com.example.demo.service.FundService;
import com.example.demo.service.InstituteService;
import com.example.demo.utils.CSVDataReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.demo.controller.FundController.FUND_URL;

@RestController
@RequestMapping(FUND_URL)
public class FundController {
    public static final String FUND_URL = "/funds";

    private final FundService fundService;
    private final InstituteService instituteService;

    public FundController(FundService fundService, InstituteService instituteService) {
        this.fundService = fundService;
        this.instituteService = instituteService;
    }

    @PostMapping
    public ResponseEntity save() {
        CSVDataReader csvDataReader = new CSVDataReader();
        List<Institute> institutes = instituteService.save(csvDataReader.getHeader());
        fundService.save(csvDataReader.getBody(), institutes);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity find() {
        FundResponse fundResponse = fundService.find();
        return ResponseEntity.ok(fundResponse);
    }
}
