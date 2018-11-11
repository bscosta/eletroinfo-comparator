package com.eletroinfo.eletroinfo.comparator.service;

import com.eletroinfo.eletroinfo.comparator.entitie.Menu;
import com.eletroinfo.eletroinfo.comparator.filter.MenuFeatureFilter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

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

    Optional<Menu> findById(Long id);
}
