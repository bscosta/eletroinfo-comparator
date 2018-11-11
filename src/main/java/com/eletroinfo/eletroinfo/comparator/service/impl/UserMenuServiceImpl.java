package com.eletroinfo.eletroinfo.comparator.service.impl;

import com.eletroinfo.eletroinfo.comparator.entitie.UserMenu;
import com.eletroinfo.eletroinfo.comparator.repository.UserMenuRepository;
import com.eletroinfo.eletroinfo.comparator.service.UserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Bruno Costa
 */

@Service
public class UserMenuServiceImpl implements UserMenuService {

    @Autowired
    private UserMenuRepository userMenuRepository;

    public Optional<UserMenu> findByUserId(Long id) {
        return this.userMenuRepository.findByUserIdAndDeletedIsFalse(id);
    }
}
