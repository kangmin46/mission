package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public class HousingFund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer month;

    @Column(nullable = false)
    private Integer amount;

    @ManyToOne
    private Institute institute;

    public HousingFund(Integer year, Integer month, Integer amount, Institute institute) {
        this.year = year;
        this.month = month;
        this.amount = amount;
        this.institute = institute;
    }

    public HousingFund() {
    }

    public Integer getYear() {
        return year;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getAmount() {
        return amount;
    }

    public Institute getInstitute() {
        return institute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HousingFund that = (HousingFund) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(year, that.year) &&
            Objects.equals(month, that.month) &&
            Objects.equals(amount, that.amount) &&
            Objects.equals(institute, that.institute);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year, month, amount, institute);
    }
}
