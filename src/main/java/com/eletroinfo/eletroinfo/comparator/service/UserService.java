package com.eletroinfo.eletroinfo.comparator.service;

import com.eletroinfo.eletroinfo.comparator.entitie.User;
import com.eletroinfo.eletroinfo.comparator.filter.UserFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    /**
     *  Salva um usuario no banco de dados e retorna o usuario com o ID
     *
     * @param user
     * @return User
     */
    User save(User user);

    Optional<User> findById(Long id);

    Page<User> findByParameters(UserFilter userFilter, Pageable pageable);

    boolean existsByLoginAndDeletedFalse(String login);

    boolean existsByEmailAndDeletedFalse(String email);
}
