package com.eletroinfo.eletroinfo.comparator.repository;

import com.eletroinfo.eletroinfo.comparator.entitie.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
