package com.eletroinfo.eletroinfo.comparator.service.impl;

import com.eletroinfo.eletroinfo.comparator.entitie.Address;
import com.eletroinfo.eletroinfo.comparator.entitie.Contact;
import com.eletroinfo.eletroinfo.comparator.entitie.Provider;
import com.eletroinfo.eletroinfo.comparator.filter.ProviderFilter;
import com.eletroinfo.eletroinfo.comparator.repository.ProviderRepository;
import com.eletroinfo.eletroinfo.comparator.repository.custom.ProviderRepositoryCustom;
import com.eletroinfo.eletroinfo.comparator.service.AddressService;
import com.eletroinfo.eletroinfo.comparator.service.ContactService;
import com.eletroinfo.eletroinfo.comparator.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Bruno Costa
 */

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderRepositoryCustom providerRepositoryCustom;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ContactService contactService;

    public PageImpl<Provider> findByParameters(ProviderFilter providerFilter, Pageable pageable) {
        return this.providerRepositoryCustom.findByParameters(providerFilter.getName(), providerFilter.getAddress(), providerFilter.getContact(), pageable);
    }

    public boolean existsByNameAndDeletedFalse(String name) {
        return this.providerRepository.existsByNameAndDeletedFalse(name);
    }

    public Long countByProviderIdAndAddressAndDeletedIsFalse(Long id, String address) {
        return providerRepository.countByProviderIdAndAddressAndDeletedIsFalse(id, address);
    }

    public Long countByProviderIdAndContactValueAndDeletedIsFalse(Long id, String contactValue) {
        return providerRepository.countByProviderIdAndContactValueAndDeletedIsFalse(id, contactValue);
    }

    public Provider save(Provider provider) {
        for (Address address : provider.getAddresses()) {
            if (address.getId() == null) {
                address = addressService.save(address);
            }
        }
        for (Contact contact : provider.getContacts()) {
            if (contact.getId() == null) {
                contact = contactService.save(contact);
            }
        }
        return providerRepository.save(provider);
    }

    public Optional<Provider> findById(Long id) {
        return providerRepository.findByIdAndDeletedIsFalse(id);
    }
}
