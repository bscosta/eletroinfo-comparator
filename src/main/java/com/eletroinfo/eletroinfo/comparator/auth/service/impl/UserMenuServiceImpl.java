package com.eletroinfo.eletroinfo.comparator.auth.service.impl;

import com.eletroinfo.eletroinfo.comparator.dto.PermissionDto;
import com.eletroinfo.eletroinfo.comparator.auth.entitie.Feature;
import com.eletroinfo.eletroinfo.comparator.auth.entitie.Menu;
import com.eletroinfo.eletroinfo.comparator.auth.entitie.User;
import com.eletroinfo.eletroinfo.comparator.auth.entitie.UserMenu;
import com.eletroinfo.eletroinfo.comparator.auth.repository.UserMenuRepository;
import com.eletroinfo.eletroinfo.comparator.auth.service.MenuService;
import com.eletroinfo.eletroinfo.comparator.auth.service.UserMenuService;
import com.eletroinfo.eletroinfo.comparator.auth.service.UserPermissionService;
import com.eletroinfo.eletroinfo.comparator.auth.service.UserService;
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

    @Autowired
    private UserService userService;

    @Autowired
    private UserPermissionService userPermissionService;

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

        return permissionDto;
    }

    public PermissionDto saveUserPermission(PermissionDto permissionDto) {
        List<UserMenu> userMenuList = new ArrayList<>();

        for (String menuFeature : permissionDto.getListConsultedMenusFeaturesIds()) {
            if (permissionDto.getListSavedMenusFeaturesIds().stream().filter(mf -> mf.equals(menuFeature)).count() == 0) {
                String[] splitMenuFeature = menuFeature.split("[.]");
                Long menuId = Long.valueOf(splitMenuFeature[0]);
                Long featureId = Long.valueOf(splitMenuFeature[1]);
                Feature feature = new Feature();
                feature.setId(featureId);
                if (userMenuList.stream().filter(userMenu -> userMenu.getMenuId().getId().equals(menuId)).count() == 0) {
                    Optional<UserMenu> userMenu = this.findByUserIdAndMenuId(permissionDto.getUserDto().getId(), menuId);
                    if (userMenu.isPresent()) {
                        userMenu.get().getFeatures().add(feature);
                        userMenuList.add(userMenu.get());
                    }
                } else {
                    for (UserMenu userMenu : userMenuList) {
                        if (userMenu.getMenuId().getId().equals(menuId)) {
                            userMenu.getFeatures().add(feature);
                        }
                    }
                }

            }
        }

        for (String menuFeature : permissionDto.getListSavedMenusFeaturesIds()) {
            if (permissionDto.getListConsultedMenusFeaturesIds().stream().filter(mf -> mf.equals(menuFeature)).count() == 0) {
                String[] splitMenuFeature = menuFeature.split("[.]");
                Long menuId = Long.valueOf(splitMenuFeature[0]);
                Long featureId = Long.valueOf(splitMenuFeature[1]);
                if (userMenuList.stream().filter(userMenu -> userMenu.getMenuId().getId().equals(menuId)).count() == 0) {
                    Optional<UserMenu> userMenu = this.findByUserIdAndMenuId(permissionDto.getUserDto().getId(), menuId);
                    if (userMenu.isPresent()) {
                        userMenu.get().getFeatures().removeIf(feature -> feature.getId().equals(featureId));
                        userMenuList.add(userMenu.get());
                    }
                } else {
                    for (UserMenu userMenu : userMenuList) {
                        if (userMenu.getMenuId().getId().equals(menuId)) {
                            userMenu.getFeatures().removeIf(feature -> feature.getId().equals(featureId));
                        }
                    }
                }

            }
        }

        for (UserMenu userMenu : userMenuList) {
            this.userMenuRepository.save(userMenu);
        }

        return permissionDto;
    }

    private Optional<UserMenu> findByUserIdAndMenuId(Long userId, Long menuId) {
        return this.userMenuRepository.findByUserIdAndMenuIdAndDeletedIsFalse(userId, menuId);
    }
}
