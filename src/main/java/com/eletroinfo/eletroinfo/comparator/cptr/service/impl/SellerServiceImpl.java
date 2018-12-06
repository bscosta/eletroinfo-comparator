package com.eletroinfo.eletroinfo.comparator.cptr.service.impl;

import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Contact;
import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Seller;
import com.eletroinfo.eletroinfo.comparator.cptr.filter.SellerFilter;
import com.eletroinfo.eletroinfo.comparator.cptr.repository.SellerRepository;
import com.eletroinfo.eletroinfo.comparator.cptr.repository.custom.SellerRepositoryCustom;
import com.eletroinfo.eletroinfo.comparator.cptr.service.ContactService;
import com.eletroinfo.eletroinfo.comparator.cptr.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Long countBySellerIdAndContactValueAndDeletedIsFalse(Long id, String contactValue) {
        return sellerRepository.countBySellerIdAndContactValueAndDeletedIsFalse(id, contactValue);
    }

    public Seller save(Seller seller) {
        for (Contact contact : seller.getContacts()) {
            if (contact.getId() == null) {
                contact = contactService.save(contact);
            }
        }
        return sellerRepository.save(seller);
    }

    public Optional<Seller> findById(Long id) {
        return sellerRepository.findByIdAndDeletedIsFalse(id);
    }

    public List<Seller> findAll() {
        return this.sellerRepository.findAll();
    }

    public void delete(Long id) {
        Optional<Seller> seller = sellerRepository.findById(id);
        if (seller.isPresent()) {
            if (seller.get().getContacts() != null || !seller.get().getContacts().isEmpty()) {
                for (Contact contact : seller.get().getContacts()) {
                    contact.setDeleted(true);
                    contactService.save(contact);
                }
            }
            seller.get().setDeleted(true);
            sellerRepository.save(seller.get());
        }
    }
}
