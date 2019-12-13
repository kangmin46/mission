package com.example.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Institute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private int code;

    public Institute(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public Institute() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Institute institute = (Institute) o;
        return code == institute.code &&
            Objects.equals(id, institute.id) &&
            Objects.equals(name, institute.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
