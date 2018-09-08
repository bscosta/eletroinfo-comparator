package com.eletroinfo.eletroinfo.comparator.service.impl;

import com.eletroinfo.eletroinfo.comparator.entitie.Contact;
import com.eletroinfo.eletroinfo.comparator.repository.ContactRepository;
import com.eletroinfo.eletroinfo.comparator.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    public void delete(Long id, String fkTable, Long fkId, String fkName) {
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isPresent()) {
            String contactDeleted = contact.get().getName() + " | fkTable: " + fkTable + " - fkId: " + fkId + " - fkName: " + fkName;
            contact.get().setName(contactDeleted);
            contact.get().setDeleted(true);
            contactRepository.save(contact.get());
        }
    }
}
