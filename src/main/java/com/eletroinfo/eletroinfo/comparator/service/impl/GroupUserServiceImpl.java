package com.eletroinfo.eletroinfo.comparator.service.impl;

import com.eletroinfo.eletroinfo.comparator.entitie.GroupUser;
import com.eletroinfo.eletroinfo.comparator.filter.GroupUserFilter;
import com.eletroinfo.eletroinfo.comparator.repository.GroupUserRepository;
import com.eletroinfo.eletroinfo.comparator.service.GroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Bruno Costa
 */

@Service
public class GroupUserServiceImpl implements GroupUserService {

    @Autowired
    private GroupUserRepository groupUserRepository;

    public GroupUser save(GroupUser groupUser) {
        return groupUserRepository.save(groupUser);
    }

    public PageImpl<GroupUser> findByParameters(GroupUserFilter groupUserFilter, Pageable pageable) {
        return this.groupUserRepository.findByParameters(groupUserFilter.getName(), groupUserFilter.getDescription(), pageable);
    }

    @Transactional
    public Optional<GroupUser> findById(Long id) {
        return groupUserRepository.findById(id);
    }
}
