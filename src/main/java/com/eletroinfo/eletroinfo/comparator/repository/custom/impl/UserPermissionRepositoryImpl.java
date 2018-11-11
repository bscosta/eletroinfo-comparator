package com.eletroinfo.eletroinfo.comparator.repository.custom.impl;

import com.eletroinfo.eletroinfo.comparator.dto.*;
import com.eletroinfo.eletroinfo.comparator.repository.custom.UserPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Bruno Costa
 */

@Repository
public class UserPermissionRepositoryImpl implements UserPermissionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private List<ParentMenuDto> parentMenuDtosFinal = new ArrayList<>();
    private Set<ParentMenuDto> parentMenuDtoList = new HashSet<>();
    private Map<Long, Set<MenuChildDto>> mapMenuChild = new HashMap<>();
    private Map<Long, Set<FeatureDto>> mapFeatures = new HashMap<>();

    @Transactional(readOnly = true)
    public PermissionDto findPermissionById(Long id) {

        parentMenuDtosFinal = new ArrayList<>();
        parentMenuDtoList = new HashSet<>();
        mapMenuChild = new HashMap<>();
        mapFeatures = new HashMap<>();

        PermissionDto permissionDto = new PermissionDto();
        UserDto userDto = findUserById(id);

        if (userDto.getId() != null) {
            PermissionDto permissionUser = null;
            PermissionDto permissionGroupUser = null;
            if (userDto.getUserType() <= 1) {
                permissionUser = findAllUserPermisson();
                permissionUser.setUserDto(userDto);
                return permissionUser;
            }

        }

        return null;
    }

    @Transactional(readOnly = true)
    public UserDto findUserById(Long id) {
        UserDto userDtoResult = new UserDto();
        StringBuilder query = new StringBuilder();

        /**
         * Busca menus e features do usuario
         */
        query.append(" select u.id, u.name, u.email, u.login, u.user_type ");
        query.append(" from user u ");
        query.append(" where u.id = " + id);
        query.append(" and u.deleted = false");

        jdbcTemplate.query(query.toString(), new RowMapper<UserDto>() {
            public UserDto mapRow(ResultSet rs, int i) throws SQLException {
                try {
                    if (rs.getObject(1) != null) {
                        userDtoResult.setId(rs.getLong(1));
                        userDtoResult.setName(rs.getString(2));
                        userDtoResult.setEmail(rs.getString(3));
                        userDtoResult.setLogin(rs.getString(4));
                        userDtoResult.setUserType(rs.getLong(5));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return userDtoResult;
            }
        });

        return userDtoResult;
    }



    @Transactional(readOnly = true)
    public PermissionDto findPermissonUserByUserId(Long id) {
        PermissionDto permissionDto = new PermissionDto();

        CopyOnWriteArrayList<ParentMenuDto> userparentMenuDtoList = new CopyOnWriteArrayList<>();
        Map<Long, List<MenuChildDto>> mapUserMenuChild = new HashMap<>();

        StringBuilder query = new StringBuilder();

        query.append(" select m.id, m.name, m.url, m.menu_top_id, m.icon, m.tag, m.internal_menu, , m.internationalization_code ");
        query.append(" from menu m ");
        query.append(" inner join user_menu um on um.menu_id = m.id ");
        query.append(" where m.deleted = false ");
        query.append(" and um.deleted = false ");
        query.append(" and um.user_id = " + id);

        jdbcTemplate.query(query.toString(), new RowMapper<PermissionDto>() {
            @Override
            public PermissionDto mapRow(ResultSet rs, int i) throws SQLException {
                PermissionDto permissionResult = new PermissionDto();
                List<MenuChildDto> menuChildDtoList = new ArrayList<>();
                long menuTopId = 0;
                if (rs.getObject(4) != null) {
                    menuTopId = rs.getLong(4);
                }
                if (menuTopId == 0) {
                    ParentMenuDto parentMenuDto = new ParentMenuDto(rs.getLong(1), rs.getString(2), rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getString(8));
                    userparentMenuDtoList.add(parentMenuDto);
                }

                if (menuTopId != 0) {
                    MenuChildDto menuChildDto = new MenuChildDto(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getLong(4), rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getString(8));

                    if (rs.getLong(1) > 0) {
                        List<FeatureDto> featureDtoList = getFeatures(rs.getLong(1), 0, 0);
                        menuChildDto.getFeatureDtoList().addAll(featureDtoList);
                    }

                    if (mapUserMenuChild.get(rs.getLong(4)) != null && !mapUserMenuChild.get(rs.getLong(4)).contains(menuChildDto)) {
                        menuChildDtoList.clear();
                        menuChildDtoList.addAll(mapUserMenuChild.get(rs.getLong(4)));
                        menuChildDtoList.add(menuChildDto);
                    }

                    if (mapUserMenuChild.get(rs.getLong(4)) == null) {
                        menuChildDtoList.add(menuChildDto);
                    }

                    mapUserMenuChild.put(rs.getLong(4), menuChildDtoList);
                }

                return permissionResult;
            };
        });

        //ordena os menus pais
        orderMenuFather(userparentMenuDtoList);

        for (ParentMenuDto parentMenuDto : userparentMenuDtoList) {
            List<MenuChildDto> mapMenuChildDtoList = mapUserMenuChild.get(parentMenuDto.getId());
            if (mapMenuChildDtoList != null && !mapMenuChildDtoList.isEmpty()) {

                //ordena os menus filhos
                orderMenuChild(mapMenuChildDtoList);

                parentMenuDto.setMenuChildDtoList(mapMenuChildDtoList);
            }
        }

        permissionDto.setParentMenusDto(userparentMenuDtoList);

        return permissionDto;
    }

    @Transactional(readOnly = true)
    public PermissionDto findAllUserPermisson() {
        PermissionDto permissionDto = new PermissionDto();

        CopyOnWriteArrayList<ParentMenuDto> parentMenuDtoList = new CopyOnWriteArrayList<>();
        Map<Long, List<MenuChildDto>> mapMenuChild = new HashMap<>();

        StringBuilder query = new StringBuilder();

        query.append(" select m.id, m.name, m.url, m.menu_top_id, m.icon, m.tag, m.internal_menu, m.internationalization_code ");
        query.append(" from menu m ");
        query.append(" where m.deleted = false ");

        jdbcTemplate.query(query.toString(), new RowMapper<PermissionDto>() {
            @Override
            public PermissionDto mapRow(ResultSet rs, int i) throws SQLException {
                PermissionDto permissionResult = new PermissionDto();
                List<MenuChildDto> menuChildDtoList = new ArrayList<>();
                long menuTopId = 0;
                if (rs.getObject(4) != null) {
                    menuTopId = rs.getLong(4);
                }
                if (menuTopId == 0) {
                    ParentMenuDto parentMenuDto = new ParentMenuDto(rs.getLong(1), rs.getString(2), rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getString(8));
                    parentMenuDtoList.add(parentMenuDto);
                }

                if (menuTopId != 0) {
                    MenuChildDto menuChildDto = new MenuChildDto(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getLong(4), rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getString(8));

                    if (rs.getLong(1) > 0) {
                        List<FeatureDto> featureDtoList = getFeatures(rs.getLong(1), 0, 0);
                        menuChildDto.getFeatureDtoList().addAll(featureDtoList);
                    }

                    if (mapMenuChild.get(rs.getLong(4)) != null && !mapMenuChild.get(rs.getLong(4)).contains(menuChildDto)) {
                        menuChildDtoList.clear();
                        menuChildDtoList.addAll(mapMenuChild.get(rs.getLong(4)));
                        menuChildDtoList.add(menuChildDto);
                    }

                    if (mapMenuChild.get(rs.getLong(4)) == null) {
                        menuChildDtoList.add(menuChildDto);
                    }

                    mapMenuChild.put(rs.getLong(4), menuChildDtoList);
                }

                return permissionResult;
            };
        });

        //ordena os menus pais
        orderMenuFather(parentMenuDtoList);

        for (ParentMenuDto parentMenuDto : parentMenuDtoList) {
            List<MenuChildDto> mapMenuChildDtoList = mapMenuChild.get(parentMenuDto.getId());
            if (mapMenuChildDtoList != null && !mapMenuChildDtoList.isEmpty()) {

                //ordena os menus filhos
                orderMenuChild(mapMenuChildDtoList);

                parentMenuDto.setMenuChildDtoList(mapMenuChildDtoList);
            }
        }

        permissionDto.setParentMenusDto(parentMenuDtoList);

        return permissionDto;
    }

    public void orderMenuFather(List<ParentMenuDto> parentMenuDtoList) {
        parentMenuDtoList.sort((m1, m2) -> m1.getName().compareTo(m2.getName()));
    }

    public void orderMenuChild(List<MenuChildDto> menuChildDtoList) {
        menuChildDtoList.sort((m1, m2) -> m1.getName().compareTo(m2.getName()));
    }

    public List<FeatureDto> getFeatures(long menuId, long userId, long groupUserId) {

        StringBuilder query = new StringBuilder();

        /**
         * Busca menus e features do usuario
         */
        query.append(" select f.id, f.name, f.code ");
        query.append(" from feature f ");


        if(userId > 0){
            query.append("inner join user_menu_feature umf on umf.feature_id = f.id");
            query.append("inner join user_menu um on um.id = umf.user_menu_id");
            query.append("where um.user_id = " +  userId);
        }

        if(groupUserId > 0){
            query.append("inner join group_user_menu_feature gumf on gumf.feature_id = f.id");
            query.append("inner join group_user_menu gum on gum.id = gumf.group_user_menu_id");
            query.append("where gum.group_user_id = " +  groupUserId);
        }

        if (menuId > 0) {
            query.append(" inner join menu_feature mf on mf.feature_id = f.id");
            query.append(" inner join menu m on m.id = mf.menu_id");
            query.append(" where m.id = " + menuId);
        }

        query.append(" and f.deleted = false ");

        List<FeatureDto> featureDtoListResult = jdbcTemplate.query(query.toString(), new RowMapper<FeatureDto>() {
            @Override
            public FeatureDto mapRow(ResultSet rs, int i) throws SQLException {
                FeatureDto featureDto = new FeatureDto();
                try {
                    if (rs.getObject(1) != null) {
                        featureDto.setId(rs.getLong(1));
                        featureDto.setName(rs.getString(2));
                        featureDto.setCode(rs.getString(3));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return featureDto;
            }
        });

        return featureDtoListResult;
    }
}
