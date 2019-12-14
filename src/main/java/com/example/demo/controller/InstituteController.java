package com.example.demo.controller;

import com.example.demo.response.FundAverageMinMaxResponse;
import com.example.demo.response.InstituteResponse;
import com.example.demo.response.MaxInstituteResponse;
import com.example.demo.service.InstituteService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.example.demo.controller.InstituteController.INSTITUTE_URL;

@Controller
@RequestMapping(INSTITUTE_URL)
public class InstituteController {
    public static final String INSTITUTE_URL = "/institutes";
    private final InstituteService instituteService;

    public InstituteController(InstituteService instituteService) {
        this.instituteService = instituteService;
    }

    @GetMapping
    public ResponseEntity<List<InstituteResponse>> find() {
        List<InstituteResponse> instituteResponses = instituteService.find();
        return ResponseEntity.ok(instituteResponses);
    }

    @GetMapping("/{instituteCode}/averages")
    public ResponseEntity<FundAverageMinMaxResponse> findAverage(@PathVariable String instituteCode) {
        FundAverageMinMaxResponse fundAverageMinMaxResponse = instituteService.findAverageMinMax(instituteCode);
        return ResponseEntity.ok(fundAverageMinMaxResponse);
    }

    @GetMapping("/max")
    public ResponseEntity findMaxInstitute() {
        MaxInstituteResponse maxInstituteResponse = instituteService.findMaxInstitute();
        return ResponseEntity.ok(maxInstituteResponse);
    }
}
