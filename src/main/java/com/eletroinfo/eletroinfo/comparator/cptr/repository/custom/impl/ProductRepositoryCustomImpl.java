package com.eletroinfo.eletroinfo.comparator.cptr.repository.custom.impl;

import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Product;
import com.eletroinfo.eletroinfo.comparator.cptr.repository.custom.ProductRepositoryCustom;
import com.eletroinfo.eletroinfo.comparator.security.UserLogged;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Bruno Costa
 */

@Repository
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public PageImpl<Product> findByParameters(String name, String barcode, Pageable pageable) {
        UserLogged userLogged = (UserLogged) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        StringBuilder sql = new StringBuilder();
        StringBuilder where = new StringBuilder();

        sql.append("SELECT p FROM Product p ");

        where.append(" WHERE p.deleted is false ");

        if (name != null && !name.isEmpty())
            where.append(" AND UPPER(p.name) LIKE UPPER('%" + name+ "%') ");

        if (!barcode.isEmpty())
            where.append(" AND p.barcode LIKE '%" + barcode + "%' " );

        /*if (userLogged.getPermissionDto().getUserDto().getUserType() > 3)
            where.append(" AND  b.seller.user.id = " + userLogged.getPermissionDto().getUserDto().getId());*/

        /**
         * Fazendo um count
         */
        StringBuilder sqlCountTotal = new StringBuilder();
        sqlCountTotal.append(" SELECT count(p) FROM Product p ").append(where.toString());

        Long total = (Long) entityManager.createQuery(sqlCountTotal.toString()).getSingleResult();
        /**
         * End Count
         */

        /**
         * Finalizando query
         */
        //where.append(" ORDER BY p.dateRegister desc");
        sql.append(where.toString());

        Query query = entityManager.createQuery(sql.toString(), Product.class)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize());

        return new PageImpl<Product>(query.getResultList(), pageable, (Long) total);
    }
}
