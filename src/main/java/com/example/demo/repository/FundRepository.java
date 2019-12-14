package com.example.demo.repository;

import com.example.demo.Entity.Fund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FundRepository extends JpaRepository<Fund, Long> {
    @Query(value =
        "select institute.name, sum(amount) as amount, year from fund\n" +
        "inner join institute\n" +
        "on institute.id = fund.institute_id\n" +
        "group by institute.name, year;", nativeQuery = true)
    List<Object[]> findStatistics();

}
