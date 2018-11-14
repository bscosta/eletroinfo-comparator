package com.eletroinfo.eletroinfo.comparator.service;

import com.eletroinfo.eletroinfo.comparator.dto.PermissionDto;
import com.eletroinfo.eletroinfo.comparator.entitie.UserMenu;

import java.util.Optional;

public interface UserMenuService {

    Optional<UserMenu> findByUserId(Long id);

    PermissionDto saveUserAccess(PermissionDto permissionDto);
}
