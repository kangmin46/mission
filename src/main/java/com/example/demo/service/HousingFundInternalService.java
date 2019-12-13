package com.example.demo.service;

import com.example.demo.Entity.HousingFund;
import com.example.demo.repository.HousingFundRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HousingFundInternalService {

    private final HousingFundRepository housingFundRepository;

    public HousingFundInternalService(HousingFundRepository housingFundRepository) {
        this.housingFundRepository = housingFundRepository;
    }

    public HousingFund save(final HousingFund housingFund) {
        return housingFundRepository.save(housingFund);
    }
}
