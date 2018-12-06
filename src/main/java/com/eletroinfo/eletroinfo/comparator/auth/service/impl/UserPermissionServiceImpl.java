package com.eletroinfo.eletroinfo.comparator.auth.service.impl;

import com.eletroinfo.eletroinfo.comparator.dto.PermissionDto;
import com.eletroinfo.eletroinfo.comparator.auth.repository.custom.UserPermissionRepository;
import com.eletroinfo.eletroinfo.comparator.auth.service.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPermissionServiceImpl implements UserPermissionService {

    @Autowired
    private UserPermissionRepository userPermissionRepository;

    public PermissionDto findByPermissionById(Long id) {
        return this.userPermissionRepository.findPermissionById(id,null, false);
    }

    public PermissionDto findByPermissionForLogonByLogin(String login) {
        return this.userPermissionRepository.findPermissionById(null, login, true);
    }
}
