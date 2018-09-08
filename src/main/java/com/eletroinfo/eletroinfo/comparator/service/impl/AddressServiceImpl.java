package com.eletroinfo.eletroinfo.comparator.service.impl;

import com.eletroinfo.eletroinfo.comparator.entitie.Address;
import com.eletroinfo.eletroinfo.comparator.repository.AddressRepository;
import com.eletroinfo.eletroinfo.comparator.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Bruno Costa
 */

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address save(Address address) {
        return this.addressRepository.save(address);
    }

    public void delete(Long id, String fkTable, Long fkId, String fkName) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isPresent()) {
            String addressDeleted = address.get().getAddress() + " | fkTable: " + fkTable + " - fkId: " + fkId + " - fkName: " + fkName;
            address.get().setAddress(addressDeleted);
            address.get().setDeleted(true);
            addressRepository.save(address.get());
        }
    }
}
