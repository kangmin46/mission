package com.example.demo.Entity;

import com.example.demo.exception.InvalidPasswordException;
import com.example.demo.exception.InvalidUserNameException;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    public User(String name, String password) {
        checkValidName(name);
        checkValidPassword(password);
        this.name = name;
        this.password = password;
    }

    private void checkValidName(String name) {
        if (StringUtils.isBlank(name) || StringUtils.isEmpty(name)) {
            throw new InvalidUserNameException();
        }
    }

    private void checkValidPassword(String password) {
        if (StringUtils.isBlank(password) || StringUtils.isEmpty(password)) {
            throw new InvalidPasswordException();
        }
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
