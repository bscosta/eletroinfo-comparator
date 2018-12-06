package com.eletroinfo.eletroinfo.comparator.auth.repository;

import com.eletroinfo.eletroinfo.comparator.auth.entitie.GroupUser;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Bruno Costa
 */

@Repository
public interface GroupUserRepository extends JpaRepository<GroupUser, Long> {

    @Query("SELECT gu FROM GroupUser gu WHERE UPPER(gu.name) LIKE UPPER(CONCAT('%',:name,'%')) "
            + " AND UPPER(gu.description) LIKE UPPER(CONCAT('%',:description,'%')) "
            + " AND deleted is false ")
    PageImpl<GroupUser> findByParameters(@Param("name") String name, @Param("description") String description, Pageable pageable);
}
