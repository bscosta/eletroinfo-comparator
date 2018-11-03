package com.eletroinfo.eletroinfo.comparator.repository.custom.impl;

import com.eletroinfo.eletroinfo.comparator.entitie.Budget;
import com.eletroinfo.eletroinfo.comparator.repository.custom.BudgetRepositoryCustom;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class BudgetRepositoryCustomImpl implements BudgetRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    public PageImpl<Budget> findByParameters(String productName, String providerName, String sellerName, Long barcode, Pageable pageable) {

        StringBuilder sql = new StringBuilder();
        StringBuilder where = new StringBuilder();

        sql.append("SELECT b FROM Budget b ");

        where.append(" WHERE b.deleted is false ");

        if (productName != null && !productName.isEmpty())
        where.append(" AND UPPER(b.product.name) LIKE UPPER('%" + productName + "%') ");

        if (providerName != null && !providerName.isEmpty())
        where.append(" AND UPPER(b.provider.name) LIKE UPPER('%" + providerName + "%') ");

        if (sellerName != null && !sellerName.isEmpty())
        where.append(" AND UPPER(b.seller.name) LIKE UPPER('%" + sellerName + "%') ");

        if (barcode != null && barcode > 0)
        where.append(" AND b.barcode = " + barcode );

        /**
         * Fazendo um count
         */
        StringBuilder sqlCountTotal = new StringBuilder();
        sqlCountTotal.append(" SELECT count(b) FROM Budget b ").append(where.toString());

        Long total = (Long) entityManager.createQuery(sqlCountTotal.toString()).getSingleResult();
        /**
         * End Count
         */

        /**
         * Finalizando query
         */
        where.append(" ORDER BY b.dateRegister desc");
        sql.append(where.toString());

        Query query = entityManager.createQuery(sql.toString(), Budget.class)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize());

        return new PageImpl<Budget>(query.getResultList(), pageable, (Long) total);
    }

}
