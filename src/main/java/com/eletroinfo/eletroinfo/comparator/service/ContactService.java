package com.eletroinfo.eletroinfo.comparator.service;

import com.eletroinfo.eletroinfo.comparator.entitie.Contact;

public interface ContactService {

    Contact save(Contact contact);

    void delete(Long id, String fkTable, Long fkId, String fkName);
}
