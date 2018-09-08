package com.eletroinfo.eletroinfo.comparator.repository;

import com.eletroinfo.eletroinfo.comparator.entitie.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sun.rmi.runtime.Log;

import java.util.Optional;

/**
 * @author Bruno Costa
 */

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

    @Transactional(readOnly = true)
    boolean existsByNameAndDeletedFalse(String name);

    @Transactional(readOnly = true)
    @Query(" SELECT count(p) FROM Provider p WHERE "
            + " p.id IN (SELECT pa.providerId FROM ProviderAddress pa, Address a "
            +  " WHERE pa.addressId = a.id AND a.address = :address )"
            +  " AND p.id = :id "
            +  " AND p.deleted is false ")
    Long countByProviderIdAndAddressAndDeletedIsFalse(@Param("id") Long id, @Param("address") String address);

    @Transactional(readOnly = true)
    @Query(" SELECT count(p) FROM Provider p WHERE "
            + " p.id IN (SELECT pc.providerId FROM ProviderContact pc, Contact c "
            +  " WHERE pc.contactId = c.id AND c.valueContact = :contactValue )"
            +  " AND p.id = :id "
            +  " AND p.deleted is false ")
    Long countByProviderIdAndContactValueAndDeletedIsFalse(@Param("id") Long id, @Param("contactValue") String contactValue);

    @Transactional(readOnly = true)
    Optional<Provider> findByIdAndDeletedIsFalse(Long id);
}
