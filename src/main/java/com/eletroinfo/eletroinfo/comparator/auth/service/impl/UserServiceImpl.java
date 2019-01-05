package com.eletroinfo.eletroinfo.comparator.auth.service.impl;

import com.eletroinfo.eletroinfo.comparator.dto.UserDto;
import com.eletroinfo.eletroinfo.comparator.auth.entitie.User;
import com.eletroinfo.eletroinfo.comparator.auth.filter.UserFilter;
import com.eletroinfo.eletroinfo.comparator.auth.repository.UserRepository;
import com.eletroinfo.eletroinfo.comparator.auth.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
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

    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setPassword(null);
        }
        return user;
    }

    @Transactional(readOnly = true)
    public Optional<User> findByIdForValidation(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return user;
    }

    @Transactional(readOnly = true)
    public PageImpl<User> findByParameters(UserFilter userFilter, Pageable pageable) {
        return this.userRepository.findByParameters(userFilter.getName(), userFilter.getEmail(), userFilter.getLogin(), userFilter.getUserType(), pageable);
    }

    public UserDto convertUserEntityToUserDto(User user) {
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    public User convertUserDtoToUserEntity(UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDto, User.class);
        return user;
    }

    public boolean existsByLoginAndDeletedFalse(String login) {
        return this.userRepository.existsByLoginAndDeletedFalse(login);
    }

    public Optional<User> findByLogin(String login) {
        return this.userRepository.findByLogin(login);
    }

    public boolean existsByEmailAndDeletedFalse(String email) {
        return this.userRepository.existsByEmailAndDeletedFalse(email);
    }

    public void delete(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        user.get().setDeleted(true);
        this.userRepository.save(user.get());
    }
}
