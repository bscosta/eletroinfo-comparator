package com.eletroinfo.eletroinfo.comparator.cptr.repository.custom;

import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Provider;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * @author Bruno Costa
 */

public interface ProviderRepositoryCustom {

    /**
     *
     * @param name
     * @param address
     * @param contact
     * @param pageable
     * @return
     */
    PageImpl<Provider> findByParameters(String name, String address, String contact, Pageable pageable);

}