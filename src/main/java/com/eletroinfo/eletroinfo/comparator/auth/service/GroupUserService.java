package com.eletroinfo.eletroinfo.comparator.auth.service;

import com.eletroinfo.eletroinfo.comparator.auth.entitie.GroupUser;
import com.eletroinfo.eletroinfo.comparator.auth.filter.GroupUserFilter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * @author Bruno Costa
 */

public interface GroupUserService {

    /**
     *
     * @param groupUser
     * @return
     */
    GroupUser save(GroupUser groupUser);

    /**
     *
     * @param groupUserFilter
     * @param pageable
     * @return
     */
    PageImpl<GroupUser> findByParameters(GroupUserFilter groupUserFilter, Pageable pageable);

    /**
     *
     * @param id
     * @return
     */
    Optional<GroupUser> findById(Long id);
}
