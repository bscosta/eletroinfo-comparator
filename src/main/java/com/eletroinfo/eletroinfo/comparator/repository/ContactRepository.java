package com.eletroinfo.eletroinfo.comparator.repository;

import com.eletroinfo.eletroinfo.comparator.entitie.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
