package com.eletroinfo.eletroinfo.comparator.cptr.repository;

import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bruno Costa
 */

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
