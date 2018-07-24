package com.eletroinfo.eletroinfo.comparator.service.impl;

import com.eletroinfo.eletroinfo.comparator.entitie.User;
import com.eletroinfo.eletroinfo.comparator.filter.UserFilter;
import com.eletroinfo.eletroinfo.comparator.repository.UserRepository;
import com.eletroinfo.eletroinfo.comparator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Page<User> findByParameters(UserFilter userFilter, Pageable pageable) {
        return this.userRepository.findByParameters(userFilter.getName(), userFilter.getEmail(), userFilter.getLogin(), pageable);
    }

    public boolean existsByLoginAndDeletedFalse(String login) {
        return this.userRepository.existsByLoginAndDeletedFalse(login);
    }

    public boolean existsByEmailAndDeletedFalse(String email) {
        return this.userRepository.existsByEmailAndDeletedFalse(email);
    }
}
