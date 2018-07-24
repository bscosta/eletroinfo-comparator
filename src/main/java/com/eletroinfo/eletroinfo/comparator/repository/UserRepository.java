package com.eletroinfo.eletroinfo.comparator.repository;

import com.eletroinfo.eletroinfo.comparator.entitie.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional(readOnly = true)
    boolean existsByLoginAndDeletedFalse(String login);

    @Transactional(readOnly = true)
    boolean existsByEmailAndDeletedFalse(String email);

    @Query("SELECT u FROM User u WHERE UPPER(u.name) LIKE UPPER(CONCAT('%',:name,'%')) "
            + " AND UPPER(u.email) LIKE UPPER(CONCAT('%',:email,'%')) "
            + " AND UPPER(u.login) LIKE UPPER(CONCAT('%',:login,'%')) " )
    Page<User> findByParameters(@Param("name") String name, @Param("email") String email, @Param("login") String login, Pageable pageable);
}
