package com.eletroinfo.eletroinfo.comparator.service;

import com.eletroinfo.eletroinfo.comparator.dto.PermissionDto;

/**
 * @author Bruno Costa
 */

public interface UserPermissionService {

    PermissionDto findByPermissionById(Long id);

    PermissionDto findByPermissionForLogonByLogin(String login);
}
