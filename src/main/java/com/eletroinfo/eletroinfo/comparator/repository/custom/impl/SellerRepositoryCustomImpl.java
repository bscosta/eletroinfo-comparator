package com.eletroinfo.eletroinfo.comparator.repository.custom.impl;

import com.eletroinfo.eletroinfo.comparator.entitie.Seller;
import com.eletroinfo.eletroinfo.comparator.repository.custom.SellerRepositoryCustom;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Bruno Costa
 */

@Repository
public class SellerRepositoryCustomImpl implements SellerRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    public PageImpl<Seller> findByParameters(String name, String contact, Pageable pageable) {
        StringBuilder sql = new StringBuilder();
        StringBuilder where = new StringBuilder();

        sql.append("SELECT s FROM Seller s ");

        where.append(" WHERE s.deleted is false ");

        if (!contact.isEmpty() || name.isEmpty())
        where.append(" AND s.id IN (SELECT sc.sellerId FROM SellerContact sc, Contact c WHERE sc.contactId = c.id " +
                     "AND UPPER(c.valueContact) LIKE UPPER(CONCAT('%" + contact + "%'))) ");

        if (contact.isEmpty() && name.isEmpty())
        where.append(" OR s.id not in (SELECT sc.sellerId FROM SellerContact sc, Contact c " +
                     "WHERE sc.contactId = c.id)");

        if (!name.isEmpty())
        where.append(" AND UPPER(s.name) LIKE UPPER(CONCAT('%" + name + "%'))");

        /**
         * Fazendo um count
         */
        StringBuilder sqlCountTotal = new StringBuilder();
        sqlCountTotal.append("SELECT count(s) FROM Seller s").append(where.toString());

        Long total = (Long) entityManager.createQuery(sqlCountTotal.toString()).getSingleResult();
        /**
         * End Count
         */

        /**
         * Finalizando query
         */
        where.append(" ORDER BY s.dateRegister desc");
        sql.append(where.toString());

        Query query = entityManager.createQuery(sql.toString(), Seller.class)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize());

        return new PageImpl<Seller>(query.getResultList(), pageable, (Long) total);

    }
}
