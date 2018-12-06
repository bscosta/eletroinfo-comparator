package com.eletroinfo.eletroinfo.comparator.auth.service;

import com.eletroinfo.eletroinfo.comparator.dto.PermissionDto;

/**
 * @author Bruno Costa
 */

public interface UserPermissionService {

    PermissionDto findByPermissionById(Long id);

    PermissionDto findByPermissionForLogonByLogin(String login);
}
