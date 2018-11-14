package com.eletroinfo.eletroinfo.comparator.service.impl;

import com.eletroinfo.eletroinfo.comparator.dto.PermissionDto;
import com.eletroinfo.eletroinfo.comparator.entitie.Menu;
import com.eletroinfo.eletroinfo.comparator.entitie.User;
import com.eletroinfo.eletroinfo.comparator.entitie.UserMenu;
import com.eletroinfo.eletroinfo.comparator.repository.UserMenuRepository;
import com.eletroinfo.eletroinfo.comparator.service.MenuService;
import com.eletroinfo.eletroinfo.comparator.service.UserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Bruno Costa
 */

@Service
public class UserMenuServiceImpl implements UserMenuService {

    @Autowired
    private UserMenuRepository userMenuRepository;

    @Autowired
    private MenuService menuService;

    public Optional<UserMenu> findByUserId(Long id) {
        return this.userMenuRepository.findByUserIdAndDeletedIsFalse(id);
    }

    public PermissionDto saveUserAccess(PermissionDto permissionDto) {
        List<Long> childMenusIdsSavedFromUser = new ArrayList<>();
        Set<Long> parentMenusIds = new HashSet<>();
        for (Long id : permissionDto.getListConsultedsMenusChildIds()) {
            if (permissionDto.getListSavedIds().stream().filter(aLong -> aLong.equals(id)).count() == 0) {
                UserMenu userMenu = new UserMenu();
                userMenu.setUserId(new User());
                userMenu.setMenuId(new Menu());
                userMenu.getUserId().setId(permissionDto.getUserDto().getId());
                userMenu.getMenuId().setId(id);
                userMenu = this.userMenuRepository.save(userMenu);
                childMenusIdsSavedFromUser.add(userMenu.getMenuId().getId());
            }
        }

        for(Long id : permissionDto.getListSavedIds()) {
            if (permissionDto.getListConsultedsMenusChildIds().stream().filter(aLong -> aLong.equals(id)).count() == 0) {
                Menu menu = new Menu();
                menu.setId(id);
                this.userMenuRepository.removeByMenuId(menu);
            }
        }

        for (Long menuChildId : childMenusIdsSavedFromUser) {
            Optional<Menu> menu = this.menuService.findById(menuChildId);
            if (menu.isPresent()) {
                parentMenusIds.add(menu.get().getMenuTop().getId());
            }
        }

        /*for (Long parentMenuId : parentMenusIds) {
            UserMenu userMenu = new UserMenu();
            userMenu.setMenuId(new Menu());
            userMenu.setUserId(new User());
            userMenu.getMenuId().setId(parentMenuId);
            userMenu.getUserId().setId(permissionDto.getUserDto().getId());
            userMenu = this.userMenuRepository.save(userMenu);
        }*/

        return permissionDto;
    }
}
