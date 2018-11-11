package com.eletroinfo.eletroinfo.comparator.repository.custom.impl;

import com.eletroinfo.eletroinfo.comparator.entitie.Menu;
import com.eletroinfo.eletroinfo.comparator.repository.custom.MenuRepositoryCustom;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @Bruno Costa
 */

@Repository
public class MenuRepositoryCustomImpl implements MenuRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    public PageImpl<Menu> findByParameters(String name, String menuTopName, Pageable pageable) {
        StringBuilder sql = new StringBuilder();
        StringBuilder where = new StringBuilder();

        sql.append("SELECT m FROM Menu m ");

        where.append(" WHERE m.deleted is false ");

        if (!name.isEmpty())
            where.append(" AND UPPER(m.name) LIKE UPPER(CONCAT('%" + name + "%')) ");

        if (!menuTopName.isEmpty())
            where.append(" AND UPPER(m.menuTop.name) LIKE UPPER(CONCAT('%" + menuTopName + "%')) ");

        /**
         * Fazendo um count
         */
        StringBuilder sqlCountTotal = new StringBuilder();
        sqlCountTotal.append("SELECT count(m) FROM Menu m ").append(where.toString());

        Long total = (Long) entityManager.createQuery(sqlCountTotal.toString()).getSingleResult();
        /**
         * End Count
         */

        /**
         * Finalizando query
         */
        where.append(" ORDER BY m.dateRegister desc");
        sql.append(where.toString());

        Query query = entityManager.createQuery(sql.toString(), Menu.class)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize());

        return new PageImpl<Menu>(query.getResultList(), pageable, (Long) total);
    }
}
