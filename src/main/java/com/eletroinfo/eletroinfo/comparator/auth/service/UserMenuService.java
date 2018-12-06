package com.eletroinfo.eletroinfo.comparator.auth.service;

import com.eletroinfo.eletroinfo.comparator.dto.PermissionDto;
import com.eletroinfo.eletroinfo.comparator.auth.entitie.UserMenu;

import java.util.Optional;

public interface UserMenuService {

    Optional<UserMenu> findByUserId(Long id);

    PermissionDto saveUserAccess(PermissionDto permissionDto);

    PermissionDto saveUserPermission(PermissionDto permissionDto);
}
