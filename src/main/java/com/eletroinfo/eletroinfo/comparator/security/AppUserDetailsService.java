package com.eletroinfo.eletroinfo.comparator.security;

import com.eletroinfo.eletroinfo.comparator.dto.MenuChildDto;
import com.eletroinfo.eletroinfo.comparator.dto.ParentMenuDto;
import com.eletroinfo.eletroinfo.comparator.dto.PermissionDto;
import com.eletroinfo.eletroinfo.comparator.entitie.User;
import com.eletroinfo.eletroinfo.comparator.service.UserPermissionService;
import com.eletroinfo.eletroinfo.comparator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserPermissionService userPermissionService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        PermissionDto permissionDto = this.userPermissionService.findByPermissionForLogonByLogin(login);
        if (permissionDto == null) {
            new UsernameNotFoundException("Usuário ou senha incorretos");
        }
        List<GrantedAuthority> grantedAuthorities = this.getAuthorities(permissionDto);
        return new UserLogged(permissionDto, grantedAuthorities);
    }

    private PermissionDto getPermissoes(User user) {
        //Listando as permissões do usuário
        return this.userPermissionService.findByPermissionById(user.getId());
    }

    public List<GrantedAuthority> getAuthorities(PermissionDto permissionDto){
        List<GrantedAuthority> features = new ArrayList<>();
        for(ParentMenuDto menuFatherDto : permissionDto.getParentMenusDto()){
            for(MenuChildDto menuChildDto : menuFatherDto.getMenuChildDtoList()){
                features.addAll(menuChildDto.getFeatureDtoList().stream().map(featureDto -> new SimpleGrantedAuthority(featureDto.getCode())).collect(Collectors.toList()));

            }
        }
        return features;
    }
}
