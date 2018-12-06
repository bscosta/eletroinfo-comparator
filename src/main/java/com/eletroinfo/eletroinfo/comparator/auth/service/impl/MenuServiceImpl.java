package com.eletroinfo.eletroinfo.comparator.auth.service.impl;

import com.eletroinfo.eletroinfo.comparator.dto.MenuChildDto;
import com.eletroinfo.eletroinfo.comparator.auth.entitie.Menu;
import com.eletroinfo.eletroinfo.comparator.auth.filter.MenuFeatureFilter;
import com.eletroinfo.eletroinfo.comparator.auth.repository.MenuRepository;
import com.eletroinfo.eletroinfo.comparator.auth.repository.custom.MenuRepositoryCustom;
import com.eletroinfo.eletroinfo.comparator.auth.service.MenuService;
import org.modelmapper.ModelMapper;
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
        return this.menuRepository.findByIdAndDeletedIsFalse(id);
    }

    public void delete(Long id) {
        Optional<Menu> menu = this.menuRepository.findById(id);
        if (menu.isPresent()) {
            menu.get().setDeleted(true);
            menuRepository.save(menu.get());
        }
    }

    public Menu convertMenuDtoToMenuEntity(MenuChildDto menuChildDto) {
        return new ModelMapper().map(menuChildDto, Menu.class);
    }
}
