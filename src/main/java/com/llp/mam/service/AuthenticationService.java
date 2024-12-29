package com.llp.mam.service;


import com.llp.mam.entity.User;
import com.llp.mam.exception.UserNotFoundException;
import com.llp.mam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UserNotFoundException {
        return userRepository.findByUserName(userName);
    }



//    private final PasswordEncoder passwordEncoder;
//
//
//    public Optional<User> loadUserByUserId(Long userId) throws UserNotFoundException    {
//
//        return userRepository.findById(userId);
//    }
//
//
//    public AuthenticationService() {
//     this.passwordEncoder = new BCryptPasswordEncoder();
//    }
//
//
//    public String encryptedPassword   (String rawPassword) {
//
//        return passwordEncoder.encode(rawPassword);
//    }
//
//    public Boolean matches(String rawPassword, String encodedPassword) {
//        return passwordEncoder.matches(rawPassword, encodedPassword);
//    }


}
