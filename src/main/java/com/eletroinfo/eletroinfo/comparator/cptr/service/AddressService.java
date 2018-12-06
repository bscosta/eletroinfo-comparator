package com.eletroinfo.eletroinfo.comparator.cptr.service;

import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Address;

/**
 * @author Bruno Costa
 */

public interface AddressService {

    /**
     *
     * @param address
     * @return
     */
    Address save(Address address);

    /**
     *
     * @param id
     * @param fkTable
     * @param fkId
     * @param fkName
     */
    void delete(Long id, String fkTable, Long fkId, String fkName);
}
