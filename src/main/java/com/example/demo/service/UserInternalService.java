package com.example.demo.service;

import com.example.demo.Entity.User;
import com.example.demo.exception.MisMatchPasswordException;
import com.example.demo.repository.UserRepository;
import com.example.demo.vo.SignUpLoginDto;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserInternalService {

    private final UserRepository userRepository;

    public UserInternalService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User save(SignUpLoginDto signUpLoginDto) {
        String hashPassword = BCrypt.hashpw(signUpLoginDto.getPassword(), BCrypt.gensalt());
        return userRepository.save(new User(signUpLoginDto.getId(), hashPassword));
    }

    @Transactional(readOnly = true)
    public User findByName(String name, String password) {
        User user = userRepository.findByName(name);
        if(!BCrypt.checkpw(password, user.getPassword())) {
            throw new MisMatchPasswordException();
        }
        return user;
    }
}
