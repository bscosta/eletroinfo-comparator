package com.eletroinfo.eletroinfo.comparator.repository;

import com.eletroinfo.eletroinfo.comparator.entitie.User;
import com.eletroinfo.eletroinfo.comparator.enumeration.UserType;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional(readOnly = true)
    boolean existsByLoginAndDeletedFalse(String login);

    @Transactional(readOnly = true)
    boolean existsByEmailAndDeletedFalse(String email);

    @Transactional(readOnly = true)
    Optional<User> findByLogin(String login);

    @Query("SELECT u FROM User u WHERE UPPER(u.name) LIKE UPPER(CONCAT('%',:name,'%')) "
            + " AND UPPER(u.email) LIKE UPPER(CONCAT('%',:email,'%')) "
            + " AND UPPER(u.login) LIKE UPPER(CONCAT('%',:login,'%')) "
            + " AND (:userType is null OR u.userType = :userType) "
            + " AND deleted is false ")
    PageImpl<User> findByParameters(@Param("name") String name, @Param("email") String email, @Param("login") String login, @Param("userType") UserType userType, Pageable pageable);
}
