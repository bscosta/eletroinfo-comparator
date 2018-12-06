package com.eletroinfo.eletroinfo.comparator.auth.repository.custom;

import com.eletroinfo.eletroinfo.comparator.dto.PermissionDto;

/**
 * @author Bruno Costa
 */

public interface UserPermissionRepository {

    PermissionDto findPermissionById(Long id, String login, Boolean isLogOn);
}
