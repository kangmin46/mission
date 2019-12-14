package com.example.demo.service;

import com.example.demo.Entity.Institute;
import com.example.demo.repository.InstituteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InstituteInternalService {

    private final InstituteRepository instituteRepository;

    public InstituteInternalService(final InstituteRepository instituteRepository) {
        this.instituteRepository = instituteRepository;
    }

    public Institute save(final Institute institute) {
        return instituteRepository.save(institute);
    }

    @Transactional(readOnly = true)
    public List<Institute> findAll() {
        return instituteRepository.findAll();
    }
}
