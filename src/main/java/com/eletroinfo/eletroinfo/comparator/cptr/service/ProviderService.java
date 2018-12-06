package com.eletroinfo.eletroinfo.comparator.cptr.service;

import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Provider;
import com.eletroinfo.eletroinfo.comparator.cptr.filter.ProviderFilter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author Bruno Costa
 */

public interface ProviderService {

    /**
     *
     * @param providerFilter
     * @param pageable
     * @return
     */
    PageImpl<Provider> findByParameters(ProviderFilter providerFilter, Pageable pageable);

    /**
     *
     * @param name
     * @return
     */
    boolean existsByNameAndDeletedFalse(String name);

    /**
     *
     * @param id
     * @param address
     * @return
     */
    Long countByProviderIdAndAddressAndDeletedIsFalse(Long id, String address);

    /**
     *
     * @param id
     * @param contactValue
     * @return
     */
    Long countByProviderIdAndContactValueAndDeletedIsFalse(Long id, String contactValue);

    /**
     *
     * @param provider
     * @return
     */
    Provider save(Provider provider);

    /**
     *
     * @param id
     * @return
     */
    Optional<Provider> findById(Long id);

    /**
     *
     * @return
     */
    List<Provider> findAll();

    /**
     *
     * @param id
     */
    void delete(Long id);
}