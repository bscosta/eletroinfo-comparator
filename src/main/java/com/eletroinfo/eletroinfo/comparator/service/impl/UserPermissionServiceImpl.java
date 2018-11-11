package com.eletroinfo.eletroinfo.comparator.service.impl;

import com.eletroinfo.eletroinfo.comparator.dto.PermissionDto;
import com.eletroinfo.eletroinfo.comparator.repository.custom.UserPermissionRepository;
import com.eletroinfo.eletroinfo.comparator.service.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPermissionServiceImpl implements UserPermissionService {

    @Autowired
    private UserPermissionRepository userPermissionRepository;

    public PermissionDto findByPermissionById(Long id) {
        return this.userPermissionRepository.findPermissionById(id);
    }
}
