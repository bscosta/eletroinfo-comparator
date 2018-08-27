package com.eletroinfo.eletroinfo.comparator.service.impl;

import com.eletroinfo.eletroinfo.comparator.entitie.Contact;
import com.eletroinfo.eletroinfo.comparator.entitie.Seller;
import com.eletroinfo.eletroinfo.comparator.filter.SellerFilter;
import com.eletroinfo.eletroinfo.comparator.repository.SellerRepository;
import com.eletroinfo.eletroinfo.comparator.repository.custom.SellerRepositoryCustom;
import com.eletroinfo.eletroinfo.comparator.service.ContactService;
import com.eletroinfo.eletroinfo.comparator.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Bruno Costa
 */

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private SellerRepositoryCustom sellerRepositoryCustom;

    @Autowired
    ContactService contactService;

    public PageImpl<Seller> findByParameters(SellerFilter sellerFilter, Pageable pageable) {
        return sellerRepositoryCustom.findByParameters(sellerFilter.getName(), sellerFilter.getContact(), pageable);
    }

    public Seller save(Seller seller) {
        seller.getContacts().removeIf(contact -> contact.getValueContact() == null || contact.getValueContact().isEmpty());
        for (Contact contact : seller.getContacts()) {
            if (contact.getId() == null) {
                contact = contactService.save(contact);
            }
        }
        return sellerRepository.save(seller);
    }

    public Optional<Seller> findById(Long id) {
        return sellerRepository.findById(id);
    }
}
