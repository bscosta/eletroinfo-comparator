package com.eletroinfo.eletroinfo.comparator.cptr.service;

import com.eletroinfo.eletroinfo.comparator.cptr.entitie.Contact;

public interface ContactService {

    Contact save(Contact contact);

    void delete(Long id, String fkTable, Long fkId, String fkName);
}
