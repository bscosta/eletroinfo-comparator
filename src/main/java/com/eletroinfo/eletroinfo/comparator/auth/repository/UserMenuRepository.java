package com.eletroinfo.eletroinfo.comparator.auth.repository;

import com.eletroinfo.eletroinfo.comparator.auth.entitie.Menu;
import com.eletroinfo.eletroinfo.comparator.auth.entitie.UserMenu;
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

    @Transactional(readOnly = true)
    @Query(" SELECT um From UserMenu um "
         + " WHERE um.userId.id = :userId "
         + " AND um.menuId.id = :menuId "
         + " AND um.deleted is false ")
    Optional<UserMenu> findByUserIdAndMenuIdAndDeletedIsFalse(@Param("userId") Long userId, @Param("menuId") Long menuId);
}
