package com.eletroinfo.eletroinfo.comparator.repository;

import com.eletroinfo.eletroinfo.comparator.entitie.Menu;
import com.eletroinfo.eletroinfo.comparator.entitie.UserMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Bruno Costa
 */

@Repository
public interface UserMenuRepository extends JpaRepository<UserMenu, Long> {

    @Transactional(readOnly = true)
    Optional<UserMenu> findByUserIdAndDeletedIsFalse(Long id);

    @Transactional
    void removeByMenuId(Menu menu);
}