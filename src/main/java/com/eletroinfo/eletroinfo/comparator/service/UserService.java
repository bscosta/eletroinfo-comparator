package com.eletroinfo.eletroinfo.comparator.service;

import com.eletroinfo.eletroinfo.comparator.entitie.User;

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

    boolean existsByLoginAndDeletedFalse(String login);

    boolean existsByEmailAndDeletedFalse(String email);
}
