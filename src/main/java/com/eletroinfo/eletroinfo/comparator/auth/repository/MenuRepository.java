package com.eletroinfo.eletroinfo.comparator.auth.repository;

import com.eletroinfo.eletroinfo.comparator.auth.entitie.Menu;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Bruno Costa
 */

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query("SELECT m FROM Menu m WHERE UPPER(m.name) LIKE UPPER(CONCAT('%',:name,'%')) "
            + " AND m.menuTop.name LIKE CONCAT('%',:menuTopId,'%') "
            + " AND m.deleted is false ")
    PageImpl<Menu> findByParameters(@Param("name") String name, @Param("menuTopId") String menuTopId, Pageable pageable);

    Optional<Menu> findByIdAndDeletedIsFalse(Long id);

    List<Menu> findAllByMenuTopNullAndDeletedIsFalse();
}
