package com.example.demo.repository;

import com.example.demo.Entity.HousingFund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HousingFundRepository extends JpaRepository<HousingFund, Long> {
}
