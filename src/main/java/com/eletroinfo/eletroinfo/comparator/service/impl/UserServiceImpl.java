package com.eletroinfo.eletroinfo.comparator.service.impl;

import com.eletroinfo.eletroinfo.comparator.entitie.User;
import com.eletroinfo.eletroinfo.comparator.repository.UserRepository;
import com.eletroinfo.eletroinfo.comparator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @author Bruno Costa
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public boolean existsByLoginAndDeletedFalse(String login) {
        return this.userRepository.existsByLoginAndDeletedFalse(login);
    }

    public boolean existsByEmailAndDeletedFalse(String email) {
        return this.userRepository.existsByEmailAndDeletedFalse(email);
    }
}
