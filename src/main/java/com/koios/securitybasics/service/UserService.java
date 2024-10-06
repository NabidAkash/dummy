package com.koios.securitybasics.service;

import com.koios.securitybasics.model.Authority;
import com.koios.securitybasics.model.User;
import com.koios.securitybasics.repository.AuthorityRepository;
import com.koios.securitybasics.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;


    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username.toLowerCase());
    }

    public void saveUser(User user) {
        Authority authority = authorityRepository.findByAuthority("ROlE_USER");
        user.setAuthorities(Set.of(authority));
        userRepository.save(user);
    }

    public void saveAdmin(User user) {
        Authority authority = authorityRepository.findByAuthority("ROlE_ADMIN");
        user.setAuthorities(Set.of(authority));
        userRepository.save(user);
    }

}
