package com.eletroinfo.eletroinfo.comparator.auth.service;

import com.eletroinfo.eletroinfo.comparator.dto.MenuChildDto;
import com.eletroinfo.eletroinfo.comparator.auth.entitie.Menu;
import com.eletroinfo.eletroinfo.comparator.auth.filter.MenuFeatureFilter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author Bruno Costa
 */

public interface MenuService {

    /**
     *
     * @param menuFeatureFilter
     * @param pageable
     * @return
     */
    PageImpl<Menu> findByParameters(MenuFeatureFilter menuFeatureFilter, Pageable pageable);

    /**
     *
     * @return
     */
    List<Menu> findAllParentMenus();

    /**
     *
     * @param menu
     * @return
     */
    Menu save(Menu menu);

    /**
     *
     * @param id
     * @return
     */
    Optional<Menu> findById(Long id);

    /**
     *
     * @param id
     */
    void delete(Long id);

    /**
     *
     * @param menuChildDto
     * @return
     */
    Menu convertMenuDtoToMenuEntity(MenuChildDto menuChildDto);
}
