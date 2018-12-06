package com.eletroinfo.eletroinfo.comparator.cptr.repository.custom.impl;

import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Provider;
import com.eletroinfo.eletroinfo.comparator.cptr.repository.custom.ProviderRepositoryCustom;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class ProviderRespositoryCustomImpl implements ProviderRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    public PageImpl<Provider> findByParameters(String name, String address, String contact, Pageable pageable) {
        StringBuilder sql = new StringBuilder();
        StringBuilder where = new StringBuilder();

        sql.append("SELECT p FROM Provider p ");

        where.append(" WHERE p.deleted is false ");

        if (!contact.isEmpty() || name.isEmpty())
            where.append(" AND p.id in ( SELECT pc.providerId FROM ProviderContact pc, Contact c WHERE pc.contactId = c.id" +
                    " AND UPPER(c.valueContact) LIKE UPPER(CONCAT('%" + contact + "%'))) ");

        if (contact.isEmpty() && name.isEmpty())
            where.append(" OR p.id not in (SELECT pc.providerId FROM ProviderContact pc, Contact c WHERE pc.contactId = c.id)");

        where.append(" AND p.deleted is false ");

        if (!name.isEmpty())
            where.append(" AND UPPER(p.name) LIKE UPPER(CONCAT('%" + name + "%')) ");

        /**
         * Fazendo um count
         */
        StringBuilder sqlCountTotal = new StringBuilder();
        sqlCountTotal.append("SELECT count(p) FROM Provider p ").append(where.toString());

        Long total = (Long) entityManager.createQuery(sqlCountTotal.toString()).getSingleResult();
        /**
         * End Count
         */

        /**
         * Finalizando query
         */
        where.append(" ORDER BY p.dateRegister desc");
        sql.append(where.toString());

        Query query = entityManager.createQuery(sql.toString(), Provider.class)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize());

        return new PageImpl<Provider>(query.getResultList(), pageable, (Long) total);
    }
}
