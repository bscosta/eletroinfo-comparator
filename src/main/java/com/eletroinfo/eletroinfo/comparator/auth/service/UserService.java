package com.eletroinfo.eletroinfo.comparator.auth.service;

import com.eletroinfo.eletroinfo.comparator.dto.UserDto;
import com.eletroinfo.eletroinfo.comparator.auth.entitie.User;
import com.eletroinfo.eletroinfo.comparator.auth.filter.UserFilter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author Bruno Costa
 */

public interface UserService {

    /**
     *  Salva um user no banco de dados e retorna o user com o ID
     *
     * @param user
     * @return User
     */
    User save(User user);

    Optional<User> findById(Long id);

    Optional<User> findByIdForValidation(Long id);

    PageImpl<User> findByParameters(UserFilter userFilter, Pageable pageable);

    UserDto convertUserEntityToUserDto(User user);

    User convertUserDtoToUserEntity(UserDto userDto);

    boolean existsByLoginAndDeletedFalse(String login);

    boolean existsByEmailAndDeletedFalse(String email);

    Optional<User> findByLogin(String login);

    void delete(Long id);
}
