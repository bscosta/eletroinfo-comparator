package com.eletroinfo.eletroinfo.comparator.service.impl;

import com.eletroinfo.eletroinfo.comparator.entitie.Menu;
import com.eletroinfo.eletroinfo.comparator.filter.MenuFeatureFilter;
import com.eletroinfo.eletroinfo.comparator.repository.MenuRepository;
import com.eletroinfo.eletroinfo.comparator.repository.custom.MenuRepositoryCustom;
import com.eletroinfo.eletroinfo.comparator.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Bruno Costa
 */

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuRepositoryCustom menuRepositoryCustom;

    public PageImpl<Menu> findByParameters(MenuFeatureFilter menuFeatureFilter, Pageable pageable) {
        return this.menuRepositoryCustom.findByParameters(menuFeatureFilter.getName(), menuFeatureFilter.getMenuTop(), pageable);
    }

    public List<Menu> findAllParentMenus() {
        return this.menuRepository.findAllByMenuTopNullAndDeletedIsFalse();
    }

    public Menu save(Menu menu) {
        return this.menuRepository.save(menu);
    }

    public Optional<Menu> findById(Long id) {
        return this.menuRepository.findById(id);
    }
}
