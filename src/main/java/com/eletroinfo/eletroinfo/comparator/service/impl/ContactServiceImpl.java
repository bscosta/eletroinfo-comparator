package com.eletroinfo.eletroinfo.comparator.service.impl;

import com.eletroinfo.eletroinfo.comparator.entitie.Contact;
import com.eletroinfo.eletroinfo.comparator.repository.ContactRepository;
import com.eletroinfo.eletroinfo.comparator.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }
}
